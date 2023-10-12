package JuninWins.Project.exceptions

class PolicyIdNotFoundException(id: Long): RuntimeException("Policy with ID $id not found!")