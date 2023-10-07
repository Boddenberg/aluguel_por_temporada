package JuninWins.Project.exceptions

class AccommodationIdNotFoundException(id: String) : RuntimeException("Accomodation with ID $id not found!")