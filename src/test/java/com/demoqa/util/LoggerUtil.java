package com.demoqa.util;

import com.demoqa.listeners.LogListener;
import org.apache.log4j.Logger;

public class LoggerUtil {
    private static Logger logger = Logger.getLogger(LogListener.class);

    public static void log(String message) {
        logger.info(message);
    }

    public static Logger getLogger() {
        return logger;
    }
}
