package com.phoenix.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.phoenix.common.domain.RoleDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.utils.R;

@Service
public interface RoleService {

	public RoleDO get(String roleId);

	public List<RoleDO> list(Map<String, Object> map);
	
	public int count(Map<String, Object> params);

	public int save(RoleDO role);

	public int update(RoleDO role);

	public int remove(String roleId);

	public R list(String userId);

	public int batchremove(String[] roleIds);
	
	public List<Tree<RoleDO>> getTree();
	
}
