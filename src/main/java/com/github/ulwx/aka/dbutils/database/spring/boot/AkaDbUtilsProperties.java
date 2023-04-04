package com.github.ulwx.aka.dbutils.database.spring.boot;

import com.github.ulwx.aka.dbutils.database.utils.DbConst;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = AkaDbUtilsProperties.PREFIX)
public class AkaDbUtilsProperties {
    public static final String PREFIX = "aka.dbutils";
    private  String tableNameRule= DbConst.TableNameRules.underline_to_camel;
    private  String tableColumRule=DbConst.TableColumRules.underline_to_camel;
    public String getTableNameRule() {
        return tableNameRule;
    }

    public void setTableNameRule(String tableNameRule) {
        this.tableNameRule = tableNameRule;
    }

    public String getTableColumRule() {
        return tableColumRule;
    }

    public void setTableColumRule(String tableColumRule) {
        this.tableColumRule = tableColumRule;
    }

}
