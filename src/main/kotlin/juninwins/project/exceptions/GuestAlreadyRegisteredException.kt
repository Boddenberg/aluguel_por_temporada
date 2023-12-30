package juninwins.project.exceptions

class GuestAlreadyRegisteredException(cpf: String) : RuntimeException("Guest with CPF $cpf is already registered!")