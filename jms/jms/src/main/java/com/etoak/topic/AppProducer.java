package com.etoak.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ������
 */
public class AppProducer {

	private static final String url ="tcp://192.168.175.130:61616";
	private static final String topicName = "topic-etoak";
	
	public static void main(String[] args) throws JMSException {
		//1.����ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2.����Connection
		Connection connection = connectionFactory.createConnection();
		//3.�����B��
		connection.start();
		//4.������Ԓ �������Ƿ�������̎��
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.����һ��Ŀ��
		Destination destination= session.createTopic(topicName);
		//6.����һ�����a��
		MessageProducer mp = session.createProducer(destination);
		
		for(int i = 0;i < 100;i++){
			//7.������Ϣ
			TextMessage textMessage = session.createTextMessage("test" + i);
			//8.�l����Ϣ
			mp.send(textMessage);
			
			System.out.println("�l����Ϣ��" + textMessage.getText());
		}
		
		//9.�P�]�B��
		connection.close();
		
	}	

}
