package cn.yat.mapper;

import cn.yat.entity.Testcase;
import cn.yat.entity.TestcaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestcaseMapper {
    int countByExample(TestcaseExample example);

    int deleteByExample(TestcaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Testcase record);

    int insertSelective(Testcase record);

    List<Testcase> selectByExample(TestcaseExample example);

    Testcase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Testcase record, @Param("example") TestcaseExample example);

    int updateByExample(@Param("record") Testcase record, @Param("example") TestcaseExample example);

    int updateByPrimaryKeySelective(Testcase record);

    int updateByPrimaryKey(Testcase record);
}