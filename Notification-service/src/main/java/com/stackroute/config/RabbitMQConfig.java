package com.stackroute.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String Queue = "javatechie_queue";
    public static final String Exchange = "javatechie_exchange";
    public static final String ROUTING_KEY = "javatechie_routingkey";

   @Bean
	public Queue queue() {
		return new Queue(Queue);
    }
   @Bean

    public TopicExchange exchange() {
        return new TopicExchange(Exchange);
    
}	
   @Bean

    public Binding binding() {
    	return BindingBuilder
    			.bind(queue())
    			.to(exchange()).with(ROUTING_KEY);
    }
   @Bean
   public MessageConverter convertor() {
	   return new Jackson2JsonMessageConverter();
   }
   @Bean
   public AmqpTemplate templet(ConnectionFactory connectionFactry) {
	   final RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactry);
	   rabbitTemplate.setMessageConverter(convertor());
	   return rabbitTemplate;
	   
	   
   }
    }
