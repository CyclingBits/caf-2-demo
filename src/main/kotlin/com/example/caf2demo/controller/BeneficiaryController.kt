package com.example.caf2demo.controller

import com.example.caf2demo.model.Beneficiary
import com.example.caf2demo.service.BeneficiaryService
import com.example.caf2demo.service.ContractorService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

@Controller
class BeneficiaryController(
    private val beneficiaryService: BeneficiaryService,
    private val contractorService: ContractorService,
) {
    @GetMapping("/beneficiaries")
    fun getAllBeneficiaries(model: Model): String {
        model.addAttribute("beneficiaryList", beneficiaryService.getAllBeneficiaries())
        return "beneficiary-table :: beneficiaryList"
    }

    @GetMapping("/contractor/{id}/beneficiaries")
    fun getContractorBeneficiaries(
        @PathVariable id: Long,
        model: Model,
    ): String {
        model.addAttribute("beneficiaryList", beneficiaryService.getBeneficiariesByContractorId(id))
        return "beneficiary-table :: beneficiaryList"
    }

    @PostMapping("/beneficiary/save")
    fun saveBeneficiary(
        @RequestParam contractorId: Long,
        @RequestParam name: String,
        @RequestParam nip: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<String> {
        val contractor =
            contractorService.getContractorById(contractorId)
                ?: return ResponseEntity.badRequest().body("Kontrahent nie istnieje")

        val beneficiary =
            Beneficiary(
                name = name,
                nip = nip,
                contractor = contractor,
                date = date,
            )

        beneficiaryService.saveBeneficiary(beneficiary)
        return ResponseEntity.ok("Beneficjent został dodany")
    }
}
