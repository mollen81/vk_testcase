package service;

import data.entity.DataEntity;
import lombok.NonNull;

import java.util.Optional;

public interface DataEntityService {
    DataEntity put(@NonNull String key, byte[] value);

    Optional<DataEntity> get(String key);

    boolean delete(String key);

    long count();
}
