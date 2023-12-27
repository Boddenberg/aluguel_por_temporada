package juninwins.project.repository

import juninwins.project.model.Guest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GuestRepository : JpaRepository<Guest, String>