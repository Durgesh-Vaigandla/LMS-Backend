package in.bkitsolutions.lmsbackend.controller;

import in.bkitsolutions.lmsbackend.dto.ApiResponse;
import in.bkitsolutions.lmsbackend.dto.AttemptDtos;
import in.bkitsolutions.lmsbackend.model.Answer;
import in.bkitsolutions.lmsbackend.model.TestAttempt;
import in.bkitsolutions.lmsbackend.service.AttemptService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/tests/{testId}/attempts")
    public ResponseEntity<ApiResponse<TestAttempt>> startAttempt(Authentication auth,
                                                                 @PathVariable Long testId,
                                                                 @Valid @RequestBody(required = false) AttemptDtos.StartAttemptRequest req) {
        String email = (String) auth.getPrincipal();
        TestAttempt attempt = attemptService.startAttempt(email, testId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Attempt started", attempt));
    }

    @PostMapping("/attempts/{attemptId}/answers")
    public ResponseEntity<ApiResponse<Answer>> submitAnswer(Authentication auth,
                                                            @PathVariable Long attemptId,
                                                            @Valid @RequestBody AttemptDtos.SubmitAnswerRequest req) {
        String email = (String) auth.getPrincipal();
        Answer ans = attemptService.submitOrUpdateAnswer(email, attemptId, req.getQuestionId(), req.getAnswerText());
        return ResponseEntity.ok(ApiResponse.ok("Answer saved", ans));
    }

    @PostMapping("/attempts/{attemptId}/submit")
    public ResponseEntity<ApiResponse<TestAttempt>> submitAttempt(Authentication auth, @PathVariable Long attemptId) {
        String email = (String) auth.getPrincipal();
        TestAttempt ta = attemptService.submitAttempt(email, attemptId);
        return ResponseEntity.ok(ApiResponse.ok("Attempt submitted", ta));
    }

    @GetMapping("/attempts/{attemptId}")
    public ResponseEntity<ApiResponse<TestAttempt>> getAttempt(Authentication auth, @PathVariable Long attemptId) {
        String email = (String) auth.getPrincipal();
        TestAttempt ta = attemptService.getAttempt(email, attemptId);
        return ResponseEntity.ok(ApiResponse.ok("Attempt", ta));
    }
}
