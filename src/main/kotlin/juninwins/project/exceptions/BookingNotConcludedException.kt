package juninwins.project.exceptions


class BookingNotConcludedException (id: Long) : RuntimeException("Reserva com ID $id não foi concluída e não está pronta para ser avaliada.")