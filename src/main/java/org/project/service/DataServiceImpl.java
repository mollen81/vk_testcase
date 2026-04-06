package org.project.service;

import org.project.data.entity.DataEntity;
import org.project.data.repository.DataEntityRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DataServiceImpl implements DataService {
    private final DataEntityRepository repository;

    @Autowired
    public DataServiceImpl(DataEntityRepository repository) {
        this.repository = repository;
    }


    @Override
    public DataEntity put(@NonNull String key, byte[] value) {
        DataEntity data = new DataEntity(key, value);
        repository.save(data);
        return data;
    }

    @Override
    public Optional<DataEntity> get(String key) {
        if(repository.existsById(key)) {
            return repository.findById(key);
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(String key) {
        if(repository.existsById(key)) {
            repository.deleteById(key);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<DataEntity> range(String key_since, String key_to) {
        return repository.findAllByKeyBetween(key_since, key_to);
    }


    @Override
    public long count() {
        return repository.count();
    }
}
