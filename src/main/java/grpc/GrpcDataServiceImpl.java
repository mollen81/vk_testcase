package grpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.Empty;
import data.entity.DataEntity;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.project.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;
import service.DataService;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.StreamSupport.stream;


@GrpcService
public class GrpcDataServiceImpl extends DataServiceGrpc.DataServiceImplBase {
    private final DataService dataService;

    @Autowired
    public GrpcDataServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }


    @Override
    public void putData(PutDataRequest request, StreamObserver<PutDataResponse> responseObserver) {
        DataEntity entity = dataService.put(request.getKey(), request.getValue().toByteArray());

        responseObserver.onNext(PutDataResponse.newBuilder()
                .setEntity(org.project.grpc.DataEntity.newBuilder()
                        .setKey(entity.getKey())
                        .setValue(ByteString.copyFrom(entity.getValue()))
                        .buildPartial())
                .build());
        responseObserver.onCompleted();
    }


    @Override
    public void getData(GetDataRequest request, StreamObserver<GetDataResponse> responseObserver) {
        DataEntity entity = dataService.get(request.getKey()).get();

        responseObserver.onNext(GetDataResponse.newBuilder()
                        .setValue(ByteString.copyFrom(entity.getValue()))
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteData(DeleteDataRequest request, StreamObserver<DeleteDataResponse> responseObserver) {
        boolean success = dataService.delete(request.getKey());

        responseObserver.onNext(DeleteDataResponse.newBuilder()
                        .setSuccess(success)
                        .build());
        responseObserver.onCompleted();
    }

    @Override
    public void range(RangeRequest request, StreamObserver<org.project.grpc.DataEntity> responseObserver) {
        List<DataEntity> entityList = dataService.range(request.getKeySince(), request.getKeyTo());




    }

    @Override
    public void count(Empty request, StreamObserver<CountResponse> responseObserver) {
        long count = dataService.count();

        responseObserver.onNext(CountResponse.newBuilder()
                        .setCount(count)
                        .build());
        responseObserver.onCompleted();
    }
}
