package org.rail.project.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.rail.project.dto.ProductDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Product", description = "Api of Product")
public interface ProductControllerApi {
    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}")))
    })
    List<ProductDto> getAllProducts();

    @Operation(summary = "GET product by id")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product deleted\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with name: laptop}"))),
    })
    ProductDto getProductById(@Parameter(required = true, description = "Name of the object that needs to be deleted")
                              @PathVariable("id") Long id);

    @Operation(summary = "Add a new product to factory")
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
    String postProduct(@Parameter(description = "Product object that needs to be saved", required = true)
                       @RequestBody ProductDto productDTO);

    @Operation(summary = "Update a product in factory")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Product created",
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product updated\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with name: laptop}")))
    })
    String putProduct(@Parameter(required = true, description = "Object to be updated")
                      @RequestBody ProductDto productDto,
                      @Parameter(required = true, description = "Name of the object that needs to be updated")
                      @PathVariable("id") Long id);

    @Operation(summary = "PATCH a product in factory")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Product created",
                    responseCode = "201",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product updated\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with name: laptop}")))
    })
    String patchProduct(@Parameter(required = true, description = "Object to be updated")
                        @RequestBody ProductDto productDto,
                        @Parameter(required = true, description = "Name of the object that needs to be updated")
                        @PathVariable("id") Long id);

    @Operation(summary = "Delete a product from factory")
    @ApiResponses(value = {
            @ApiResponse(
                    description = "Successful operation",
                    responseCode = "200",
                    content = @Content(
                            mediaType = "application/json", schema = @Schema(example = "{\"product deleted\"}"))),
            @ApiResponse(
                    description = "Bad request",
                    responseCode = "400",
                    content = @Content(schema = @Schema(example = "{\"name\":\"string\"}"))),
            @ApiResponse(
                    description = "Product not found",
                    responseCode = "404",
                    content = @Content(schema = @Schema(example = "{Product not found with name: laptop}"))),
    })
    String deleteProduct(@Parameter(required = true, description = "Name of the object that needs to be deleted")
                         @PathVariable("id") Long id);
}
