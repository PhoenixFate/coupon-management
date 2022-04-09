package com.phoenix.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.phoenix.coupon.domain.CouponStatistic;
import com.phoenix.coupon.domain.DynamicBean;
 
public class ClassUtil {
 
    /**
     *
     * @param object   旧的对象带值
     * @param addMap   动态需要添加的属性和属性类型
     * @param addValMap  动态需要添加的属性和属性值
     * @return  新的对象
     * @throws Exception
     */
    public Object dynamicClass(Object object,HashMap<String,Object> addMap, HashMap<String,Object> addValMap) throws Exception {
        HashMap<String,Object> returnMap = new HashMap<String,Object>();
        HashMap<String,Object> typeMap = new HashMap<String,Object>();
 
 
        Class<?> type = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(object);
                //可以判断为 NULL不赋值
                returnMap.put(propertyName, result);
                typeMap.put(propertyName, descriptor.getPropertyType());
            }
        }
 
        returnMap.putAll(addValMap);
        typeMap.putAll(addMap);
        //map转换成实体对象
        DynamicBean bean = new DynamicBean(typeMap);
        //赋值
        Set<String> keys = typeMap.keySet();
        for (Iterator it = keys.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            bean.setValue(key, returnMap.get(key));
        }
        Object obj = bean.getObject();
        return obj;
    }
    
    public static void main(String[] args) {
    	CouponStatistic order = new CouponStatistic();
        order.setNums(5);
        order.setCouponName("义诊券");
        /* List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
 
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderid(1);
        orderDetail.setOrderPrice("1USD");
        orderDetail.setOrderSku("Sku1");
 
        orderDetailList.add(orderDetail);
 
        OrderDetail orderDetail2 = new OrderDetail();
        orderDetail2.setOrderid(1);
        orderDetail2.setOrderPrice("2USD");
        orderDetail2.setOrderSku("Sku2");
        orderDetailList.add(orderDetail2);*/
        System.out.println(JSON.toJSONString(order));
        try {
            HashMap addMap = new HashMap();
            HashMap addValMap = new HashMap();
            addMap.put("test", Class.forName("java.lang.String"));
            addValMap.put("test", "测试");
            Object obj2= new ClassUtil().dynamicClass(order,addMap,addValMap);
 
            System.out.println(JSON.toJSONString(obj2));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
