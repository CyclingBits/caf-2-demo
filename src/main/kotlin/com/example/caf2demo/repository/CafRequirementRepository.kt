package com.example.caf2demo.repository

import com.example.caf2demo.model.CafRequirement
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository interface for CafRequirement entity.
 */
@Repository
interface CafRequirementRepository : JpaRepository<CafRequirement, Long> {
    /**
     * Find all requirements for a specific CAF.
     */
    fun findByCafId(cafId: Long): List<CafRequirement>
}
