package webdevelopment.secondsystem.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;
import webdevelopment.secondsystem.domain.entity.OrderForm;
import webdevelopment.secondsystem.service.Impl.RedisServiceImpl;

import javax.annotation.Resource;


@Service
public class MQSender {
    private static Logger log =  LoggerFactory.getLogger(MQSender.class);

    @Resource
    private RabbitTemplate rabbitTemplate;
    public void sendMessage(OrderFormDto order) {
        String msg = RedisServiceImpl.beanToString(order);
        log.info("send message:" + msg);
//        System.out.println("send message:" + msg);
        rabbitTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
    }
}
