package com.logger.logappender;

import com.logger.enums.LogLevel;

public interface Appender {
    /**
     * Appends a log message to the output.
     *
     * @param logLevel The level of the log (DEBUG, INFO, WARN, ERROR).
     * @param message  The log message to append.
     */
    void append(LogLevel logLevel, String message);
}
