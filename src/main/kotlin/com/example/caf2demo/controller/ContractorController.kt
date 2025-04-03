package com.example.caf2demo.controller
import com.example.caf2demo.model.CafStatus
import com.example.caf2demo.model.CafType
import com.example.caf2demo.service.CafService
import com.example.caf2demo.service.ContractorService
import com.example.caf2demo.service.LimitService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.math.BigDecimal
import java.time.LocalDate

@Controller
class ContractorController(
    private val contractorService: ContractorService,
    private val limitService: LimitService,
    private val cafService: CafService,
) {
    @GetMapping("/")
    fun home(model: Model): String {
        val contractors = contractorService.getAllContractors()

        // For each contractor, get limits and then CAFs in IN_PROGRESS status and max utilization
        val contractorInProgressCafTypes = mutableMapOf<Long, List<CafType>>()
        val contractorMaxUtilization = mutableMapOf<Long, Int>()
        val contractorOldestExpiryDate = mutableMapOf<Long, LocalDate?>()

        contractors.forEach { contractor ->
            val limits = limitService.getLimitsByContractorId(contractor.id)
            val inProgressCafs = mutableListOf<CafType>()

            // Calculate max utilization percentage for this contractor
            var maxUtilizationPercent = 0
            var oldestExpiryDate: LocalDate? = null
            limits.forEach { limit ->
                if (limit.value > BigDecimal.ZERO) {
                    val utilizationPercent = (limit.used * BigDecimal(100) / limit.value).toInt()
                    if (utilizationPercent > maxUtilizationPercent) {
                        maxUtilizationPercent = utilizationPercent
                    }
                }

                // Track the oldest expiry date
                if (oldestExpiryDate == null || (limit.dateTo.isBefore(oldestExpiryDate))) {
                    oldestExpiryDate = limit.dateTo
                }

                val cafs = cafService.getCafsByLimitId(limit.id!!)
                inProgressCafs.addAll(
                    cafs.filter { it.status == CafStatus.IN_PROGRESS }
                        .map { it.type },
                )
            }

            contractorInProgressCafTypes[contractor.id] = inProgressCafs
            contractorMaxUtilization[contractor.id] = maxUtilizationPercent
            contractorOldestExpiryDate[contractor.id] = oldestExpiryDate
        }

        model.addAttribute("contractors", contractors)
        model.addAttribute("inProgressCafTypes", contractorInProgressCafTypes)
        model.addAttribute("maxUtilization", contractorMaxUtilization)
        model.addAttribute("oldestExpiryDates", contractorOldestExpiryDate)
        model.addAttribute("currentDate", LocalDate.now())
        return "index"
    }

    @GetMapping("/contractors")
    fun getContractors(
        model: Model,
        @org.springframework.web.bind.annotation.RequestParam(required = false) search: String?,
    ): String {
        val contractors = contractorService.searchContractors(search)

        // For each contractor, get limits and then CAFs in IN_PROGRESS status and max utilization
        val contractorInProgressCafTypes = mutableMapOf<Long, List<CafType>>()
        val contractorMaxUtilization = mutableMapOf<Long, Int>()
        val contractorOldestExpiryDate = mutableMapOf<Long, LocalDate?>()

        contractors.forEach { contractor ->
            val limits = limitService.getLimitsByContractorId(contractor.id)
            val inProgressCafs = mutableListOf<CafType>()

            // Calculate max utilization percentage for this contractor
            var maxUtilizationPercent = 0
            var oldestExpiryDate: LocalDate? = null
            limits.forEach { limit ->
                if (limit.value > BigDecimal.ZERO) {
                    val utilizationPercent = (limit.used * BigDecimal(100) / limit.value).toInt()
                    if (utilizationPercent > maxUtilizationPercent) {
                        maxUtilizationPercent = utilizationPercent
                    }
                }

                // Track the oldest expiry date
                if (oldestExpiryDate == null || (limit.dateTo.isBefore(oldestExpiryDate))) {
                    oldestExpiryDate = limit.dateTo
                }

                val cafs = cafService.getCafsByLimitId(limit.id!!)
                inProgressCafs.addAll(
                    cafs.filter { it.status == CafStatus.IN_PROGRESS }
                        .map { it.type },
                )
            }

            contractorInProgressCafTypes[contractor.id] = inProgressCafs
            contractorMaxUtilization[contractor.id] = maxUtilizationPercent
            contractorOldestExpiryDate[contractor.id] = oldestExpiryDate
        }

        model.addAttribute("contractors", contractors)
        model.addAttribute("inProgressCafTypes", contractorInProgressCafTypes)
        model.addAttribute("maxUtilization", contractorMaxUtilization)
        model.addAttribute("oldestExpiryDates", contractorOldestExpiryDate)
        model.addAttribute("currentDate", LocalDate.now())
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
        val activeCafs =
            limits.associateBy(
                { it.id!! },
                { cafService.getActiveCafForLimit(it.id!!) },
            ).filterValues { it != null }

        model.addAttribute("limits", limits)
        model.addAttribute("activeCafs", activeCafs)
        return "limit-table :: limitsList"
    }
}
