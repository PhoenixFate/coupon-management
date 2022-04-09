package com.phoenix.core.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class BasicDaoMybatisImpl extends SqlSessionDaoSupport {
    public BasicDaoMybatisImpl() {
    }

    public <T> List<T> selectList(String sqlId, Object params) {
        return this.getSqlSession().selectList(sqlId, params);
    }

    public <K, V> Map<K, V> selectMap(String sqlId, Object params, String keyName) {
        return this.getSqlSession().selectMap(sqlId, params, keyName);
    }

    public <T> T selectOne(String sqlId, Object params) {
        T t = this.getSqlSession().selectOne(sqlId, params);
        return t;
    }

    public int save(String sqlId, Object params) {
        return this.insert(sqlId, params);
    }

    public int insert(String sqlId, Object params) {
        return this.getSqlSession().insert(sqlId, params);
    }

    public int insertBatch(String sqlId, List<?> list) {
        SqlSessionTemplate template = new SqlSessionTemplate(this.getSqlSessionFactory(), ExecutorType.BATCH);
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Object o = var5.next();
            template.insert(sqlId, o);
        }

        return list.size();
    }

    public int updateBatch(String sqlId, List<?> paramlist) {
        SqlSessionTemplate template = new SqlSessionTemplate(this.getSqlSessionFactory(), ExecutorType.BATCH);
        Iterator var5 = paramlist.iterator();

        while(var5.hasNext()) {
            Object o = var5.next();
            template.update(sqlId, o);
        }

        return paramlist.size();
    }

    public int update(String sqlId, Object params) {
        return this.getSqlSession().update(sqlId, params);
    }

    public int delete(String sqlId, Object params) {
        return this.getSqlSession().delete(sqlId, params);
    }

    public int deleteBatch(String sqlId, List<?> paramList) {
        SqlSessionTemplate template = new SqlSessionTemplate(this.getSqlSessionFactory(), ExecutorType.BATCH);
        Iterator var5 = paramList.iterator();

        while(var5.hasNext()) {
            Object o = var5.next();
            template.delete(sqlId, o);
        }

        return paramList.size();
    }

    public void flush() {
        this.getSqlSession().flushStatements();
    }

    @Autowired(
            required = true
    )
    public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


//
//
//    private SqlSessionFactory getSqlSessionFactory() {
//        return ((SqlSessionTemplate)this.getSqlSession()).getSqlSessionFactory();
//    }
}
