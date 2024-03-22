package org.rail.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rail.model.User;

import java.util.List;

@Tag(name = "User endpoints")
public interface UserOpenApi {
    @Operation(summary = "Get All users from jsonPlaceholder.com")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}")))
    })
    List<User> getAllUsers();

    @Operation(summary = "Get All users from redis DB", description = "I used this just to test redis DB")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}")))
    })
    Iterable<User> getUsersFromDb();

    @Operation(summary = "POST a user to the redis DB", description = "I used this just to test redis DB")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"user saved\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
    })
    String saveUser(@Parameter(
            description = "Product object that needs to be saved",
            required = true) User user);

}
