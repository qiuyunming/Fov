package org.ifsim.vairline.core.article.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 文章趋向（喜欢、收藏、报名）
 * @author shentong
 * @date 2018年1月5日 上午11:04:42
 * @version V1.0
 */
public class ArticleTendency extends BaseEntity{

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description:用户名
	 */
	private String username;
	
	/**
	 * @Description:头像
	 */
	private String photo;

	/**
	 * @Description:目标id
	 */
	private Integer targetId;

	/**
	 * @Description:类型（点赞0、收藏1、报名2）
	 */
	private Integer type;

	/**
	 * @Description: 是否评论
	 */
	private Boolean isComment;

	/**
	 * @Description: 目标用户名
	 */
	private String targetUsername;

	/**
	 * @Description: 是否已被查看
	 */
	private Boolean isViewed;

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Boolean getIsComment() {
		return isComment;
	}

	public void setIsComment(Boolean isComment) {
		this.isComment = isComment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		return "ArticleTendency [username=" + username + ", targetId=" + targetId + ", type=" + type + ", isComment="
				+ isComment + ", targetUsername=" + targetUsername + ", isViewed=" + isViewed + "]";
	}

}
