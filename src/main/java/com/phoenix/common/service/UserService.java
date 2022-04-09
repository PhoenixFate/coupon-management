package com.phoenix.common.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.phoenix.common.domain.UserDO;
import com.phoenix.common.domain.UserVO;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	UserDO get(String userId);

	List<UserDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserDO user);

	int update(UserDO user);

	int remove(String userId);

	int batchremove(String[] userIds);

	boolean checkExists(Map<String, Object> params);

	Set<String> listRoles(String userId);

	/*
	 * 重置密码（用户操作）
	 */
	int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
	
	/*
	 * 重置密码（管理员操作）
	 */
	int adminResetPwd(UserVO userVO) throws Exception;
	
	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

}
