package bank.demo.dto.config;

import bank.demo.dto.scanner.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration //эта анотация помогает собрать все бины вместе
@ComponentScan(basePackages = "bank.demo.dto")  //сканирует весь проект на бины, где мы выбрали и
// главное не сканировать тесы
@PropertySource("classpath:application.properties")
@EnableTransactionManagement

public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    //ещё подход как можно выставлять по списку
//
//   @Autowired
//    private UIAction uIMenuEnter; //здесь обязательно поле должно совпадать с именем класса, он автоматически
//    // это сделает, надо просто первую букву поменять на маленькую
//    @Autowired
//    private UIAction uICreate;
//
//
//   @Bean (name = "withoutExit") //бины как я понимаю, это тот же компонент, только для метода
//   //это название мы можем использовать в другом месте
//   //бин может быть чем угодно, кроме войда
//    public List<UIAction> uiActions(){
//      return List.of(uIMenuEnter,uICreate);//здесь выставляем наш список
//   }
}
