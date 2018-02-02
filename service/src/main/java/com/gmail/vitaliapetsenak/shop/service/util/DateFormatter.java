package com.gmail.vitaliapetsenak.shop.service.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public String format(Date date) {
        return format.format(date);
    }

    public Date parse(String source) throws ParseException {
        return format.parse(source);
    }
}
