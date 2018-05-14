package cn.yat.mapper;

import cn.yat.entity.Db;
import cn.yat.entity.DbExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DbMapper {
    int countByExample(DbExample example);

    int deleteByExample(DbExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Db record);

    int insertSelective(Db record);

    List<Db> selectByExample(DbExample example);

    Db selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Db record, @Param("example") DbExample example);

    int updateByExample(@Param("record") Db record, @Param("example") DbExample example);

    int updateByPrimaryKeySelective(Db record);

    int updateByPrimaryKey(Db record);
}