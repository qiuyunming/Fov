package org.ifsim.vairline.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 分页
 * @author shentong
 * @date 2018年2月22日 下午2:14:48
 * @version V1.0
 */
public class Page<T> implements Serializable{

	/**
	 * @Description: 当前页数
	 */
	private int pageNum;
	/**
	 * @Description: 页面大小
	 */
	private int pageSize;
	/**
	 * @Description: 总记录条数
	 */
	private int totalRecord;
	/**
	 * @Description: 总页数
	 */
	private int totalPage;
	/**
	 * @Description: 开始索引
	 */
	private int startIndex;
	/**
	 * @Description: 列表
	 */
	private List<T> list;

	/**
	 * @Description: 页数开始
	 */
	private int start;

	/**
	 * @Description: 页数结束
	 */
	private int end;

	/**
	 * @Description: 分页长度
	 */
	private int pageLength = 5;

	public Page(int pageNum, int pageSize, int totalRecord) {
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;

		if (totalRecord % pageSize == 0) {
			this.totalPage = totalRecord / pageSize;
		} else {
			this.totalPage = totalRecord / pageSize + 1;
		}

		this.startIndex = (pageNum - 1) * pageSize;

		this.start = 1;
		this.end = pageLength;
		if (totalPage <= pageLength) {
			this.end = this.totalPage;
		} else {

			if (pageLength % 2 != 0) {
				this.start = pageNum - pageLength / 2;
				this.end = pageNum + pageLength / 2;

			} else {
				this.start = pageNum - (pageLength / 2 - 1);
				this.end = pageNum + pageLength / 2;
			}

			if (start < 1) {
				this.start = 1;
			}
			if (end > this.totalPage) {
				this.end = totalPage;
			}
		}

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
