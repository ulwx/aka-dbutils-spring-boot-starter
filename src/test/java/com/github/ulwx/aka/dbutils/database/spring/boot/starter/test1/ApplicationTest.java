package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test1;

import com.github.ulwx.aka.dbutils.tool.support.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTest {
    @Autowired
    MyService myService;
    
    @Test
    public  void testMapper() throws Exception{

         myService.init();
         myService.updateByMapper();
        Address address1=myService.queryAddressByMapper(1);
        Assert.equal(address1.getName(),"abcd");
        Address address2= myService.queryAddressByMapper(2);
        Assert.equal(address2.getName(),"abcd");
    }
    @Test
    public void testDao() throws Exception{
        myService.init();
        myService.updateMdb();
        Address address1=myService.queryAddressByMapper(1);
        Assert.equal(address1.getName(),"abcdef");
        Address address2= myService.queryAddressByMapper(2);
        Assert.equal(address2.getName(),"b");

    }





}
