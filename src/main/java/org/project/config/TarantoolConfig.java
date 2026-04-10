package org.project.config;

import io.tarantool.driver.api.TarantoolClient;
import io.tarantool.driver.api.TarantoolClientConfig;
import io.tarantool.driver.api.TarantoolResult;
import io.tarantool.driver.api.tuple.TarantoolTuple;
import io.tarantool.driver.auth.SimpleTarantoolCredentials;
import io.tarantool.driver.core.ClusterTarantoolTupleClient;
import io.tarantool.driver.mappers.DefaultMessagePackMapper;
import org.msgpack.value.BinaryValue;
import org.msgpack.value.ValueFactory;
import org.msgpack.value.ValueType;
import org.msgpack.value.impl.ImmutableBinaryValueImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mapping.model.PropertyNameFieldNamingStrategy;
import org.springframework.data.tarantool.core.TarantoolTemplate;
import org.springframework.data.tarantool.core.convert.MappingTarantoolConverter;
import org.springframework.data.tarantool.core.convert.TarantoolConverter;
import org.springframework.data.tarantool.core.convert.TarantoolCustomConversions;
import org.springframework.data.tarantool.core.convert.TarantoolMapTypeAliasAccessor;
import org.springframework.data.tarantool.core.mapping.TarantoolMappingContext;
import org.springframework.data.tarantool.repository.config.EnableTarantoolRepositories;
import org.springframework.data.tarantool.repository.config.TarantoolRepositoryOperationsMapping;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Configuration
@EnableTarantoolRepositories(basePackages = "org.project.data.repository")
public class TarantoolConfig {

    @Bean
    public TarantoolClientConfig tarantoolClientConfig() {
        return TarantoolClientConfig.builder()
                .withCredentials(new SimpleTarantoolCredentials("guest", ""))
                .build();
    }

    @Bean
    public TarantoolClient<TarantoolTuple, TarantoolResult<TarantoolTuple>> tarantoolClient(
            TarantoolClientConfig tarantoolClientConfig) {
        ClusterTarantoolTupleClient client = new ClusterTarantoolTupleClient(
                tarantoolClientConfig,
                "localhost", 3301
        );

        DefaultMessagePackMapper mapper = (DefaultMessagePackMapper) client.getConfig().getMessagePackMapper();
        mapper.registerValueConverter(
                ValueType.BINARY,
                byte[].class,
                (ImmutableBinaryValueImpl source) -> source.asByteArray()
        );

        mapper.registerObjectConverter(
                byte[].class,
                BinaryValue.class,
                ValueFactory::newBinary
        );

        return client;
    }

    @Bean
    public TarantoolMappingContext tarantoolMappingContext() {
        TarantoolMappingContext context = new TarantoolMappingContext();
        context.setFieldNamingStrategy(PropertyNameFieldNamingStrategy.INSTANCE);
        return context;
    }

    @Bean
    public TarantoolTemplate tarantoolTemplate(
            TarantoolClient<TarantoolTuple, TarantoolResult<TarantoolTuple>> tarantoolClient,
            TarantoolMappingContext mappingContext,
            TarantoolConverter converter) {
        return new TarantoolTemplate(
                tarantoolClient,
                mappingContext,
                converter,
                ForkJoinPool.defaultForkJoinWorkerThreadFactory
        );
    }

    @Bean
    public TarantoolRepositoryOperationsMapping tarantoolRepositoryOperationsMapping(
            TarantoolTemplate tarantoolTemplate) {
        return new TarantoolRepositoryOperationsMapping(tarantoolTemplate);
    }

    @Bean
    public TarantoolConverter tarantoolConverter(TarantoolMappingContext mappingContext) {
        var conversions = new TarantoolCustomConversions(List.of(
                new BinaryValueToByteArrayConverter(),
                new ByteArrayToBinaryValueConverter()
        ));
        return new MappingTarantoolConverter(
                mappingContext,
                new TarantoolMapTypeAliasAccessor("_type"),
                conversions
        );
    }
}