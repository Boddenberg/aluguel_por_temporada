package juninwins.project.model.guest

import jakarta.persistence.*
import juninwins.project.model.accommodation.Accommodation

@Entity
@Table(name = "tb_cliente_hospedagem")
data class HostAccommodations(
        @Id
    @Column(
        name = "id",
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_cpf", nullable = false)
    val guest: Guest,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinTable(
        name = "tb_cliente_hospedagem_accommodations",
        joinColumns = [JoinColumn(name = "guest_accommodations_id")],
        inverseJoinColumns = [JoinColumn(name = "accommodations_id")]
    )
    var accommodations: MutableList<Accommodation> = mutableListOf()
)
