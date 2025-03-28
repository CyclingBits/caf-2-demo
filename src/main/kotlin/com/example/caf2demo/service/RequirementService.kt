package com.example.caf2demo.service

import com.example.caf2demo.model.Caf
import com.example.caf2demo.model.Requirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

/**
 * Service for handling requirements validation.
 */
@Service
class RequirementService(
    @Autowired
    private val applicationContext: ApplicationContext,
) {
    /**
     * Validates a specific requirement.
     *
     * @param requirement the requirement to validate
     * @param caf the CAF to validate against
     * @return true if the requirement is fulfilled, false otherwise
     */
    fun validate(
        requirement: Requirement,
        caf: Caf,
    ): Boolean {
        val validator = applicationContext.getBean(requirement.validatorClass.java)
        return validator.validate(caf)
    }

    /**
     * Gets all available requirements.
     *
     * @return list of all requirements
     */
    fun getAllRequirements(): List<Requirement> {
        return Requirement.values().toList()
    }
}
