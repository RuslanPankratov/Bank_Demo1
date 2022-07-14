package bank.controller.admin;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class UserControllerIT {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DatabaseSetup(value = "classpath:dbunit/admin/users/find-users-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/admin/users/find-users-dataset.xml")
    void shouldGetByIdUser() throws Exception {
        mockMvc.perform(get("/users/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.firstName").value("test name"))
                .andExpect(jsonPath("$.user.lastName").value("test name"))
                .andExpect(jsonPath("$.user.typeOfBenefits").value("DISABILITY_ONE_TWO"))
                .andExpect(jsonPath("$.user.age").value("200"))
                .andExpect(jsonPath("$.user.idUser").value("10"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/admin/users/find-all-users-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/admin/users/find-all-users-dataset.xml")
    void shouldFindAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[2].firstName").value("test"))
                .andExpect(jsonPath("$.users[2].lastName").value("test"))
                .andExpect(jsonPath("$.users[2].typeOfBenefits").value("DISABILITY_ONE_TWO"))
                .andExpect(jsonPath("$.users[2].age").value("200"))
                .andExpect(jsonPath("$.users[2].idUser").value("3"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/admin/users/create-user-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/admin/users/create-user-dataset.xml")
    void shouldCreateUser() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdUserId").value("1"));
    }

    @Test
    @DatabaseSetup(value = "classpath:dbunit/admin/users/update-user-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/admin/users/update-user-dataset.xml")
    void shouldUpdateUser() throws Exception {
        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("test first name"))
                .andExpect(jsonPath("$.lastName").value("test last name"))
                .andExpect(jsonPath("$.typeOfBenefits").value("DISABILITY_ONE_TWO"))
                .andExpect(jsonPath("$.age").value("200"))
                .andExpect(jsonPath("$.idUser").value("10"));
    }

    private String createUserJSON() throws JSONException {
        return new JSONObject()
                .put("firstName", "test first name")
                .put("lastName", "test last name")
                .put("age", "200")
                .put("typeOfBenefits", "DISABILITY_ONE_TWO")
                .toString();
    }

    private String updateUserJSON() throws JSONException {
        return new JSONObject()
                .put("idUser", "10")
                .put("firstName", "test first name")
                .put("lastName", "test last name")
                .put("age", "200")
                .put("typeOfBenefits", "DISABILITY_ONE_TWO")
                .toString();
    }

}