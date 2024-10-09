package gov.la.webserver.user.controllers;
import gov.la.webserver.user.dto.UserDTO;
import gov.la.webserver.user.dto.UserRegisterDTO;
import gov.la.webserver.user.entity.User;
import gov.la.webserver.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "User API", description = "User API")
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Operation(
            summary = "get All api user",
            description = "Query all users",
            security= @SecurityRequirement(name = "basicScheme")
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {
//        List<User> userList = userRepository.findAll();
        log.info("call getAllUsers");
        List<UserDTO> userList = userService.findAllUsers();
        return ResponseEntity.ok(userList);
    }

    @Operation(
            summary = "get user",
            description = "Query save users"
    )
    @Parameter(
            name = "id", description = "indentifier", example = "1", required = true
    )
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long id) {
        log.info("call getUsers >>> {}",id);
        UserDTO userDTO = userService.getDetail(id);
//        if (userDTO==null){
//            return ResponseEntity.notFound().build();
//
//        }
        return ResponseEntity.ok(userDTO);
    }

    @Operation(
            summary = "create user",
            description = "Register users"
    )
    @Parameters({
            @Parameter(name = "id", description = "indentifier", example = "1", required = true),
            @Parameter(name = "name", description = "user name", example = "javis", required = true),
            @Parameter(name = "age", description = "age user", example = "41", required = true),
            @Parameter(name = "nickName", description = "nickname", example = "SOLO", required = true),
            @Parameter(name = "userName", description = "userName", example = "SOLO", required = true),
            @Parameter(name = "password", description = "password", example = "SO1445LO", required = true)
    })
    @ApiResponse(
            responseCode = "200",
            description = "Success"
    )
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody final UserRegisterDTO user) {
        log.info("call createUsers >>> namne >> {} / age >> {} /nickname >>{}"
                ,user.getName(), user.getAge(), user.getNickname());
        UserDTO userDTO =userService.registerUser(user);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(
            summary = "update user",
            description = "update users"
    )
//    @Parameters({
//            @Parameter(name = "id", description = "identifier", example = "1", required = true),
//    })
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content={
                    @Content(mediaType = "application/json",schema = @Schema(implementation = User.class))}
    )


    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable final Long id, @RequestBody final UserDTO userDTO) {
        log.info("call UpdatedUsers >>>id >>> {}/ namne >> {} / age >> {} /nickname >>{}",id, userDTO.getName(), userDTO.getAge(), userDTO.getNickName());
       UserDTO resUserDTO = userService.updateUser(id, userDTO);
       if(resUserDTO ==null) {
           return ResponseEntity.notFound().build();
       }else {
           return ResponseEntity.ok(resUserDTO);
//
       }

    }

    @Operation(
            summary = "delete user",
            description = "delete users"
    )
//    @Parameter(
//            name = "id", description = "indentifier", example = "1", required = true
//    )
    @ApiResponse(
            responseCode = "200",
            description = "Success",
            content={
                    @Content(mediaType = "application/json",schema = @Schema(implementation = User.class))}
    )
    @Parameter(name = "id", description = "id", example = "1",required = true)
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable final Long id) {
        log.info("call DeleteUser >>> id >>> {}",id);
        Boolean isDeleted = userService.deleteUser(id);
        return ResponseEntity.ok(isDeleted);
    }


}


