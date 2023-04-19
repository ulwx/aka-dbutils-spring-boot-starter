package com.github.ulwx.aka.dbutils.database.spring.boot;

import com.github.ulwx.aka.dbutils.database.spring.AkaMpperScannerConfigurer;
import com.github.ulwx.aka.dbutils.database.spring.MDataBaseFactory;
import com.github.ulwx.aka.dbutils.database.spring.MDataBaseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.List;
@EnableConfigurationProperties(AkaDbUtilsProperties.class)
@ConditionalOnClass({ MDataBaseTemplate.class })
@Configuration
public class AkaDbUtilsAutoConfiguration {
    private final AkaDbUtilsProperties properties;
    private static final Logger logger = LoggerFactory.getLogger(AkaDbUtilsAutoConfiguration.class);
    public AkaDbUtilsAutoConfiguration(AkaDbUtilsProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MDataBaseFactory mDataBaseFactory(DataSource dataSource) {
        MDataBaseFactory mDataBaseFactory = new MDataBaseFactory(dataSource);
        mDataBaseFactory.setTableColumRule(properties.getTableColumRule());
        mDataBaseFactory.setTableNameRule(properties.getTableNameRule());
        return mDataBaseFactory;

    }
    @Bean(destroyMethod = "")
    @ConditionalOnMissingBean
    public MDataBaseTemplate mDataBaseTemplate(MDataBaseFactory mDataBaseFactory) {
        return new MDataBaseTemplate(mDataBaseFactory);
    }
    @Configuration
    @Import(AkaAutoConfiguredMapperScannerRegistrar.class)
    @ConditionalOnMissingBean({ AkaMpperScannerConfigurer.class })
    public static class MapperScannerRegistrarNotFoundConfiguration  {

    }
    public static class AkaAutoConfiguredMapperScannerRegistrar implements BeanFactoryAware,
            ImportBeanDefinitionRegistrar, EnvironmentAware {

        private BeanFactory beanFactory;
        private Environment environment;

        @Override
        public void setEnvironment(Environment environment) {
            this.environment=environment;
        }

        @Override
        public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

            if (!AutoConfigurationPackages.has(this.beanFactory)) {
                logger.debug("Could not determine auto-configuration package, automatic mapper scanning disabled.");
                return;
            }

            List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
            if (logger.isDebugEnabled()) {
                packages.forEach(pkg -> logger.debug("Using auto-configuration base package '{}'", pkg));
            }

            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(AkaMpperScannerConfigurer.class);

            AkaDbUtilsProperties akaDbUtilsProperties=null;
            BindResult<AkaDbUtilsProperties> bindResult=
                    Binder.get(environment).bind(AkaDbUtilsProperties.PREFIX, Bindable.of(AkaDbUtilsProperties.class));
            if(bindResult.isBound()){
                akaDbUtilsProperties= bindResult.get();
            }

            String basePackage=StringUtils.collectionToCommaDelimitedString(packages);
            builder.addPropertyValue("basePackages",basePackage );
            registry.registerBeanDefinition(AkaMpperScannerConfigurer.class.getName(), builder.getBeanDefinition());
        }

        @Override
        public void setBeanFactory(BeanFactory beanFactory) {
            this.beanFactory = beanFactory;
        }

    }


}
