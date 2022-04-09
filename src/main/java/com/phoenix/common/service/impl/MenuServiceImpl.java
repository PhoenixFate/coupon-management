package com.phoenix.common.service.impl;

import com.phoenix.common.dao.MenuDao;
import com.phoenix.common.dao.RoleMenuDao;
import com.phoenix.common.domain.MenuDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.MenuService;
import com.phoenix.common.utils.BuildTree;
import com.phoenix.core.utils.UUIDUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private RoleMenuDao roleMenuDao;

	@Override
	public List<MenuDO> list(Map<String, Object> params) {
		List<Tree<MenuDO>> trees = listMenuTree(params);
		List<MenuDO> menus = new ArrayList<>();
		return initMenuList(trees, menus,0);
	}
	
	@Override
	public int count(Map<String, Object> map) {
		return menuDao.count(map);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int remove(String menuId) {
		int result = menuDao.remove(menuId);
		return result;
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int save(MenuDO menu) {
		menu.setMenuId(UUIDUtil.getUUID());
		int r = menuDao.save(menu);
		return r;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(MenuDO menu) {
		int r = menuDao.update(menu);
		return r;
	}

	@Override
	public MenuDO get(String menuId) {
		MenuDO menuDO = menuDao.get(menuId);
		return menuDO;
	}

	@Override
	public Tree<MenuDO> getTree() {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.list(new HashMap<>(16));
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentMenuId());
			tree.setText(sysMenuDO.getMenuName()+"("+(StringUtils.isBlank(sysMenuDO.getRemark())?"":sysMenuDO.getRemark())+")");
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getMenuUrl());
			attributes.put("icon", sysMenuDO.getMenuIcon());
			attributes.put("menuPerms", sysMenuDO.getMenuPerms());
			attributes.put("menuType", sysMenuDO.getMenuType());
			attributes.put("remark", StringUtils.isBlank(sysMenuDO.getRemark())?"":sysMenuDO.getRemark());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<MenuDO> getTree(String roleId) {
		List<MenuDO> menus = menuDao.list(new HashMap<String, Object>(16));
		List<String> menuIds = roleMenuDao.listMenuIdByRoleId(roleId);
		for (MenuDO menu : menus) {
			if (menuIds.contains(menu.getParentMenuId())) {
				menuIds.remove(menu.getParentMenuId());
			}
		}
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		for (MenuDO sysMenuDO : menus) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentMenuId().toString());
			tree.setText(sysMenuDO.getMenuName()+"("+(StringUtils.isBlank(sysMenuDO.getRemark())?"":sysMenuDO.getRemark())+")");
			Map<String, Object> state = new HashMap<>(16);
			String menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getMenuUrl());
			attributes.put("icon", sysMenuDO.getMenuIcon());
			attributes.put("menuPerms", sysMenuDO.getMenuPerms());
			attributes.put("menuType", sysMenuDO.getMenuType());
			attributes.put("remark", StringUtils.isBlank(sysMenuDO.getRemark())?"":sysMenuDO.getRemark());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<MenuDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Set<String> listPerms(String userId) {
		List<String> perms = menuDao.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<MenuDO>> listMenuTree(Map<String, Object> params) {
		List<Tree<MenuDO>> trees = new ArrayList<Tree<MenuDO>>();
		List<MenuDO> menuDOs = menuDao.listMenuByUserId(params);
		for (MenuDO sysMenuDO : menuDOs) {
			Tree<MenuDO> tree = new Tree<MenuDO>();
			tree.setId(sysMenuDO.getMenuId());
			tree.setParentId(sysMenuDO.getParentMenuId().toString());
			tree.setText(sysMenuDO.getMenuName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getMenuUrl());
			attributes.put("icon", sysMenuDO.getMenuIcon());
			attributes.put("menuPerms", sysMenuDO.getMenuPerms());
			attributes.put("menuType", sysMenuDO.getMenuType());
			attributes.put("remark", StringUtils.isBlank(sysMenuDO.getRemark())?"":sysMenuDO.getRemark());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<MenuDO>> list = BuildTree.buildList(trees, "0");
		return list;
	}
	
	private List<MenuDO> initMenuList(List<Tree<MenuDO>> trees,List<MenuDO> menuDOs,int deepth){
		int currentDeepth = deepth;
		for(Tree<MenuDO> tree : trees ) {
			MenuDO menuDO = new MenuDO();
			menuDO.setMenuId(tree.getId());
			menuDO.setParentMenuId(tree.getParentId());
			menuDO.setMenuName(tree.getText());
			menuDO.setMenuUrl(tree.getAttributes().get("url")==null?"":tree.getAttributes().get("url").toString());
			menuDO.setMenuPerms(tree.getAttributes().get("menuPerms")==null?"":tree.getAttributes().get("menuPerms").toString());
			menuDO.setMenuType(Integer.valueOf(tree.getAttributes().get("menuType").toString()));
			menuDO.setMenuIcon(tree.getAttributes().get("icon")==null?"":tree.getAttributes().get("icon").toString());
			menuDO.setRemark(tree.getAttributes().get("remark")==null?"":tree.getAttributes().get("remark").toString());
			menuDO.setDeepth(currentDeepth);
			if (tree.isHasChildren()) {
				deepth = currentDeepth+1;
				menuDOs.add(menuDO);
				initMenuList(tree.getChildren(),menuDOs,deepth);
			}else {
				menuDOs.add(menuDO);
			}
		}
		return menuDOs;
	}

}
