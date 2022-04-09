package com.phoenix.common.service.impl;

import java.util.*;

import com.phoenix.common.dao.UserDao;
import com.phoenix.common.dao.UserRoleDao;
import com.phoenix.common.domain.UserDO;
import com.phoenix.common.domain.UserRoleDO;
import com.phoenix.common.domain.UserVO;
import com.phoenix.common.service.UserService;
import com.phoenix.common.utils.*;
import com.phoenix.core.utils.UUIDUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	

	@Override
	public UserDO get(String userId) {
		UserDO user = userDao.get(userId);
		return user;
	}

	@Override
	public List<UserDO> list(Map<String, Object> map) {
		return userDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map) {
		return userDao.count(map);
	}

	@Transactional
	@Override
	public int save(UserDO user) {
		user.setUserId(UUIDUtil.getUUID());
		user.setPassword(MD5Utils.encrypt(user.getUserName(), user.getPassword()));
		user.setCreateGmt(new Date());
		int count = userDao.save(user);
		String userId = user.getUserId();
		userRoleDao.removeByUserId(userId);
		UserRoleDO userRoleDO = new UserRoleDO();
		userRoleDO.setUserRoleId(UUIDUtil.getUUID());
		userRoleDO.setUserId(userId);
		userRoleDO.setRoleId(user.getRoleId());
		userRoleDao.save(userRoleDO);
		return count;
	}

	@Transactional
	@Override
	public int update(UserDO user) {
		user.setUpdateGmt(new Date());
		int r = userDao.update(user);
		userRoleDao.removeByUserId(user.getUserId());
		UserRoleDO userRoleDO = new UserRoleDO();
		userRoleDO.setUserRoleId(UUIDUtil.getUUID());
		userRoleDO.setUserId(user.getUserId());
		userRoleDO.setRoleId(user.getRoleId());
		userRoleDao.save(userRoleDO);
		return r;
	}

	@Override
	public int remove(String userId) {
		userRoleDao.removeByUserId(userId);
		return userDao.remove(userId);
	}

	@Override
	public boolean checkExists(Map<String, Object> params) {
		boolean exit;
		exit = userDao.list(params).size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(String userId) {
		return null;
	}

	@Override
	public int resetPwd(UserVO userVO,UserDO userDO) throws Exception {
		if(Objects.equals(userVO.getUserDO().getUserId(),userDO.getUserId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUserName(),userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUserName(),userVO.getPwdNew()));
				return userDao.update(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		UserDO userDO =get(userVO.getUserDO().getUserId());
		if("admin".equals(userDO.getUserName())){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUserName(), userVO.getPwdNew()));
		return userDao.update(userDO);


	}

	@Transactional
	@Override
	public int batchremove(String[] userIds) {
		int count = userDao.batchRemove(userIds);
		userRoleDao.batchRemoveByUserId(userIds);
		return count;
	}

	@Override
	public int updatePersonal(UserDO userDO) {
		return userDao.update(userDO);
	}
}
