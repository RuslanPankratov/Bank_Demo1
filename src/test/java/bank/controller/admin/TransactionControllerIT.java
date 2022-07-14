package bank.controller.admin;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
@AutoConfigureMockMvc
@SpringBootTest
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class},
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldTransaction() throws Exception {
        mockMvc.perform(put("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transactionJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("amount").value("20000"))
                .andExpect(jsonPath("transactionType").value("PAY"))
                .andExpect(jsonPath("withWhomTheDeal").value("CREDIT"))
                .andExpect(jsonPath("transactionSuccess").value("SUCCESSFUL"))
                .andExpect(jsonPath("idUser").value("12"));
    }

    private String transactionJSON() throws JSONException {
        return new JSONObject()
                .put("amount", "20000")
                .put("transactionType", "PAY")
                .put("withWhomTheDeal", "CREDIT")
                .put("transactionSuccess", "SUCCESSFUL")
                .put("idUser", "12")
                .toString();
    }

}