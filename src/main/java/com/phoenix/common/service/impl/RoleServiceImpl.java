package com.phoenix.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoenix.common.dao.RoleDao;
import com.phoenix.common.dao.RoleMenuDao;
import com.phoenix.common.dao.UserRoleDao;
import com.phoenix.common.domain.RoleDO;
import com.phoenix.common.domain.RoleMenuDO;
import com.phoenix.common.domain.Tree;
import com.phoenix.common.service.RoleService;
import com.phoenix.common.utils.BuildTree;
import com.phoenix.common.utils.R;
import com.phoenix.core.utils.UUIDUtil;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    private RoleDao roleDao;
    
    @Autowired
    private RoleMenuDao roleMenuDao;
    
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleDO> list(Map<String, Object> map) {
        List<RoleDO> roles = roleDao.list(map);
        return roles;
    }

    @Override
	public int count(Map<String, Object> map) {
		return roleDao.count(map);
	}

    @Override
    public R list(String userId) {
        String roleId = userRoleDao.getRoleId(userId);
        if(StringUtils.isBlank(roleId)) {
        	roleId="";
        }
        List<RoleDO> roles = roleDao.list(new HashMap<>(16));
        return R.ok().put("roles", roles).put("roleId", roleId);
    }
    @Transactional
    @Override
    public int save(RoleDO role) {
    	role.setRoleId(UUIDUtil.getUUID());
        int count = roleDao.save(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getRoleId();
        List<RoleMenuDO> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleMenuId(UUIDUtil.getUUID());
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuDao.removeByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuDao.batchSave(rms);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(String roleId) {
        int count = roleDao.remove(roleId);
        roleMenuDao.removeByRoleId(roleId);
        return count;
    }

    @Override
    public RoleDO get(String roleId) {
        RoleDO roleDO = roleDao.get(roleId);
        return roleDO;
    }

    @Override
    public int update(RoleDO role) {
        int r = roleDao.update(role);
        List<String> menuIds = role.getMenuIds();
        String roleId = role.getRoleId();
        roleMenuDao.removeByRoleId(roleId);
        List<RoleMenuDO> rms = new ArrayList<>();
        for (String menuId : menuIds) {
            RoleMenuDO rmDo = new RoleMenuDO();
            rmDo.setRoleMenuId(UUIDUtil.getUUID());
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuDao.batchSave(rms);
        }
        return r;
    }

    @Override
    public int batchremove(String[] roleIds) {
        int r = roleDao.batchRemove(roleIds);
        return r;
    }
    
    @Override
	public List<Tree<RoleDO>> getTree() {
		List<Tree<RoleDO>> trees = new ArrayList<Tree<RoleDO>>();
		Map<String, Object> map = new HashMap<>();
		map.put("roleSign", "1");
		List<RoleDO> roleDOs = roleDao.list(map);
		for (RoleDO roleDO : roleDOs) {
			Tree<RoleDO> tree = new Tree<RoleDO>();
			tree.setId(roleDO.getRoleId());
			tree.setParentId("0");
			tree.setText(roleDO.getRoleName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		return BuildTree.buildList(trees, "0");
	}
    
}
