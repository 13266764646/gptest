package com.gwy.utils;

import org.slf4j.Logger;
import org.slf4j.spi.LocationAwareLogger;

/**
 * 日志步骤打印
 * <p>
 * [log stage begin]
 * step 1/5 : ${message}
 * step 2/5: ${message}
 * [log successfully.]
 * </p>
 *
 * @author Haixiang.Zheng
 * @since 2017/11/9
 */
public class LoggerStep {

    private static final String callerFQCN = LoggerStep.class.getName();

    private LocationAwareLogger locationAwareLogger;
    private Logger logger;

    public enum Style {
        FIRST("step %s/%s"),
        SECOND("(%s/%s)"),
        THIRD("[%s/%s]");

        private String style;

        Style(String style) {
            this.style = style;
        }

        public String getStyle() {
            return style;
        }
    }

    /**
     * 当前的步骤数
     */
    private int nowStep;
    /**
     * 总共的步骤数
     */
    private int stepCount;
    /**
     * 打印格式
     */
    private Style style;

    private LoggerStep(Logger logger, int nowStep, int stepCount, Style style) {
        this.logger = logger;
        this.nowStep = nowStep;
        this.stepCount = stepCount;
        this.style = style;
        if (logger instanceof LocationAwareLogger) {
            locationAwareLogger = (LocationAwareLogger) logger;
        }
    }


    /**
     * 打印log
     * <p>
     * step 1/${stepCount} : ${message}
     * </p>
     *
     * @param message 日志信息
     */
    public void log(String message, Object... params) {
        this.info(String.format("%s : %s", String.format(style.getStyle(), nowStep, stepCount), message), params);
        nowStep++;
    }

    /**
     * 打印log
     * <p>
     * step 1/${stepCount} : ok
     * </p>
     **/
    public void logOk() {
        String message = "ok";
        this.info(String.format("%s : %s", String.format(style.getStyle(), nowStep - 1, stepCount), message));
    }

    /**
     * 打印log
     * <p>
     * step 1/${stepCount} : error,return
     * </p>
     **/
    public void logError() {
        String message = "error,return";
        this.info(String.format("%s : %s", String.format(style.getStyle(), nowStep - 1, stepCount), message));
    }

    public void start() {
        this.info("[log stage begin]");
    }

    public void end() {
        this.info("[log successfully.]");
    }

    private void info(String message, Object... params) {
        try {
            locationAwareLogger.log(null, callerFQCN, LocationAwareLogger.INFO_INT, message, params, null);
        } catch (Exception e) {
            logger.info(message, params);
        }
    }

    /**
     * {@link LoggerStep}
     *
     * @param logger    slf4j logger
     * @param stepCount 总步骤数
     * @return new Instance
     */
    public static LoggerStep build(Logger logger, int stepCount) {
        return new LoggerStep(logger, 1, stepCount, Style.FIRST);
    }

    /**
     * {@link LoggerStep}
     *
     * @param logger    slf4j logger
     * @param stepCount 总步骤数
     * @return new Instance
     */
    public static LoggerStep build(Logger logger, int stepCount, Style style) {
        return new LoggerStep(logger, 1, stepCount, style);
    }

    public int getNowStep() {
        return nowStep;
    }

    public int getStepCount() {
        return stepCount;
    }
}
