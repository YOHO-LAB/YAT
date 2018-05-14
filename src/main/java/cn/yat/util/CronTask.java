package cn.yat.util;


import cn.yat.service.TestcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时执行
 * */
@Component	
@EnableScheduling 
public class CronTask {
	@Autowired
	private TestcaseService tcs;

	@Scheduled(cron="0 0 3 * * ? ")
//	@Scheduled(cron="*/10 * * * * ? ")
	public void runDailyCi() {
		try {
			tcs.runDailyCi("每日CI",null,null,0,500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//    每隔5秒执行一次：*/5 * * * * ?
//@Scheduled(cron="*/2 * * * * ? ") 
//    每隔1分钟执行一次：0 */1 * * * ?
//
//    每天23点执行一次：0 0 23 * * ?
//
//    每天凌晨1点执行一次：0 0 1 * * ?
//
//    每月1号凌晨1点执行一次：0 0 1 1 * ?
//
//    每月最后一天23点执行一次：0 0 23 L * ?
//
//    每周星期天凌晨1点实行一次：0 0 1 ? * L
//
//    在26分、29分、33分执行一次：0 26,29,33 * * * ?
//
//    每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?

}
