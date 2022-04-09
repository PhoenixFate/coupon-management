package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.MenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuDao {

	public MenuDO get(String menuId);
	
	public List<MenuDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(MenuDO menu);
	
	public int update(MenuDO menu);
	
	public int remove(String menu_id);
	
	public int batchRemove(String[] menuIds);
	
	public List<MenuDO> listMenuByUserId(Map<String, Object> params);
	
	public List<String> listUserPerms(String userId);
}
