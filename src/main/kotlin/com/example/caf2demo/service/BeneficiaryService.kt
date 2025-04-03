package com.example.caf2demo.service

import com.example.caf2demo.model.Beneficiary
import com.example.caf2demo.repository.BeneficiaryRepository
import org.springframework.stereotype.Service

@Service
class BeneficiaryService(
    private val beneficiaryRepository: BeneficiaryRepository,
) {
    fun getAllBeneficiaries(): List<Beneficiary> = beneficiaryRepository.findAll()

    fun getBeneficiariesByContractorId(contractorId: Long): List<Beneficiary> {
        return beneficiaryRepository.findByContractorId(contractorId)
    }

    fun saveBeneficiary(beneficiary: Beneficiary): Beneficiary {
        return beneficiaryRepository.save(beneficiary)
    }
}
