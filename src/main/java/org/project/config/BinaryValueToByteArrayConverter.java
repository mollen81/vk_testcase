package org.project.config;

import org.msgpack.value.BinaryValue;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class BinaryValueToByteArrayConverter implements Converter<BinaryValue, byte[]> {
    @Override
    public byte[] convert(BinaryValue source) {
        return source.asByteArray();
    }
}