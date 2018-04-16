package org.ifsim.vairline.operator.vo;

import java.util.List;

public class TableVO<E> {

	private List<E> rows;

	private Integer total;

	public TableVO() {
	}

	public TableVO(List<E> rows) {
		this.rows = rows;
	}

	public List<E> getRows() {
		return rows;
	}

	public void setRows(List<E> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
