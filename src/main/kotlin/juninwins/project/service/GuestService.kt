package juninwins.project.service

import juninwins.project.model.guest.GuestComplete

interface GuestService {

    fun save(customer : GuestComplete) : GuestComplete
    fun findGuestByCPF(cpf : String) : GuestComplete
    fun findAllGuests(): List<GuestComplete>
}