package com.example.caf2demo.service

import com.example.caf2demo.model.Rating
import com.example.caf2demo.repository.RatingRepository
import org.springframework.stereotype.Service

@Service
class RatingService(
    private val ratingRepository: RatingRepository,
) {
    fun getAllRatings(): List<Rating> = ratingRepository.findAll()

    fun getRatingsByContractorId(contractorId: Long): List<Rating> {
        return ratingRepository.findByContractorId(contractorId)
    }

    fun saveRating(rating: Rating): Rating {
        return ratingRepository.save(rating)
    }
}
