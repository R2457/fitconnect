package com.stackroute.converter;

import org.springframework.core.convert.converter.Converter;
import java.sql.Time;
import java.util.Date;

public class TimeToDateConverter implements Converter<Time, Date> {

    @Override
    public Date convert(Time source) {
        if (source == null) {
            return null;
        }
        return new Date(source.getTime());
    }
}

