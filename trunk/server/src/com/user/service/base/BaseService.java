package com.user.service.base;

import javax.annotation.Resource;

import com.danga.MemCached.MemCachedClient;
import com.user.cache.ehcache.CacheUtil;

/**
 * 抽象服务类模板
 * @author Administrator
 *
 */
public abstract class BaseService {
	/**
	 * ehcache工具(手动使用)
	 */
	protected CacheUtil ehcache;
	@Resource(name="cacheUtil")
	public void setEhCache(CacheUtil cache) {
	   this.ehcache = cache;
	}
	
	/**
	 * memcache工具(手动使用)
	 */
	protected MemCachedClient memcache;
	@Resource(name="memCachedClient")
    public void setMemcache(MemCachedClient memcache) {
        this.memcache = memcache;
    }
	
}
