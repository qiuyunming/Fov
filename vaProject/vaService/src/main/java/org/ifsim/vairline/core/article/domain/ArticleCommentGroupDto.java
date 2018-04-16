package org.ifsim.vairline.core.article.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 评论VO
 * @author shentong
 * @date 2018年1月9日 下午8:54:56
 * @version V1.0
 */
public class ArticleCommentGroupDto implements Serializable {
	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description: 父评论
	 */
	private ArticleComment parent;
	/**
	 * @Description: 子评论
	 */
	private List<ArticleComment> subList;

	public ArticleComment getParent() {
		return parent;
	}

	public void setParent(ArticleComment parent) {
		this.parent = parent;
	}

	public List<ArticleComment> getSubList() {
		if (subList != null && subList.size() == 1 && subList.get(0).getId() == null) {
			subList.clear();
		}
		return subList;
	}

	public void setSubList(List<ArticleComment> subList) {
		this.subList = subList;
	}

	@Override
	public String toString() {
		return "ArticleCommentGroupDto [parent=" + parent + ", subList=" + subList + "]";
	}

}
