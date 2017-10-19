package com.etoak.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Enumeration;

public class AppConsumer {
	
	/*private static final String url = "tcp://192.168.158.132:61616";
	private static final String queueName = "queue-etoak";*/
	private static final String url ="tcp://192.168.175.130:61616";
//	private static final String url = "failover:(tcp://192.168.158.132:61616,tcp://192.168.158.132:61617,tcp://192.168.158.132:61618)?randomize=true";
	private static final String queueName = "queue-test";
	
	public static void main(String[] args) throws JMSException {
		//1.����ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2.����Connection
		Connection connection = connectionFactory.createConnection();
		//3.�����B��
		connection.start();

		//��ȡJMSXPropertyNames
		Enumeration names = connection.getMetaData().getJMSXPropertyNames();
		while (names.hasMoreElements()) {
			String name = (String)names.nextElement();
			System.out.print(name);
		}

		//4.������Ԓ �������Ƿ�������̎��
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.����һ��Ŀ��
		Destination destination= session.createQueue(queueName);
		//6.����һ�����M��
		MessageConsumer consumer = session.createConsumer(destination);
		//7.����һ���O ��
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				//8.�@ȡ��Ϣ
				TextMessage tm = (TextMessage)message;
				try {
					System.out.println("������Ϣ��" + tm.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
