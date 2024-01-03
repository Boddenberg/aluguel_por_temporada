package juninwins.project.model.review

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "tb_accommodation_host_review")
data class ReviewByGuest (

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(name = "made_by_cpf")
        var madeByCPF: String? = null,

        @Column(name = "made_by_name")
        var madeByName: String? = null,

        @Column(name = "cleaning_score")
        val cleaningScore: Int,

        @Column(name = "cleaning_comment")
        val cleaningComment: String,

        @Column(name = "comfort_score")
        val comfortScore: Int,

        @Column(name = "comfort_comment")
        val comfortComment: String,

        @Column(name = "location_score")
        val locationScore: Int,

        @Column(name = "location_comment")
        val locationComment: String,

        @Column(name = "host_score")
        val hostScore: Int,

        @Column(name = "host_comment")
        val hostComment: String,

        @Column(name = "general_comment")
        val generalComment: String,

        @Column(name = "booking_date")
        val bookingDate : String,

        @Column(name = "submission_date")
        val submissionDate: LocalDate = LocalDate.now()
)