package com.example.cydemo.controller;


import com.example.cydemo.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public class BaseController<T> {

    private BaseDao<T> TDao;
    //由于BaseDao的bean被ban掉了，必须在子类中手动注入
    public void init(BaseDao<T> dao)
    {
        this.TDao = dao;
    }
    /**
     * 添加/修改字段
     * @param t （对象）
     * @return "成功"
     */
    public String addT(T t)
    {
        TDao.save(t);
        return "成功";
    }
    /**
     * 根据id删除字段
     * @param id
     * @return "删除成功"
     * @throws RuntimeException //id输错
     */
    public String deleteT(long id) throws RuntimeException
    {
        TDao.deleteById(id);
        return "删除成功";
    }

    /**
     * 根据id获取字段映射的对象
     * @param id
     * @return T对象
     * @throws RuntimeException //id输错
     */
    public T getOneTById(Long id) throws RuntimeException
    {
        return TDao.getOne(id);
    }
    /**
     * 精确/模糊搜索
     * @param key
     * @param value
     * @param isFuzzy
     * @return List<T>（无序）
     * @throws RuntimeException  //key可能输错，root和数据库找不到key
     */
    public List<T> searchTs(String key, String value, boolean isFuzzy)//throws RuntimeException
    {
        //定制检索条件，返回一个谓词实例
        Specification<T> spec = new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate equal = criteriaBuilder.equal(root.get(key),value);
                Predicate like = criteriaBuilder.like(root.get(key).as(String.class),"%"+value+"%");
                return (isFuzzy? like : equal);            }
        };
        //把构造好的谓词放入接口 搜索
        List<T> list = TDao.findAll(spec);
        return list;
    }



}
