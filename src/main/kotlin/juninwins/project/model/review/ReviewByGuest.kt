package juninwins.project.model.review

import jakarta.persistence.*
import java.time.LocalDate


data class ReviewByGuest (


        val id: Long? = null,


        var madeByCPF: String? = null,


        var madeByName: String? = null,


        val cleaningScore: Int,


        val cleaningComment: String,


        val comfortScore: Int,


        val comfortComment: String,


        val locationScore: Int,


        val locationComment: String,


        val hostScore: Int,


        val hostComment: String,


        val generalComment: String,


        val bookingDate : String,


        val submissionDate: LocalDate = LocalDate.now()
)