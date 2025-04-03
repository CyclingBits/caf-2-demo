package com.example.caf2demo.service

import com.example.caf2demo.model.Contractor
import com.example.caf2demo.repository.ContractorRepository
import org.springframework.stereotype.Service

@Service
class ContractorService(
    private val contractorRepository: ContractorRepository,
) {
    fun getAllContractors(): List<Contractor> {
        return contractorRepository.findAll()
    }

    fun searchContractors(searchTerm: String?): List<Contractor> {
        if (searchTerm.isNullOrBlank()) {
            return contractorRepository.findAll()
        }
        return contractorRepository.findByNipContainingOrNameContainingIgnoreCase(searchTerm, searchTerm)
    }

    fun getContractorById(id: Long): Contractor? {
        return contractorRepository.findById(id).orElse(null)
    }
}
