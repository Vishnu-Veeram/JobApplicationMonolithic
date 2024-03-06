package com.vicky.microservices.review.impl;

import com.vicky.microservices.company.Company;
import com.vicky.microservices.company.CompanyService;
import com.vicky.microservices.review.Review;
import com.vicky.microservices.review.ReviewRepository;
import com.vicky.microservices.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.findCompanyByID(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            if (companyService.findCompanyByID(companyId) != null && reviewRepository.existsById(reviewId)) {
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                company.getReviews().remove(review);
                companyService.updateCompanyById(companyId, company);
                reviewRepository.deleteById(reviewId);
                return true;
            }
            return false;
        }catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if(companyService.findCompanyByID(companyId) != null){
            Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
            if (reviewOptional.isPresent()) {
                Review review = reviewOptional.get();
                review.setTitle(updatedReview.getTitle());
                review.setRating(updatedReview.getRating());
                review.setDescription(updatedReview.getDescription());
                review.setCompany(companyService.findCompanyByID(companyId));
                reviewRepository.save(review);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
