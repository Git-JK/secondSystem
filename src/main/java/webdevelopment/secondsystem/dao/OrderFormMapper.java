package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.OrderForm;

@Repository
@Mapper
public interface OrderFormMapper {
    /**
     * 插入一条order数据
     * @param orderForm
     * @return 返回受影响行数
     */
    Integer insert(OrderForm orderForm);

    /**
     * 根据order id删除一条order数据
     * @param orderId
     * @return 返回受影响行数
     */
    Integer deleteById(Integer orderId);

    /**
     * 更新一条order数据
     * @param orderForm
     * @return 返回受影响行数
     */
    Integer update(OrderForm orderForm);

    /**
     * 根据order id查找一条order数据
     * @param orderId
     * @return
     */
    OrderForm findById(Integer orderId);
}
