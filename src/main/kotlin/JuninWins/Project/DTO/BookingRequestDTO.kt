package JuninWins.Project.DTO

import java.time.LocalDate

data class BookingRequestDTO(
    val startDate: LocalDate,
    val endDate: LocalDate
)