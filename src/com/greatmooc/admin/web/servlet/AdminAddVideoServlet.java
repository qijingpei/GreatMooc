package com.greatmooc.admin.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Video;

import com.greatmooc.service.VideoService;

public class AdminAddVideoServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		

	//1.上传三步，
		//1.1创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//1.2通过工厂，创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024*1024*500);//设置上传的单个文件大小为500M
		//1.3解析request得到FileItemList
		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异常，说明单个文件超出了3M
			error("上传的文件超出了500M!", request, response);
			return;
		}
		
		/*
		 * 2.把FileItemList封装到Course中
		 * 首先把普通表单字段放到map中，再把map转换为Course和category对象，然后建立两者之间的联系
		 */
		Map<String , Object> map = new HashMap<String , Object>();
		for(FileItem fileItem:fileItems){
			if(fileItem.isFormField()){//若是 普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		
		String cou_id = (String) map.get("cou_id");
		System.out.println(cou_id);
		
		Chapter chapter = CommonUtils.toBean(map, Chapter.class);
		Video video = CommonUtils.toBean(map, Video.class);
		video.setChapter(chapter);
		
		/*
		 * 3.把上传的视频保存起来
		 * 》获取文件名，截取
		 * 》给文件名添加前缀uuid防止图片重名
		 * 》校验图片的扩展名，只能是.mp4
		 * 》校验图片的尺寸
		 * 》指定图片的保存路径，用到ServletContext的getRealPath函数
		 * 》保存图片
		 * 》把图片的路径设置给Course
		 */
		FileItem fileItem = fileItems.get(1);//获取第二个表单项，即视频
		String filename = fileItem.getName();
		//3.1截取文件名，因为部分浏览器上传的是绝对路径
		int index = filename.lastIndexOf("\\");
		if(index!=-1){
			filename = filename.substring(index+1);
		}
		//3.2给文件名前缀加上uuid防止重名
		filename = CommonUtils.uuid()+"_"+filename ;
		//3.3校验图片的扩展名
		if(!filename.toLowerCase().endsWith(".mp4")&&!filename.toLowerCase().endsWith(".flv")){
			error("上传图片的格式必须是.mp4或.flv格式！", request, response);
			return ;
		}
		/*
		 * 3.4校验图片的尺寸
		 * 保存上传的图片，把图片new成图片对象：Image，Icon,ImageIcon,BufferedImage,ImageIO
		 */
		//3.4.1获取真实路径，即项目的路径+文件名
		String savepath =this.getServletContext().getRealPath("/videos");
		//3.4.2创建目标文件
		File destFile = new File(savepath,filename);
		//3.4.3保存文件
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException("保存临时文件时"+e);
		}
		
		
		
		//视频路径设置给video
		video.setVid_path("videos/"+filename);
		
		
		//为video设置id，调用service完成添加
		String vid_id = CommonUtils.uuid();
		video.setVid_id(vid_id);
		new VideoService().add(video);
		
		request.setAttribute("cou_id", cou_id);
		request.setAttribute("msg", "添加课程成功！");
		request.getRequestDispatcher("/admin/AdminChapterServlet?method=findAll&cou_id="+cou_id).forward(request, response);
		
	}

	
	private void error(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/adminjsps/video/add.jsp").
				forward(request, response);
	}
}
