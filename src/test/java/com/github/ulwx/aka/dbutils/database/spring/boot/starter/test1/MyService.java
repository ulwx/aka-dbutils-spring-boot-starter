package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test1;

import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.database.spring.boot.starter.test2.AddressMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService {
    private AddressDao addressDao;
    private AddressMapper mapper;
    private MDataBaseTemplate mDataBaseTemplate;
    public MyService(AddressDao addressDao, AddressMapper mapper,MDataBaseTemplate mDataBaseTemplate) {
        this.addressDao = addressDao;
        this.mapper=mapper;
        this.mDataBaseTemplate=mDataBaseTemplate;
    }

    public void init(){
        mDataBaseTemplate.exeScript("",
                "test.sql",false,false,";", "utf-8");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateMdb(){

        List<Address> list= addressDao.getListMd();
        //声明了Propagation.NESTED事务
        addressDao.updateMd(1);
        try {
            addressDao.updateMdById(2);
        }catch (Exception e){
            e.printStackTrace();
        }
       //MyService方法的内部调用会使用被调用方法上声明的事务失效，所以需要用下面方式调用
       ((MyService) AopContext.currentProxy()).updateMdbOther();
        //MyService方法的内部调用会使用被调用方法上声明的事务失效，下面的内部调用还是在updateMdb()方法的事务里。
        updateMdbOther();


    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void updateMdbOther(){
        System.out.println("call updateMdbOther");
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateByMapper(){
         mapper.update(1);
         mapper.update(2);
    }

    public Address queryAddressByMapper(int id){
        return mapper.getOne(id);
    }

}
