package com.phoenix.common.domain;

import java.io.Serializable;



/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 15:05:14
 */
public class UserRoleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户角色ID
	private String userRoleId;
	//用户ID
	private String userId;
	//角色ID
	private String roleId;

	/**
	 * 设置：用户角色ID
	 */
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}
	/**
	 * 获取：用户角色ID
	 */
	public String getUserRoleId() {
		return userRoleId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public String getUserId() {
		return userId;
	}
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
	
	@Override
    public String toString() {
        return "UserRoleDO{" +
                "userRoleId=" + userRoleId +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
