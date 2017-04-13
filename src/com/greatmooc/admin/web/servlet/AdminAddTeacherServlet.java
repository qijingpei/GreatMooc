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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;
import com.greatmooc.service.SchoolService;
import com.greatmooc.service.TeacherService;



public class AdminAddTeacherServlet extends HttpServlet {
	private TeacherService teacherService=new TeacherService();
	private SchoolService schoolService=new SchoolService();
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 *  >上传三步：
				*创建工厂
				*创建解析器
				*解析request得到表单字段！
			>把表单字段封装到Teacher对象中
			>保存上传文件，把保存的路径设置给Teacher的tea_bimg、tea_simg属性
			>调用service方法保存Teacher对象到数据库中
			>调用findAll()(其实调用load()方法更好)
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory(15*1024,new File("F:/temp"));
		ServletFileUpload sfu=new ServletFileUpload(factory);
		try {
			List<FileItem> fileItemList=sfu.parseRequest(request);
			/*
			 * 把fileItemList中的数据封装到Teacher对象中
			 * 	>把所有普通表单字段数据先封装到Map中
			 *  >再把map中的数据封装到Teacher对象中
			 */
			Map<String,String> map=new HashMap<String,String>();
			for(FileItem fileItem:fileItemList) {
				if(fileItem.isFormField()) {
					//普通表单项
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));//key是属性名字，值是值
				}
			}
			
			//一键封装  
			Teacher teacher=CommonUtils.toBean(map, Teacher.class);
			//为Teacher指定tea_id
			teacher.setTea_id(CommonUtils.uuid());
			/*
			 * 需要把Map中的sch_id封装到School对象中，再把School赋给Teacher
			 */
			School school=CommonUtils.toBean(map, School.class);
			teacher.setSchool(school);
			
			/*
			 * 2.保存上传的文件
			 *   >获取要保存的目录
			 *   >获取要保存的文件名称
			 */
			//得到保存的目录（即真实路径，这里指定保存到“teacher_img”文件夹下）
			//（有一种说法是：为了安全考虑要放到WEB—INF下，而不是WebRoot下）（不然如果有人上传jsp到WebRoot下，会打乱我们的项目）
			String savepath=this.getServletContext().getRealPath("/teacher_img/");
			/*
			 * 处理浏览器上传的绝对路径问题
			 * 360浏览器上传的不是绝对路径而是文件名，
			 * 所以我这里的fileItemList.get(1).getName()返回的是图片的所有路径，
			 * 所以我将这个路径截取，截取出这个照片的文件名出来
			 */
			String filename1=fileItemList.get(1).getName();
			String filename2=fileItemList.get(2).getName();
			
			int index1=filename1.lastIndexOf("\\");
			int index2=filename2.lastIndexOf("\\");
			
			if(index1!=-1) {
				filename1=filename1.substring(index1+1);
			}
			if(index2!=-1) {
				filename2=filename2.substring(index2+1);
			}
			
			/*
			 * 检验文件的扩展名
			 */
			if(!filename1.toLowerCase().endsWith("jpg") && !filename1.toLowerCase().endsWith("png") && !filename1.toLowerCase().endsWith("jpeg")) {
				request.setAttribute("msg", "您上传的大图片不是JPG或PNG扩展名！");
				request.setAttribute("schoolList", schoolService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/teacher/add.jsp").forward(request, response);
				return ;//别忘了return
			}
			if(!filename2.toLowerCase().endsWith("jpg") && !filename2.toLowerCase().endsWith("png") && !filename2.toLowerCase().endsWith("jpeg")) {
				request.setAttribute("msg", "您上传的小图片不是JPG或PNG扩展名！");
				request.setAttribute("schoolList", schoolService.findAll());
				request.getRequestDispatcher("/adminjsps/admin/teacher/add.jsp").forward(request, response);
				return ;//别忘了return
			}
			/*
			 * 3.给文件名称添加uuid前缀，处理文件同名问题
			 */
			String savename1=CommonUtils.uuid()+"_"+filename1;
			String savename2=CommonUtils.uuid()+"_"+filename2;
			/*
			 * 开始保存文件
			 */
			//使用目录和文件名称创建目标文件
			File destFile1=new File(savepath,savename1);
			File destFile2=new File(savepath,savename2);
			System.out.println(savepath);
			System.out.println(destFile1);
			System.out.println(destFile2);
			//保存上传文件到目标文件位置
			fileItemList.get(1).write(destFile1);
			fileItemList.get(2).write(destFile2);
			
			/*
			 * 3.设置Teacher对象的bimg和simg,即把图片的路径设置给Teacher的bimg和simg
			 */
			teacher.setTea_bimg("teacher_img/"+savename1);
			teacher.setTea_simg("teacher_img/"+savename2);
			/*
			 * 4.使用service完成保存
			 */
			teacherService.add(teacher);
			
			/*
			 * 5.返回到教师列表
			 */
			request.getRequestDispatcher("/admin/AdminTeacherServlet?method=findAll").forward(request, response);
		} catch (FileUploadException e) {
			throw new RuntimeException("教师图片上传出现了错误！");
		} catch (Exception e) {
			throw new RuntimeException("增加教师出现了错误！"+e);
		}
		
	}

}
