package service;

import data.entity.DataEntity;
import data.repository.DataEntityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataEntityService {
    @Autowired
    private DataEntityRepository repository;


    public @NonNull DataEntity put(String key, byte value) {
        DataEntity data = DataEntity.builder()
                    .key(key)
                    .value(value).build();
        repository.save(data);
        return data;
    }

    public Optional<DataEntity> get(String key) {
        if(repository.existsById(key)) {
            return repository.findById(key);
        }
        return Optional.empty();
    }

    public void delete(String key) {
        if(repository.existsById(key)) {
            repository.deleteById(key);
        }
    }

    public void update(String key, byte value) {
        DataEntity data = DataEntity.builder()
                .key(key)
                .value(value).build();
        repository.save(data);
    }

    public long count() {
        return repository.count();
    }
}
