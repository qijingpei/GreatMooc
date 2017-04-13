package com.greatmooc.web.servlet;

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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.commons.CommonUtils;

import com.greatmooc.domain.User;
import com.greatmooc.service.PersonService;




/*
 * 修改用户头像、昵称、个性签名
 */
public class EditPersonServlet extends HttpServlet {
	private PersonService personService=new PersonService();
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("到了这里1111");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		/*
		 * 上传三步：
		 * 1.解析工厂
		 * 2.创建解析器
		 * 3.解析request得到表单字段！
		 * 		把表单字段封装到User对象中
		 * 		保存上传文件，把保存的路径设置给User的user_bimg属性
		 * 		调用service方法修改数据库中对应的User对象
		 * 		调用load方法
		 */
		DiskFileItemFactory factory=new DiskFileItemFactory( );
		ServletFileUpload sfu=new ServletFileUpload(factory);
		//设置单个文件大小限制为1M
		sfu.setFileSizeMax(1024*1024);
		try {
			List<FileItem> fileItemList=sfu.parseRequest(request);
			/*
			 * 把fileItemList中的数据封装到User对象中
			 * 		>把所有普通表单字段数据先封装到Map中
			 * 		>再把map中的数据封装到User对象中
			 */
			Map<String,String> map=new HashMap<String,String>();
			for(FileItem fileItem:fileItemList) {
				if(fileItem.isFormField()) {//普通表单项
				//map中key是表单项名称，值是值
					map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));						
				}
			}
			//System.out.println(fileItemList);
			//一键封装
			User user=CommonUtils.toBean(map, User.class);
			/*
			 * 校验：
			 * 1.昵称的为空和长度校验，个性签名的长度校验
			 * 
			 */
			String username=user.getUsername();
			if(username==null||username.trim().isEmpty()||username.length()<2||username.length()>15) {
				request.setAttribute("msg", "修改失败：用户名长度必须在2-15之间，且不能为空！");
				request.getRequestDispatcher("/jsps/person/left.jsp").forward(request, response);
				return ;
			}
			String user_desc=user.getUser_desc();
			if(user_desc.length()>95) {
				request.setAttribute("msg", "修改失败：用户名长度必须在95字之内，且不能为空！");
				request.getRequestDispatcher("/jsps/person/left.jsp").forward(request, response);
				return ;
			}
				
			//System.out.println(user);
			/*
			 * 2.保存上传的文件
			 * 		>获取要保存的目录
			 * 		>获取要保存的文件名称
			 */
			//得到要保存的目录（即真实路径，这里制定保存到“user_img”文件夹下）
			/*
			 * 如果用户没有上传头像，那么就不再读取文件!!
			 */
			//System.out.println("文件1："+fileItemList.get(0).getName());
			//System.out.println("上传的文件名为："+fileItemList.get(3).getName());
			//记录下原来图片的存储位置，方便再次调用
			if(fileItemList.get(3).getName()==null||fileItemList.get(3).getName()=="") {
				//调用service保存修改的信息，跳转
				//保存原有的照片路径给User对象的user_bimg属性
				user.setUser_img(personService.findByUser_id(user.getUser_id()).getUser_img());
				//完成更改
				personService.edit(user);
				request.getSession().setAttribute("session_user", personService.findByUser_id(user.getUser_id()));
				request.getRequestDispatcher("/jsps/person/left.jsp").forward(request, response);
				return ;
			}
				
			String savepath=this.getServletContext().getRealPath("/user_img/");
			/*
			 * 处理浏览器上传的绝对路径问题
			 * 这次图片的顺序是第二个，也就是get(3)
			 */
			String filename=fileItemList.get(3).getName();//可能抛出异常valid.....
			//System.out.println(filename);
			/*
			 * 校验：如果上传的文件不是以jpg、jpeg、png、gif结尾，报错
			 */
			int ix=filename.lastIndexOf(".");
			String houzhui=filename.substring(ix+1);
			if((houzhui.equalsIgnoreCase("jpg")||houzhui.equalsIgnoreCase("jpeg")||
					houzhui.equalsIgnoreCase("png")||houzhui.equalsIgnoreCase("gif"))==false) {
				request.setAttribute("msg", "上传失败：头像图片必须是jpg、jpeg、png或者gif格式！");
				request.getRequestDispatcher("/jsps/person/left.jsp").forward(request, response);
				return ;
			}
			
			int index=filename.lastIndexOf("\\");
			if(index !=-1) {
				filename=filename.substring(index+1);
			}
			
			//加uuid前缀，处理文件同名问题
			String savename=CommonUtils.uuid()+"_"+filename;
			/*
			 * 开始保存文件
			 */
			//使用目录和文件名称创建目标文件
			File destFile=new File(savepath,savename);//在“savaname”文件夹下新建文件“savename”
			//保存
			fileItemList.get(3).write(destFile);//在"destFile"处保存上传的图片
			//将图片路径设置给User对象
			user.setUser_img("user_img/"+savename);
			/*
			 * 3.调用service的edit()方法完成图片的修改
			 */
			//System.out.println(user);
			personService.edit(user);
			/*
			 * 4.返回到个人用户信息
			 */
			//System.out.println("要跳转啦！");
			//往session域存储User对象
			request.getSession().setAttribute("session_user", personService.findByUser_id(user.getUser_id()));
			request.getRequestDispatcher("/jsps/person/left.jsp").forward(request, response);
			//request.getRequestDispatcher("/PersonServlet?method=load&user_id='${session_user.user_id}'");
		} catch (FileSizeLimitExceededException e) { //校验文件大小失败
			//System.out.println("校验文件大小");
			request.setAttribute("msg", "上传失败：您上传的文件超出了1MB");
			request.getRequestDispatcher("/jsps/person/left.jsp")
					.forward(request, response);
		} catch (Exception e) {
			throw new RuntimeException(e+"修改用户信息时出现了错误");
		}
		
	}

}
