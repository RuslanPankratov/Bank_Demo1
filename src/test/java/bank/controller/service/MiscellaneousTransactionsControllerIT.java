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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class}
        , mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class MiscellaneousTransactionsControllerIT {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DatabaseSetup("classpath:dbunit/service/miscellaneous-transactions-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/service/miscellaneous-transactions-dataset.xml")
    void shouldCalculate() throws Exception{
        mockMvc.perform(put("/withdrawOrDeposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(withdrawOrDepositJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("300"))
                .andExpect(jsonPath("transactionType").value("WITHDRAWAL"))
                .andExpect(jsonPath("betweenWhomTheTransaction").value("INSIDE"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("3"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/service/miscellaneous-transactions-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/service/miscellaneous-transactions-dataset.xml")
    void shouldSender() throws Exception{
        mockMvc.perform(put("/sender")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(senderJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responses[0].amount").value("1000"))
                .andExpect(jsonPath("$.responses[0].idUser").value("3"))
                .andExpect(jsonPath("$.responses[0].transactionType").value("SENDING"))
                .andExpect(jsonPath("$.responses[0].transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("$.responses[1].amount").value("1000"))
                .andExpect(jsonPath("$.responses[1].idUser").value("4"))
                .andExpect(jsonPath("$.responses[1].transactionType").value("RECEIVING"))
                .andExpect(jsonPath("$.responses[1].transactionSuccess").value("SUCCESSFUL"));
    }

    private String withdrawOrDepositJSON() throws JSONException {
        return new JSONObject()
                .put("amount","300")
                .put("transactionType","WITHDRAWAL")
                .put("betweenWhomTheTransaction","INSIDE")
                .put("transactionSuccess","SUCCESSFUL")
                .put("idUser","3")
                .toString();
    }

    private String senderJSON() throws JSONException {
        return new JSONObject()
                .put("senderUserId","3")
                .put("recipientUserId","4")
                .put("departureAmount","1000")
                .toString();
    }

}