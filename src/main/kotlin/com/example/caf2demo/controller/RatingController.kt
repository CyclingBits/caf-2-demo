package com.example.caf2demo.controller

import com.example.caf2demo.service.RatingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class RatingController(private val ratingService: RatingService) {
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
}
