package org.richard.vacancy_management.modules.candidate.use_cases;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.richard.vacancy_management.exceptions.CandidateNotFoundException;
import org.richard.vacancy_management.exceptions.JobNotFoundException;
import org.richard.vacancy_management.modules.candidate.CandidateRepository;
import org.richard.vacancy_management.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;
  
  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void should_not_be_able_to_apply_job_with_candidate_not_found() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception exception) {
      assertThat(exception).isInstanceOf(CandidateNotFoundException.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  public void should_not_be_able_to_apply_job_with_job_not_found() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception exception) {
      assertThat(exception).isInstanceOf(JobNotFoundException.class);
    }
  }
}
