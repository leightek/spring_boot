package com.leightek.todo.config;

import com.leightek.todo.error.ToDoErrorHandler;
import com.leightek.todo.validator.ToDoValidator;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.jms.ConnectionFactory;

@Configuration
public class ToDoConfig {

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_class_");
        return converter;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setErrorHandler(new ToDoErrorHandler());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    // for JMS Pub/Sub
    /*@Bean
    public DefaultMessageListenerContainer jmsListenerContainerFactory() {
        DefaultMessageListenerContainer dmlc = new DefaultMessageListenerContainer();
        dmlc.setPubSubDomain(true);
        return dmlc;
    }*/

    @Configuration
    static class MethodListenerConfig implements JmsListenerConfigurer {

        @Override
        public void configureJmsListeners(JmsListenerEndpointRegistrar jmsListenerEndpointRegistrar) {
            jmsListenerEndpointRegistrar.setMessageHandlerMethodFactory(myHandlerMethodFactory());
        }

        @Bean
        public DefaultMessageHandlerMethodFactory myHandlerMethodFactory() {
            DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
            factory.setValidator(new ToDoValidator());
            return factory;
        }
    }
}
