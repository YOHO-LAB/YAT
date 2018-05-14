package cn.yat.util;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

@Component
public class AsyncExecutorUtil<T> {

	private ExecutorService executor ;

	private void initExecutor(){
		
		if(executor == null || executor.isShutdown()){
			executor = Executors.newCachedThreadPool();
		}
	}
	public List<Future<T>> doInvokeAll(List<Callable<T>> taskList ,long time){
		initExecutor();
		List<Future<T>> list = Lists.newArrayList();
		try {
			list = executor.invokeAll(taskList , time , TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}
		return list;
	}
	public List<Future<T>> doInvokeAll(List<Callable<T>> taskList ){
		initExecutor();
		List<Future<T>> list = Lists.newArrayList();
		try {
			list = executor.invokeAll(taskList , 5000 , TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}
		return list;
	}
	public List<Future<T>> doInvokeAllNoTimeLimit(List<Callable<T>> taskList){
		initExecutor();
		List<Future<T>> list = Lists.newArrayList();
		try {
			list = executor.invokeAll(taskList);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			executor.shutdown();
		}
		return list;
	}
	
	public void doExecute(Runnable task){
		initExecutor();
		executor.execute(task);
	}
	public void doExecuteAll(List<Runnable> taskList){
		initExecutor();
		for(Runnable task : taskList){
			executor.execute(task);
		}
	}
	
	
	
	public void invokAll(List<T> list){
		List<Callable<String>> taskList = Lists.newArrayList();
		for(T t : list){
			taskList.add(new Callable<String>() {
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					return "";
				}
			});
		}
	}
	public void ececuteAll(List<T> list){
		List<Runnable> taskList = Lists.newArrayList();
		for(T t : list){
			taskList.add(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
}
