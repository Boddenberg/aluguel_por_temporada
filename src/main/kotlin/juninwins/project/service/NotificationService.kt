package juninwins.project.service

fun interface NotificationService {

    fun sendSmsMessage(phoneNumber: String, message: String)
}