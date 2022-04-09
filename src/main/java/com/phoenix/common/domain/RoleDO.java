package com.phoenix.common.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * 角色表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 14:59:51
 */
public class RoleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//角色ID
	private String roleId;
	//角色名称
	private String roleName;
	//备注
	private String remark;
	//创建用户id
	private String createUser;
	//创建时间
	private Date createGmt;
	//创建时间
	private Date updateGmt;
	//对应菜单集合
	private List<String> menuIds;

	/**
	 * 设置：角色ID
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * 设置：角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：创建用户id
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	/**
	 * 获取：创建用户id
	 */
	public String getCreateUser() {
		return createUser;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateGmt(Date createGmt) {
		this.createGmt = createGmt;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateGmt() {
		return createGmt;
	}
	
	public Date getUpdateGmt() {
		return updateGmt;
	}
	
	public void setUpdateGmt(Date updateGmt) {
		this.updateGmt = updateGmt;
	}
	
	public List<String> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<String> menuIds) {
		this.menuIds = menuIds;
	}
	
}
