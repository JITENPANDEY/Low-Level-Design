package com.logger.logformatter;

import com.logger.enums.LogLevel;

import java.time.LocalDateTime;

public class SimpleFormatter implements LogFormatter {

    @Override
    public String format(LogLevel logLevel, String message) {
        return String.format("[%s] [%s] [%s] %s", LocalDateTime.now(), Thread.currentThread().getName(), logLevel.name(), message);
    }
}
