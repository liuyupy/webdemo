package com.liuyu.component.mq.activemq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiQueueExample {

	private static final String MQ_URL = "tcp://localhost:61616";
	
	private static final String DEFAULT_QUEUE = "localloop1,localloop2";
	private static final String QUEUE_1 = "localloop1";
	private static final String QUEUE_2 = "localloop2";
	
	public static void main(String[] args) throws JMSException, InterruptedException {
		QueueClient client1 = new QueueClient(MQ_URL, "client1", QUEUE_1);
		QueueClient client2 = new QueueClient(MQ_URL, "client2", QUEUE_2);
		client1.listen();
		client2.listen();
		
		QueueServer server1 = new QueueServer(MQ_URL, DEFAULT_QUEUE);
		server1.init();
		for(int i = 0 ; i < 3; i++){
			server1.sendTextMessage("message content " + i);
			Thread.sleep(3000l);
		}
	}
}

class QueueServer{
	String url;
	String queueName;
	
	Connection conn;
	Session session;
	Destination destination;
	MessageProducer producer;
	
	
	public QueueServer(String url , String queueName){
		this.url = url;
		this.queueName = queueName;
	}
	
	public void init() throws JMSException{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		conn = factory.createConnection();
		conn.start();
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination  = session.createQueue(queueName);    
		producer = session.createProducer(destination);   
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);   
	}
	
	public void sendTextMessage(String text) throws JMSException{
		TextMessage message = session.createTextMessage(text);
		producer.send(message);
	}
}


class QueueClient implements MessageListener{
	
	private static Logger LOGGER = LoggerFactory.getLogger(QueueClient.class);
	
	private String url;
	private String clientId;
	private String queueName;
	
	public QueueClient(String url , String clientId , String queueName){
		this.url = url;
		this.clientId = clientId;
		this.queueName = queueName;
	}
	
	public void listen() throws JMSException{
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
		Connection conn = factory.createConnection();
		conn.setClientID(clientId);
		conn.start();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination destination  = session.createQueue(queueName);      
		MessageConsumer consumer = session.createConsumer(destination);      
		consumer.setMessageListener(this);    
		
		MessageConsumer consumer2 = session.createConsumer(destination);      
		consumer2.setMessageListener(this);    
	}
	
	public void onMessage(Message message) {
		try {
			LOGGER.info("message {} comsumed by client {}" , message.getJMSMessageID() , clientId);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}