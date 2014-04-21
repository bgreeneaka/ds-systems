/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/logs")
})
public class LoggingBean implements MessageListener {

    private final static Logger LOGGER = Logger.getLogger(LoggingBean.class.getName());

    public LoggingBean() {
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            Serializable objectData = objectMessage.getObject();
            String msg = (String) objectData;

            FileHandler fh;
            // %t = C:\Users\[username]\AppData\Local\Temp on windows7
            fh = new FileHandler("%t/ShopWebApp.log");
            LOGGER.addHandler(fh);
            
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            
             LOGGER.info(msg);
             fh.close();
        } catch (SecurityException | IOException | JMSException e) {
            LOGGER.severe(e.toString());
        }
    }
}
