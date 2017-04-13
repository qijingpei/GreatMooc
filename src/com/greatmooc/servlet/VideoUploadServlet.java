package com.greatmooc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.greatmooc.domain.Video;
import com.greatmooc.factory.DaoFactory;
import com.greatmooc.listener.myProgressListener;
import com.greatmooc.util.Time;

public class VideoUploadServlet extends HttpServlet{
	private Video video = new Video();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2048*1024);
		myProgressListener getBarListener = new myProgressListener(request);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setProgressListener(getBarListener);
		try {
			List formList = upload.parseRequest(request);
			Iterator<Object> formItem = formList.iterator();
			while (formItem.hasNext()) {
				FileItem item = (FileItem) formItem.next();
				if (item.isFormField()) {
					String paramName = item.getFieldName();
					String paramValue = item.getString("UTF-8");
					if (paramName.equals("upload_title")) {
						video.setVid_name(paramValue);
					}
					
				} else {
					String paramName = item.getFieldName();
					String fileName = item.getName().substring(item.getName().lastIndexOf("\\")+1);
					File file = new File("F:\\apache-tomcat-8.0.24-windows-x64\\apache-tomcat-8.0.24\\webapps\\mooc\\videos"
							+ "\\" + fileName);
					OutputStream out = item.getOutputStream();
					InputStream in = item.getInputStream();
					request.getSession().setAttribute("outPutStream", out);
					request.getSession().setAttribute("inPutStream", in);
					item.write(file);
					if (paramName.equals("upload_video")) {
						video.setVid_path("videos/"+fileName);
					}
				}
			}
			DaoFactory.getVideoInstance().add(video);
		} catch (FileUploadException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

}
