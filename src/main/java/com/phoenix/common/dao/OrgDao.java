package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.OrgDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgDao {

	public OrgDO get(String orgId);
	
	public OrgDO getByOrgCode(String orgCode);
	
	public List<OrgDO> list(Map<String,Object> map);
	
	public int count(Map<String,Object> map);
	
	public int save(OrgDO org);
	
	public int update(OrgDO org);
	
	public int remove(String orgId);
	
	public int batchRemove(String[] orgIds);
	
	public List<OrgDO> getOrgs(Map<String,Object> map);
	
}
