package juninwins.project.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import juninwins.project.model.accommodation.Accommodation
import java.sql.Timestamp


@Entity
@Table(name = "tb_hospedagem_historico")
@JsonIgnoreProperties
class AccommodationHistory(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "update_at")
        val updateAt : Timestamp,
        @OneToMany(cascade = [CascadeType.ALL])
        @JoinColumn(name = "hospedagem_id")
        var accommodation : List<Accommodation>
)