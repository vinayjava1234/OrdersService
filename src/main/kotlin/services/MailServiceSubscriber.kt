package services

import mail.MailServer
import model.Event

class MailServiceSubscriber {

    var eventList:List<String> = listOf("Placed","Packing","Ready","NoInventory")
    constructor(){
        MailServer.instance.registerSubscriber(this,eventList)

    }
    open fun receivedMessage(event:Event){
        println(event.message)
    }
}