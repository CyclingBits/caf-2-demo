package com.example.caf2demo.controller

import com.example.caf2demo.model.Rating
import com.example.caf2demo.service.ContractorService
import com.example.caf2demo.service.RatingService
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
class RatingController(
    private val ratingService: RatingService,
    private val contractorService: ContractorService,
) {
    @GetMapping("/ratings")
    fun getAllRatings(model: Model): String {
        model.addAttribute("ratingList", ratingService.getAllRatings())
        return "rating-table :: ratingList"
    }

    @GetMapping("/contractor/{id}/ratings")
    fun getContractorRatings(
        @PathVariable id: Long,
        model: Model,
    ): String {
        model.addAttribute("ratingList", ratingService.getRatingsByContractorId(id))
        return "rating-table :: ratingList"
    }

    @PostMapping("/rating/save")
    fun saveRating(
        @RequestParam contractorId: Long,
        @RequestParam points: Int,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate,
    ): ResponseEntity<String> {
        val contractor =
            contractorService.getContractorById(contractorId)
                ?: return ResponseEntity.badRequest().body("Kontrahent nie istnieje")

        val rating =
            Rating(
                points = points,
                contractor = contractor,
                date = date,
            )

        ratingService.saveRating(rating)
        return ResponseEntity.ok("Rating został dodany")
    }
}
