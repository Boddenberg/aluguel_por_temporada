package juninwins.project.service

import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.guest.Guest
import juninwins.project.model.review.ReviewByGuest
import juninwins.project.model.review.ReviewByHost
import org.springframework.http.ResponseEntity

interface GuestService {

    fun save(customer : Guest) : Guest

    fun findGuestByCPF(cpf : String) : Guest

//    fun reviewAccommodationByGuest(hostCPF : String, guestCPF : String, idBooking: Long, idAccommodation : Long, review : ReviewByGuest) : Accommodation
//
//    fun reviewGuestByHost(hostCPF : String, guestCPF : String, idBooking: Long, review : ReviewByHost) : Guest
//
//    fun update(cpf : String, newCustomer : Guest) : Guest
//
//    fun deleteById(cpf : String) : ResponseEntity<String>

}