package com.cy.sys.log.dao;

import com.cy.pj.sys.dao.BaseDao;
import com.cy.pj.sys.dao.SysDeptDao;
import com.cy.pj.sys.entity.AbstractObject;
import com.cy.pj.sys.entity.SysDept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class SysDeptDaoTest {
    @Autowired
    private SysDeptDao sysDeptDao;

    @Test
    void testFindObjects() {
        List<Map<String,Object>> depts = sysDeptDao.findMapObjects();
        System.out.println(Arrays.deepToString(depts.toArray()));

    }
    @Autowired
    private BeanFactory beanFactory;
    @Test
    void test2(){
        SuperImpl<SysDept, SysDeptDao> sysDeptDaoSuper = new SuperImpl<>(beanFactory);
    }
}

interface IBase<T extends AbstractObject,D extends BaseDao<T>>{

}

class SuperImpl<T extends AbstractObject,D extends BaseDao<T>> implements IBase<T,D>{

    private Class<D> clazz;
    public SuperImpl(BeanFactory beanFactory){
        // 获取父类的type
        Type type = getClass().getGenericSuperclass();
        System.out.println(type.getTypeName());
        // 强转为ParameterizedType，可以使用获取泛型类型的方法
//        ParameterizedType pType = (ParameterizedType) type;
//        Type[] actualTypeArguments = pType.getActualTypeArguments();
//        System.out.println(actualTypeArguments.length);
//        // 获取泛型的class
//        this.clazz = (Class<D>) pType.getActualTypeArguments()[1];
//
//        D dao = beanFactory.getBean(clazz);
//
//        List<Map<String, Object>> mapObjects = dao.findMapObjects();
//        System.out.println(mapObjects);
    }
}
