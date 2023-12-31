package juninwins.project.repository

import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.GuestAccommodations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GuestAccommodationsRepository : JpaRepository<GuestAccommodations, String> {

    @Query("SELECT ga.accommodations FROM GuestAccommodations ga WHERE ga.cpf = :cpf")
    fun findAccommodationsByGuestCpf(cpf: String): List<Accommodation>


}