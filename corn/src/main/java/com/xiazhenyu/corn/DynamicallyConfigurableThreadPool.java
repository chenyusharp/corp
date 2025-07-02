package com.xiazhenyu.corn;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Date: 2025/6/18
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class DynamicallyConfigurableThreadPool {


    private final ThreadPoolExecutor executor;
    private final String poolName;


    public DynamicallyConfigurableThreadPool(String poolName, MockConfigService configService) {
        this.poolName = poolName;
        // this.configService = configService;

        // Initial configuration from config service
        int coreSize= configService.getInt(poolName + ".corePoolSize", 2);
        int maxSize= configService.getInt(poolName + ".maximumPoolSize", 10);
        // ... other params ...
        this.executor = new ThreadPoolExecutor(coreSize, maxSize, /* ... */);

        // Register listeners for dynamic updates
        configService.addChangeListener(poolName + ".corePoolSize", (key, newValue) -> {
            try {
                int newCoreSize= Integer.parseInt(newValue);
                System.out.println("Dynamically updating " + poolName + " corePoolSize to " + newCoreSize);
                executor.setCorePoolSize(newCoreSize);
            } catch (NumberFormatException e) {
                System.err.println("Invalid format for corePoolSize: " + newValue);
            }
        });

        configService.addChangeListener(poolName + ".maximumPoolSize", (key, newValue) -> {
            try {
                int newMaxSize= Integer.parseInt(newValue);
                System.out.println("Dynamically updating " + poolName + " maximumPoolSize to " + newMaxSize);
                executor.setMaximumPoolSize(newMaxSize);
            } catch (NumberFormatException e) {
                System.err.println("Invalid format for maximumPoolSize: " + newValue);
            }
        });
        // ... listeners for other configurable parameters ...
    }




    // Example ConfigService and main (very simplified)
    static class MockConfigService {

        private Map<String, String> configs = new ConcurrentHashMap<>();
        private Map<String, List<ConfigChangeListener>> listeners = new ConcurrentHashMap<>();




        public ExecutorService getExecutor() { return executor; }

        public MockConfigService() { // Default initial configs
            configs.put("myPool.corePoolSize", "2");
            configs.put("myPool.maximumPoolSize", "5");
        }

        public int getInt(String key, int def) {
            return Integer.parseInt(configs.getOrDefault(key, String.valueOf(def)));
        }

        public void addChangeListener(String key, ConfigChangeListener listener) {
            listeners.computeIfAbsent(key, k -> new CopyOnWriteArrayList< >()).add(listener);
        }

        public void updateConfig(String key, String value) { // Simulate config change from center
            configs.put(key, value);
            System.out.println("Config changed in center: " + key + " = " + value);
            if (listeners.containsKey(key)) {
                listeners.get(key).forEach(l -> l.onChange(key, value));
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MockConfigService configSvc = new MockConfigService();
        DynamicallyConfigurableThreadPool dynamicPool =
                new DynamicallyConfigurableThreadPool("myPool", configSvc);

        ThreadPoolExecutor actualExecutor = (ThreadPoolExecutor) dynamicPool.getExecutor();
        System.out.println("Initial: Core=" + actualExecutor.getCorePoolSize() + ", Max=" + actualExecutor.getMaximumPoolSize());

        // Simulate some tasks running
        for (int i = 0; i < 3; i++) {
            actualExecutor.submit(() -> {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                }
            });
        }

        Thread.sleep(1000);
        configSvc.updateConfig("myPool.corePoolSize", "3"); // Trigger change
        configSvc.updateConfig("myPool.maximumPoolSize", "8");

        Thread.sleep(1000);
        System.out.println("After update: Core=" + actualExecutor.getCorePoolSize() + ", Max=" + actualExecutor.getMaximumPoolSize());

        GracefulShutdownExample.shutdownExecutorService(actualExecutor, "DynamicPool", 5);
    }

}