package com.example.caf2demo.controller

import com.example.caf2demo.model.CafType
import com.example.caf2demo.service.CafService
import com.example.caf2demo.service.IzpService
import com.example.caf2demo.service.LimitService
import com.example.caf2demo.service.RatingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class CafController(
    private val cafService: CafService,
    private val limitService: LimitService,
    private val ratingService: RatingService,
    private val izpService: IzpService,
) {
    @PostMapping("/limit/{limitId}/caf/increase")
    fun createIncreaseCaf(
        @PathVariable limitId: Long,
        model: Model,
    ): String {
        val caf = cafService.createCaf(limitId, CafType.INCREASE)
        return if (caf != null) {
            "redirect:/caf/" + caf.id
        } else {
            "redirect:/"
        }
    }

    @PostMapping("/limit/{limitId}/caf/extension")
    fun createExtensionCaf(
        @PathVariable limitId: Long,
        model: Model,
    ): String {
        val caf = cafService.createCaf(limitId, CafType.EXTENSION)
        return if (caf != null) {
            "redirect:/caf/" + caf.id
        } else {
            "redirect:/"
        }
    }

    @PostMapping("/limit/{limitId}/caf/suspension")
    fun createSuspensionCaf(
        @PathVariable limitId: Long,
        model: Model,
    ): String {
        val caf = cafService.createCaf(limitId, CafType.SUSPENSION)
        return if (caf != null) {
            "redirect:/caf/" + caf.id
        } else {
            "redirect:/"
        }
    }

    @PostMapping("/limit/{limitId}/caf/resume")
    fun createResumeCaf(
        @PathVariable limitId: Long,
        model: Model,
    ): String {
        val caf = cafService.createCaf(limitId, CafType.RESUME)
        return if (caf != null) {
            "redirect:/caf/" + caf.id
        } else {
            "redirect:/"
        }
    }

    @GetMapping("/caf/{id}")
    fun getCafDetails(
        @PathVariable id: Long,
        model: Model,
    ): String {
        val caf = cafService.getCafById(id)
        if (caf != null) {
            // Get the requirements list for this CAF
            val requirements = cafService.getRequirementsForCaf(caf.id!!)

            // Get the contractor ID
            val contractorId = caf.limit.contractor.id

            // Get latest rating for the contractor
            val ratings = ratingService.getRatingsByContractorId(contractorId)
            val latestRating = ratings.maxByOrNull { it.date }

            // Get latest IZP for the contractor
            val izps = izpService.getIzpByContractorId(contractorId)
            val latestIzp = izps.maxByOrNull { it.date }

            model.addAttribute("caf", caf)
            model.addAttribute("requirements", requirements)
            model.addAttribute("latestRating", latestRating)
            model.addAttribute("latestIzp", latestIzp)

            return "caf-edit"
        }
        return "redirect:/"
    }

    @PostMapping("/caf/{id}/delete")
    fun deleteCaf(
        @PathVariable id: Long,
    ): String {
        val caf = cafService.getCafById(id)
        val contractorId = caf?.limit?.contractor?.id
        if (contractorId != null) {
            cafService.deleteCaf(id)
            return "redirect:/contractor/$contractorId"
        }
        return "redirect:/"
    }
}
