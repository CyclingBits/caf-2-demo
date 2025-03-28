package com.example.caf2demo.service.validator

import com.example.caf2demo.model.Caf
import org.springframework.stereotype.Service

/**
 * Validator for Analysis requirement.
 */
@Service
class AnalysisValidator : RequirementValidator {
    override fun validate(caf: Caf): Boolean {
        // Implementation for Analysis validation
        // Always returns true as this is a placeholder
        return false
    }
}
