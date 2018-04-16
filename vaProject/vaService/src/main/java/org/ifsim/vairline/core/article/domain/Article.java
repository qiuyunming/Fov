package org.ifsim.vairline.core.article.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 文章实体
 * @author shentong
 * @date 2018年1月5日 上午9:32:53
 * @version V1.0
 */
public class Article extends BaseEntity {
	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description:作者
	 */
	private String author;

	/**
	 * @Description:标题
	 */
	private String title;

	/**
	 * @Description: 头像
	 */
	private String head;

	/**
	 * @Description:封面
	 */
	private String photo;

	/**
	 * @Description:阅读量
	 */
	private Integer countOfReading;

	/**
	 * @Description:内容
	 */
	private String content;

	/**
	 * @Description:类型id
	 */
	private Integer typeId;

	/**
	 * @Description:是否过期
	 */
	private Boolean isOvertime;
	
	/**
	 * @Description: 内容纯文字
	 */
	private String contentText;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getCountOfReading() {
		return countOfReading;
	}

	public void setCountOfReading(Integer countOfReading) {
		this.countOfReading = countOfReading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Boolean getIsOvertime() {
		return isOvertime;
	}

	public void setIsOvertime(Boolean isOvertime) {
		this.isOvertime = isOvertime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	@Override
	public String toString() {
		return "Article [author=" + author + ", title=" + title + ", head=" + head + ", photo=" + photo
				+ ", countOfReading=" + countOfReading + ", content=" + content + ", typeId=" + typeId + ", isOvertime="
				+ isOvertime + "]";
	}

}
