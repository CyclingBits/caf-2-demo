package com.example.caf2demo.service

import com.example.caf2demo.model.Contractor
import com.example.caf2demo.model.Izp
import com.example.caf2demo.model.Limit
import com.example.caf2demo.repository.ContractorRepository
import com.example.caf2demo.repository.IzpRepository
import com.example.caf2demo.repository.LimitRepository
import org.springframework.stereotype.Service

@Service
class ContractorService(
    private val contractorRepository: ContractorRepository,
    private val limitRepository: LimitRepository,
    private val izpRepository: IzpRepository,
) {
    fun getAllContractors(): List<Contractor> {
        return contractorRepository.findAll()
    }

    fun getContractorById(id: Long): Contractor? {
        return contractorRepository.findById(id).orElse(null)
    }

    fun getLimitsByContractorId(contractorId: Long): List<Limit> {
        return limitRepository.findAllByContractorId(contractorId)
    }

    fun getIzpByContractorId(contractorId: Long): List<Izp> {
        return izpRepository.findAllByContractorId(contractorId)
    }

    fun getAllIzp(): List<Izp> {
        return izpRepository.findAll()
    }
}
