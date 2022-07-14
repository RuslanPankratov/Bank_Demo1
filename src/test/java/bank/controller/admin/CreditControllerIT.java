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
class CreditControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("classpath:dbunit/admin/credit/credit-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/credit/credit-dataset.xml")
    void shouldGetByIdCredit() throws Exception {
        mockMvc.perform(get("/credits/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditDTO.idCredit").value("3"))
                .andExpect(jsonPath("$.creditDTO.howMuchToPay").value("11790.95"))
                .andExpect(jsonPath("$.creditDTO.paid").value("0.0"))
                .andExpect(jsonPath("$.creditDTO.percentRate").value("2.15"))
                .andExpect(jsonPath("$.creditDTO.theTotalAmountYouPay").value("11790.95"))
                .andExpect(jsonPath("$.creditDTO.countMonthsToPay").value("100.0"))
                .andExpect(jsonPath("$.creditDTO.bankProfit").value("1170.0"))
                .andExpect(jsonPath("$.creditDTO.howMuchIsTheLoan").value("10000.0"))
                .andExpect(jsonPath("$.creditDTO.paymentPerMonth").value("117.91"))
                .andExpect(jsonPath("$.creditDTO.idUser").value("3"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/credit/credit-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/credit/credit-dataset.xml")
    void shouldFindAllCredit() throws Exception {
        mockMvc.perform(get("/credits"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditDTOS[2].idCredit").value("4"))
                .andExpect(jsonPath("$.creditDTOS[2].howMuchToPay").value("11790.95"))
                .andExpect(jsonPath("$.creditDTOS[2].paid").value("0.0"))
                .andExpect(jsonPath("$.creditDTOS[2].percentRate").value("2.15"))
                .andExpect(jsonPath("$.creditDTOS[2].theTotalAmountYouPay").value("11790.95"))
                .andExpect(jsonPath("$.creditDTOS[2].countMonthsToPay").value("100.0"))
                .andExpect(jsonPath("$.creditDTOS[2].bankProfit").value("1170.0"))
                .andExpect(jsonPath("$.creditDTOS[2].howMuchIsTheLoan").value("10000.0"))
                .andExpect(jsonPath("$.creditDTOS[2].paymentPerMonth").value("117.91"))
                .andExpect(jsonPath("$.creditDTOS[2].idUser").value("4"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/credit/credit-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/credit/credit-dataset.xml")
    void shouldCreateCredit() throws Exception {
        mockMvc.perform(post("/credits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createCreditJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdCreditId").value("1"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/credit/credit-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/credit/credit-dataset.xml")
    void shouldUpdateCredit() throws Exception {
        mockMvc.perform(put("/credits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateCreditJSON()))
                 .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCredit").value("3"))
                .andExpect(jsonPath("$.howMuchToPay").value("999999.95"));
    }

    private String createCreditJSON() throws JSONException {
        return new JSONObject()
                .put("howMuchToPay", "11790.95")
                .put("paid", "0.0")
                .put("percentRate", "2.15")
                .put("theTotalAmountYouPay", "11790.95")
                .put("countMonthsToPay", "100.0")
                .put("bankProfit", "1170.0")
                .put("howMuchIsTheLoan", "10000.0")
                .put("paymentPerMonth", "117.91")
                .put("idUser", "10")
                .toString();
    }

    private String updateCreditJSON() throws JSONException {
        return new JSONObject()
                .put("howMuchToPay", "999999.95")
                .put("paid", "0.0")
                .put("percentRate", "2.15")
                .put("theTotalAmountYouPay", "11790.95")
                .put("countMonthsToPay", "100.0")
                .put("bankProfit", "1170.0")
                .put("howMuchIsTheLoan", "10000.0")
                .put("paymentPerMonth", "117.91")
                .put("idUser", "10")
                .put("idCredit", "3")
                .toString();
    }

}