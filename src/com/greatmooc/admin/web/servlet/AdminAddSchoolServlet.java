package com.greatmooc.admin.web.servlet;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
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

import com.greatmooc.domain.School;
import com.greatmooc.service.SchoolService;

import cn.itcast.commons.CommonUtils;


public class AdminAddSchoolServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1上传3步
		 * 1.1创建工厂
		 */
		FileItemFactory factory = new DiskFileItemFactory();
		//1.2创建解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(1024*1024*2);//设置单个文件的大小上限为2M；
		//1.3解析request得到List<FileItem>
		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			//出现异常，说明单个文件大小超出了1M
			error("上传的文件大小超出了2M，请换小点的图!",request,response);
			return ;
		}
		
		//2.先把图通表单字段放在map中，再映射到School中
		Map<String, Object> map = new HashMap<String, Object>();
		for(FileItem fileItem:fileItems){
			if(fileItem.isFormField()){
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		//把，map映射到School中
		School school = CommonUtils.toBean(map, School.class);
		System.out.println(school);
		/*
		 * 3.把上传的图片保存起来
		 * 3.1获取文件名截取
		 * 3.2给文件名添加uuid前缀，防止重名
		 * 3.3校验图片的扩展名只能是JPG个PNG
		 * 3.4校验图片的尺寸
		 * 3.5指定文件的保存路径，要用ServletContext.getRealPath
		 * 3.6保存
		 * 3.7把保存的路径设置给School
		 */
		FileItem fileItem = fileItems.get(1);//获取大图
		String filename = fileItem.getName();//注意这个得到的是上传路径
		int index = filename.lastIndexOf("\\");
		if(index!=-1){//等于-1说明没有\
			filename = filename.substring(index+1);
		}
		//给文件名加上uuid，防止重名
		filename = CommonUtils.uuid()+"_"+filename;
		filename = filename.substring(index+1);
		//3.3校验扩展名
		if(!filename.toLowerCase().endsWith(".jpg")&&!filename.toLowerCase().endsWith(".png")){
			error("图片格式必须是JPG或PNG", request, response);
			return ;
		}
		/*
		 * 3.4校验图片的尺寸
		 * 	》先要保存图片，把图片new成图片对象：Image，Icon、ImageIcon、BufferImage、ImageIO
		 * 	3.41获取图片的真实路径
		 * 	3.42创建目标文件
		 * 	3.43保存文件
		 */
		String savePath = this.getServletContext().getRealPath("/school_img");
		File destFile = new File(savePath, filename);
		try {
			fileItem.write(destFile);//会把临时文件重定向到指定路径，再删除临时文件；
		} catch (Exception e) {
			throw new RuntimeException("保存临时文件，用来校验尺寸时出错了！");
		}
		/*
		 * 校验图片的尺寸
		 * 1.使用文件路径来创建ImageIcon
		 * 2.通过ImageIcon得到image
		 * 3.获取宽高校验
		 */
		ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
		Image image = icon.getImage();
		//(image.getHeight(null)/image.getWidth(null)<0.5||image.getHeight(null)/image.getWidth(null)>0.7)
		if((image.getWidth(null)>1300||image.getWidth(null)<1000)
				||(image.getHeight(null)>300||image.getHeight(null)<260)){
			error("您上传的图片尺寸不符合1180*280的比例！", request, response);
			destFile.delete();//删除图片
			return;
		}
		
		//把图片的路径设置给school
		school.setSch_bimg("school_img/"+filename);
		
		
		//保存小图
		fileItem = fileItems.get(2);//获取小图
		filename = fileItem.getName();//注意这个得到的是上传路径
		index = filename.lastIndexOf("\\");
		if(index!=-1){//等于-1说明没有\
			filename = filename.substring(index+1);
		}
		//给文件名加上uuid，防止重名
		filename = CommonUtils.uuid()+"_"+filename;
		filename = filename.substring(index+1);
		//3.3校验扩展名
		if(!(filename.toLowerCase().endsWith(".jpg")||filename.toLowerCase().endsWith(".png"))){
			error("图片格式必须是JPG或PNG", request, response);
			return ;
		}
		/*
		 * 3.4校验图片的尺寸
		 * 	》先要保存图片，把图片new成图片对象：Image，Icon、ImageIcon、BufferImage、ImageIO
		 * 	3.41获取图片的真实路径
		 * 	3.42创建目标文件
		 * 	3.43保存文件
		 */
		savePath = this.getServletContext().getRealPath("/school_img");
		destFile = new File(savePath, filename);
		try {
			fileItem.write(destFile);//会把临时文件重定向到指定路径，再删除临时文件；
		} catch (Exception e) {
			throw new RuntimeException("保存临时文件，用来校验尺寸时出错了！");
		}
		/*
		 * 校验图片的尺寸
		 * 1.使用文件路径来创建ImageIcon
		 * 2.通过ImageIcon得到image
		 * 3.获取宽高校验
		 */
		icon = new ImageIcon(destFile.getAbsolutePath());
		image = icon.getImage();
		//(image.getHeight(null)/image.getWidth(null)<0.5||image.getHeight(null)/image.getWidth(null)>0.7)
		if((image.getWidth(null)>180||image.getWidth(null)<150)
				||(image.getHeight(null)>80||image.getHeight(null)<50)){
			error("您上传的图片尺寸不符合164*60的比例！", request, response);
			destFile.delete();//删除图片
			return;
		}

		//把图片的路径设置给school
		school.setSch_simg("school_img/"+filename);
		
		//4.调用schoolService的add方法，完成添加
		school.setSch_id(CommonUtils.uuid());
		SchoolService schoolService = new SchoolService();
		schoolService.add(school);
		
		//保存成功信息，转发到msg.jsp
		request.setAttribute("msg", "添加学校成功！");
		request.getRequestDispatcher("/adminjsps/school/msg.jsp").forward(request, response);		
	}
	private void error(String msg, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/adminjsps/school/add.jsp").forward(req, resp);
		
	}
}
