package juninwins.project.service

import juninwins.project.model.guest.Guest
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable

interface GuestService {

    fun save(customer : Guest) : Guest
    fun findGuestByCPF(cpf : String) : Guest
    fun findAllGuests(): List<Guest>
    fun updateGuest(guest: Guest) : Guest
    fun deleteGuestByCPF(cpf: String)
}