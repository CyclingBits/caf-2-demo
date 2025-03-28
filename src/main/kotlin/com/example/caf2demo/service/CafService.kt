package com.example.caf2demo.service

import com.example.caf2demo.model.Caf
import com.example.caf2demo.model.CafStatus
import com.example.caf2demo.model.CafType
import com.example.caf2demo.repository.CafRepository
import com.example.caf2demo.repository.LimitRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafService(private val cafRepository: CafRepository, private val limitRepository: LimitRepository) {
    fun getAllCafs(): List<Caf> {
        return cafRepository.findAll()
    }

    fun getCafById(id: Long): Caf? {
        return cafRepository.findById(id).orElse(null)
    }

    fun getCafsByLimitId(limitId: Long): List<Caf> {
        return cafRepository.findByLimitId(limitId)
    }

    @Transactional
    fun createCaf(
        limitId: Long,
        cafType: CafType,
    ): Caf? {
        val limit = limitRepository.findById(limitId).orElse(null) ?: return null
        
        // Check if there's already an active CAF for this limit
        val existingCaf = getActiveCafForLimit(limitId)
        if (existingCaf != null) {
            return existingCaf
        }
        
        val caf = Caf(limit = limit, type = cafType, status = CafStatus.IN_PROGRESS)
        return cafRepository.save(caf)
    }
    
    fun getActiveCafForLimit(limitId: Long): Caf? {
        return cafRepository.findByLimitId(limitId)
            .firstOrNull { it.status == CafStatus.IN_PROGRESS }
    }

    @Transactional
    fun updateCafStatus(
        id: Long,
        status: CafStatus,
    ): Caf? {
        val caf = cafRepository.findById(id).orElse(null) ?: return null
        val updatedCaf = caf.copy(status = status)
        return cafRepository.save(updatedCaf)
    }

    @Transactional
    fun deleteCaf(id: Long): Boolean {
        if (!cafRepository.existsById(id)) {
            return false
        }
        cafRepository.deleteById(id)
        return true
    }
}
