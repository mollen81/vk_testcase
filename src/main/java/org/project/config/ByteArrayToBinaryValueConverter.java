package org.project.config;

import org.msgpack.value.impl.ImmutableBinaryValueImpl;
import org.msgpack.value.ValueFactory;
import org.springframework.core.convert.converter.Converter;

public class ByteArrayToBinaryValueConverter implements Converter<byte[], ImmutableBinaryValueImpl> {
    @Override
    public ImmutableBinaryValueImpl convert(byte[] source) {
        return (ImmutableBinaryValueImpl) ValueFactory.newBinary(source);
    }
}