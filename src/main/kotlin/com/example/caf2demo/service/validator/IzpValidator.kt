package com.example.caf2demo.service.validator

import org.springframework.stereotype.Service

/**
 * Validator for IZP requirement.
 */
@Service
class IzpValidator : RequirementValidator {
    override fun validate(): Boolean {
        // Implementation for IZP validation
        return true
    }
}
