package com.example.caf2demo.service.validator

import org.springframework.stereotype.Service

/**
 * Validator for Approval requirement.
 */
@Service
class ApprovalValidator : RequirementValidator {
    override fun validate(): Boolean {
        // Implementation for Approval validation
        return true
    }
}