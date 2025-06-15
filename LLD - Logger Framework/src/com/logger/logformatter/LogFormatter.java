package com.logger.logformatter;

import com.logger.enums.LogLevel;

public interface LogFormatter {
    String format(LogLevel logLevel, String message);
}
