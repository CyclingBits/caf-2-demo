package com.example.caf2demo.service.validator

import com.example.caf2demo.model.Caf

/**
 * Interface defining a validator for requirement processing.
 */
interface RequirementValidator {
    /**
     * Validates the requirement based on the CAF data.
     *
     * @param caf The CAF to validate against
     * @return true if the requirement is fulfilled, false otherwise
     */
    fun validate(caf: Caf): Boolean
}
