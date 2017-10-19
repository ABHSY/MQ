package com.etoak.topic;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * ��Ϣ������
 */
public class AppConsumer {

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
		
		//9.�P�]�B��
		//connection.close();
		
	}	

}
