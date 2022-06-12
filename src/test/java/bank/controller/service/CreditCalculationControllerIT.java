package bank.controller.service;

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
class CreditCalculationControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("classpath:dbunit/service/credit-calculate-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/service/credit-calculate-dataset.xml")
    void shouldCalculate() throws Exception {
        mockMvc.perform(put("/users/3/credits/operation=CALCULATE")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(calculationJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("30000"))
                .andExpect(jsonPath("transactionType").value("RECEIVING"))
                .andExpect(jsonPath("withWhomTheDeal").value("CREDIT"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("3"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/service/credit-calculate-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/service/credit-calculate-dataset.xml")
    void shouldPAY() throws Exception {
        mockMvc.perform(put("/users/4/credits/operation=PAY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("117.91"))
                .andExpect(jsonPath("transactionType").value("PAY"))
                .andExpect(jsonPath("withWhomTheDeal").value("CREDIT"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("4"));
    }


    private String calculationJSON() throws JSONException {
        return new JSONObject()
              //  .put("idUser", "3")
                .put("currentPercentUser", "10")
                .put("amountOfCredit", "30000")
                .put("numberOfMonthsOfLoan", "300")
                .toString();
    }

}