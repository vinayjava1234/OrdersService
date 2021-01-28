package service

import model.Event
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import services.MailServiceSubscriber
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MailServiceTest {


    @Test
    fun MailServiceTest(){

        val subscriber = MailServiceSubscriber()
        val event = Event("Placed","Hi, your order has been Placed")
        subscriber.receivedMessage(event)
    }
}