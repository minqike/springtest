package com.min.jvmTest;

import lombok.Data;

import java.util.Date;

@Data
public class ClientStatus {
    private String id;
    private String projectName;
    private int version;
    private String[] remark;
    private String group;
    private Date commitDate;
    /**
     * 当前进程运行的主机名
     */
    private String host;
    /**
     * 当前进程所在的IP地址
     */
    private String ipAddress;
    /**
     * 空闲内存
     */
    private long freeMemory;
    /**
     * 内存总量
     */
    private long totalMemory;
    /**
     * java虚拟机允许开启的最大的内存
     */
    private long maxMemory;

    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 进程号
     */
    private long pid;


    /**
     * 程序启动时间
     */
    private Date startTime;

    /**
     * 类所在路径
     */
    private String classPath;

    private String projectPath;

    /**
     * 程序运行时间，单位毫秒
     */
    private long runtime;
    /**
     * 线程总量
     */
    private int threadCount;
}
