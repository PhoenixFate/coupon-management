package com.phoenix.common.service;

import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.domain.Tree;

import java.util.List;
import java.util.Map;

public interface OrgService {
	
	OrgDO get(String orgId);
	
	List<OrgDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrgDO orgDO);
	
	boolean exit(Map<String, Object> params);
	
	int update(OrgDO orgDO);
	
	int remove(String orgId);
	
	int batchRemove(String[] orgIds);

	List<OrgDO> getOrgs(Map<String, Object> map);
	
	public Tree<OrgDO> getTree();
	
	int saveRefundConfig(OrgDO orgDO);
}
