package bank.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class}
        , mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class InsuranceCalculatorControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("classpath:dbunit/insurance-calculator-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/insurance-calculator-dataset.xml")
    void shouldCalculate() throws Exception {
        mockMvc.perform(put("/users/3/insurances/operation=CALCULATE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(insuranceCalculateJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("30000"))
                .andExpect(jsonPath("transactionType").value("RECEIVING"))
                .andExpect(jsonPath("withWhomTheDeal").value("INSURANCE"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("3"));
    }


    @Test
    @DatabaseSetup("classpath:dbunit/insurance-calculator-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/insurance-calculator-dataset.xml")
    void shouldPay() throws Exception {
        mockMvc.perform(put("/users/4/insurance/4/operation=PAY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("900.0"))
                .andExpect(jsonPath("transactionType").value("PAY"))
                .andExpect(jsonPath("withWhomTheDeal").value("INSURANCE"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("4"));
    }

    private String insuranceCalculateJSON() throws JSONException {
        return new JSONObject()
               // .put("idUser", "3")
                .put("sum", "30000")
                .put("typeInsurance", "ITEMS")
                .toString();
    }

}