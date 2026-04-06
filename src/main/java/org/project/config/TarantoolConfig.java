package org.project.config;

import io.tarantool.driver.api.TarantoolClient;
import io.tarantool.driver.api.TarantoolClientConfig;
import io.tarantool.driver.api.TarantoolResult;
import io.tarantool.driver.api.tuple.TarantoolTuple;
import io.tarantool.driver.auth.SimpleTarantoolCredentials;
import io.tarantool.driver.core.ClusterTarantoolTupleClient;
import io.tarantool.driver.core.ProxyTarantoolTupleClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.tarantool.core.TarantoolTemplate;
import org.springframework.data.tarantool.core.convert.MappingTarantoolConverter;
import org.springframework.data.tarantool.core.convert.TarantoolConverter;
import org.springframework.data.tarantool.core.convert.TarantoolCustomConversions;
import org.springframework.data.tarantool.core.convert.TarantoolMapTypeAliasAccessor;
import org.springframework.data.tarantool.core.mapping.TarantoolMappingContext;
import org.springframework.data.tarantool.repository.config.EnableTarantoolRepositories;
import org.springframework.data.tarantool.repository.config.TarantoolRepositoryOperationsMapping;

import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

@Configuration
@EnableTarantoolRepositories(basePackages = "org.project.data.repository")
public class TarantoolConfig {  // ← больше не наследуем

    @Bean
    public TarantoolClientConfig tarantoolClientConfig() {
        return TarantoolClientConfig.builder()
                .withCredentials(new SimpleTarantoolCredentials("guest", ""))
                .build();
    }

    @Bean
    public TarantoolClient<TarantoolTuple, TarantoolResult<TarantoolTuple>> tarantoolClient(
            TarantoolClientConfig tarantoolClientConfig) {
        return new ClusterTarantoolTupleClient(  // ← убрали ProxyTarantoolTupleClient
                tarantoolClientConfig,
                "localhost", 3301
        );
    }

    @Bean
    public TarantoolMappingContext tarantoolMappingContext() {
        return new TarantoolMappingContext();
    }

    @Bean
    public TarantoolConverter tarantoolConverter(TarantoolMappingContext mappingContext) {
        return new MappingTarantoolConverter(
                mappingContext,
                new TarantoolMapTypeAliasAccessor("_type"),
                new TarantoolCustomConversions(Collections.emptyList())
        );
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
}