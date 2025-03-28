package com.example.caf2demo.service.validator

import com.example.caf2demo.model.Caf
import com.example.caf2demo.model.Contractor
import com.example.caf2demo.repository.IzpRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

/**
 * Validator for IZP requirement.
 * Checks if the last IZP record is less than a year old.
 */
@Service
class IzpValidator(
    private val izpRepository: IzpRepository,
) : RequirementValidator {
    override fun validate(caf: Caf): Boolean {
        // Get the contractor from the CAF
        val contractor = caf.limit.contractor
        // Check if the contractor has a recent IZP
        return hasRecentIzp(contractor)
    }

    /**
     * Checks if a specific contractor has an IZP record less than a year old.
     */
    fun hasRecentIzp(contractor: Contractor): Boolean {
        // Get the most recent IZP record for this contractor
        val latestIzp =
            izpRepository.findAllByContractorId(contractor.id)
                .maxByOrNull { it.date }
                ?: return false // No IZP records found for this contractor

        // Check if the IZP date is less than a year old
        val oneYearAgo = LocalDate.now().minusYears(1)
        return latestIzp.date.isAfter(oneYearAgo)
    }
}
