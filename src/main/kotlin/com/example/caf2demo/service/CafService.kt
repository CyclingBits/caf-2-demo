package com.example.caf2demo.service

import com.example.caf2demo.model.Caf
import com.example.caf2demo.model.CafRequirement
import com.example.caf2demo.model.CafStatus
import com.example.caf2demo.model.CafType
import com.example.caf2demo.model.CafTypeRequirement
import com.example.caf2demo.model.Requirement
import com.example.caf2demo.repository.CafRepository
import com.example.caf2demo.repository.CafRequirementRepository
import com.example.caf2demo.repository.LimitRepository
import com.example.caf2demo.service.validator.RequirementValidator
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafService(
    private val cafRepository: CafRepository,
    private val limitRepository: LimitRepository,
    private val cafRequirementRepository: CafRequirementRepository,
    private val applicationContext: ApplicationContext,
) {
    fun getAllCafs(): List<Caf> {
        return cafRepository.findAll()
    }

    fun getCafById(id: Long): Caf? {
        return cafRepository.findById(id).orElse(null)
    }

    fun getCafsByLimitId(limitId: Long): List<Caf> {
        return cafRepository.findByLimitId(limitId)
    }

    @Transactional
    fun createCaf(
        limitId: Long,
        cafType: CafType,
    ): Caf? {
        val limit = limitRepository.findById(limitId).orElse(null) ?: return null

        // Check if there's already an active CAF for this limit
        val existingCaf = getActiveCafForLimit(limitId)
        if (existingCaf != null) {
            return existingCaf
        }

        val caf = Caf(limit = limit, type = cafType, status = CafStatus.IN_PROGRESS)
        val savedCaf = cafRepository.save(caf)

        // Generate requirements based on CAF type
        generateRequirements(savedCaf)

        return savedCaf
    }

    /**
     * Generates requirements for a CAF based on its type and validates them.
     */
    @Transactional
    fun generateRequirements(caf: Caf) {
        // Get requirements for this CAF type from the mapping
        val requirements = CafTypeRequirement.getRequirementsForCafType(caf.type)

        // Create and save requirement entities with validation results
        requirements.forEach { requirement ->
            val validator = getValidatorForRequirement(requirement)
            val isFulfilled = validator.validate()

            val cafRequirement =
                CafRequirement(
                    caf = caf,
                    requirement = requirement,
                    fulfilled = isFulfilled,
                )
            cafRequirementRepository.save(cafRequirement)
        }
    }

    /**
     * Gets the appropriate validator for a requirement.
     */
    private fun getValidatorForRequirement(requirement: Requirement): RequirementValidator {
        // Get validator instance from Spring context based on the class defined in the Requirement enum
        return applicationContext.getBean(requirement.validatorClass.java)
    }

    /**
     * Gets all requirements for a specific CAF.
     */
    fun getRequirementsForCaf(cafId: Long): List<CafRequirement> {
        return cafRequirementRepository.findByCafId(cafId)
    }

    fun getActiveCafForLimit(limitId: Long): Caf? {
        return cafRepository.findByLimitId(limitId)
            .firstOrNull { it.status == CafStatus.IN_PROGRESS }
    }

    @Transactional
    fun updateCafStatus(
        id: Long,
        status: CafStatus,
    ): Caf? {
        val caf = cafRepository.findById(id).orElse(null) ?: return null
        val updatedCaf = caf.copy(status = status)
        return cafRepository.save(updatedCaf)
    }

    @Transactional
    fun deleteCaf(id: Long): Boolean {
        if (!cafRepository.existsById(id)) {
            return false
        }

        // Delete all requirements associated with this CAF first
        val requirements = cafRequirementRepository.findByCafId(id)
        cafRequirementRepository.deleteAll(requirements)

        // Then delete the CAF itself
        cafRepository.deleteById(id)
        return true
    }
}
