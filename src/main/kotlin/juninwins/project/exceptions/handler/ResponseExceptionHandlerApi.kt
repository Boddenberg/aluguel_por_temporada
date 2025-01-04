package juninwins.project.exceptions.handler

import juninwins.project.exceptions.accommodation.*
import juninwins.project.exceptions.address.CEPValidationException
import juninwins.project.exceptions.booking.*
import juninwins.project.exceptions.guest.*
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@ControllerAdvice
class ResponseExceptionHandlerApi : ResponseEntityExceptionHandler() {


    private val logger = LoggerFactory.getLogger(this::class.java)
    private val baseURL = "http://localhost:8080"
    private val patternTimeStamp = "dd/MM/yyyy HH:mm:ss"

    @ExceptionHandler(value = [
        AccommodationIdNotFoundException::class,
        BookingNotFoundException::class,
        GuestNotFoundException::class
    ])
    fun handleNotFound(exception: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND, request, mapOf(
            "self" to Link("$baseURL/register", "create resource", "POST")
        ))
    }

    @ExceptionHandler(value = [
        PolicySizeThresholdException::class,
        DuplicatePolicyException::class,
        CEPValidationException::class,
        CPFNotAuthorizeToUpdateException::class,
        StartDatateIsEqualOrAfterEndDateException::class,
        InvalidPhoneNumberFormatException::class
    ])
    fun handleBadRequest(exception: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request)
    }

    @ExceptionHandler(value = [
        GuestAlreadyRegisteredException::class,
        BookingNotConcludedException::class,
        BookingAlreadyReviewedException::class
    ])
    fun handleConflict(exception: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception, HttpStatus.CONFLICT, request)
    }

    @ExceptionHandler(value = [
        GuestResponsibilityException::class,
        SameGuestAndHostException::class
    ])
    fun handleUnauthorized(exception: RuntimeException, request: WebRequest): ResponseEntity<ErrorResponse> {
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: org.springframework.http.HttpHeaders,
        status: org.springframework.http.HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = ex.bindingResult.allErrors.joinToString("; ") { error ->
            when (error) {
                is org.springframework.validation.FieldError -> "${error.field}: ${error.defaultMessage}"
                else -> error.defaultMessage ?: "Validation error"
            }
        }

        val timeStamp = DateTimeFormatter.ofPattern(patternTimeStamp)
            .withZone(ZoneId.systemDefault())
            .format(Instant.now())

        val errorResponse = ErrorResponse(
            status = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = errors,
            _links = emptyMap(),
            timestamp = timeStamp,
            path = request.getDescription(false)
        )

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse)
    }


    private fun buildErrorResponse(
        exception: RuntimeException,
        status: HttpStatus,
        request: WebRequest,
        links: Map<String, Link> = emptyMap()
    ): ResponseEntity<ErrorResponse> {
        val timeStamp = DateTimeFormatter.ofPattern(patternTimeStamp)
            .withZone(ZoneId.systemDefault())
            .format(Instant.now())
        logger.error("Exception handled: ${exception.message}", exception)
        val errorResponse = ErrorResponse(
            status = status.value(),
            message = exception.message,
            _links = links,
            timestamp = timeStamp,
            path = request.getDescription(false)
        )
        return ResponseEntity.status(status).body(errorResponse)
    }

    data class ErrorResponse(
        val status: Int,
        val message: String?,
        val _links: Map<String, Link>?,
        val timestamp: String?,
        val path: String?
    )

    data class Link(val href: String, val description: String?, val method: String?)
}
