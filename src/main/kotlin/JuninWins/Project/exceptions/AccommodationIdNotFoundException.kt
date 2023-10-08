package JuninWins.Project.exceptions

class AccommodationIdNotFoundException(id: Long) : RuntimeException("Accomodation with ID $id not found!")