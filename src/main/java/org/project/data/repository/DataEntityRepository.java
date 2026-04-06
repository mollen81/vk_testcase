package org.project.data.repository;

import org.project.data.entity.DataEntity;
import org.springframework.data.tarantool.repository.TarantoolRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataEntityRepository extends TarantoolRepository<DataEntity, String> {
    boolean existsById(String key);

    Optional<DataEntity> findById(String key);

    DataEntity save(DataEntity entity);

    void deleteById(String key);

    List<DataEntity> findAllByKeyBetween(String key_since, String key_to);

    long count();
}
