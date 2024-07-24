package bg.softuni.Registry_GasInstallations.web;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bg.softuni.Registry_GasInstallations.model.entity.GasInstallationEntity;
import bg.softuni.Registry_GasInstallations.repository.GasInstallationRepository;
import com.jayway.jsonpath.JsonPath;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
//@WithMockUser(
//        username = "pesho@example.com",
//        roles = {"USER", "ADMIN"}
//)
public class GasInstallationControllerIT {

    @Autowired
    private GasInstallationRepository gasInstallationRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        gasInstallationRepository.deleteAll();
    }

    @Test
    public void testGetById() throws Exception {

        // Arrange
        var actualEntity = createTestGas();

        // ACT
        ResultActions result = mockMvc
                .perform(get("/gas/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(actualEntity.getId())))
                .andExpect(jsonPath("$.damtnNumber", is(actualEntity.getDamtnNumber())))
                .andExpect(jsonPath("$.damtnDate", is(actualEntity.getDamtnDate())))
                .andExpect(jsonPath("$.registrationData", is(actualEntity.getRegistrationData())))
                .andExpect(jsonPath("$.type", is(actualEntity.getType())))
                .andExpect(jsonPath("$.manufacturer", is(actualEntity.getManufacturer())))
                .andExpect(jsonPath("$.model", is(actualEntity.getModel())))
                .andExpect(jsonPath("$.pressure", is(actualEntity.getPressure())))
                .andExpect(jsonPath("$.power", is(actualEntity.getPower())))
                .andExpect(jsonPath("$.ownerId", is(actualEntity.getOwnerId())));
    }

    @Test
    public void testGasNotFound() throws Exception {
        mockMvc
                .perform(get("/gas/{id}", "1000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateGas() throws Exception {
        MvcResult result = mockMvc.perform(post("/gas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                  {
                   "damtnNumber": "1234444",
                   "damtnDate": "2024-05-01",
                   "registrationData": "2024-06-05",
                   "type": "ГИ на ПГ",
                   "manufacturer": "ЕМЕРТ",
                   "model": "EFT704L",
                   "pressure": "2",
                   "power": "2",
                   "ownerId": 1
                  }
                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        long id = JsonPath.read(body, "$.id");

        Optional<GasInstallationEntity> createdGasOpt = gasInstallationRepository.findById(id);

        Assertions.assertTrue(createdGasOpt.isPresent());

        GasInstallationEntity createdGas = createdGasOpt.get();

        Assertions.assertEquals("1234444", createdGas.getDamtnNumber());
        Assertions.assertEquals("2024-05-01", createdGas.getDamtnDate().toString());
        Assertions.assertEquals(LocalDate.parse("2024-06-05"), createdGas.getRegistrationData());
        Assertions.assertEquals("ГИ на ПГ", createdGas.getType());
        Assertions.assertEquals("ЕМЕРТ", createdGas.getManufacturer());
        Assertions.assertEquals("EFT704L", createdGas.getModel());
        Assertions.assertEquals("2", createdGas.getPressure());
        Assertions.assertEquals("2", createdGas.getPower());
        Assertions.assertEquals(Long.valueOf("1"), createdGas.getOwnerId());

    }

    @Test
    public void testDeleteGas() throws Exception {

        var actualEntity = createTestGas();

        mockMvc.perform(delete("/gas/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        Assertions.assertTrue(
                gasInstallationRepository.findById(actualEntity.getId()).isEmpty()
        );
    }

    private GasInstallationEntity createTestGas() {
        return gasInstallationRepository.save(
                new GasInstallationEntity()
                        .setDamtnNumber("1234444")
                        .setDamtnDate(LocalDate.parse("2024-05-01"))
                        .setRegistrationData(LocalDate.parse("2024-06-05"))
                        .setType("ГИ на ПГ")
                        .setManufacturer("ЕМЕРТ")
                        .setModel("EFT704L")
                        .setPressure("2")
                        .setPower("2")
                        .setOwnerId(1L)
        );
    }

}
