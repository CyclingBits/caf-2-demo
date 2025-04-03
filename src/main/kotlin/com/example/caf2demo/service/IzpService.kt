package com.example.caf2demo.service

import com.example.caf2demo.model.Izp
import com.example.caf2demo.repository.IzpRepository
import org.springframework.stereotype.Service

@Service
class IzpService(
    private val izpRepository: IzpRepository,
) {
    fun getIzpByContractorId(contractorId: Long): List<Izp> {
        return izpRepository.findAllByContractorId(contractorId)
    }

    fun getAllIzp(): List<Izp> {
        return izpRepository.findAll()
    }
}
