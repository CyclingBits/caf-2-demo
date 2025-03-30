package com.example.caf2demo.repository

import com.example.caf2demo.model.Document
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DocumentRepository : JpaRepository<Document, Long> {
    fun findByContractorId(contractorId: Long): List<Document>
}
