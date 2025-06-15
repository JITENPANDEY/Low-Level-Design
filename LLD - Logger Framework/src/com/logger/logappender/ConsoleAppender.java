package com.logger.logappender;

import com.logger.enums.LogLevel;
import com.logger.logformatter.LogFormatter;

public class ConsoleAppender implements Appender {

    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void append(LogLevel logLevel, String message) {
        String formattedMessage = formatter.format(logLevel, message);
        System.out.println(formattedMessage);
    }
}
