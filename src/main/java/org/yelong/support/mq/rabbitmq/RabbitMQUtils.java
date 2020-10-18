package org.yelong.support.mq.rabbitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * RabbitMQ工具类
 * 
 * @since 2.1.1
 */
public final class RabbitMQUtils {

	private RabbitMQUtils() {
	}

	/**
	 * 创建RabbitMQ连接
	 * 
	 * @param connectionProperties 连接配置
	 * @return RabbitMQ连接
	 * @throws IOException      创建失败
	 * @throws TimeoutException 创建失败
	 */
	public static Connection createConnection(RabbitMQConnectionProperties connectionProperties)
			throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(connectionProperties.getHost());
		factory.setPort(connectionProperties.getPort());
		factory.setUsername(connectionProperties.getUsername());
		factory.setPassword(connectionProperties.getPassword());
		// 创建连接
		return factory.newConnection();
	}

	/**
	 * 基本的向对列发送消息
	 * 
	 * @param connection RabbitMQ连接
	 * @param queueName  对列名称
	 * @param body       发送的消息内容
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void basicPublish(Connection connection, String queueName, byte[] body)
			throws IOException, TimeoutException {
		// 创建信道
		Channel channel = connection.createChannel();
		// 创建一个type="direct"、持久化的、非自动删除的交换器
//		channel.exchangeDeclare("exchange_demo", "direct", true, false, null);
		// 创建一个持久化、非排他的、非自动删除的队列
		channel.queueDeclare(queueName, true, false, false, null);
		// 将交换器与队列通过路由键绑定
//		channel.queueBind(queueName, "exchange_demo", "routingkey_demo");
		channel.basicPublish("", queueName, null, body);
		channel.close();
	}

}
