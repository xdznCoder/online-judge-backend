package cn.xdzn.oj.common.util;


import cn.xdzn.oj.common.exception.CustomException;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 缓存时间戳解决System.currentTimeMillis()高并发下性能问题<br/>
 *     问题根源分析: <a href="http://pzemtsov.github.io/2017/07/23/the-slow-currenttimemillis.html">...</a>
 *
 **/
public class SystemClock {

    /**
     *
     */
    private final long period;
    /**
     *
     */
    private final AtomicLong now;

    /**
     *
     */
    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    /**
     * 枚举单例法
     */
    public enum SystemClockEnum {
        /**
         * 系统时钟
         */
        SYSTEM_CLOCK;
        private volatile SystemClock systemClock;
        SystemClockEnum() {
        }
        public synchronized SystemClock getInstance() {
            if (systemClock == null) {
                systemClock = new SystemClock(1);
            }
            return systemClock;
        }
    }

    /**
     * 获取单例对象
     */
    private static SystemClock getInstance() {
        return SystemClockEnum.SYSTEM_CLOCK.getInstance();
    }

    /**
     * 获取当前毫秒时间戳
     */
    public static long now() {
        return getInstance().now.get();
    }

    /**
     * 起一个线程定时刷新时间戳
     */
    private void scheduleClockUpdating() {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(
                // 核心线程数
                1,
                runnable -> {
                    Thread thread = new Thread(runnable, "System Clock");
                    thread.setDaemon(true);
                    return thread;
                }
        );

        scheduler.scheduleWithFixedDelay(() -> {
            try {
                now.set(System.currentTimeMillis());
            } catch (Exception e) {
                // 处理异常，可以记录日志等
                e.printStackTrace();
                throw new CustomException(e);
            }
        }, 0, period, TimeUnit.MILLISECONDS);

        // 在程序退出时调用关闭线程池
        Runtime.getRuntime().addShutdownHook(new Thread(scheduler::shutdown));
    }


}
