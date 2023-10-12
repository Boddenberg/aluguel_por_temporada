package JuninWins.Project.service.impl

import JuninWins.Project.DTO.BookingRequestDTO
import JuninWins.Project.enums.StatusReservaEnum
import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.model.Booking
import JuninWins.Project.repository.BookingRepository
import JuninWins.Project.service.AccommodationService
import JuninWins.Project.service.BookingService
import JuninWins.Project.service.GuestService
import org.modelmapper.ModelMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookingServiceImpl (val bookingRepository: BookingRepository,
                          val guestService: GuestService,
                          val accommodationService: AccommodationService) : BookingService {

    private val modelMapper = ModelMapper()


    override fun save(booking: BookingRequestDTO, cpf: String, id: Long): Booking {
        val guest = guestService.findGuestByCPF(cpf)
        val accommodation = accommodationService.findAccomodationById(id)
        val newBooking = Booking(
            accommodation,
            booking.startDate,
            booking.endDate,
            calculateBookingDurationDays(booking.startDate, booking.endDate),
            calculateBookingTotalPrice (accommodation.basePrice, calculateBookingDurationDays(booking.startDate, booking.endDate), id),
        guest,
        StatusReservaEnum.CANCELED)

        return bookingRepository.save(newBooking)
    }

    override fun findBookingById(id: Long): Booking {
        return findById(id)
    }

    override fun update(id: Long, newBooking: Booking): Booking {
        val currentBooking = findById(id)

        modelMapper.map(newBooking, currentBooking)

        return bookingRepository.save(currentBooking)

    }

    override fun deleteById(id: Long): ResponseEntity<String> {
        val booking = bookingRepository.findById(id)

        if (booking.isPresent) {
            bookingRepository.deleteById(id)
            return ResponseEntity.ok("Booking deleted with success!")
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking ID not found")
    }


    private fun findById(id: Long): Booking {
        return bookingRepository.findById(id).orElseThrow { AccommodationIdNotFoundException(id) }
    }

    private fun calculateBookingDurationDays(startDate: LocalDate, endDate: LocalDate): Int {
        return endDate.until(startDate).days
    }

    private fun calculateBookingTotalPrice(precoBase: Double, duracaoReserva: Int, id: Long): Double {
        val accommodation = accommodationService.findAccomodationById(id)

        var totalPrice = precoBase * duracaoReserva

        for (discountPolicy in accommodation._discountPolicy) {
            when (discountPolicy.policyType) {
                "semanal" -> {
                    if (discountPolicy.discountPercentage != 0.0) {
                        totalPrice *= (1.0 - discountPolicy.discountPercentage / 100.0)
                    }
                }
                // refatorar para acrescentar outras políticas, tipo a quinzenal e a mensal
            }
        }

        return if (totalPrice != precoBase * duracaoReserva) {
            totalPrice
        } else {
            precoBase * duracaoReserva
        }
    }



}

        /*
           TODO's:
            Reservas não podem ser excluídas. Reservas podem ser apenas canceladas de acordo com a política de cancelamento.
            Resevas só podem ser atualizadas se as datas estiverem livres.
            Data de check-in não pode ser posterior a de check-out
            Reservas são de no mínimo 1 dia. Do horário de check-in ao horário de check-out.
            Cobrar preço por hóspede e preço base.


     */


