package gov.la.webserver.review.controller;

import gov.la.webserver.common.response.dto.ApiResponseDTO;
import gov.la.webserver.review.dto.ReviewDTO;
import gov.la.webserver.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Tag(name = "Review API", description = "Endpoints for managing reviews of films")
@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "Create a new review", description = "Allows users to create a new review for a film.")
    @ApiResponse(responseCode = "200", description = "Review successfully created")
    @PostMapping
    public Mono<ApiResponseDTO<Object>> createReview(
            @Parameter(description = "Review data transfer object containing review information", required = true)
            @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.createReview(reviewDTO);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Review Created")
                .body(savedReview)
                .build());
    }

    @Operation(summary = "Update an existing review", description = "Allows users to update an existing review by ID.")
    @ApiResponse(responseCode = "200", description = "Review successfully updated")
    @PutMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> updateReview(
            @Parameter(description = "ID of the review to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated review data transfer object", required = true)
            @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Review Updated")
                .body(updatedReview)
                .build());
    }

    @Operation(summary = "Delete a review", description = "Allows admins to delete a review by ID.", security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Review successfully deleted")
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> deleteReview(
            @Parameter(description = "ID of the review to delete", required = true)
            @PathVariable Long id) {
        reviewService.deleteReview(id);
        return Mono.just(ApiResponseDTO.builder()
                .code(200)
                .message("Review Deleted")
                .build());
    }

    @Operation(summary = "Get all reviews", description = "Fetches all available reviews for films.")
    @ApiResponse(responseCode = "200", description = "Reviews retrieved successfully")
    @GetMapping
    public Flux<ApiResponseDTO<Object>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.getAllReviews();
        return Flux.fromIterable(reviews)
                .map(review -> ApiResponseDTO.builder()
                        .code(200)
                        .message("Review List Found")
                        .body(review)
                        .build());
    }
}
