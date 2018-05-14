package cn.yat.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class FileRWUtil {

	public static JSONObject readFile(String filepath) {
		JSONObject res = new JSONObject();
		res.put("success", false);
		res.put("data", "");
		try {
			File f = new File(filepath);
			JSONArray arr = new JSONArray();
			if (f.exists()) {
				FileReader read = new FileReader(f);
				BufferedReader buff = new BufferedReader(read);
				try {
					boolean isEnd = false;
					int i = 0;
					while (!isEnd) {
						String line;
						i++;
						line = buff.readLine();
						if (line == null) {
							res.put("totalLine", i - 1);
							isEnd = true;
						} else {
							JSONObject obj = new JSONObject();
							obj.put("line", i);
							obj.put("value", line);
							arr.add(obj);
						}
					}
					res.put("success", true);
					res.put("data", arr);
				} catch (Exception e) {
					res.put("success", false);
					res.put("data", e.getMessage());
				} finally {
					buff.close();
					read.close();
				}
			} else {
				res.put("success", false);
				res.put("data", "文件不存在！");
			}
		} catch (Exception e) {
			res.put("success", false);
			res.put("data", e.getMessage());
		}

		return res;
	}

	public static JSONObject writeFile(String filepath , JSONArray data) {
		JSONObject res = new JSONObject();
		res.put("success", false);
		res.put("data", "");
		try {
			File f = new File(filepath);
			if(f.exists()){
		        FileWriter fw = new FileWriter(f);
	            BufferedWriter bw = new BufferedWriter(fw);
	            for(int i=0;i<data.size();i++){
					String value = data.getJSONObject(i).getString("value");
					bw.write(value);
					if(i<data.size()-1){
						bw.newLine();
					}
				}
	            res.put("success", true);
	    		res.put("data", "success");
	            bw.close();
	            fw.close();
			}
		} catch (Exception e) {
			res.put("success", false);
			res.put("data", e.getMessage());
		}

		return res;
	}

	public static JSONObject readFile(String filepath, int page, int linePerPage) {
		JSONObject res = new JSONObject();
		res.put("success", false);
		res.put("data", "");
		try {
			File f = new File(filepath);
			JSONArray arr = new JSONArray();
			if (f.exists()) {
				FileReader read = new FileReader(f);
				BufferedReader buff = new BufferedReader(read);
				try {
					boolean isEnd = false;
					int i = 0;
					while (!isEnd) {
						String line;
						i++;
						line = buff.readLine();
						if (line == null) {
							res.put("totalLine", i - 1);
							res.put("totalPage", Math.ceil((i - 1) / linePerPage));
							isEnd = true;
						} else {
							if (i > (page - 1) * linePerPage && i <= page * linePerPage) {
								JSONObject obj = new JSONObject();
								obj.put("line", i);
								obj.put("value", line);
								arr.add(obj);
							}
						}

					}
					res.put("success", true);
					res.put("data", arr);
				} catch (Exception e) {
					res.put("success", false);
					res.put("data", e.getMessage());
				} finally {
					buff.close();
					read.close();
				}
			} else {
				res.put("success", false);
				res.put("data", "文件不存在！");
			}
		} catch (Exception e) {
			res.put("success", false);
			res.put("data", e.getMessage());
		}

		return res;
	}

	public static JSONObject writeFile(String filepath , JSONArray data , int page , int linePerPage) {
		JSONObject res = new JSONObject();
		res.put("success", false);
		res.put("data", "");
		try {
			File f = new File(filepath);
			if(f.isFile() && f.exists()){
				JSONObject oRead = readFile(filepath);
				if(oRead.getBoolean("success")){
					JSONArray arr = oRead.getJSONArray("data");
					for(int i=0;i<linePerPage;i++){
						int c = (page-1)*linePerPage+i;
						JSONObject oData = arr.getJSONObject(c);
						oData.put("value", data.getJSONObject(i).getString("value"));
					}
					FileWriter fw = new FileWriter(f);
					BufferedWriter bw = new BufferedWriter(fw);
		            for(int i=0;i<arr.size();i++){
						String value = arr.getJSONObject(i).getString("value");
						bw.write(value);
						if(i<arr.size()-1){
							bw.newLine();
						}
					}
		            res.put("success", true);
		    		res.put("data", "success");
		    		bw.close();
		            fw.close();
		            
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.put("success", false);
			res.put("data", e.getMessage());
		}
		return res;
	}

	public static List<String> readFileList(String filepath) throws Exception{
		List<String> list = Lists.newArrayList();
		File f = new File(filepath);
		FileReader read = new FileReader(f);
		BufferedReader buff = new BufferedReader(read);
		boolean isEnd = false;
		int i = 0;
		while (!isEnd) {
			String line;
			i++;
			line = buff.readLine();
			if (line == null) {
				isEnd = true;
			} else {
				list.add(line);
			}
		}
		buff.close();
		read.close();
		return list;
	}

	public static void writeFileList(String filepath , List<String> list , boolean append) throws Exception{
		File f = new File(filepath);
		FileWriter fw = new FileWriter(f,append);
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i=0;i<list.size();i++){
			String value = list.get(i);
			bw.write(value);
			if(i<list.size()-1){
				bw.newLine();
			}
		}
		bw.close();
		fw.close();
	}

	public static String uploadFile(String fullSaveDirPath , int maxSizeMb ,HttpServletRequest request , boolean overWriteSameName) throws Exception{
		if(fullSaveDirPath==null || fullSaveDirPath.trim().equals("")){
			throw new Exception("目录不能为空！");
		}
		String tmpPath = "/tmp";
		File fileDir = new File(fullSaveDirPath);
		if(!fileDir.exists()){
			throw new Exception("目录不存在！");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//最大缓存
		factory.setSizeThreshold(5*1024);
		//设置临时文件目录
		factory.setRepository(new File(tmpPath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxSizeMb*1024*1024);
		List<FileItem> items = upload.parseRequest(request);
		String allFileNames = "";
		for (FileItem item : items) {
			if(!item.isFormField()){
				String fileName = item.getName();
				if(!overWriteSameName){
					File file = new File(fullSaveDirPath+"/"+fileName);
					if(file.exists()){
						throw new Exception("文件<"+fullSaveDirPath+"/"+fileName+">已存在！");
					}
				}
				allFileNames += fileName + " ";
			}
		}

		for (FileItem item : items) {
			if(!item.isFormField()){
				String fileName = item.getName();
				File file = new File(fullSaveDirPath+"/"+fileName);
				item.write(file);
			}
		}

		return allFileNames;
	}
	public static void downloadFile(String fullFileParentPath ,String fileName,HttpServletResponse response) throws Exception{
		String fullPath = fullFileParentPath + "/" + fileName;
		File f = new File(fullPath);
		if(f.exists()){
			//重置response
			response.reset();
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			//解决中文文件名显示问题
			response.addHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
			//设置文件长度
			int fileLength = (int)f.length();
			response.setContentLength(fileLength);
			if(fileLength!=0){
				InputStream inStream = new FileInputStream(f);
				byte[] buf = new byte[4096];
				//创建输出流
				ServletOutputStream servletOS = response.getOutputStream();
				int readLength;
				//读取文件内容并写入到response的输出流当中
				while((readLength = inStream.read(buf))!=-1){
					servletOS.write(buf, 0, readLength);
				}
				//关闭输入流
				inStream.close();
				//刷新输出流缓冲
				servletOS.flush();
				//关闭输出流
				servletOS.close();
			}
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("文件<"+fullPath+">不存在！");
		}
	}



	public static void main(String [] args){
		JSONArray arr = new JSONArray();
		for(int i=0;i<3;i++){
			arr.add(JSONObject.parseObject("{\"line\":"+(i+1)+",\"value\":\"哈哈"+(i+1)+"\"}"));
		}
		System.out.println(writeFile("C:/Users/yoho/Desktop/test.txt", arr, 2, 3));
	}
}
