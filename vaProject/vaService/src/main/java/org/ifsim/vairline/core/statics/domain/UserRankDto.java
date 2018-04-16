package org.ifsim.vairline.core.statics.domain;

/**
 * @Description: 用户排名
 * @author shentong
 * @date 2018年1月22日 下午12:31:45
 * @version V1.0
 */
public class UserRankDto {
	/**
	 * @Description:用户名
	 */
	private String username;

	/**
	 * @Description: 用户头像
	 */
	private String photo;
	
	/**
	 * @Description: 总时间
	 */
	private Long sumTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getSumTime() {
		return sumTime;
	}

	public void setSumTime(Long sumTime) {
		this.sumTime = sumTime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "UserRankDto [username=" + username + ", photo=" + photo + ", sumTime=" + sumTime + "]";
	}
	
}
