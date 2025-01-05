package juninwins.project.service

import juninwins.project.model.guest.DTO.UpdateGuestDTO
import juninwins.project.model.guest.Guest

interface GuestService {

    fun save(customer : Guest) : Guest
    fun findGuestByCPF(cpf : String) : Guest
    fun findAllGuests(): List<Guest>
    fun updateGuest(guestDTO: UpdateGuestDTO) : Guest
    fun deleteGuestByCPF(cpf: String)
}