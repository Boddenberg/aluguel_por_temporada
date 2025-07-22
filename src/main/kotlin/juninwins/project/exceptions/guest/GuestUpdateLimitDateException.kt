package juninwins.project.exceptions.guest

import juninwins.project.model.guest.Guest

class GuestUpdateLimitDateException(existingGuest: Guest, daysRemaining: Long) : RuntimeException(
    "O cadastro do hóspede com CPF ${existingGuest.cpf} só poderá ser alterado em $daysRemaining dia(s). É necessário aguardar 30 dias desde a última modificação."
)
