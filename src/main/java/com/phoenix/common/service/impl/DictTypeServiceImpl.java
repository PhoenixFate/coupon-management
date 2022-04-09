package com.phoenix.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;
import com.phoenix.common.dao.DictDao;
import com.phoenix.common.dao.DictTypeDao;
import com.phoenix.common.domain.DictDO;
import com.phoenix.common.domain.DictTypeDO;
import com.phoenix.common.service.DictTypeService;
import com.phoenix.core.utils.UUIDUtil;


@Service
public class DictTypeServiceImpl implements DictTypeService {
	
	@Autowired
    private DictTypeDao dictTypeDao;
	
	@Autowired
    private DictDao dictDao;

    @Override
    public DictTypeDO get(String dictId) {
        return dictTypeDao.get(dictId);
    }

    @Override
    public List<DictTypeDO> list(Map<String, Object> map) {
        return dictTypeDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictTypeDao.count(map);
    }

    @Override
    public int save(DictTypeDO sysDict) {
    	sysDict.setTypeId(UUIDUtil.getUUID());
    	int count = dictTypeDao.count(ImmutableMap.of("typeCode", sysDict.getTypeCode()));
		if (count > 0) {
			return -1;
		}
        return dictTypeDao.save(sysDict);
    }

    @Override
    public int update(DictTypeDO sysDict) {
    	List<DictTypeDO> typelist = dictTypeDao.list(ImmutableMap.of("typeCode", sysDict.getTypeCode()));
		if (null != typelist && !typelist.isEmpty()) {
			if (typelist.size() > 1) {
				return -1;
			}
			if (!sysDict.getTypeId().equals(typelist.get(0).getTypeId())) {
				return -1;
			}
		}
        return dictTypeDao.update(sysDict);
    }

    @Override
    public int remove(String typeId) {
    	
    	List<DictDO> dictList = dictDao.list(ImmutableMap.of("typeId", typeId));
    	if (null != dictList && !dictList.isEmpty()) {
    		return -1;
    	}
        return dictTypeDao.remove(typeId);
    }

    @Override
    public int batchRemove(String[] typeIds) {
    	int count = dictDao.getTypeCout(typeIds);
    	if (count > 0) {
    		return -1;
    	}
        return dictTypeDao.batchRemove(typeIds);
    }

    @Override
    public List<DictTypeDO> listType() {
        return dictTypeDao.list(new HashMap<String, Object>());
    }

	  
  


}
