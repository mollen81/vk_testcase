package org.project.config;

import org.msgpack.value.impl.ImmutableBinaryValueImpl;
import org.springframework.core.convert.converter.Converter;

public class BinaryValueToByteArrayConverter implements Converter<ImmutableBinaryValueImpl, byte[]> {
    @Override
    public byte[] convert(ImmutableBinaryValueImpl source) {
        return source.asByteArray();
    }
}