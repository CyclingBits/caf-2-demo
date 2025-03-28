package com.example.caf2demo.repository

import com.example.caf2demo.model.Izp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Repository for IZP entity
 */
@Repository
interface IzpRepository : JpaRepository<Izp, Long> {
    fun findAllByContractorId(contractorId: Long): List<Izp>
}
