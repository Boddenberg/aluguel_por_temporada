package juninwins.project.service

import juninwins.project.model.guest.GuestComplete
import juninwins.project.model.review.Review

interface GuestService {

    fun save(customer : GuestComplete) : GuestComplete
    fun saveReview(review: Review) : Review
    fun findGuestByCPF(cpf : String) : GuestComplete

//    fun reviewAccommodationByGuest(hostCPF : String, guestCPF : String, idBooking: Long, idAccommodation : Long, review : ReviewByGuest) : Accommodation
//
//    fun reviewGuestByHost(hostCPF : String, guestCPF : String, idBooking: Long, review : ReviewByHost) : Guest
//
//    fun update(cpf : String, newCustomer : Guest) : Guest
//
//    fun deleteById(cpf : String) : ResponseEntity<String>

}