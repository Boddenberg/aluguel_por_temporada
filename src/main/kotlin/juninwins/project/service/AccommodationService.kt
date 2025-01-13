package juninwins.project.service

import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.accommodation.DTO.AccommodationDTO
import juninwins.project.model.guest.DTO.UpdateGuestDTO

interface AccommodationService {

    fun saveAccommodation(accommodation: Accommodation) : Accommodation
    fun findAccommodationById(id : String) : Accommodation
    fun findAccommodationByCPF(cpf : String) : List<Accommodation>
    fun findAllAccommodations(): List<Accommodation>
    fun updateAccommodation(accommodationDTO: AccommodationDTO) : Accommodation
    fun deleteAccommodationById(id: String)
}