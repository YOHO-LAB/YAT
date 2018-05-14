package cn.yat.mapper;

import cn.yat.entity.DataSourceLoop;
import cn.yat.entity.DataSourceLoopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataSourceLoopMapper {
    int countByExample(DataSourceLoopExample example);

    int deleteByExample(DataSourceLoopExample example);

    int deleteByPrimaryKey(Integer caseId);

    int insert(DataSourceLoop record);

    int insertSelective(DataSourceLoop record);

    List<DataSourceLoop> selectByExample(DataSourceLoopExample example);

    DataSourceLoop selectByPrimaryKey(Integer caseId);

    int updateByExampleSelective(@Param("record") DataSourceLoop record, @Param("example") DataSourceLoopExample example);

    int updateByExample(@Param("record") DataSourceLoop record, @Param("example") DataSourceLoopExample example);

    int updateByPrimaryKeySelective(DataSourceLoop record);

    int updateByPrimaryKey(DataSourceLoop record);
}