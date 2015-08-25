package com.user.action.base;

import javax.annotation.Resource;

import com.danga.MemCached.MemCachedClient;
import com.user.cache.ehcache.CacheUtil;
import com.user.entity.page.Page;

public abstract class BaseAction {
	/**
	 * 处理分页对象，将起始偏移量和每页显示个数防止到page的map里，传给MYBATIS
	 * @param page
	 */
	protected void processPage(Page page) {
		if (page != null) {
			page.getCondition().put("startIndex", page.getStartIndex());
			page.getCondition().put("pageSize", page.getPageSize());
		}
	}
	
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
