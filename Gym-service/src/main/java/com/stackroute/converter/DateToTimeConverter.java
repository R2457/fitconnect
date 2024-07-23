package com.stackroute.converter;

import org.springframework.core.convert.converter.Converter;
import java.sql.Time;
import java.util.Date;

public class DateToTimeConverter implements Converter<Date, Time> {

    @Override
    public Time convert(Date source) {
        if (source == null) {
            return null;
        }
        return new Time(source.getTime());
    }
}


