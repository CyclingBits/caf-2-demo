package com.example.caf2demo.controller

import com.example.caf2demo.model.Document
import com.example.caf2demo.model.DocumentType
import com.example.caf2demo.service.ContractorService
import com.example.caf2demo.service.DocumentService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import java.time.LocalDate

@Controller
class DocumentController(
    private val documentService: DocumentService,
    private val contractorService: ContractorService,
) {
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

    @PostMapping("/documents/save")
    fun saveDocument(
        @RequestParam contractorId: Long,
        @RequestParam type: String,
        @RequestParam file: MultipartFile,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<String> {
        if (file.isEmpty) {
            return ResponseEntity.badRequest().body("Plik jest wymagany")
        }

        val contractor =
            contractorService.getContractorById(contractorId)
                ?: return ResponseEntity.badRequest().body("Kontrahent nie istnieje")

        val document =
            Document(
                contractor = contractor,
                type = DocumentType.valueOf(type),
                date = date,
                file = file.bytes,
            )

        documentService.saveDocument(document)
        return ResponseEntity.ok("Dokument został dodany")
    }
}
