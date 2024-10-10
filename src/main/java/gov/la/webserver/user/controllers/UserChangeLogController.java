package gov.la.webserver.user.controllers;

import gov.la.webserver.common.response.dto.ApiResponseDTO;
import gov.la.webserver.user.dto.UserChangeLogDTO;
import gov.la.webserver.user.service.UserChangeLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@Tag(name = "User API", description = "User API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/user-change-logs")
public class UserChangeLogController {
    private final UserChangeLogService userChangeLogService;


    @Operation(
            summary = "get All user change list",
            description = "Query saved user change in the form of a list",
            security= @SecurityRequirement(name = "basicScheme")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @Secured("ROLE_ADMIN")
    @GetMapping
    public Flux<ApiResponseDTO<Object>> getAllUserChangeLogs() {

        log.info("call getAllUsersChangelog");
        List<UserChangeLogDTO> userChangLogs = userChangeLogService.getAllUserChangeLog();
        return Flux.fromIterable(userChangLogs)
                .map(log -> ApiResponseDTO.builder()
                        .code(200)
                        .message("Find All User ChangeLog")
                        .body(log)
                        .build());
    }


}

