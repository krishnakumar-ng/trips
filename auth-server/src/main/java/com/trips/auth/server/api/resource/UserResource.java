package com.trips.auth.server.api.resource;

import com.trips.auth.server.constants.ApiConstants;
import com.trips.auth.server.data.models.UserResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.trips.auth.server.constants.ApiConstants.AUTHORIZATION;

@Tag(name = "user-controller-resource")
@RequestMapping("/user")
public interface UserResource {
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Get User Details")
    @GetMapping("/{username}")
    public ResponseEntity<UserResponseModel> getUserDetails(@RequestHeader("Authorization") String token,
                                                            @PathVariable("username") String username);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "PATCH", summary = "Update User Password")
    @PatchMapping("/{username}/update/password")
    public ResponseEntity<UserResponseModel> updateUserPassword(@RequestHeader(AUTHORIZATION) String token,
                                                               @PathVariable("username") String username,
                                                               @RequestParam("password") String password);

    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = ApiConstants.MESSAGE_SUCCESS),
                    @ApiResponse(responseCode = "400", description = ApiConstants.MESSAGE_BAD_REQUEST),
                    @ApiResponse(responseCode = "500", description = ApiConstants.MESSAGE_INTERNAL_SERVER_ERROR),
            }
    )
    @Operation(method = "POST", summary = "Update User Roles")
    @PatchMapping("/{username}/update/roles")
    public ResponseEntity<UserResponseModel> updateUserRoles(@RequestHeader("Authorization") String token,
                                                            @PathVariable("username") String username,
                                                            @RequestParam("roles") String roles);

}
