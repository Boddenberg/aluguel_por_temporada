package juninwins.project.exceptions.guest

class GuestAlreadyRegisteredException (cpf: String): RuntimeException("CPF $cpf is already registered!")