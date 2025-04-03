package com.example.caf2demo.repository

import com.example.caf2demo.model.Beneficiary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeneficiaryRepository : JpaRepository<Beneficiary, Long> {
    fun findByContractorId(contractorId: Long): List<Beneficiary>
}
