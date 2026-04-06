package org.project.service;

import org.project.data.entity.DataEntity;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface DataService {
    DataEntity put(@NonNull String key, byte[] value);

    Optional<DataEntity> get(String key);

    boolean delete(String key);

    List<DataEntity> range(String key_since, String key_to);

    long count();
}
