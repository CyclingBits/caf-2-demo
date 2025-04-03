package com.example.caf2demo.repository

import com.example.caf2demo.model.Contractor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for Contractor entity
 */
@Repository
interface ContractorRepository : JpaRepository<Contractor, Long> {
    fun findByNipContainingOrNameContainingIgnoreCase(nip: String, name: String): List<Contractor>
}
