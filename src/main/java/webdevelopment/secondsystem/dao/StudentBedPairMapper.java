package webdevelopment.secondsystem.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import webdevelopment.secondsystem.domain.entity.StudentBedPair;

@Repository
@Mapper
public interface StudentBedPairMapper {
    /**
     * 插入一条学生-床位数据
     * @param studentBedPair
     * @return 返回受影响行数
     */
    Integer insert(StudentBedPair studentBedPair);

    /**
     * 根据studentId来删除一条学生-床位数据
     * @param studentId
     * @return 返回受影响行数
     */
    Integer deleteByStudentId(Long studentId);

    /**
     * 更新一条学生-床位数据
     * @param studentBedPair
     * @return 返回受影响行数
     */
    Integer update(StudentBedPair studentBedPair);

    /**
     * 根据学生id来查询一条学生-床位数据
     * @param studentId
     * @return 如果查询到数据则返回，否则返回NULL
     */
    StudentBedPair findByStudentId(Long studentId);
}
