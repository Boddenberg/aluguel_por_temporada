package juninwins.project.model.review

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.*
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
@DynamoDbBean
data class ReviewByGuest(

        @Column(name = "made_by_cpf", nullable = true)
        var madeByCPF: String? = null,

        @Column(name = "made_by_name", nullable = true)
        var madeByName: String? = null,

        @Column(name = "cleaning_score", nullable = false)
        val cleaningScore: Int = 0,

        @Column(name = "cleaning_comment", nullable = false, columnDefinition = "TEXT")
        val cleaningComment: String = "",

        @Column(name = "comfort_score", nullable = false)
        val comfortScore: Int = 0,

        @Column(name = "comfort_comment", nullable = false, columnDefinition = "TEXT")
        val comfortComment: String = "",

        @Column(name = "location_score", nullable = false)
        val locationScore: Int = 0,

        @Column(name = "location_comment", nullable = false, columnDefinition = "TEXT")
        val locationComment: String = "",

        @Column(name = "host_score", nullable = false)
        val hostScore: Int = 0,

        @Column(name = "host_comment", nullable = false, columnDefinition = "TEXT")
        val hostComment: String = "",

        @Column(name = "general_comment", nullable = false, columnDefinition = "TEXT")
        val generalComment: String = "",

        @Column(name = "booking_date", nullable = false)
        val bookingDate: String = "",

        @Column(name = "submission_date", nullable = false)
        val submissionDate: LocalDate = LocalDate.now()
)
