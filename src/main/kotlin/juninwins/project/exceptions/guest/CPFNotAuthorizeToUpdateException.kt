package juninwins.project.exceptions.guest

class CPFNotAuthorizeToUpdateException(cpf: String): RuntimeException("you are not authorized to update the CPF: $cpf already registered")