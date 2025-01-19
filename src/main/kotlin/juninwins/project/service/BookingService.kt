package juninwins.project.service

import juninwins.project.model.booking.Booking
import juninwins.project.model.booking.DTO.UpdateBookingDTO

interface BookingService {

    fun saveBooking(booking: Booking): Booking

    fun findBookingById(id: String): Booking

    fun findAllBookings(): List<Booking>

    fun updateBooking(updatedBooking: UpdateBookingDTO): Booking

    fun deleteBookingById(id: String)
}
