package com.example.caf2demo.service.validator

import com.example.caf2demo.model.Caf
import org.springframework.stereotype.Service

/**
 * Validator for Cause requirement.
 */
@Service
class CauseValidator : RequirementValidator {
    override fun validate(caf: Caf): Boolean {
        // Implementation for Cause validation
        // Always returns true as this is a placeholder
        return false
    }
}
