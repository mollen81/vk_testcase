package org.project.config;

import org.msgpack.value.BinaryValue;
import org.msgpack.value.ValueFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class ByteArrayToBinaryValueConverter implements Converter<byte[], BinaryValue> {
    @Override
    public BinaryValue convert(byte[] source) {
        return ValueFactory.newBinary(source);
    }
}