package com.issimo.jsm.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AppProductor {
	private  static  final String url="tcp://localhost:61616?jms.useAsyncSend=true";
	private  static  final String topicName="topic-test";
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
		Destination destination= session.createTopic(topicName);
		//6.创建生成者
		MessageProducer producer=session.createProducer(destination);
		//循环100次
		for(int i=0;i<100;i++){
			//7创建消息
			TextMessage textMessage=session.createTextMessage("test"+i);
			//8.发布消息
			producer.send(textMessage);
			
			System.out.println("发送消息"+textMessage.getText());
		}
		//关闭连接 
		connection.close();
	}
}
