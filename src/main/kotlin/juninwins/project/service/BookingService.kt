package juninwins.project.service

import juninwins.project.model.booking.Booking

interface BookingService {

    fun saveBooking(booking: Booking): Booking

    fun findBookingById(id: String): Booking

    fun findAllBookings(): List<Booking>

    fun updateBooking(id: String, updatedBooking: Booking): Booking

    fun deleteBookingById(id: String)
}
