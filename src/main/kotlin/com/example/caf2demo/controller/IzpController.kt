package com.example.caf2demo.controller

import com.example.caf2demo.model.Izp
import com.example.caf2demo.service.ContractorService
import com.example.caf2demo.service.IzpService
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
class IzpController(
    private val izpService: IzpService,
    private val contractorService: ContractorService,
) {
    @GetMapping("/izp")
    fun getAllIzp(model: Model): String {
        model.addAttribute("izpList", izpService.getAllIzp())
        return "izp-table :: izpList"
    }

    @GetMapping("/contractor/{id}/izp")
    fun getContractorIzp(
        @PathVariable id: Long,
        model: Model,
    ): String {
        model.addAttribute("izpList", izpService.getIzpByContractorId(id))
        return "izp-table :: izpList"
    }

    @PostMapping("/izp/save")
    fun saveIzp(
        @RequestParam contractorId: Long,
        @RequestParam points: Int,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<String> {
        val contractor =
            contractorService.getContractorById(contractorId)
                ?: return ResponseEntity.badRequest().body("Kontrahent nie istnieje")

        val izp =
            Izp(
                points = points,
                contractor = contractor,
                date = date,
            )

        izpService.saveIzp(izp)
        return ResponseEntity.ok("IZP został dodany")
    }
}
