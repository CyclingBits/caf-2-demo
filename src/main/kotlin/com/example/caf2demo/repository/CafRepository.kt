package com.example.caf2demo.repository

import com.example.caf2demo.model.Caf
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CafRepository : JpaRepository<Caf, Long> {
    fun findByLimitId(limitId: Long): List<Caf>
}
