package juninwins.project.enums

enum class StatusReservaEnum(val descricao: String) {
    PENDING("Reserva pendente"),
    CONFIRMED("Reserva confirmada"),
    IN_PROGRESS("Reserva em andamento"),
    CANCELED("Reserva cancelada"),
    CONCLUDED("Reserva concluída"),
    READY_TO_REVIEW("Pronta para avaliação"),
    NOT_READY_TO_REVIEW("Pronta para avaliação")
}
