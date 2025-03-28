package com.example.caf2demo.service.validator

/**
 * Interface defining a validator for requirement processing.
 */
interface RequirementValidator {
    /**
     * Validates the requirement.
     * 
     * @return true if the requirement is fulfilled, false otherwise
     */
    fun validate(): Boolean
}