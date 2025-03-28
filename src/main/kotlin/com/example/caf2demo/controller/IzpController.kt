package com.example.caf2demo.controller

import com.example.caf2demo.service.ContractorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IzpController(private val contractorService: ContractorService) {
    @GetMapping("/izp")
    fun getAllIzp(model: Model): String {
        model.addAttribute("izpList", contractorService.getAllIzp())
        return "izp-table :: izpList"
    }

    @GetMapping("/contractor/{id}/izp")
    fun getContractorIzp(
        @PathVariable id: Long,
        model: Model,
    ): String {
        model.addAttribute("izpList", contractorService.getIzpByContractorId(id))
        return "izp-table :: izpList"
    }
}
