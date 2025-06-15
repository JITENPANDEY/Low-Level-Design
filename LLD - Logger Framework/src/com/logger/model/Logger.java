package com.logger.model;

import com.logger.config.LoggerConfig;
import com.logger.enums.LogLevel;
import com.logger.logappender.ConsoleAppender;
import com.logger.logformatter.SimpleFormatter;
import lombok.Data;
import lombok.Getter;

@Data
public class Logger {
    private LoggerConfig loggerConfig;
    @Getter
    private static final Logger instance = new Logger();// singleton

    private Logger() {
        this.loggerConfig = new LoggerConfig(LogLevel.INFO, new ConsoleAppender(new SimpleFormatter())); // Default config, can be set later
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARN, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    private void log(LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= loggerConfig.getLogLevel().ordinal()) {
            loggerConfig.getAppender().append(logLevel, message);
        }
    }

}
