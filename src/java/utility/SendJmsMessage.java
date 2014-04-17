
package utility;

import Controller.CancelOrder;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SendJmsMessage {
    // Cant use resource in injection glassfish 4.0, it gives null
    // so we manually lookup the resource
    private Queue jmsQueue;
    private ConnectionFactory jmsConnectionFactory;
    
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
