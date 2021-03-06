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
class MiscellaneousTransactionsControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DatabaseSetup("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    void shouldWithdrawal() throws Exception{
        mockMvc.perform(put("/creditCards/3/operation=WITHDRAWAL")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(withdrawalJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("300"))
                .andExpect(jsonPath("transactionType").value("WITHDRAWAL"))
                .andExpect(jsonPath("withWhomTheDeal").value("INSIDE"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("3"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    void shouldDeposit() throws Exception{
        mockMvc.perform(put("/creditCards/3/operation=DEPOSIT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(depositJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("3000"))
                .andExpect(jsonPath("transactionType").value("DEPOSIT"))
                .andExpect(jsonPath("withWhomTheDeal").value("INSIDE"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("3"));
    }


    @Test
    @DatabaseSetup("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/miscellaneous-transactions-dataset.xml")
    void shouldSender() throws Exception{
        mockMvc.perform(put("/userSender/3/usersRecipient/4/creditCards/operation=SENDER")
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

    private String depositJSON() throws JSONException {
        return new JSONObject()
                .put("amount","3000")
                .put("transactionType","DEPOSIT")
                .put("withWhomTheDeal","INSIDE")
                .put("transactionSuccess","SUCCESSFUL")
                .toString();
    }

    private String withdrawalJSON() throws JSONException {
        return new JSONObject()
                .put("amount","300")
                .put("transactionType","WITHDRAWAL")
                .put("withWhomTheDeal","INSIDE")
                .put("transactionSuccess","SUCCESSFUL")
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