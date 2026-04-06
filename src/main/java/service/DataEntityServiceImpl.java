package service;

import data.entity.DataEntity;
import data.repository.DataEntityRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class DataEntityServiceImpl {

    private DataEntityRepository repository;

    @Autowired
    public DataEntityServiceImpl(DataEntityRepository repository) {
        this.repository = repository;
    }


    public DataEntity put(@NonNull String key, byte[] value) {
        DataEntity data = new DataEntity(key, value);
        repository.save(data);
        return data;
    }

    public Optional<DataEntity> get(String key) {
        if(repository.existsById(key)) {
            return repository.findById(key);
        }
        return Optional.empty();
    }

    public boolean delete(String key) {
        if(repository.existsById(key)) {
            repository.deleteById(key);
            return true;
        }
        else {
            return false;
        }
    }

    public long count() {
        return repository.count();
    }
}
