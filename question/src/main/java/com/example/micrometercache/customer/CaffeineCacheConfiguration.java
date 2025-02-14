package com.example.micrometercache.customer;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.cache.CaffeineStatsCounter;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
class CaffeineCacheConfiguration {

    private static final String CUSTOMER_CACHE = "customerCache";
    private static final String ITEM_CACHE = "itemCache";

    @Bean
    CacheManagerCustomizer<CaffeineCacheManager> caffeineCacheManagerCacheManagerCustomizer(final MeterRegistry meterRegistry) {
        return cacheManager -> registerCache(cacheManager, meterRegistry);
    }

    private void registerCache(final CaffeineCacheManager cacheManager, final MeterRegistry meterRegistry) {
        cacheManager.registerCustomCache(CUSTOMER_CACHE, customerCache(meterRegistry));
        cacheManager.registerCustomCache(ITEM_CACHE, itemCache(meterRegistry));
    }

    private Cache<Object, Object> customerCache(final MeterRegistry meterRegistry) {
        return Caffeine.newBuilder()
                .recordStats(() -> new CaffeineStatsCounter(meterRegistry, CUSTOMER_CACHE))
                .expireAfterWrite(19, TimeUnit.MINUTES)
                .maximumSize(90)
                .build();
    }

    private Cache<Object, Object> itemCache(final MeterRegistry meterRegistry) {
        return Caffeine.newBuilder()
                .recordStats(() -> new CaffeineStatsCounter(meterRegistry, ITEM_CACHE))
                .expireAfterWrite(2, TimeUnit.MINUTES)
                .build();
    }

}
