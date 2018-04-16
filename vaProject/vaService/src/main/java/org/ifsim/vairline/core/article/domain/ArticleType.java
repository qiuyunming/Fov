package org.ifsim.vairline.core.article.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 文章类别
 * @author shentong
 * @date 2018年1月5日 上午9:42:15
 * @version V1.0
 */
public class ArticleType extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description:标题
	 */
	private String title;

	/**
	 * @Description: 封面
	 */
	private String photo;
	/**
	 * @Description:描述
	 */
	private String describe;
	/**
	 * @Description:公告
	 */
	private String notice;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "ArticleType [title=" + title + ", describe=" + describe + ", notice=" + notice + "]";
	}

}
