package com.greatmooc.admin.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.greatmooc.domain.Category;
import com.greatmooc.domain.Chapter;
import com.greatmooc.domain.Course;
import com.greatmooc.domain.School;
import com.greatmooc.domain.Teacher;
import com.greatmooc.service.CategoryService;
import com.greatmooc.service.ChapterService;
import com.greatmooc.service.CourseService;
import com.greatmooc.service.SchoolService;
import com.greatmooc.service.TeacherService;
import com.greatmooc.util.JsonUtil;

import net.sf.json.util.JSONUtils;


import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCourseServlet extends BaseServlet {
	private CourseService courseService = new CourseService();
	private CategoryService categoryService = new CategoryService();
	private SchoolService schoolService = new SchoolService();
	private TeacherService teacherService = new TeacherService();
	private ChapterService chapterService = new ChapterService();
	/**
	 * 显示所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findCategoryAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> parents = categoryService.findAll();//和前台公用一个findAll方法
		request.setAttribute("parents", parents);
		return "f:/adminjsps/left.jsp";
	}
	//查找所有课程
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		List<Course> courses = courseService.findAll();
		request.setAttribute("courses", courses);
		request.setAttribute("addType", 1);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/adminjsps/course/list.jsp";
	}
	
	//ajaxAddMore,查找全部课程时，加载更多
	public String ajaxAddMore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取参数begin
		 * 2.通过begin调用service的findall方法，将返回的list赋值给Pagebean对象pb
		 * 3.向客户端传递校验结果
		 */
		String begin = request.getParameter("begin");
		String cate_id = request.getParameter("cate_id");
		String cou_name = request.getParameter("cou_name");
		System.err.println(cate_id);
		System.out.println(cou_name);
		List<Course> courses = new ArrayList<Course>();
		if(cate_id!=null){
			 courses = courseService.ajaxAddMore(Integer.parseInt(begin),"cate_id",cate_id);
		}else if(cou_name!=null){
			courses = courseService.ajaxAddMore(Integer.parseInt(begin),"cou_name",cou_name);
		}else {
			courses = courseService.ajaxAddMore(Integer.parseInt(begin),null,null);
		}

		String coursesJSON = JsonUtil.list2json(courses);
//			System.out.println(coursesJSON);
		if(coursesJSON.equals("[]")){//若json为空，则向客户端输出false
			response.getWriter().print(false);
		}
		else 
			response.getWriter().print(coursesJSON);
		return null;
	}
	
	//通过cate_id查询课程详细
	public String findByCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cate_id = request.getParameter("cate_id");
		List<Course> courses = courseService.findByCategory(cate_id);
		request.setAttribute("cate_id", cate_id);//"加载更多"时，要用分类id
		request.setAttribute("courses", courses);
		request.setAttribute("addType", 2);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/adminjsps/course/list.jsp";
	}
	
	//通过cou_id查询课程详细
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_id = request.getParameter("cou_id");
		Course course = courseService.load(cou_id);
		List<Chapter> chapters = chapterService.findAll(null,cou_id);
		course.setChapters(chapters);
		request.setAttribute("course", course);
		/*
		 * 获取全部学校和一级分类，课程所属的二级分类对应的一级分类下的所有二级分类
		 * 同理，获取课程所属学校下的所有教师
		 * 保存在request中，返回到add.jsp
		 */
		List<School> schools = schoolService.findAll();
		List<Teacher> teachers = teacherService.findBySchool(course.getSchool().getSch_id());
		List<Category> parents = categoryService.findParents();
		List<Category> children = categoryService.findChildren(
				course.getCategory().getParent().getCate_id());
		request.setAttribute("schools", schools);
		request.setAttribute("teachers", teachers);
		request.setAttribute("parents", parents);
		request.setAttribute("children", children);

		return "f:/adminjsps/course/desc.jsp";
	}
	
	//通过课程名模糊搜索课程
	public String searchByCou_name(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cou_name = request.getParameter("cou_name");
		List<Course> courses = courseService.searchByCou_name(cou_name);
		request.setAttribute("courses", courses);
		request.setAttribute("cou_name", cou_name);//加载更多时，要用这个参数
		request.setAttribute("addType", 3);//addType表示“加载更多按钮”的不同，1表示查找全部课程时的，2表示按分类时的，3表示模糊搜索时的，
		return "f:/jsps/course/list.jsp";
	}
	//添加课程之前
	public String addPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取全部学校和一级分类，保存在request中，返回到add.jsp
		List<School> schools = schoolService.findAll();
		List<Category> parents = categoryService.findParents();
		request.setAttribute("schools", schools);
		request.setAttribute("parents", parents);
		
		return "f:/adminjsps/course/add.jsp";
	}
	//ajax加载教师
	public String ajaxFindTeacher(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sch_id = request.getParameter("sch_id");
		List<Teacher> teachers = teacherService.findBySchool(sch_id);
		String teachersJson = JsonUtil.list2json(teachers);
		if(teachersJson.equals("[]")){
			response.getWriter().print(false);
		}else {
			response.getWriter().print(teachersJson);
		}
		return null;
	}
	//ajax加载二级分类
	public String ajaxFindChildren(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pcate_id = request.getParameter("pcate_id");
		List<Category> categories = categoryService.findChildren(pcate_id);
		String categorysJson = JsonUtil.list2json(categories);
		if(categorysJson.equals("[]")){
			response.getWriter().print(false);
		}else {
			response.getWriter().print(categorysJson);
		}
		return null;
	}
	//修改课程信息
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map map = request.getParameterMap();
		Course course = CommonUtils.toBean(map, Course.class);
		Category category = CommonUtils.toBean(map, Category.class);
		Teacher teacher = CommonUtils.toBean(map, Teacher.class);
		School school = CommonUtils.toBean(map,School.class);
		
		course.setCategory(category);
		course.setTeacher(teacher);
		course.setSchool(school);
		courseService.edit(course);
		//返回到课程详细的界面
		request.setAttribute("cou_id", course.getCou_id());
		return load(request,response);
	}
	//删除课程
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String  cou_id = request.getParameter("cou_id");
		int count = chapterService.findChapterCountByCourse(cou_id);
		if(count>0){
			request.setAttribute("msg", "该课程下有章节，不能删除！");
			return "f:/adminjsps/msg.jsp";
		}
		courseService.delete(cou_id);
		return "f:/adminjsps/course/main.jsp";
	}
}
