package bank.controller.admin;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class CreditCardControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    void shouldFindByIdCreditCard() throws Exception {
        mockMvc.perform(get("/creditCards/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditCardDTO.login").value("login test 5"))
                .andExpect(jsonPath("$.creditCardDTO.password").value("password test"))
                .andExpect(jsonPath("$.creditCardDTO.invoiceAmount").value("23000.0"))
                .andExpect(jsonPath("$.creditCardDTO.withdrawalLimit").value("2000.0"))
                .andExpect(jsonPath("$.creditCardDTO.idCreditCard").value("5"))
                .andExpect(jsonPath("$.creditCardDTO.idUser").value("6"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    void shouldFindAllCreditCard() throws Exception {
        mockMvc.perform(get("/creditCards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditCardDTOS[2].login").value("login test 4"))
                .andExpect(jsonPath("$.creditCardDTOS[2].password").value("password test"))
                .andExpect(jsonPath("$.creditCardDTOS[2].invoiceAmount").value("39000.0"))
                .andExpect(jsonPath("$.creditCardDTOS[2].withdrawalLimit").value("2000.0"))
                .andExpect(jsonPath("$.creditCardDTOS[2].idCreditCard").value("4"))
                .andExpect(jsonPath("$.creditCardDTOS[2].idUser").value("5"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    void shouldAddCreditCard() throws Exception {
        mockMvc.perform(post("/creditCards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(addCreditCardJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdCreditCardId").value("1"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/creditCard/credit-card-dataset.xml")
    void shouldUpdateCreditCard() throws Exception {
        mockMvc.perform(put("/creditCards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCreditCardJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("login test 7"))
                .andExpect(jsonPath("$.password").value("password test"))
                .andExpect(jsonPath("$.invoiceAmount").value("899000.0"))
                .andExpect(jsonPath("$.withdrawalLimit").value("2000.0"))
                .andExpect(jsonPath("$.idCreditCard").value("5"))
                .andExpect(jsonPath("$.idUser").value("10"));
    }

    private String addCreditCardJSON() throws JSONException {
        return new JSONObject()
                .put("login", "login test 7")
                .put("password", "password test")
                .put("invoiceAmount", "23000.0")
                .put("withdrawalLimit", "2000.0")
                .put("idUser", "10")
                .toString();
    }


    private String updateCreditCardJSON() throws JSONException {
        return new JSONObject()
                .put("idCreditCard", "5")
                .put("login", "login test 7")
                .put("password", "password test")
                .put("invoiceAmount", "899000.0")
                .put("withdrawalLimit", "2000.0")
                .put("idUser", "10")
                .toString();
    }
}