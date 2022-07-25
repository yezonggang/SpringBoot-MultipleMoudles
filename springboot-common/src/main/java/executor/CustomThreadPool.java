package executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


/**
 * @author y25958
 */
public class CustomThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(CustomThreadPool.class);

    public static ExecutorService createThreadPool(Integer queueSize){
        return new ThreadPoolExecutor(40,
                50,
                3,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(queueSize),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return newThread(r);
                    }
                });
    }

}
