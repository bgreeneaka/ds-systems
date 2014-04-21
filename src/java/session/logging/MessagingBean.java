/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.logging;

import Controller.shop.CancelOrder;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author chromodynamics
 */
@Stateless
public class MessagingBean implements MessagingBeanLocal {

    private Queue jmsQueue;
    private ConnectionFactory jmsConnectionFactory;

    @Override
    public void sendMessage(Object message) {
        try {
            jmsQueue = (Queue) new InitialContext().lookup("jms/logs");
            jmsConnectionFactory = (ConnectionFactory) new InitialContext().lookup("jms/logsFactory");

            Connection connection = jmsConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(jmsQueue);

            ObjectMessage msg = session.createObjectMessage();
            msg.setObject((Serializable) message);

            producer.send(msg);
        } catch (JMSException | NamingException ex) {
            Logger.getLogger(CancelOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
