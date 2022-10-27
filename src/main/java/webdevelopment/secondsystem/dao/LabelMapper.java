package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.Label;

import java.util.List;

@Repository
@Mapper
public interface LabelMapper {
    /**
     * 插入一条label数据
     * @param label
     * @return 返回受影响行数
     */
    Integer insert(Label label);

    /**
     * 根据label name删除一条label数据
     * @param name
     * @return 返回受影响行数
     */
    Integer deleteByName(String name);

    /**
     * 更新一条label数据
     * @param label
     * @return 返回受影响行数
     */
    Integer update(Label label);

    /**
     * 根据label name查询一条label数据
     * @param name
     * @return 若存在该数据则返回，否则返回null
     */
    Label findByName(String name);

    /**
     * 获取label列表
     * @return 返回Label List
     */
    List<Label> getLabelList();
}
