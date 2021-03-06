package ru.otus;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Main {

//    VM Options: -Xms1G -Xmx2G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\IdeaProjects\javatrainingproject\HW05\dumps1\ -XX:+UseSerialGC
//    VM Options: -Xms1G -Xmx2G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\IdeaProjects\javatrainingproject\HW05\dumps2\ -XX:+UseParallelGC
//    VM Options: -Xms1G -Xmx2G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\IdeaProjects\javatrainingproject\HW05\dumps3\ -XX:+UseConcMarkSweepGC
//    VM Options: -Xms1G -Xmx2G -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\IdeaProjects\javatrainingproject\HW05\dumps4\ -XX:+UseG1GC
    public static void main(String... args) throws Exception {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());
        MBeanServer beenServer = ManagementFactory.getPlatformMBeanServer();
        Benchmark bean = new Benchmark();
        beenServer.registerMBean(bean, new ObjectName("ru.otus:type=Benchmark"));
        bean.setSize(50_000);
        bean.start();
    }
}
