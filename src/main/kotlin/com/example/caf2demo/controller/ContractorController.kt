package com.example.caf2demo.controller

import com.example.caf2demo.service.ContractorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class ContractorController(private val contractorService: ContractorService) {
    
    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("contractors", contractorService.getAllContractors())
        return "index"
    }
    
    @GetMapping("/contractors")
    fun getContractors(model: Model): String {
        model.addAttribute("contractors", contractorService.getAllContractors())
        return "contractor-table :: contractorsList"
    }
}