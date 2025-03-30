package com.example.caf2demo.controller

import com.example.caf2demo.service.DocumentService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DocumentController(private val documentService: DocumentService) {
    @GetMapping("/contractor/{id}/documents")
    fun getContractorDocuments(
        @PathVariable id: Long,
        model: Model,
    ): String {
        model.addAttribute("documentList", documentService.getDocumentsByContractorId(id))
        return "document-table :: documentList"
    }

    @GetMapping("/documents/{id}")
    fun downloadDocument(
        @PathVariable id: Long,
    ): ResponseEntity<ByteArray> {
        val document = documentService.getDocumentById(id) ?: return ResponseEntity.notFound().build()

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM
        headers.setContentDispositionFormData("attachment", "document_${document.id}")

        return ResponseEntity(document.file, headers, HttpStatus.OK)
    }
}
