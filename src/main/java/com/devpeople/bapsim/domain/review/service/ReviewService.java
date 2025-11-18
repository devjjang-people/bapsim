package com.devpeople.bapsim.domain.review.service;

import com.devpeople.bapsim.domain.review.entity.Review;
import com.devpeople.bapsim.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    /**
     * 리뷰 저장 (작성)
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * 내 리뷰 단건 조회
     */
    public Review getMyReview(Long userId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        if (!review.getUserId().equals(userId)) {
            throw new IllegalArgumentException("본인의 리뷰만 조회 할 수 있습니다.");
        }

        return review;
    }

    /**
     * 내 리뷰 목록 조회
     */
    public List<Review> getMyReviews(Long userId) {
        return  reviewRepository.findByUserId(userId);
    }

    /**
     * 내 리뷰 수정
     */
    public Review updateMyReview(Long userId, Long reviewId, String content, Integer rating, String images) {
        Review review = getMyReview(userId, reviewId);

        review.setIsEdited(true);
        review.setContent(content);
        review.setRating(rating);
        review.setImages(images);

        return reviewRepository.save(review);
    }

    /**
     * 내 리뷰 삭제
     */
    public void deleteMyReview(Long userId, Long reviewId) {
        Review review = getMyReview(userId, reviewId);
        reviewRepository.delete(review);
    }


}
