package mail

import model.Event
import services.MailServiceSubscriber
import services.OrdersService

class MailServer {

    var subscribers: MutableMap<String, MailServiceSubscriber> = HashMap<String, MailServiceSubscriber>()

    companion object {
        var instance: MailServer = MailServer()
    }


    open fun registerSubscriber(mss: MailServiceSubscriber, eventList: List<String>) {
        eventList.forEach { event -> subscribers.put(event, mss) }
    }

    open fun sendMessage(event: Event) {
        subscribers.get(event.eventType)?.receivedMessage(event)
    }
}