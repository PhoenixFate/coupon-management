package com.phoenix.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.phoenix.common.domain.UserDO;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class ShiroUtils {
    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }
    public static UserDO getUser() {
        Object object = getSubjct().getPrincipal();
        return (UserDO)object;
    }
    
    /**
     * 
      * 方法描述：是否超级管理员
      * @param 
      * @return 
      * @version 1.0
      * @author phoenix
      * 2018年4月2日 上午10:53:34
     */
    public static boolean isAdmin()
    {
    	UserDO userDo = getUser();
    	if(userDo == null || userDo.getUserName() == null)
    	{
    		return false;
    	}
    	return "phoenixadmin".equals(userDo.getUserName()) || "phoenix".equals(userDo.getUserName());
    }
   
    public static String getUserId() {
        return getUser().getUserId();
    }
    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
