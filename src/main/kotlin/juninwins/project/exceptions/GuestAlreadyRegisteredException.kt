package juninwins.project.exceptions

class GuestAlreadyRegisteredException (cpf: String): RuntimeException("CPF $cpf is already registered!")