package com.example.caf2demo.service.validator

import com.example.caf2demo.model.Caf
import com.example.caf2demo.model.Contractor
import com.example.caf2demo.repository.RatingRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * Validator for Rating requirement.
 * Checks if the last Rating record is less than a year old.
 */
@Service
class RatingValidator(
    private val ratingRepository: RatingRepository,
) : RequirementValidator {
    override fun validate(caf: Caf): Boolean {
        // Get the contractor from the CAF
        val contractor = caf.limit.contractor
        // Check if the contractor has a recent Rating
        return hasRecentRating(contractor)
    }

    /**
     * Checks if a specific contractor has a Rating record less than a year old.
     */
    fun hasRecentRating(contractor: Contractor): Boolean {
        // Get the most recent Rating record for this contractor
        val latestRating =
            ratingRepository.findByContractorId(contractor.id)
                .maxByOrNull { it.date }
                ?: return false // No Rating records found for this contractor

        // Check if the Rating date is less than a year old
        val oneYearAgo = LocalDate.now().minusYears(1)
        return latestRating.date.isAfter(oneYearAgo)
    }
}
