package com.phoenix.common.dao;

import com.phoenix.common.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleDao {

	public int save(UserRoleDO userRole);
	
	public String getRoleId(String userId);

	public int removeByUserId(String userId);

	public int batchRemoveByUserId(String[] ids);
}
