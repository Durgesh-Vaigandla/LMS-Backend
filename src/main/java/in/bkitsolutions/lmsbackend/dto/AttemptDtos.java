package in.bkitsolutions.lmsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AttemptDtos {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StartAttemptRequest {
        // no body needed now; reserved for future
        private Boolean placeholder;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubmitAnswerRequest {
        @NotNull
        private Long questionId;
        // For MCQ: "A" or "A,B"; For FILL_BLANK: free text
        @NotBlank
        private String answerText;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubmitAttemptRequest {
        // no body yet
        private Boolean placeholder;
    }
}
