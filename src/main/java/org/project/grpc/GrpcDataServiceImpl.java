package org.project.grpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import org.project.data.entity.DataEntity;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.project.data.repository.DataEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.project.service.DataService;

import java.util.Arrays;
import java.util.List;


@GrpcService
public class GrpcDataServiceImpl extends DataServiceGrpc.DataServiceImplBase {
    private final DataEntityRepository repo;

    @Autowired
    public GrpcDataServiceImpl(DataEntityRepository repo) {
        this.repo = repo;
    }


    @Override
    public void putData(PutDataRequest request, StreamObserver<PutDataResponse> responseObserver) {
        try {
            repo.save(DataEntity.builder()
                    .key(request.getKey())
                    .value(request.getValue().toByteArray())
                    .build());

            responseObserver.onNext(PutDataResponse.newBuilder()
                    .setStatus(true)
                    .build());
            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Put failed: " + Arrays.toString(e.getStackTrace()))
                    .withCause(e)
                    .asRuntimeException());
        }
    }


    /*@Override
    public void getData(GetDataRequest request, StreamObserver<GetDataResponse> responseObserver) {
        try {
            DataEntity entity = dataService.get(request.getKey()).get();

            responseObserver.onNext(GetDataResponse.newBuilder()
                    .setValue(ByteString.copyFrom(entity.getValue()))
                    .build());
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Get failed: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void deleteData(DeleteDataRequest request, StreamObserver<DeleteDataResponse> responseObserver) {
        try {
            boolean success = dataService.delete(request.getKey());

            responseObserver.onNext(DeleteDataResponse.newBuilder()
                    .setSuccess(success)
                    .build());
            responseObserver.onCompleted();
        }
        catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Delete failed: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void range(RangeRequest request, StreamObserver<org.project.grpc.DataEntity> responseObserver) {
        try {
            List<DataEntity> entities = dataService.range(
                    request.getKeySince(),
                    request.getKeyTo()
            );

            entities.stream()
                    .map(this::mapToProto)
                    .forEach(responseObserver::onNext);

            responseObserver.onCompleted();

        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Range failed: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void count(Empty request, StreamObserver<CountResponse> responseObserver) {
        try {
            long count = dataService.count();

            responseObserver.onNext(CountResponse.newBuilder()
                    .setCount(count)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Count failed: " + e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }*/


    private org.project.grpc.DataEntity mapToProto(DataEntity entity) {
        return org.project.grpc.DataEntity.newBuilder()
                .setKey(entity.getKey())
                .setValue(ByteString.copyFrom(entity.getValue()))
                .build();
    }
}
