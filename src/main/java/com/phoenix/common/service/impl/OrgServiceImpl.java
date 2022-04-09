package com.phoenix.common.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.phoenix.common.dao.OrgDao;
import com.phoenix.common.domain.OrgDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.OrgService;
import com.phoenix.common.utils.BuildTree;
import com.phoenix.common.utils.MD5Utils;
import com.phoenix.core.utils.UUIDUtil;



@Service
public class OrgServiceImpl implements OrgService {
	@Autowired
	private OrgDao orgDao;
	
	@Override
	public OrgDO get(String orgId){
		OrgDO org = orgDao.get(orgId);
		return org;
	}
	
	@Override
	public List<OrgDO> list(Map<String, Object> map){
		return orgDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orgDao.count(map);
	}
	
	@Override
	public int save(OrgDO org){
		org.setOrgId(UUIDUtil.getUUID());
		org.setCreateGmt(new Date());
		org.setInitRoleStatus("0");
		return orgDao.save(org);
	}
	
	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit = false;
		if(null == params.get("oldOrgCode") || !params.get("oldOrgCode").toString().equals(params.get("orgCode").toString())) {
			exit = orgDao.list(params).size() > 0;
		}
		return exit;
	}
	
	@Override
	public int update(OrgDO org){
		org.setUpdateGmt(new Date());
		return orgDao.update(org);
	}
	
	@Override
	public int remove(String orgId){
		return orgDao.remove(orgId);
	}
	
	@Override
	public int batchRemove(String[] orgIds){
		return orgDao.batchRemove(orgIds);
	}

	@Override
	public List<OrgDO> getOrgs(Map<String, Object> map){
		map.put("status", "1");
		return orgDao.getOrgs(map);
	}
	
	@Override
	public Tree<OrgDO> getTree() {
		List<Tree<OrgDO>> trees = new ArrayList<Tree<OrgDO>>();
		List<OrgDO> orgList = orgDao.list(ImmutableMap.of("status", "1"));
		for (OrgDO org : orgList) {
			Tree<OrgDO> tree = new Tree<OrgDO>();
			tree.setId(org.getOrgCode());
			tree.setText(org.getOrgName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<OrgDO> t = BuildTree.build(trees);
		return t;
	}
	
	@Override
	public int saveRefundConfig(OrgDO orgDO) {
		OrgDO orgVo = orgDao.get(orgDO.getOrgId());
		if (StringUtils.isNotEmpty(orgVo.getRefundPassword())) {
			if (!Objects.equal(MD5Utils.encrypt(orgVo.getOrgCode(),orgDO.getOldRefundPassword()),orgVo.getRefundPassword())) {
				return -1;
			}
		}
		orgDO.setRefundPassword(MD5Utils.encrypt(orgVo.getOrgCode(), orgDO.getRefundPassword()));
		return orgDao.update(orgDO);
			
	}
	
}
