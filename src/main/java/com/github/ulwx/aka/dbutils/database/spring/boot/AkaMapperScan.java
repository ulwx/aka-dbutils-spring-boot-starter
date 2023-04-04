
package com.github.ulwx.aka.dbutils.database.spring.boot;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(AkaMapperScannerRegistrar.class)
@Repeatable(AkaMapperScans.class)
public @interface AkaMapperScan {

  @AliasFor("basePackages")
  String[] value() default {};

  @AliasFor("value")
  String[] basePackages() default {};

  Class<?>[] basePackageClasses() default {};

  String mdDataBaseTemplateBeanName() default "";


}
