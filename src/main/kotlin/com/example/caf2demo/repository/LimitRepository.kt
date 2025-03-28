package com.example.caf2demo.repository

import com.example.caf2demo.model.Limit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LimitRepository : JpaRepository<Limit, Long> {
    fun findAllByContractorId(contractorId: Long): List<Limit>
}
