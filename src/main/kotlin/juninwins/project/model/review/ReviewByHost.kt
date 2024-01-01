package juninwins.project.model.review

import jakarta.persistence.*
import java.time.LocalDate


@Entity
@Table(name = "tb_guest_review")
class ReviewByHost(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "review_id")
        val id: Long? = null,

        // Scores
        @Column(name = "cleaning_score")
        val cleaningScore: Int,

        @Column(name = "cleaning_comment")
        val cleaningComment: String,

        @Column(name = "rules_score")
        val rulesScore: Int,

        @Column(name = "rules_comment")
        val rulesComment: String,

        @Column(name = "general_comment")
        val generalComment: String,

        @Column(name = "booking_date")
        val bookingDate : String,

        @Column(name = "submission_date")
        val submissionDate: LocalDate = LocalDate.now()

)
