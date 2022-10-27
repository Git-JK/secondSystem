package webdevelopment.secondsystem.service;

import webdevelopment.secondsystem.domain.entity.OrderForm;
import webdevelopment.secondsystem.domain.dto.OrderFormDto;

public interface OrderService {
    /**
     * 检查order中的数据是否存在性别不统一、有人选过宿舍、学号错误、验证码错误
     * @param orderFormDto
     * @return
     */
    Boolean dataCheck(OrderFormDto orderFormDto);
    /**
     * 检查order的申请时间是否合法
     * @param orderFormDto
     * @return
     */
    Boolean timeCheck(OrderFormDto orderFormDto);
    OrderFormDto orderProcessing(OrderFormDto orderFormDto);
}
