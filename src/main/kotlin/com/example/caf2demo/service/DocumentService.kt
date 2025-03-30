package com.example.caf2demo.service

import com.example.caf2demo.model.Document
import com.example.caf2demo.repository.DocumentRepository
import org.springframework.stereotype.Service

@Service
class DocumentService(private val documentRepository: DocumentRepository) {
    fun getAllDocuments(): List<Document> {
        return documentRepository.findAll()
    }

    fun getDocumentsByContractorId(contractorId: Long): List<Document> {
        return documentRepository.findByContractorId(contractorId)
    }

    fun getDocumentById(id: Long): Document? {
        return documentRepository.findById(id).orElse(null)
    }

    fun saveDocument(document: Document): Document {
        return documentRepository.save(document)
    }
}
