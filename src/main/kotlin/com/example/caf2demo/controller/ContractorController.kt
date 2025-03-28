package com.example.caf2demo.controller
import com.example.caf2demo.model.CafStatus
import com.example.caf2demo.service.CafService
import com.example.caf2demo.service.ContractorService
import com.example.caf2demo.service.LimitService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class ContractorController(
    private val contractorService: ContractorService, 
    private val limitService: LimitService,
    private val cafService: CafService
) {
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

    @GetMapping("/contractor/{id}")
    fun getContractorDetails(
        @PathVariable id: Long,
        model: Model,
    ): String {
        val contractor = contractorService.getContractorById(id)
        if (contractor != null) {
            model.addAttribute("contractor", contractor)
            model.addAttribute("limits", limitService.getLimitsByContractorId(id))
            return "contractor-details"
        }
        return "redirect:/"
    }

    @GetMapping("/contractor/{id}/limits")
    fun getContractorLimits(
        @PathVariable id: Long,
        model: Model,
    ): String {
        val limits = limitService.getLimitsByContractorId(id)
        
        // Get active CAFs for limits and create a map by limit ID
        val activeCafs = limits.associateBy(
            { it.id!! },
            { cafService.getActiveCafForLimit(it.id!!) }
        ).filterValues { it != null }
        
        model.addAttribute("limits", limits)
        model.addAttribute("activeCafs", activeCafs)
        return "limit-table :: limitsList"
    }
}
