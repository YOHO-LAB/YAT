package cn.yat.mapper;

import cn.yat.entity.BshJavaCode;
import cn.yat.entity.BshJavaCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BshJavaCodeMapper {
    int countByExample(BshJavaCodeExample example);

    int deleteByExample(BshJavaCodeExample example);

    int deleteByPrimaryKey(Integer opsId);

    int insert(BshJavaCode record);

    int insertSelective(BshJavaCode record);

    List<BshJavaCode> selectByExample(BshJavaCodeExample example);

    BshJavaCode selectByPrimaryKey(Integer opsId);

    int updateByExampleSelective(@Param("record") BshJavaCode record, @Param("example") BshJavaCodeExample example);

    int updateByExample(@Param("record") BshJavaCode record, @Param("example") BshJavaCodeExample example);

    int updateByPrimaryKeySelective(BshJavaCode record);

    int updateByPrimaryKey(BshJavaCode record);
}