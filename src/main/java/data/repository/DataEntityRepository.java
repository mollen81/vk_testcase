package data.repository;

import data.entity.DataEntity;
import lombok.NonNull;
import org.springframework.data.tarantool.repository.TarantoolRepository;

import java.util.Optional;

public interface DataEntityRepository extends TarantoolRepository<DataEntity, String> {
    @Override
    boolean existsById(String key);

    @Override
    @NonNull
    Optional<DataEntity> findById(String key);

    @Override
    DataEntity save(DataEntity entity);

    @Override
    void deleteById(String key);

    @Override
    long count();
}
