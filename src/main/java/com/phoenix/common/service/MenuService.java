package com.phoenix.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.phoenix.common.domain.MenuDO;
import com.phoenix.common.domain.Tree;

@Service
public interface MenuService {

	List<Tree<MenuDO>> listMenuTree(Map<String, Object> params);

	Tree<MenuDO> getTree();
	
	Tree<MenuDO> getTree(String userId);

	List<MenuDO> list(Map<String, Object> params);
	
	int count(Map<String, Object> params);

	int remove(String menuId);

	int save(MenuDO menu);

	int update(MenuDO menu);

	MenuDO get(String menuId);

	Set<String> listPerms(String userId);
}
