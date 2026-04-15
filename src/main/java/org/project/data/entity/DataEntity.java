package org.project.data.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.tarantool.core.mapping.Field;
import org.springframework.data.tarantool.core.mapping.Tuple;

@Tuple("KV")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class DataEntity {
    @Id
    @NonNull
    @Field(name = "key")
    private String key;

    @Field(name = "value")
    private byte[] value;
}
