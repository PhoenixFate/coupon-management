package com.phoenix.common.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 菜单管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-20 13:43:47
 */
public class MenuDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//菜单ID
	private String menuId;
	//父菜单ID，一级菜单为0
	private String parentMenuId;
	//菜单名称
	private String menuName;
	//菜单URL
	private String menuUrl;
	//授权(多个用逗号分隔，如：user:list,user:create)
	private String menuPerms;
	//类型   0：目录   1：菜单   2：按钮
	private Integer menuType;
	//菜单图标
	private String menuIcon;
	//排序
	private Integer menuOrderNum;
	//创建时间
	private Date createGmt;
	//修改时间
	private Date updateGmt;
	
	private String remark;

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
	/**
	 * 设置：父菜单ID，一级菜单为0
	 */
	public void setParentMenuId(String parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
	/**
	 * 获取：父菜单ID，一级菜单为0
	 */
	public String getParentMenuId() {
		return parentMenuId;
	}
	/**
	 * 设置：菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * 获取：菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * 设置：菜单URL
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * 获取：菜单URL
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * 设置：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public void setMenuPerms(String menuPerms) {
		this.menuPerms = menuPerms;
	}
	/**
	 * 获取：授权(多个用逗号分隔，如：user:list,user:create)
	 */
	public String getMenuPerms() {
		return menuPerms;
	}
	/**
	 * 设置：类型   0：目录   1：菜单   2：按钮
	 */
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	/**
	 * 获取：类型   0：目录   1：菜单   2：按钮
	 */
	public Integer getMenuType() {
		return menuType;
	}
	/**
	 * 设置：菜单图标
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	/**
	 * 获取：菜单图标
	 */
	public String getMenuIcon() {
		return menuIcon;
	}
	/**
	 * 设置：排序
	 */
	public void setMenuOrderNum(Integer menuOrderNum) {
		this.menuOrderNum = menuOrderNum;
	}
	/**
	 * 获取：排序
	 */
	public Integer getMenuOrderNum() {
		return menuOrderNum;
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

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	//节点深度
	private int deepth = 0;

	public int getDeepth() {
		return deepth;
	}
	public void setDeepth(int deepth) {
		this.deepth = deepth;
	}
	
}
