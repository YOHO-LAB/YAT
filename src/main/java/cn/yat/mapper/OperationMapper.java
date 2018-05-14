package cn.yat.mapper;

import cn.yat.entity.Operation;
import cn.yat.entity.OperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OperationMapper {
    int countByExample(OperationExample example);

    int deleteByExample(OperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Operation record);

    int insertSelective(Operation record);

    List<Operation> selectByExample(OperationExample example);

    Operation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Operation record, @Param("example") OperationExample example);

    int updateByExample(@Param("record") Operation record, @Param("example") OperationExample example);

    int updateByPrimaryKeySelective(Operation record);

    int updateByPrimaryKey(Operation record);
}