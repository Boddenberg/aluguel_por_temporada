package juninwins.project.model.accommodation

import jakarta.persistence.*
import juninwins.project.model.Guest

@Entity
@Table(name = "tb_cliente_hospedagem")
data class GuestAccommodations (

    @Id
    @Column(
    name = "cpf",
    unique = true
    )
//    @field:CPF
    var cpf: String,
   // @MapsId
    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cliente_cpf")
    var guest: Guest,
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn(name = "hospedagem_id")
    var accommodations: List<Accommodation>
)