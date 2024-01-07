package juninwins.project.exceptions.booking

class BookingNotFoundException (id: String) : RuntimeException("Booking with ID $id not found!")