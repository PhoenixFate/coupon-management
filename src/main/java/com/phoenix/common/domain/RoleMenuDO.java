package com.phoenix.common.domain;

import java.io.Serializable;



/**
 * 角色与菜单对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 14:47:52
 */
public class RoleMenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//角色与菜单对应ID
	private String roleMenuId;
	//角色ID
	private String roleId;
	//菜单ID
	private String menuId;

	/**
	 * 设置：角色与菜单对应ID
	 */
	public void setRoleMenuId(String roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
	/**
	 * 获取：角色与菜单对应ID
	 */
	public String getRoleMenuId() {
		return roleMenuId;
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
	/**
	 * 设置：菜单ID
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：菜单ID
	 */
	public String getMenuId() {
		return menuId;
	}
	
	@Override
	public String toString() {
		return "RoleMenuDO{" +
				"roleMenuId=" + roleMenuId +
				", roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}
