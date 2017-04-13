package com.greatmooc.admin.web.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.greatmooc.domain.Category;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;
import com.greatmooc.service.CategoryService;
import com.greatmooc.service.CourseService;
import com.greatmooc.service.SchoolService;

import cn.itcast.commons.CommonUtils;


public class AdminAddCourseServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//1.上传三步，
		//1.1创建工厂
		FileItemFactory factory = new DiskFileItemFactory();
		//1.2通过工厂，创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024*1024*2);//设置上传的单个文件大小为3M
		//1.3解析request得到FileItemList
		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异常，说明单个文件超出了3M
			error("上传的文件超出了3M!", request, response);
			return;
		}
		
		/*
		 * 2.把FileItemList封装到Course中
		 * 首先把普通表单字段放到map中，再把map转换为Course和category对象，然后建立两者之间的联系
		 */
		Map<String , Object> map = new HashMap<String,Object>();
		for(FileItem fileItem:fileItems){
			if(fileItem.isFormField()){//若是 普通表单字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		
		Course course = CommonUtils.toBean(map, Course.class);
		School school = CommonUtils.toBean(map, School.class);
		Teacher teacher = CommonUtils.toBean(map, Teacher.class);
		Category category = CommonUtils.toBean(map, Category.class);
		course.setSchool(school);
		course.setTeacher(teacher);
		course.setCategory(category);
		
		/*
		 * 3.把上传的图片保存起来
		 * 》获取文件名，截取
		 * 》给文件名添加前缀uuid防止图片重名
		 * 》校验图片的扩展名，只能是.jsp,.png
		 * 》校验图片的尺寸
		 * 》指定图片的保存路径，用到ServletContext的getRealPath函数
		 * 》保存图片
		 * 》把图片的路径设置给Course
		 */
		FileItem fileItem = fileItems.get(1);//获取第二个表单项，即大图
		String filename = fileItem.getName();
		//3.1截取文件名，因为部分浏览器上传的是绝对路径
		int index = filename.lastIndexOf("\\");
		if(index!=-1){
			filename = filename.substring(index+1);
		}
		//3.2给文件名前缀加上uuid防止重名
		filename = CommonUtils.uuid()+"_"+filename ;
		//3.3校验图片的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")&&!filename.toLowerCase().endsWith(".png")){
			error("上传图片的格式必须是JPG或PNG格式！", request, response);
			return ;
		}
		/*
		 * 3.4校验图片的尺寸
		 * 保存上传的图片，把图片new成图片对象：Image，Icon,ImageIcon,BufferedImage,ImageIO
		 */
		//3.4.1获取真实路径，即项目的路径+文件名
		String savepath =this.getServletContext().getRealPath("/course_img");
		//3.4.2创建目标文件
		File destFile = new File(savepath,filename);
		//3.4.3保存文件
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException("保存临时文件时"+e);
		}
		
		//3.4.4校验尺寸,使用文件路径创建ImageIcon
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		//使用icon得到image
		Image image = icon.getImage();
		//获取宽高进行校验:标准是600*340,先校验比例，再校验宽高
		if((image.getWidth(null)>620||image.getWidth(null)<550)
				||(image.getHeight(null)>360||image.getHeight(null)<300)
				){
			error("您上传的图片尺寸不符合590*330的标准！", request, response);
			destFile.delete();//删除图片
			return;
		}
		
		//把大图设置给course
		course.setCou_bimg("course_img/"+filename);
		
		
		fileItem = fileItems.get(2);//获取第3个表单项，即小图
		filename = fileItem.getName();
		//3.1截取文件名，因为部分浏览器上传的是绝对路径
		index = filename.lastIndexOf("\\");
		if(index!=-1){
			filename = filename.substring(index+1);
		}
		//3.2给文件名前缀加上uuid防止重名
		filename = CommonUtils.uuid()+"_"+filename ;
		//3.3校验图片的扩展名
		if(!filename.toLowerCase().endsWith(".jpg")&&!filename.toLowerCase().endsWith(".png")){
			error("上传的大图片格式必须是JPG或PNG格式！", request, response);
			return ;
		}
		/*
		 * 3.4校验图片的尺寸
		 * 保存上传的图片，把图片new成图片对象：Image，Icon,ImageIcon,BufferedImage,ImageIO
		 */
		//3.4.1获取真实路径，即项目的路径+文件名
		savepath =this.getServletContext().getRealPath("/course_img");
		//3.4.2创建目标文件
		destFile = new File(savepath,filename);
		//3.4.3保存文件
		try {
			fileItem.write(destFile);//它会把临时文件重定向到指定的路径，再删除临时文件
		} catch (Exception e) {
			throw new RuntimeException("保存临时文件时"+e);
		}
		
		//3.4.4校验尺寸,使用文件路径创建ImageIcon
		icon = new ImageIcon(destFile.getAbsolutePath());
		//使用icon得到image
		image = icon.getImage();
		//获取宽高进行校验:标准是600*340,先校验比例，再校验宽高
		if((image.getWidth(null)>260||image.getWidth(null)<220)
				||(image.getHeight(null)>150||image.getHeight(null)<120)){
			error("您上传的小图片尺寸不符合240*135的标准！", request, response);
			destFile.delete();//删除图片
			return;
		}
		
		//把大图设置给course
		course.setCou_simg("course_img/"+filename);
		
		
		//为course设置id，调用service完成添加
		String cou_id = CommonUtils.uuid();
		course.setCou_id(cou_id);
		new CourseService().add(course);
		
		request.setAttribute("cou_id", cou_id);
		request.setAttribute("msg", "添加课程成功！");
		request.getRequestDispatcher("/adminjsps/msg.jsp").forward(request, response);
		
	}

	
	
	private void error(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("msg", msg);
		System.out.println(msg);
		request.setAttribute("parents", new CategoryService().findParents());//所有一级分类 
		request.setAttribute("schools", new SchoolService().findAll());//所有学校 
		request.getRequestDispatcher("/adminjsps/course/add.jsp").
				forward(request, response);
	}

}
