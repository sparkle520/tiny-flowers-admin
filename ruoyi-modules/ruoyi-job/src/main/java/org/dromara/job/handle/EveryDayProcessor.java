package org.dromara.job.handle;

import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.spring.SpringUtils;
import org.dromara.job.events.EveryDayJobEvent;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;
import tech.powerjob.worker.log.OmsLogger;

/**
 * 每天执行任务
 */
@Slf4j
@Component
public class EveryDayProcessor implements BasicProcessor {

    @Override
    public ProcessResult process(TaskContext context) throws Exception {

        // 在线日志功能，可以直接在控制台查看任务日志，非常便捷
        OmsLogger omsLogger = context.getOmsLogger();
        omsLogger.info("执行每天Job任务, current JobParams is {}.", context.getJobParams());

        log.debug("执行每天Job任务");
        try {
            SpringUtils.getApplicationContext().publishEvent(new EveryDayJobEvent(context.getJobParams()));
        } catch (Exception e) {
            return new ProcessResult(false, "每天任务执行报错" + e.getMessage());
        }
        log.debug("每天任务执行完毕");

        // 返回结果，该结果会被持久化到数据库，在前端页面直接查看，极为方便
        return new ProcessResult(true, "每天任务执行完毕");
    }
}
