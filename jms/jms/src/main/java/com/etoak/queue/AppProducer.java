package com.etoak.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProducer {
	
	//private static final String url = "tcp://192.168.158.132:61616";
	//private static final String queueName = "queue-etoak";
	//61617�����Զ�����61618   randomize=true���ѡ��61617  61618
//	private static final String url = "failover:(tcp://192.168.158.132:61617,tcp://192.168.158.132:61618)?randomize=true";
	private static final String url ="tcp://192.168.175.130:61616";
	private static final String queueName = "toc123";
	
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
		Destination destination= session.createQueue(queueName);
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
