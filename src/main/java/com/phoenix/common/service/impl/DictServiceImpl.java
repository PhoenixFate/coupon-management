package com.phoenix.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phoenix.common.dao.DictDao;
import com.phoenix.common.domain.DictDO;
import com.phoenix.common.service.DictService;
import com.phoenix.core.redis.JedisUtil;
import com.phoenix.core.utils.UUIDUtil;


@Service
public class DictServiceImpl implements DictService {
	@Autowired
    private DictDao dictDao;

    @Override
    public DictDO get(String dictId) {
        return dictDao.get(dictId);
    }

    
    /**
     * 
      * 方法描述：根据字典值获取公共字典名称
      * @param 
      * @return 
      * @version 1.0
      * @author phoenix
      * 2019年3月18日 上午10:48:54
     */
    public String getGlobalDictValue(String dictName, String dictType)
    {
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("dictType", dictType);
    	map.put("dictName", dictName);
		List<DictDO> list = this.list(map);
		if(list.isEmpty())
			return null;
		DictDO dict = list.get(0);
		return dict.getDictValue();
		
    }
    
    /**
     * 
      * 方法描述：根据字典值获取公共字典名称
      * @param 
      * @return 
      * @version 1.0
      * @author phoenix
      * 2019年3月18日 上午10:48:54
     */
    public String getGlobalDictName(String dictValue, String dictType)
    {
    	String redisKey = "DICT:GLOBAL:" + dictType;
    	if(JedisUtil.exists(redisKey))
    	{
    		String redisStr = JedisUtil.get(redisKey);
    		HashMap<String, String> value = JSON.parseObject(redisStr, HashMap.class);
    		return value.get(dictValue);
    	}
    	
    	HashMap<String, Object> map = new HashMap<String, Object>();
    	map.put("dictType", dictType);
		List<DictDO> list = this.list(map);
		
		HashMap<String, String> value = new HashMap<String, String>();
		for(DictDO dict : list)
		{
			value.put(dict.getDictValue(), dict.getDictName());
		}
		
		JedisUtil.set(redisKey, JSON.toJSONString(value), 10 * 60); //缓存10分钟
		
		return value.get(dictValue);
		
    }
    
    @Override
    public List<DictDO> list(Map<String, Object> map) {
        return dictDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return dictDao.count(map);
    }

    @Override
    public int save(DictDO sysDict) {
    	sysDict.setDictId(UUIDUtil.getUUID());
    	if (StringUtils.isEmpty(sysDict.getDictSort()))
    		sysDict.setDictSort("99");
        return dictDao.save(sysDict);
    }

    @Override
    public int update(DictDO sysDict) {
        return dictDao.update(sysDict);
    }

    @Override
    public int remove(String dictId) {
        return dictDao.remove(dictId);
    }

    @Override
    public int batchRemove(String[] dictIds) {
        return dictDao.batchRemove(dictIds);
    }
  

}
