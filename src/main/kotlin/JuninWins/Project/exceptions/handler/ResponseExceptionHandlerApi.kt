package JuninWins.Project.exceptions.handler

import JuninWins.Project.exceptions.AccommodationIdNotFoundException
import JuninWins.Project.exceptions.BookingNotFoundException
import JuninWins.Project.exceptions.PolicySizeThresholdException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * Este manipulador segue o padrão de formatação JSON HAL conforme definido na RFC 7807 (https://www.rfc-editor.org/rfc/rfc7807).
 */
@ControllerAdvice
class ResponseExceptionHandlerApi : ResponseEntityExceptionHandler() {

    val baseURL = "http://localhost:8080"
    val patternTimeStamp = "dd/MM/yyyy HH:mm:ss"
    /**
     * Manipula exceções do tipo AccommodationIdNotFoundException e retorna uma resposta formatada no padrão JSON HAL.
     *
     * @param exception A exceção AccommodationIdNotFoundException lançada.
     * @param request   O objeto WebRequest que representa a solicitação atual.
     * @return Uma resposta ResponseEntity com um corpo formatado no padrão JSON HAL contendo informações sobre a exceção.
     */
    @ExceptionHandler(AccommodationIdNotFoundException::class)
    fun handlingResponseForAccommodationIdNotFoundException(
        exception: AccommodationIdNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val instant = Instant.now()
        val formatter = DateTimeFormatter.ofPattern(patternTimeStamp)
            .withZone(ZoneId.systemDefault())
        val timeStamp = formatter.format(instant)
        val httpStatus = HttpStatus.NOT_FOUND.value()
        val errorResponse = ErrorResponse(
            status = httpStatus,
            message = exception.message,
            _links = mapOf(
                "self" to Link("$baseURL/register/accommodation", "create accommodation", "POST")
            ),
            timestamp = timeStamp,
            path = request.getDescription(false)
        )
        return ResponseEntity.status(httpStatus).body(errorResponse)
    }

    @ExceptionHandler(BookingNotFoundException::class)
    fun handlingResponseForBookingIdNotFoundException(
        exception: BookingNotFoundException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val instant = Instant.now()
        val formatter = DateTimeFormatter.ofPattern(patternTimeStamp)
            .withZone(ZoneId.systemDefault())
        val timeStamp = formatter.format(instant)
        val httpStatus = HttpStatus.NOT_FOUND.value()
        val errorResponse = ErrorResponse(
            status = httpStatus,
            message = exception.message,
            _links = mapOf(
                "self" to Link("$baseURL/bookings/register/booking", "create booking", "POST")
            ),
            timestamp = timeStamp,
            path = request.getDescription(false)
        )
        return ResponseEntity.status(httpStatus).body(errorResponse)
    }

    @ExceptionHandler(PolicySizeThresholdException::class)
    fun handlingResponseForPolicySizeThresholdException(
        exception: PolicySizeThresholdException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val instant = Instant.now()
        val formatter = DateTimeFormatter.ofPattern(patternTimeStamp)
            .withZone(ZoneId.systemDefault())
        val timeStamp = formatter.format(instant)
        val httpStatus = HttpStatus.BAD_REQUEST.value()
        val errorResponse = ErrorResponse(
            status = httpStatus,
            message = exception.message,
            _links = mapOf(
                "update" to Link("$baseURL/update/policy", "update policy on accommodation", "PUT"),
                "delete" to Link("$baseURL/delete/policy", "delete policy on accommodation", "DELETE")
            ),
            timestamp = timeStamp,
            path = request.getDescription(false)
        )
        return ResponseEntity.status(httpStatus).body(errorResponse)
    }


    /**
     * Data class para representar uma resposta de erro formatada no padrão JSON HAL.
     *
     * @param status    O código de status HTTP da resposta.
     * @param message   A mensagem de erro.
     * @param _links    Um mapa de links relevantes.
     * @param timestamp A data e hora da resposta.
     * @param path      O caminho da solicitação que causou o erro.
     */
    data class ErrorResponse(
        val status: Int,
        val message: String?,
        val _links: Map<String, Link>?,
        val timestamp: String?,
        val path: String?
    )

    /**
     * Data class para representar um link em uma resposta JSON HAL.
     *
     * @param href        A URL do link.
     * @param description A descrição do link.
     * @param method      O método HTTP associado ao link.
     */
    data class Link(val href: String, val description: String?, val method: String?)

}