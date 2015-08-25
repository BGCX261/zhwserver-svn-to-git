/**
 * 
 */
package com.user.entity.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Administrator
 * 
 */
public class Page {

	/** 页码 */
	private Integer pageNo = 1; 
	/** 每页显示数量 */
	private Integer pageSize; 
	/** 页内数据集合 */
	private Collection<?> content; 
	/** 符合条件的总数量 */
	private Long totalRecNum; 
	/** 当前页开始的偏移量 */
	private Integer startIndex; 
	/** 查询条件 */
	private Map<Object, Object> condition = new HashMap<Object, Object>();

	public Map<Object, Object> getCondition() {
		return condition;
	}

	public Page(Integer pageNo)
	{
		super();
		this.pageNo=pageNo;
		this.pageSize=10;
	}
	
    public Page()
    {
    	this(1);
    }
	
	@SuppressWarnings("unchecked")
	public void setCondition(Object obj) {
		try {
			this.getCondition().putAll(PropertyUtils.describe(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Collection<?> getContent() {
		return content;
	}

	public void setContent(Collection<?> content) {
		this.content = content;
	}

	public Long getTotalRecNum() {
		return totalRecNum;
	}

	public void setTotalRecNum(Long totalRecNum) {
		this.totalRecNum = totalRecNum;
	}

	public Integer getTotalPageNum() {
		return totalRecNum%pageSize!=0?(int)(totalRecNum/pageSize+1):(int)(totalRecNum/pageSize);
	}

	public Boolean getPrePage() {
		return pageNo>1;
	}

	public Boolean getNextPage() {
		return pageNo<this.getTotalPageNum();
	}

	public Long getStartIndex() {
		return 1L*pageSize*(pageNo-1);
	}

	public Long getEndIndex() {
		return pageSize*pageNo>this.getTotalRecNum()?this.getTotalRecNum():pageSize*pageNo;
	}

	/**
	 * 去除警告的get方法
	 * @return
	 */
	protected Integer removeWaningMethod() {
		return this.startIndex;
	}
}
