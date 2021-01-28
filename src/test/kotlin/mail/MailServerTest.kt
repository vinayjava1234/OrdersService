package mail

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import services.MailServiceSubscriber
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class MailServerTest {

    @Test
    fun MailServerSubscribingTest(){
        var eventList:List<String> = listOf("Placed","Packing","Ready","NoInventory")
        val mailServer = MailServer()
        val subscriber = MailServiceSubscriber()
        mailServer.registerSubscriber(subscriber,eventList)
        assertEquals(subscriber,mailServer.subscribers.get("Placed"))
    }
}