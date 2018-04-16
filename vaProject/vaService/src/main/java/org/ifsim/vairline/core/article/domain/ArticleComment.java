package org.ifsim.vairline.core.article.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 文章评论
 * @author shentong
 * @date 2018年1月5日 上午10:54:07
 * @version V1.0
 */
public class ArticleComment extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description:用户名
	 */
	private String username;

	/**
	 * @Description:目标用户
	 */
	private String targetUsername;

	/**
	 * @Description:父id
	 */
	private Integer parentId;

	/**
	 * @Description:内容
	 */
	private String content;

	/**
	 * @Description:头像
	 */
	private String photo;

	/**
	 * @Description:文章id
	 */
	private Integer articleId;

	/**
	 * @Description:是否被查看
	 */
	private Boolean isViewed;
	
	/**
	 * @Description: 喜欢量
	 */
	private Integer countOfLiking;
	
	/**
	 * @Description: 点赞数
	 */
	private Integer countOfTendency;
	
	/**
	 * @Description: 用户是否已经点击过
	 */
	private Boolean isClick;
	
	public Boolean getIsClick() {
		return isClick;
	}

	public void setIsClick(Boolean isClick) {
		this.isClick = isClick;
	}

	public Integer getCountOfTendency() {
		return countOfTendency;
	}

	public void setCountOfTendency(Integer countOfTendency) {
		this.countOfTendency = countOfTendency;
	}

	public Integer getCountOfLiking() {
		return countOfLiking;
	}

	public void setCountOfLiking(Integer countOfLiking) {
		this.countOfLiking = countOfLiking;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTargetUsername() {
		return targetUsername;
	}

	public void setTargetUsername(String targetUsername) {
		this.targetUsername = targetUsername;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Boolean getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(Boolean isViewed) {
		this.isViewed = isViewed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ArticleComment [username=" + username + ", targetUsername=" + targetUsername + ", parentId=" + parentId
				+ ", content=" + content + ", photo=" + photo + ", articleId=" + articleId + ", isViewed=" + isViewed
				+ ", countOfLiking=" + countOfLiking + ", countOfTendency=" + countOfTendency + "]";
	}


}
