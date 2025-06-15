package com.logger.logappender;

import com.logger.enums.LogLevel;
import com.logger.logformatter.LogFormatter;

import java.io.FileWriter;
import java.io.IOException;

public class FileAppender implements Appender{
    private final LogFormatter formatter;
    private final FileWriter fileWriter;

    public FileAppender(LogFormatter formatter, String fileName) {
        this.formatter = formatter;
        try {
            this.fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            throw new RuntimeException("Error initializing file writer", e);
        }
    }

    @Override
    public synchronized void append(LogLevel logLevel, String message) {
        try {
            fileWriter.write(formatter.format(logLevel, message) + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing to log file", e);
        }

    }
}
