package cn.yat.util;

import cn.yat.mapper.RecordMapper;
import cn.yat.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RecordUtil {
    @Autowired
    private RecordMapper recordMapper;

    public void addRecord(int userId , String operation , String operateData) throws Exception{
        Record oRecord = new Record();
        oRecord.setUserId(userId);
        oRecord.setOperation(operation);
        oRecord.setOperateData(operateData);
        oRecord.setOperateTime(new Date());
        recordMapper.insertSelective(oRecord);
    }
}
