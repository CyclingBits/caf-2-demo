package com.example.caf2demo.service

import com.example.caf2demo.model.Limit
import com.example.caf2demo.repository.LimitRepository
import org.springframework.stereotype.Service

@Service
class LimitService(
    private val limitRepository: LimitRepository,
) {
    fun getAllLimits(): List<Limit> {
        return limitRepository.findAll()
    }

    fun getLimitById(id: Long): Limit? {
        return limitRepository.findById(id).orElse(null)
    }

    fun getLimitsByContractorId(contractorId: Long): List<Limit> {
        return limitRepository.findAllByContractorId(contractorId)
    }
}
