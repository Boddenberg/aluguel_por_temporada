package JuninWins.Project.service

fun interface NotificationService {

    fun sendSmsMessage(phoneNumber: String, message: String)
}