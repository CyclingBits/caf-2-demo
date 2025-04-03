package com.example.caf2demo.controller

import com.example.caf2demo.service.IzpService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IzpController(private val izpService: IzpService) {
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
}
