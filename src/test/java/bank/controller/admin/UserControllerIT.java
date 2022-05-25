package bank.controller.admin;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc//необходимо, чтобы он автоматически сконфигурировал мок мвс
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //будет при каждом тесте занова создавать
// запрос в h2, то есть тесты не будут одним цельным запросом, но каждый тест будет индивидуальным
class UserControllerIT {
//для h2 надо подключить новый аппликатион.ймл
    @Autowired // автоматически заполняет классы и имплементации
    private MockMvc mockMvc;

    @Test
    void shouldCreateUser() throws Exception {

        //для таких тестов нужен json
        mockMvc.perform(post("/user") //пост это выбираем тип, @PostMapping и выбираем какой используем юрл
                        .contentType(MediaType.APPLICATION_JSON)//надо передать тип контента, то есть json
                        .content(createUserJSON()))// тут передаёт наш джейсон в тело запроса
                .andExpect(status().isOk())//и то что мы ожидаем, что не будет ошибок, так можно разные результаты узнавать
                .andExpect(jsonPath("$.createdUserId").value(""));  //$. доллар говорит, что это начала json
        //дальше мы говорим какое поле мы выбрали, и к примеру ид юзера и в валуе мы ожидаем результат ид пользователя
        // определённый
    }


//    @Test
//    void shouldGetById() throws Exception{
//        mockMvc.perform(post("/user"))
//
//    }

    private String createUserJSON() throws JSONException {
        //JSONObject() удобен тем, что ты создаёшь его как мапу, а потом ты говоришь ту стринг
        // и он преобразовывается в json
        return new JSONObject()
                .put("firstName", "test first name")
                .put("lastName", "test last name")//делаем как мапу, всовываем ему json
                .put("age", 200)
                .put("typeOfBenefits", "DISABILITY_ONE_TWO")
                .toString();//и так мы в конечном итоге передаём данные

    }


}