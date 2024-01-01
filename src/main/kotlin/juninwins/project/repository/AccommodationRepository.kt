package juninwins.project.repository

import juninwins.project.model.accommodation.Accommodation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccommodationRepository : JpaRepository<Accommodation, Long>