package cn.yat.mapper;

import cn.yat.entity.RunSummary;
import cn.yat.entity.RunSummaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RunSummaryMapper {
    int countByExample(RunSummaryExample example);

    int deleteByExample(RunSummaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RunSummary record);

    int insertSelective(RunSummary record);

    List<RunSummary> selectByExample(RunSummaryExample example);

    RunSummary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RunSummary record, @Param("example") RunSummaryExample example);

    int updateByExample(@Param("record") RunSummary record, @Param("example") RunSummaryExample example);

    int updateByPrimaryKeySelective(RunSummary record);

    int updateByPrimaryKey(RunSummary record);
}