package org.ifsim.vairline.portal.vo;

import org.ifsim.vairline.core.company.domain.CompanyStaff;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.domain.User;

/**
 * @Description: 员工/角色
 * @author shentong
 * @date 2017年12月13日 下午3:10:56
 * @version V1.0
 */
public class CompanyStaffRoleVO {

	/**
	 * @Description: 公司成员
	 */
	private CompanyStaff companyStaff;

	/**
	 * @Description: 公司等级
	 */
	private Role companyLevel;

	/**
	 * @Description: 公司职位
	 */
	private Role companyPost;

	/**
	 * @Description: 用户
	 */
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CompanyStaff getCompanyStaff() {
		return companyStaff;
	}

	public void setCompanyStaff(CompanyStaff companyStaff) {
		this.companyStaff = companyStaff;
	}

	public Role getCompanyLevel() {
		return companyLevel;
	}

	public void setCompanyLevel(Role companyLevel) {
		this.companyLevel = companyLevel;
	}

	public Role getCompanyPost() {
		return companyPost;
	}

	public void setCompanyPost(Role companyPost) {
		this.companyPost = companyPost;
	}

	@Override
	public String toString() {
		return "CompanyStaffRoleVO [companyStaff=" + companyStaff + ", companyLevel=" + companyLevel + ", companyPost="
				+ companyPost + ", user=" + user + "]";
	}

}
