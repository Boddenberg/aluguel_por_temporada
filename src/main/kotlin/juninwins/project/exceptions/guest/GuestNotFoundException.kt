package juninwins.project.exceptions.guest

class GuestNotFoundException(cpf: String): RuntimeException("Guest CPF: $cpf not found")