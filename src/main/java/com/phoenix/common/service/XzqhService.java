package com.phoenix.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.phoenix.common.domain.XzqhDO;

@Service
public interface XzqhService {
	
	List<XzqhDO> selectXzqh(Map<String, Object> map);
	
}
