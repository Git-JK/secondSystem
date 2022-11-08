package webdevelopment.secondsystem.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import webdevelopment.secondsystem.dao.OrderFormMapper;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;
import webdevelopment.secondsystem.domain.entity.OrderForm;
import webdevelopment.secondsystem.service.Impl.RedisServiceImpl;
import webdevelopment.secondsystem.service.OrderService;

import javax.annotation.Resource;

@Service
public class MQReceiver {
    public static final Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Resource
    private OrderFormMapper orderFormMapper;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        log.info("receive message:" + message);
        OrderFormDto dto = RedisServiceImpl.stringToBean(message, OrderFormDto.class);
        OrderForm order = new OrderForm(dto);
        orderFormMapper.insert(order);
//        System.out.println("receive message:" + message);
    }
}
