package com.min.jvmTest;

import com.sun.management.OperatingSystemMXBean;
import lombok.Data;

import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class JVMInfo implements Serializable {

    private static final long serialVersionUID = 7593745554626593803L;
    private static final long MBYTE = 1024 * 1204;

    /**
     * JVM 启动时间
     */
    private String jvmStartTime;
    /**
     * JVM 版本信息
     */
    private String jvmVersion;
    /**
     * jvm名称
     */
    private String jvmName;
    /**
     * 当前线程ID
     */
    private String processId;
    /**
     * 非堆内存使用情况(MB)
     */
    private MemoryUsage nonHeapMemoryUsage;
    /**
     * 堆内存使用情况(MB)
     */
    private MemoryUsage heapMemoryUsage;
    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 机器总内存(MB)
     */
    private long totalPhysicalMenory;
    /**
     * 机器以使用内存(MB)
     */
    private long freePhysicalMenory;
    /**
     * 机器可用内存比例
     */
    private String freePhysicalMenoryRatio;
    /**
     * CPU内核
     */
    private int processors;

    private String systemUpTime;

    public static JVMInfo getJVMInfo() {
        JVMInfo jvmStatus = new JVMInfo();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取JVM的启动时间，版本及名称，当前进程ID
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            jvmStatus.setJvmStartTime(simpleDateFormat.format(new Date(runtimeMXBean.getStartTime())));
            jvmStatus.setJvmVersion(runtimeMXBean.getVmVersion());
            jvmStatus.setJvmName(runtimeMXBean.getVmName());
            jvmStatus.setProcessId(runtimeMXBean.getName().split("@")[0]);

            //获取JVM内存使用状况，包括堆内存和非堆内存
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            jvmStatus.setNonHeapMemoryUsage(memoryMXBean.getNonHeapMemoryUsage());
            jvmStatus.setHeapMemoryUsage(memoryMXBean.getHeapMemoryUsage());

            //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
            OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            jvmStatus.setOsName(operatingSystemMXBean.getName());
            jvmStatus.setOsVersion(operatingSystemMXBean.getVersion());
            jvmStatus.setProcessors(operatingSystemMXBean.getAvailableProcessors());
            jvmStatus.setTotalPhysicalMenory(operatingSystemMXBean.getTotalPhysicalMemorySize() / MBYTE);
            jvmStatus.setFreePhysicalMenory(operatingSystemMXBean.getFreePhysicalMemorySize() / MBYTE);
            DecimalFormat decimalFormat = new DecimalFormat("0.00%");
            if (operatingSystemMXBean.getTotalPhysicalMemorySize() > 0) {
                jvmStatus.setFreePhysicalMenoryRatio(decimalFormat.format(Double.valueOf(operatingSystemMXBean.getFreePhysicalMemorySize()) / operatingSystemMXBean.getTotalPhysicalMemorySize()));
            }

            jvmStatus.setSystemUpTime(String.valueOf(runtimeMXBean.getUptime()));
        } catch (Exception e) {

        }
        return jvmStatus;
    }

    public static void main(String[] args) {
        JVMInfo jvmInfo = JVMInfo.getJVMInfo();
        System.out.println(jvmInfo);
    }
}

