package webdevelopment.secondsystem.controller;

import org.springframework.web.bind.annotation.*;
import webdevelopment.secondsystem.domain.entity.JsonResult;
import webdevelopment.secondsystem.domain.entity.OrderForm;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;
import webdevelopment.secondsystem.domain.vo.OrderFormVo;
import webdevelopment.secondsystem.mq.MQSender;
import webdevelopment.secondsystem.service.IUserService;
import webdevelopment.secondsystem.service.OrderService;
import webdevelopment.secondsystem.utils.exception.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private IUserService userService;
    @Resource
    private MQSender mqSender;
    private static Integer orderId = 0;

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e) {
        JsonResult<Void> result = new JsonResult<Void>(e);
        if(e instanceof UserNotFoundException) {
            result.setState(4001);
        } else if (e instanceof  SessionTimeOutException) {
            result.setState(4003);
        } else if (e instanceof BuildingNotFoundException) {
            result.setState(5001);
        } else if (e instanceof GenderNotMatchException) {
            result.setState(5002);
        } else if (e instanceof MemberNumberNotMatchException) {
            result.setState(5003);
        } else if (e instanceof VerificationCodeNotMatchException) {
            result.setState(5004);
        } else if (e instanceof UserHadRoomException) {
            result.setState(5005);
        } else if (e instanceof TimeNotMatchException) {
            result.setState(5006);
        } else if (e instanceof TimeFormatNotMatchException) {
            result.setState(5007);
        }
        return result;
    }

    @PostMapping("putOrder")
    public JsonResult<OrderFormDto> putOrder(@RequestBody OrderFormVo orderFormVo) {
        OrderFormDto orderFormDto = new OrderFormDto(orderFormVo);
        orderId++;
        orderFormDto.setOrderId(orderId);
//        mqSender.sendMessage(orderFormDto);
        OrderFormDto result = orderService.orderProcessing(orderFormDto);
        System.out.println(result);
        return new JsonResult<OrderFormDto>(200, "成功申请宿舍，订单处理结果如下：", null);
    }

    @PostMapping("getOrder")
    public JsonResult<OrderFormDto> getOrder(@RequestParam("orderId") Integer orderId) {
        OrderFormDto result = orderService.getOrderForm(orderId);
        return new JsonResult<OrderFormDto>(200, "查询订单信息如下：", result);
    }
}
