package bank.demo.dto.config;


import liquibase.integration.spring.SpringLiquibase;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Properties;

@Configuration// анотация, что это конфугурация и тут будут разные действия
@ComponentScan(basePackages = "")//тут делаем сканирование, чтобы все компоненты спринга работали, это надо там где может быть бд
@EnableTransactionManagement// позволяет проводить транзакцию, с этим надо разобраться лучше потом
@PropertySource("classpath:application.properties")//тут идёт ссылка на проперти, где содержаться ссылки на мою бд и разные важные кофигурации
public class DatabaseConfig {


    @Bean
    public SessionFactory sessionFactory(DataSource dataSource, //здесь добавляем наш класс, с нашими параметрами подключения к бд
                                         @Value("${hibernate.dialect}") String dialect,
                                         //value автоматически с проперти переносит значение в стринг к примеру диалект
                                         //тут мы пишем дополнения к нашей фабрике сесий
                                         @Value("${hibernate.hbm2ddl.auto}") String ddl,
                                         @Value("${hibernate.show_sql}") String showSql,
                                         @Value("${hibernate.package.scan}") String packageScan
    ) throws IOException {
        var sessionFactory = new LocalSessionFactoryBean(); //сесия помогает работать с объектами, делать запросы и т.д.
        //сессия .Сесия фабрика идёт одна на весь проект с неё начинается, так же у каждого юзера есть сесия, пока он в сети она есть
        //когда он выходит, сессия возвращает человека на то место где он остановился.
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(packageScan);
        var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", dialect);
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", ddl);
        hibernateProperties.setProperty("hibernate.show_sql", showSql);
        sessionFactory.setHibernateProperties(hibernateProperties);
        sessionFactory.afterPropertiesSet();

        return sessionFactory.getObject(); //тут у нас будет которая фабрика сесий
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) { //что-то связаное с
        // транзакциями, как работает я не знаю
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(@Value("${database.url}") String databaseUrl, //берёи с проперти значения для подключение к бд
                                 @Value("${database.user}") String databaseUser,
                                 @Value("${database.password}") String databasePassword,
                                 @Value("${database.driver}") String databaseDriver) {
        var datasource = new BasicDataSource();
        datasource.setUrl(databaseUrl);
        datasource.setUsername(databaseUser);// и тут эти данные добавляем нашему классу, датаСоурце
        datasource.setPassword(databasePassword);
        datasource.setDriverClassName(databaseDriver);
        return datasource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
