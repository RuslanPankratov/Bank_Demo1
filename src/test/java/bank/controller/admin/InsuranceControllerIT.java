package bank.controller.admin;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
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
public class InsuranceControllerIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DatabaseSetup("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    void shouldGetByIdInsurance() throws Exception {
        mockMvc.perform(get("/insurance/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.insurance.sumInsured").value("4000.0"))
                .andExpect(jsonPath("$.insurance.insurancePaid").value("100.0"))
                .andExpect(jsonPath("$.insurance.idInsurance").value("3"))
                .andExpect(jsonPath("$.insurance.idUser").value("3"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    void shouldFindAllInsurance() throws Exception {
        mockMvc.perform(get("/insurance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.insurances[3].sumInsured").value("2000.0"))
                .andExpect(jsonPath("$.insurances[3].insurancePaid").value("100.0"))
                .andExpect(jsonPath("$.insurances[3].idInsurance").value("5"))
                .andExpect(jsonPath("$.insurances[3].idUser").value("2"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    @DatabaseTearDown(value = "classpath:dbunit/admin/insurance/insurance-dataset.xml"
            , type = DatabaseOperation.DELETE_ALL)
    void shouldCreateInsurance() throws Exception {
        mockMvc.perform(post("/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createInsuranceJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.createdInsuranceId").value("1"));
    }

    @Test
    @DatabaseSetup("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    @DatabaseTearDown("classpath:dbunit/admin/insurance/insurance-dataset.xml")
    void shouldUpdateInsurance() throws Exception {
        mockMvc.perform(put("/insurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateInsuranceJSON()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sumInsured").value("5000.0"))
                .andExpect(jsonPath("$.insurancePaid").value("300.0"))
                .andExpect(jsonPath("$.idInsurance").value("2"))
                .andExpect(jsonPath("$.idUser").value("1"));
    }

    private String createInsuranceJSON() throws JSONException {
        return new JSONObject()
                .put("sumInsured", "5000.0")
                .put("insurancePaid", "300.0")
                .put("idUser", "10")
                .toString();
    }

    private String updateInsuranceJSON() throws JSONException {
        return new JSONObject()
                .put("sumInsured", "5000.0")
                .put("insurancePaid", "300.0")
                .put("idInsurance", "2")
                .put("idUser", "1")
                .toString();
    }

}
