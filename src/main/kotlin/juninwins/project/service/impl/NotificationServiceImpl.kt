//package juninwins.project.service.impl
//
//import juninwins.project.service.NotificationService
//import io.awspring.cloud.sns.sms.SnsSmsTemplate
//import org.slf4j.LoggerFactory
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//
//@Service
//class NotificationServiceImpl(@Autowired private val snsSmsTemplate: SnsSmsTemplate) : NotificationService {
//
//    private val logger = LoggerFactory.getLogger(NotificationServiceImpl::class.java)
//    override fun sendSmsMessage(phoneNumber: String, message: String) {
//        //TODO:  phone number to receive a message in E.164 format (for example +14155552671)
//        // usar constraint para validar numero de telefone
//        snsSmsTemplate.send(phoneNumber, message)
//        logger.info("Notification send to: $phoneNumber with message: $message")
//    }
//}