package gov.la.webserver.review.controller;

import gov.la.webserver.common.response.dto.ApiResponseDTO;
import gov.la.webserver.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import gov.la.webserver.review.dto.ReviewDTO;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Mono<ApiResponseDTO<Object>> createReview(@RequestBody ReviewDTO reviewDTO) {
        ReviewDTO saveReview= reviewService.createReview(reviewDTO);
                return Mono.just(ApiResponseDTO.builder()
                        .code(200)
                        .message("Review Created")
                        .body(saveReview)
                        .build());
    }

    @PutMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO saveReview = reviewService.updateReview(id, reviewDTO);
        return Mono.just(ApiResponseDTO.builder()
                        .code(200)
                        .message("Review Updated")
                        .body(saveReview)
                        .build());
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public Mono<ApiResponseDTO<Object>> deleteReview(@PathVariable Long id) {
         reviewService.deleteReview(id);
        return Mono.just(ApiResponseDTO.builder()
                        .code(200)
                        .message("Review Deleted")
                        .build());
    }

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
