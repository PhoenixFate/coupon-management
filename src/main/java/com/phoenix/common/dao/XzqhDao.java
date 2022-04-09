package com.phoenix.common.dao;

import java.util.List;
import java.util.Map;

import com.phoenix.common.domain.XzqhDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XzqhDao {

	public List<XzqhDO> selectXzqh(Map<String, Object> map);

}