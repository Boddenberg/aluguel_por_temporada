package JuninWins.Project.repository

import JuninWins.Project.model.Accommodation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccommodationRepository : JpaRepository<Accommodation, Long>