package data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.tarantool.core.mapping.Field;
import org.springframework.data.tarantool.core.mapping.Tuple;

@Tuple(spaceName = "KV")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataEntity {
    @Id
    @NonNull
    @Field(name = "key")
    private String key;

    @Field(name = "value")
    private byte[] value;
}
