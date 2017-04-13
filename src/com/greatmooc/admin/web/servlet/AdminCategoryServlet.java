package com.greatmooc.admin.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Category;
import com.greatmooc.service.CategoryService;
import com.greatmooc.service.CourseService;


import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService =  new CategoryService();//前后台共用一个Service
	private CourseService courseService = new CourseService();//挎包引用
	/**
	 * 显示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();//和前台公用一个findAll方法
		request.setAttribute("parents", parents);
		return "f:/adminjsps/category/list.jsp";
	}
	/**
	 * 添加一级分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addParent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*【因为后台管理是自己人，所以就不用二次校验了】
		 * 1.封装表单数据到category中，只有分类名称和分类描述
		 * 2.补全cate_id，
		 * 3.调用service的add方法
		 * 4.返回到findAll方法显示所有分类
		 */
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCate_id(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request,response);
	}
	//添加二级分类之前，显示出所有的一级分类，供选择
	public String addChildPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取参数pcate_id;
		 * 2.查找所有的一级分类
		 * 3.保存pcate_id和parents
		 * 4.返回findAll方法
		 */
		String pcate_id  = request.getParameter("pcate_id");
		List<Category> parents = categoryService.findParents();//只找出一级分类的所有信息，不找他们的子分类
		request.setAttribute("pcate_id", pcate_id);
		request.setAttribute("parents", parents);
		return "f:/adminjsps/category/add2.jsp";
	}
	/**
	 * 添加二级分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.强表单数据封装到category中
		 * 2.获取参数pcate_id
		 * 3.补全cate_id
		 * 4.设置二级分类的parent属性
		 * 5.调用service方法添加，返回findAll方法显示所有
		 */
		Category child = CommonUtils.toBean(request.getParameterMap(), Category.class);
		String pcate_id = request.getParameter("pcate_id");
		child.setCate_id(CommonUtils.uuid());
		Category parent = new Category();
		parent.setCate_id(pcate_id);
		child.setParent(parent);
		categoryService.add(child);
		return findAll(request, response);
	}
	//修改一级分类之前的加载信息
	public String editParentPre(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	String cate_id =  request.getParameter("cate_id");
	Category parent =  categoryService.load(cate_id);
	request.setAttribute("parent", parent);
	return "f:/adminjsps/category/edit.jsp";
	}
	//修改一级分类
	public String editParent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category parent = CommonUtils.toBean(request.getParameterMap(),Category.class);
		categoryService.edit(parent);
		return findAll(request, response);
	}
	//修改二级分类前的加载信息
	public String editChildPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取参数：二级分类id
		 * 2.通过id调用service的load方法，得到child
		 * 3.查找所有的一级分类
		 * 4.保存child和一级分类到request域中
		 * 【在jsp页面通过child的pcate_id和parent的cate_id是否匹配，来选中所属的一级分类】
		 */
		String cate_id = request.getParameter("cate_id");
		Category child = categoryService.load(cate_id);
		request.setAttribute("child", child);
		request.setAttribute("parents", categoryService.findParents());
		return "f:/adminjsps/category/edit2.jsp";
	}
	//修改二级分类
	public String editChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Category child = CommonUtils.toBean(request.getParameterMap(),Category.class);
		String pcate_id = request.getParameter("pcate_id");
		Category parent = new Category();
		parent.setCate_id(pcate_id);
		
		child.setParent(parent);
		categoryService.edit(child);
		return findAll(request, response);
	}
	//删除一级分类
	public String deleteParent(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pcate_id = request.getParameter("cate_id");
		int count = categoryService.findChildrenCount(pcate_id);
		if(count>0){
			request.setAttribute("msg", "该分类下面有二级分类，不能删除！！");
			return "f:/adminjsps/msg.jsp";
		}else{
			categoryService.delete(pcate_id);
			return findAll(request, response);
		}
	}
	//删除2级分类
	public String deleteChild(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cate_id = request.getParameter("cate_id");
		int count = courseService.findCourseCountByCategory(cate_id);
		if(count>0){
			request.setAttribute("msg", "该分类下面有课程，不能删除！！");
			return "f:/adminjsps/msg.jsp";
		}else{
			categoryService.delete(cate_id);
			return findAll(request, response);
		}
	}

	
}
