package com.min.jvmTest;

import java.lang.management.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
 
/**
 * @author Steven Chen
 *
 */
public class Test {
 
    public static void main(String[] args) {
        Test.printSummary();
        Test.test2();
    }




    private static NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.ENGLISH));
    private static NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.ENGLISH));
 
    private static final int Kb = 1024;
 
    public static void printSummary() {

        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();

        System.out.printf("jvmName:%s %s %s%n", runtime.getVmName(), "version", runtime.getVmVersion());
        System.out.printf("jvmJavaVer:%s%n", System.getProperty("java.version"));
        System.out.printf("jvmVendor:%s%n", runtime.getVmVendor());
        System.out.printf("jvmUptime:%s%n", toDuration(runtime.getUptime()));
        System.out.printf("threadsLive:%d%n", threads.getThreadCount());
        System.out.printf("threadsDaemon:%d%n", threads.getDaemonThreadCount());
        System.out.printf("threadsPeak:%d%n", threads.getPeakThreadCount());
        System.out.printf("threadsTotal:%d%n", threads.getTotalStartedThreadCount());
        System.out.printf("heapCurr:%d%n", mem.getHeapMemoryUsage().getUsed() / Kb);
        System.out.printf("heapMax:%d%n", mem.getHeapMemoryUsage().getMax() / Kb);
        System.out.printf("heapCommitted:%d%n", mem.getHeapMemoryUsage().getCommitted() / Kb);
        System.out.printf("osName:%s %s %s%n", os.getName(), "version", os.getVersion());
        System.out.printf("osArch:%s%n", os.getArch());
        System.out.printf("osCores:%s%n", os.getAvailableProcessors());
        System.out.printf("clsCurrLoaded:%s%n", cl.getLoadedClassCount());
        System.out.printf("clsLoaded:%s%n", cl.getTotalLoadedClassCount());
        System.out.printf("clsUnloaded:%s%n", cl.getUnloadedClassCount());
 
    }
 
    protected String printSizeInKb(double size) {
        return fmtI.format((long) (size / 1024)) + " kbytes";
    }
 
    protected static String toDuration(double uptime) {
        uptime /= 1000;
        if (uptime < 60) {
            return fmtD.format(uptime) + " seconds";
        }
        uptime /= 60;
        if (uptime < 60) {
            long minutes = (long) uptime;
            String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            return s;
        }
        uptime /= 60;
        if (uptime < 24) {
            long hours = (long) uptime;
            long minutes = (long) ((uptime - hours) * 60);
            String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
            if (minutes != 0) {
                s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            }
            return s;
        }
        uptime /= 24;
        long days = (long) uptime;
        long hours = (long) ((uptime - days) * 24);
        String s = fmtI.format(days) + (days > 1 ? " days" : " day");
        if (hours != 0) {
            s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
        }
        return s;
    }


//    测试方法二
    public static void test2(){
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: "
                + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: "
                + memorymbean.getNonHeapMemoryUsage());

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
        System.out.println("===================java options=============== ");
        System.out.println(inputArguments);



        System.out.println("=======================通过java来获取相关系统状态============================ ");
        int i = (int)Runtime.getRuntime().totalMemory()/1024;//Java 虚拟机中的内存总量,以字节为单位
        System.out.println("总的内存量 i is "+i);
        int j = (int)Runtime.getRuntime().freeMemory()/1024;//Java 虚拟机中的空闲内存量
        System.out.println("空闲内存量 j is "+j);
        System.out.println("最大内存量 is "+Runtime.getRuntime().maxMemory()/1024);

        System.out.println("=======================OperatingSystemMXBean============================ ");
        OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//    System.out.println(osm.getFreeSwapSpaceSize()/1024);
//    System.out.println(osm.getFreePhysicalMemorySize()/1024);
//    System.out.println(osm.getTotalPhysicalMemorySize()/1024);

        //获取操作系统相关信息
        System.out.println("osm.getArch() "+osm.getArch());
        System.out.println("osm.getAvailableProcessors() "+osm.getAvailableProcessors());
        //System.out.println("osm.getCommittedVirtualMemorySize() "+osm.getCommittedVirtualMemorySize());
        System.out.println("osm.getName() "+osm.getName());
        //System.out.println("osm.getProcessCpuTime() "+osm.getProcessCpuTime());
        System.out.println("osm.getVersion() "+osm.getVersion());
        //获取整个虚拟机内存使用情况
        System.out.println("=======================MemoryMXBean============================ ");
        MemoryMXBean mm=(MemoryMXBean)ManagementFactory.getMemoryMXBean();
        System.out.println("getHeapMemoryUsage "+mm.getHeapMemoryUsage());
        System.out.println("getNonHeapMemoryUsage "+mm.getNonHeapMemoryUsage());
        //获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
        System.out.println("=======================ThreadMXBean============================ ");
        ThreadMXBean tm=(ThreadMXBean)ManagementFactory.getThreadMXBean();
        System.out.println("getThreadCount "+tm.getThreadCount());
        System.out.println("getPeakThreadCount "+tm.getPeakThreadCount());
        System.out.println("getCurrentThreadCpuTime "+tm.getCurrentThreadCpuTime());
        System.out.println("getDaemonThreadCount "+tm.getDaemonThreadCount());
        System.out.println("getCurrentThreadUserTime "+tm.getCurrentThreadUserTime());

        //当前编译器情况
        System.out.println("=======================CompilationMXBean============================ ");
        CompilationMXBean gm=(CompilationMXBean)ManagementFactory.getCompilationMXBean();
        System.out.println("getName "+gm.getName());
        System.out.println("getTotalCompilationTime "+gm.getTotalCompilationTime());

        //获取多个内存池的使用情况
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<MemoryPoolMXBean> mpmList=ManagementFactory.getMemoryPoolMXBeans();
        for(MemoryPoolMXBean mpm:mpmList){
            System.out.println("getUsage "+mpm.getUsage());
            System.out.println("getMemoryManagerNames "+mpm.getMemoryManagerNames().toString());
        }
        //获取GC的次数以及花费时间之类的信息
        System.out.println("=======================MemoryPoolMXBean============================ ");
        List<GarbageCollectorMXBean> gcmList=ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean gcm:gcmList){
            System.out.println("getName "+gcm.getName());
            System.out.println("getMemoryPoolNames "+gcm.getMemoryPoolNames());
        }
        //获取运行时信息
        System.out.println("=======================RuntimeMXBean============================ ");
        RuntimeMXBean rmb=(RuntimeMXBean)ManagementFactory.getRuntimeMXBean();
        System.out.println("getClassPath "+rmb.getClassPath());
        System.out.println("getLibraryPath "+rmb.getLibraryPath());
        System.out.println("getVmVersion "+rmb.getVmVersion());
    }
}