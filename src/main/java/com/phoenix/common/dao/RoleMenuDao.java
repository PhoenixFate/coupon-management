package com.phoenix.common.dao;

import java.util.List;

import com.phoenix.common.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMenuDao {

	public List<String> listMenuIdByRoleId(String roleId);
	
	public int removeByRoleId(String roleId);
	
	public int batchSave(List<RoleMenuDO> list);
}
