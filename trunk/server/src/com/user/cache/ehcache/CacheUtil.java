package com.user.cache.ehcache;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * EHCache工具类
 * @author Administrator
 *
 */
public class CacheUtil {
	
	private Logger logger = Logger.getLogger(CacheUtil.class);
	
	private AtomicLong putCount = new AtomicLong();
	private AtomicLong getCount = new AtomicLong();
	private AtomicLong delCount = new AtomicLong();
	
	private Cache cache;
	public void setCache(Cache cache) {
		this.cache = cache;
	}
	
	public void put(Object key, Object value) {
		incPutCount();
		cache.put(new Element(key, value));
	}
	
	public void putAll(Collection<Element> elements) {
		incPutCount();
		cache.putAll(elements);
	}
	
	public Object get(Object key) {
		incGetCount();
		Element e = cache.get(key);
		return e == null ? null : e.getValue();
	}
	
	public void delete(Object key) {
		incDelCount();
		cache.remove(key);
	}
	
	private void incPutCount() {
		long newCount = putCount.incrementAndGet();
		if (newCount % 100 == 0) {
			logger.info("set cache count : " + newCount);
		}
	}
	
	private void incGetCount() {
		long newCount = getCount.incrementAndGet();
		if (newCount % 100 == 0) {
			logger.info("get cache count : " + newCount);
		}
	}
	
	private void incDelCount() {
		long newCount = delCount.incrementAndGet();
		if (newCount % 100 == 0) {
			logger.info("delete cache count : " + newCount);
		}
	}
}
