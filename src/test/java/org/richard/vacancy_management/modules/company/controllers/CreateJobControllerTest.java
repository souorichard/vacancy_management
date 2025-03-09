package org.richard.vacancy_management.modules.company.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.richard.vacancy_management.modules.company.dto.CreateJobDTO;
import org.richard.vacancy_management.modules.company.entities.CompanyEntity;
import org.richard.vacancy_management.modules.company.repositories.CompanyRepository;
import org.richard.vacancy_management.utils.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

  private MockMvc mvc;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private CompanyRepository companyRepository;

  @Before
  public void setup() {
    mvc = MockMvcBuilders.webAppContextSetup(this.context)
      .apply(SecurityMockMvcConfigurers.springSecurity())
      .build();
  }
  
  @Test
  @DisplayName("Should be able to create a new job")
  public void should_be_able_to_create_a_new_job() throws Exception {
    var company = CompanyEntity.builder()
      .name("Company Test")
      .username("company_test")
      .email("company@acme.com")
      .password("admin@1234")
      .description("Description Test")
      .build();

    company = companyRepository.saveAndFlush(company);

    var job = CreateJobDTO.builder()
      .description("Description Test")
      .benefits("Benefits Test")
      .level("Level Test")
      .build();

    var result = mvc.perform(MockMvcRequestBuilders.post("/company/job")
      .contentType(MediaType.APPLICATION_JSON)
      .content(TestUtils.objectToJSON(job))
      .header("Authorization", company.getId()))
      .andExpect(MockMvcResultMatchers.status().isOk());

    System.out.println(result);
  }
}
