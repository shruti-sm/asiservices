package com.happiestminds.asi.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.persistence.Entity;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.happiestminds.asi.beans.LoggedInUser;
/**
 * 
 * @author shruti.mishra
 */
@Configuration
@PropertySource({ "classpath:db.properties"})
@ComponentScan(basePackages = { "com.happiestminds.asi.service", "com.happiestminds.asi.dao", "com.happiestminds.asi.repository",
        "com.happiestminds.asi.domain", "com.happiestminds.asi.resource" })
@EnableTransactionManagement
@EnableScheduling
public class ServiceConfig {

    @Autowired
    private Environment environment;

    @SuppressWarnings("rawtypes")
    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(dataSource());

        ArrayList<Class> classes = new ArrayList<Class>();

        // the following will detect all classes that are annotated as @Entity
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));

        // only register classes within "com.fooPackage" package
        for (BeanDefinition bd : scanner.findCandidateComponents("com.happiestminds.asi.domain")) {
            String name = bd.getBeanClassName();
            try {
                classes.add(Class.forName(name));
            } catch (Exception E) {
                // TODO: handle exception - couldn't load class in question
            }
        } // for

        // register detected classes with AnnotationSessionFactoryBean
        lsfb.addAnnotatedClasses(classes.toArray(new Class[classes.size()]));

        lsfb.addProperties(hibernateProperties());

        return lsfb.buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
        /* return new JpaTransactionManager( entityManagerFactory() ); */

    }

    
    @Bean
    public Properties hibernateProperties() {
        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", this.environment.getProperty("hibernate.dialect"));
        prop.setProperty("hibernate.show_sql", this.environment.getProperty("hibernate.show_sql"));
        prop.setProperty("hibernate.hbm2ddl.auto", this.environment.getProperty("hibernate.hbm2ddl.auto"));

        prop.setProperty("hibernate.c3p0.min_size", this.environment.getProperty("hibernate.c3p0.min_size"));
        prop.setProperty("hibernate.c3p0.max_size", this.environment.getProperty("hibernate.c3p0.max_size"));
        prop.setProperty("hibernate.c3p0.timeout", this.environment.getProperty("hibernate.c3p0.timeout"));
        prop.setProperty("hibernate.c3p0.idle_test_periodt",
                this.environment.getProperty("hibernate.c3p0.idle_test_period"));
        prop.setProperty("hibernate.c3p0.max_statements", this.environment.getProperty("hibernate.c3p0.max_statements"));
        return prop;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();

        datasource.setDriverClassName(this.environment.getProperty("driverClassName"));
        datasource.setUrl(this.environment.getProperty("jdbc.url"));
        datasource.setUsername(this.environment.getProperty("jdbc.username"));
        datasource.setPassword(this.environment.getProperty("jdbc.password"));

        return datasource;
    }
    
    @Bean
    public DozerBeanMapper javaBeanMapper() {
        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("dozer-bean-mapping.xml");
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }
    
    @Bean
    @Scope("singleton")
    public LoggedInUser loggedInUsers() {
    	return new LoggedInUser();
    }
}
