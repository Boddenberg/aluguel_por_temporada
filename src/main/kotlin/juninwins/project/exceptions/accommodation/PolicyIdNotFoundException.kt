package juninwins.project.exceptions.accommodation

class PolicyIdNotFoundException(id: Long): RuntimeException("Policy with ID $id not found!")