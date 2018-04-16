package org.ifsim.vairline.core.user.domain;

import java.util.Date;

import org.ifsim.vairline.common.orm.BaseEntity;
import org.springframework.cache.annotation.Cacheable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Description: 用户
 * @author shentong
 * @date 2017年11月2日 下午4:29:21
 * @version V1.0
 */
public class User extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 用户名
	 */
	private String username;

	/**
	 * @Description: 真实姓名
	 */
	private String realName;

	/*
	 * 密码
	 */
	@JsonIgnore
	private String password;

	/*
	 * 照片地址
	 */
	private String photo;

	/*
	 * 邮件
	 */
	private String mail;

	/*
	 * 手机
	 */
	private String phoneNumber;

	/*
	 * 性别
	 */
	private Boolean gender;

	/*
	 * 出生日期
	 */
	private String birthday;

	/*
	 * 毕业学校
	 */
	private String colleage;

	/*
	 * 现居城市
	 */
	private String city;

	/*
	 * 最后一次登录ip地址
	 */
	private String ip;

	/*
	 * qq
	 */
	private String qq;

	/*
	 * 微信
	 */
	private String wechat;

	/*
	 * 现居地址
	 */
	private String address;

	/*
	 * 工作
	 */
	private String work;

	/*
	 * 个人声誉
	 */
	private Integer reputation;

	/*
	 * 积分
	 */
	private Integer point;

	/*
	 * 网站最后一次登录时间
	 */
	private Date websiteLoginTime;

	/*
	 * 飞行员客户端最后一次登录时间
	 */
	private Date pilotLoginTime;

	/*
	 * 管制员客户端最后一次登录时间
	 */
	private Date atcLoginTime;

	/*
	 * 飞行员状态
	 */
	private Boolean pilotState;

	/*
	 * 管制员状态
	 */
	private Boolean atcState;

	/*
	 * 账户状态（待审核、审核通过、未通过审核）
	 */
	private Integer accountState;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getColleage() {
		return colleage;
	}

	public void setColleage(String colleage) {
		this.colleage = colleage;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Date getWebsiteLoginTime() {
		return websiteLoginTime;
	}

	public void setWebsiteLoginTime(Date websiteLoginTime) {
		this.websiteLoginTime = websiteLoginTime;
	}

	public Date getPilotLoginTime() {
		return pilotLoginTime;
	}

	public void setPilotLoginTime(Date pilotLoginTime) {
		this.pilotLoginTime = pilotLoginTime;
	}

	public Date getAtcLoginTime() {
		return atcLoginTime;
	}

	public void setAtcLoginTime(Date atcLoginTime) {
		this.atcLoginTime = atcLoginTime;
	}

	public Boolean getPilotState() {
		return pilotState;
	}

	public void setPilotState(Boolean pilotState) {
		this.pilotState = pilotState;
	}

	public Boolean getAtcState() {
		return atcState;
	}

	public void setAtcState(Boolean atcState) {
		this.atcState = atcState;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", realName=" + realName + ", password=" + password + ", photo=" + photo
				+ ", mail=" + mail + ", phoneNumber=" + phoneNumber + ", gender=" + gender + ", birthday=" + birthday
				+ ", colleage=" + colleage + ", city=" + city + ", ip=" + ip + ", qq=" + qq + ", wechat=" + wechat
				+ ", address=" + address + ", work=" + work + ", reputation=" + reputation + ", point=" + point
				+ ", websiteLoginTime=" + websiteLoginTime + ", pilotLoginTime=" + pilotLoginTime + ", atcLoginTime="
				+ atcLoginTime + ", pilotState=" + pilotState + ", atcState=" + atcState + ", accountState="
				+ accountState + "]";
	}

}
