package util;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class StopwatchCPU
{
    private long start;
    private ThreadMXBean threadMXBean;

    public StopwatchCPU()
    {
        this.threadMXBean = ManagementFactory.getThreadMXBean();
        start = threadMXBean.getCurrentThreadCpuTime();
    }

    public long elapsedTime()
    {
        return (threadMXBean.getCurrentThreadCpuTime() - start)/1000000L;
    }
}
