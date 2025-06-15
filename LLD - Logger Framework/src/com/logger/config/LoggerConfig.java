package com.logger.config;

import com.logger.enums.LogLevel;
import com.logger.logappender.Appender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggerConfig {
    private final LogLevel logLevel;
    private final Appender appender;
}
