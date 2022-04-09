package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

	public UserDO get(String userId);
	
	public List<UserDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(UserDO user);
	
	public int batchSave(List<UserDO> users);
	
	public int update(UserDO user);
	
	public int remove(String user_id);
	
	public int batchRemove(String[] userIds);
	
}
