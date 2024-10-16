package juninwins.project.model.review

import jakarta.persistence.*
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import java.time.LocalDate


@DynamoDbBean
class ReviewByHost(


        val id: Long? = null,


        var madeByCPF: String?,

        var madeByName: String?,

        val cleaningScore: Int?,


        val cleaningComment: String?,


        val rulesScore: Int?,


        val rulesComment: String?,


        val generalComment: String?,


        val bookingDate : String?,


        val submissionDate: LocalDate? = LocalDate.now()

)
