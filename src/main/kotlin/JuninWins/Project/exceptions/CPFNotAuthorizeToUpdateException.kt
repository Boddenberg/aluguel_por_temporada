package JuninWins.Project.exceptions

class CPFNotAuthorizeToUpdateException(cpf: String): RuntimeException("you are not authorized to update the CPF: $cpf already registered")