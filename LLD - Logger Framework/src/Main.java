import com.logger.config.LoggerConfig;
import com.logger.enums.LogLevel;
import com.logger.logappender.FileAppender;
import com.logger.logformatter.SimpleFormatter;
import com.logger.model.Logger;

public class Main {
    public static void run() {
        Logger log = Logger.getInstance();

        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warning message");
        log.error("This is an error message");

        // Changing log level and appender
        LoggerConfig config = new LoggerConfig(LogLevel.DEBUG, new FileAppender(new SimpleFormatter(), "app.log"));
        log.setLoggerConfig(config);

        log.debug("This is a debug message");
        log.info("This is an info message");
        log.warn("This is a warning message");
        log.error("This is an error message");
    }
}