package data.repository;

import data.entity.DataEntity;
import org.springframework.data.tarantool.repository.TarantoolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataEntityRepository extends TarantoolRepository<DataEntity, String> {
    @Override
    boolean existsById(String key);

    @Override
    Optional<DataEntity> findById(String key);

    @Override
    DataEntity save(DataEntity entity);

    @Override
    void deleteById(String key);

    List<DataEntity> findAllByKeyBetween(String key_since, String key_to);

    @Override
    long count();
}
