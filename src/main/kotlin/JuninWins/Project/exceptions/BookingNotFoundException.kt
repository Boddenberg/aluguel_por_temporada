package JuninWins.Project.exceptions

class BookingNotFoundException (id: String) : RuntimeException("Booking with ID $id not found!")