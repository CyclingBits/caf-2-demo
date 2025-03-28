package com.example.caf2demo.service.validator

import org.springframework.stereotype.Service

/**
 * Validator for Rating requirement.
 */
@Service
class RatingValidator : RequirementValidator {
    override fun validate(): Boolean {
        // Implementation for Rating validation
        return true
    }
}