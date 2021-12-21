package itix.core.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//@ComponentScan(basePackages = {"itix.core.model"})
public class HibernateConfiguration {

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "oracle.jdbc.OracleDriver";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "itix";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "itix";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.OracleDialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";


    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
        dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
        dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
        dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);

        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan("itix.core.model", "itix.core.service"); // @ComponentScan not working ?
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }


    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
        properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
        // toujours à 'none' sauf pour reimport d'un nouveau fichiers via le batch ('update')
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("shutdown", "true");
        return properties;
    }

    @Bean
    public HibernateTransactionManager transactionManager(final SessionFactory sessionFactory) {
        final HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }

}
