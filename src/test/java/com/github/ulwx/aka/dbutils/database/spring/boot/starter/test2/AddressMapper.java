package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test2;

import com.github.ulwx.aka.dbutils.database.AkaMapper;
import com.github.ulwx.aka.dbutils.database.annotation.AkaParam;
import com.github.ulwx.aka.dbutils.database.spring.boot.starter.test1.Address;

public abstract class AddressMapper extends AkaMapper {

    public abstract void updateMd(@AkaParam("id")  int myid);


    public void update(int i){
        this.updateMd(i);//
    }

    public Address getOne(int id){
        Address address=new Address();
        address.setAddressId(id);
        Address raddress= this.getMdDataBase().queryOneBy(address);
        return raddress;
    }
}
