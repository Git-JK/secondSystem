package webdevelopment.secondsystem.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "seckill.queue";
    public static final String QUEUE = "queue";


    @Bean
    public MessageConverter getMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public  Queue queue() {
        return new Queue(QUEUE, true);
    }
//    @Bean
//    public Queue TestDirectQueue() {
//        return new Queue("TestDirectQueue", true);
//    }
//
//    @Bean
//    DirectExchange TestDirectExchange() {
//        return new DirectExchange("TestDirectExchange", true, false);
//    }
//
//    @Bean
//    Binding bindingDirect() {
//        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
//    }
//
//    @Bean
//    DirectExchange longlyDirectExchange() {
//        return new DirectExchange("longlyDirectExchange");
//    }
}
