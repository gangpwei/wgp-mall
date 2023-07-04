package com.wgp.mall.job.executor.param;

import java.util.List;

import lombok.Data;

/**
 * 任务执行参数
 * @author : gangpeng.wgp
 * @date : 2023/6/30
 */

@Data
public class TaskExecuteParam {

    /**
     * 任务类型
     */
    private List<String> taskTypeList;

    /**
     * 捞取的最大数量
     */
    private Integer maxLimit;

    /**
     * 捞取消息重试次数小于多少次的
     */
    private Integer retryTimes;

    /**
     * 消息状态
     */
    private List<String> statusList;

    /**
     * 是否测试
     */
    private Boolean test;

    /**
     * -10: gmtCreate10分钟前 (在前10分钟～前5分钟的区间内)
     */
    private Integer startIntervalMin;

    /**
     * -5: gmtCreate5分钟前 (在前10分钟～前5分钟的区间内)
     */
    private Integer endIntervalMin;

    /**
     * 是否限定 expectFinishTime
     * 如果为 true: expectFinishTime 当前时间之前 (在 <= now 的区间内)
     */
    private Boolean needEndExpectFinishTime = Boolean.TRUE;

}
