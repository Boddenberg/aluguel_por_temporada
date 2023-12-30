package juninwins.project.model.history

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import juninwins.project.model.accommodation.Accommodation
import juninwins.project.model.Guest


@Entity
@Table(name = "tb_historico_reservas")
@JsonIgnoreProperties
data class BookingHistory(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "cliente_cpf")
        var guest: Guest,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "hospedagem_historico_id")
        var accommodation : List<Accommodation>
)