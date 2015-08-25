package com.user.cache.memcache;

import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.danga.MemCached.MemCachedClient;

/**
 * memcache包装类，本类仅供mybatis配置二级缓存使用
 * 手动使用在baseAction和baseService都有配置
 * @author hongweizhangbj8248
 *
 */
public class MemcachedCache implements Cache {

    // Sf4j logger reference
    private static Logger logger = LoggerFactory.getLogger(MemcachedCache.class);

    /** The cache service reference. */
    protected static final MemCachedClient cacheClient = createMemcachedClient();

    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;
    private LinkedList<String> cacheKeys = new LinkedList<String>();

    public MemcachedCache(String id) {
        this.id = id;
    }
    
    /**
     * 创建缓存服务类，基于java_memcached-release_2.6.1
     * @return
     */
    protected static MemCachedClient createMemcachedClient() {
        ApplicationContext atx = new ClassPathXmlApplicationContext("/applicationContext-memcache.xml");
        return (MemCachedClient)atx.getBean("memCachedClient");
    }

    @Override
    public String getId() {
        return this.id;
    }

    // 根据 key 从缓存中获取数据
    @Override
    public Object getObject(Object key) {
        String cacheKey = String.valueOf(key.hashCode());
        Object value = cacheClient.get(cacheKey);
        if (!cacheKeys.contains(cacheKey)){
            cacheKeys.add(cacheKey);
        }
        return value;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    // 设置数据至缓存中
    @Override
    public void putObject(Object key, Object value) {
        String cacheKey = String.valueOf(key.hashCode());

        if (!cacheKeys.contains(cacheKey)){
            cacheKeys.add(cacheKey);
        }
        cacheClient.set(cacheKey, value);
    }
    // 从缓存中删除指定 key 数据
    @Override
    public Object removeObject(Object key) {
        String cacheKey = String.valueOf(key.hashCode());

        cacheKeys.remove(cacheKey);
        return cacheClient.delete(cacheKey);
    }
    //清空当前 Cache 实例中的所有缓存数据
    @Override
    public void clear() {
        for (int i = 0; i < cacheKeys.size(); i++){
            String cacheKey = cacheKeys.get(i);
            cacheClient.delete(cacheKey);
        }
        cacheKeys.clear();
    }

    @Override
    public int getSize() {
        return cacheKeys.size();
    }
}
