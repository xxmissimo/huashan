package com.issimo.jsm.queue;

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

public class AppConsumer {

	private  static  final String url="tcp://localhost:61616?jms.useAsyncSend=true";
	private  static  final String queueName="queue-test";
	public static void main(String[] args) throws JMSException {
		//1.创建连接工厂
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(url);
		//2.创建connection
		Connection connection=connectionFactory.createConnection();
		//3.启动连接
		connection.start();
		//4.创建会话
		Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5.创建目的地
		Destination destination= session.createQueue(queueName);
		//6.创建一个消费者
		MessageConsumer consumer=session.createConsumer(destination);
		//7创建监听器
		consumer.setMessageListener(new MessageListener(){
			public void  onMessage(Message message){
				TextMessage textmessage=(TextMessage) message;
				try {
					System.out.println("接收消息"+textmessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//关闭连接 
		//connection.close();
	}
		
}
