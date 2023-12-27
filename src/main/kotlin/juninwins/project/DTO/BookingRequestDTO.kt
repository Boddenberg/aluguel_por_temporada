package juninwins.project.DTO

import java.time.LocalDate

data class BookingRequestDTO(
    val startDate: LocalDate,
    val endDate: LocalDate
)