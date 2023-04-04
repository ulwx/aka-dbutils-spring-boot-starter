package com.github.ulwx.aka.dbutils.database.spring.boot.starter.test1;

import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import com.github.ulwx.aka.dbutils.tool.MD;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AddressDao {
    private MDataBaseTemplate mDataBaseTemplate;
    public AddressDao(MDataBaseTemplate mDataBaseTemplate){
        this.mDataBaseTemplate=mDataBaseTemplate;
    }

    public List<Address> getListMd(){
        Map<String, Object> mp=new HashMap<>();
        List<Address> list=mDataBaseTemplate.queryList(Address.class,
                MD.md(),mp);
        return list;

    }

    public void updateMd(int id){
        Map<String, Object> mp=new HashMap<>();
        mp.put("id",id);
        mDataBaseTemplate.update(MD.md(),mp);

    }

    @Transactional(propagation = Propagation.NESTED)
    public void updateMdById(int id){
        updateMd(id);
        throw new RuntimeException();
    }

}
