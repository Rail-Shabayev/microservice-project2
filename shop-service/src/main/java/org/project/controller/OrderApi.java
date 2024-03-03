package org.project.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.project.dto.OrderDto;
import org.project.exception.OrderNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * This class is extended by {@link OrderController} to eliminate clutter in that class.
 * It provides api specification for {@link OrderController} class
 */
@Tag(name = "Order", description = "Api of Order")
public abstract class OrderApi {
    /**
     * provides api specification for GET method
     */
    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = OrderDto.class))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}")))
    })
    public abstract List<OrderDto> getOrders();

    /**
     * provides api specification for POST method
     */
    @Operation(summary = "Add a new product to the storehouse")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Product created",
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product saved\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
    })
    public abstract String postOrder(
            @Parameter(description = "Product object that needs to be saved", required = true)
            @RequestBody OrderDto productDTO) throws OrderNotFoundException;

    /**
     * provides api specification for PUT method
     */
    @Operation(summary = "Update a product in the storehouse")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Product created",
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product updated\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"id\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with id: laptop}")))
    })
    public abstract String putOrder(
            @Parameter(required = true, description = "Object to be updated")
            @RequestBody OrderDto productDTO,
            @Parameter(required = true, description = "Name of the object that needs to be updated")
            @PathVariable("id") Long id)
            throws OrderNotFoundException;

    /**
     * provides api specification for DELETE method
     */
    @Operation(summary = "Delete a product from the storehouse")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product deleted\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"id\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with id: laptop}"))),
    })
    public abstract String deleteOrder(
            @Parameter(required = true, description = "Name of the object that needs to be deleted")
            @PathVariable("id") Long id)
            throws OrderNotFoundException;
}
