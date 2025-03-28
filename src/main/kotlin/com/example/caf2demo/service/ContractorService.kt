package com.example.caf2demo.service

import com.example.caf2demo.model.Contractor
import com.example.caf2demo.repository.ContractorRepository
import org.springframework.stereotype.Service

@Service
class ContractorService(private val contractorRepository: ContractorRepository) {
    
    fun getAllContractors(): List<Contractor> {
        return contractorRepository.findAll()
    }
}