package com.stackroute.playerrecommendationservice;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.stackroute.playerrecommendationservice.filter.JwtFilter;
import com.stackroute.playerrecommendationservice.model.PlayerStats;
import com.stackroute.playerrecommendationservice.service.RecommendedPlayerService;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableEurekaClient
public class PlayerRecommendationServiceApplication {

	public static String host = "localhost";
	public static String QUEUENAME = "playerQueue";
	public static Channel channel;
	public static Connection connection;

	public static RecommendedPlayerService recommendedPlayerService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean jwtFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/v1/recommendations/*");
		return registrationBean;
	}

	@Autowired
	public PlayerRecommendationServiceApplication(RecommendedPlayerService recommendedPlayerService) {
		PlayerRecommendationServiceApplication.recommendedPlayerService = recommendedPlayerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PlayerRecommendationServiceApplication.class, args);
		persistFavPlayerFromQueueToRecommendedStore();
	}

	private static void persistFavPlayerFromQueueToRecommendedStore() {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUENAME, false, false, false, null);
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String jsonInString = new String(delivery.getBody(), "UTF-8");
				Gson gson = new Gson();
				PlayerStats favPlayerFromQueue = gson.fromJson(jsonInString, PlayerStats.class);
				favPlayerFromQueue = recommendedPlayerService.saveRecommendedPlayer(favPlayerFromQueue);
			};
			channel.basicConsume(QUEUENAME, true, deliverCallback, consumerTag -> {
			});

		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}

	}

}
