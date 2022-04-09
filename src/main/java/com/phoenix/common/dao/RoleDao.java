package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleDao {

	public RoleDO get(String roleId);
	
	public List<RoleDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(RoleDO role);
	
	public int update(RoleDO role);
	
	public int remove(String role_id);
	
	public int batchRemove(String[] roleIds);
}
