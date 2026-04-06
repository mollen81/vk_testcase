package service;

import data.entity.DataEntity;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface DataService {
    DataEntity put(@NonNull String key, byte[] value);

    Optional<DataEntity> get(String key);

    boolean delete(String key);

    List<DataEntity> range(String key_since, String key_to);

    long count();
}
