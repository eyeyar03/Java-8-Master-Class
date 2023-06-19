package com.masterclass.employee.directory.util;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormatterEnum {

    MMMsDDscYYYY("MMM dd, yyyy");

    private DateTimeFormatter dateTimeFormatter;

    DateTimeFormatterEnum(String format) {
        dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return dateTimeFormatter;
    }
}
