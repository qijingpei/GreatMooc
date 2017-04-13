package com.greatmooc.admin.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

import com.greatmooc.domain.Admin;
import com.greatmooc.exception.AdminException;
import com.greatmooc.service.AdminService;





public class AdminServlet extends BaseServlet {
	private AdminService adminService=new AdminService();

	/*
	 * 管理员登录
	 */
	/*
	 * 1.封装表单数据
	 * 2.输入校验：判空、长度
	 *   >如果有错误：保存错误信息、form到request域，转发到regist.jsp
	 * 3.调用service的login(User form),查看用户名是否已存在、密码是否正确
	 *   >如果有错误：保存错误信息、form到request域，转发到regist.jsp
	 * 4.登陆成功，保存用户信息到session中
	 * 5.重定向到index.jsp
	 */
	public String admin_login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Admin form=CommonUtils.toBean(request.getParameterMap(), Admin.class);
		//System.out.println(form);
		
		//校验
		// 1.创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		Map<String,String> errors=new HashMap<String,String>();
		/*
		 * 2.获取form中的username、password进行检验
		 */
		String username=form.getAdm_name();
		if(username==null || username.trim().isEmpty()) {
			errors.put("username", "管理员姓名不能为空！");
		} else if (username.length()<3 || username.length()>15) {
			errors.put("username", "管理员姓名必须在3~15之间！");
		}
		
		String password=form.getAdm_pwd();
		if(password==null || password.trim().isEmpty()) {
			errors.put("password", "密码不能为空！");
		} else if (password.length()<3 || password.length()>15) {
			errors.put("password", "密码必须在3~15之间！");
		}
		/*
		 * 3.判断是否存在错误信息
		 */
		if(errors.size()>0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", form);//用于回显
			return "f:/adminjsps/admin/login.jsp";
		}
		
		/*
		 * 调用AdminService的admin_login()方法
		 * 1.登陆成功，保存用户信息到session中
		 * 2.重定向到index.jsp
		 */
		try {
			Admin admin=adminService.admin_login(form);
			request.getSession().setAttribute("session_admin", admin);
			return "r:/adminjsps/main.jsp";
		} catch (AdminException e) {
			//System.out.println(e.getMessage());
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/adminjsps/admin/login.jsp";
		}				
	}
	
	/*
	 * 加载
	 */
	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adm_id=request.getParameter("adm_id");
		//System.out.println(adm_id);
		//调用service的load(int adm_id)方法返回这个Admin对象
		request.setAttribute("admin", adminService.load(adm_id));
		return "f:/adminjsps/admin/desc.jsp";
	}
	
	/*
	 * 编辑
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adm_id=request.getParameter("adm_id");//获取管理员编号
		String adm_pwd=request.getParameter("adm_pwd");//获取管理员当前密码，用于校验
		String newname=request.getParameter("newname");
		String oldpwd=request.getParameter("oldpwd");
		String newpwd1=request.getParameter("newpwd1");
		String newpwd2=request.getParameter("newpwd2");
		/*
		 * 校验
		 */
		//校验密码是否正确
		//System.out.println(adm_pwd);
		if(!adm_pwd.equals(oldpwd)) {
			request.setAttribute("msg", "密码错误，无法完成修改");
			//System.out.println(oldpwd);
			request.setAttribute("admin",adminService.load(adm_id) );//用于回显
			return "f:/adminjsps/admin/desc.jsp";
		}
		//校验两次密码是否一致
		if(!newpwd1.equals(newpwd2)) {
			request.setAttribute("msg", "两次密码不一致，请重新设置");
			//System.out.println("两次密码不一致，请重新设置");
			request.setAttribute("admin",adminService.load(adm_id) );//用于回显
			return "f:/adminjsps/admin/desc.jsp";
		}
		//校验新用户名、密码的长度和非空
		// 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		Map<String,String> errors=new HashMap<String,String>();
		//判断新用户名
		if(newname==null || newname.trim().isEmpty()) {
			errors.put("newname", "用户名不能为空！");
		} else if (newname.length()<3 || newname.length()>15) {
			errors.put("newname", "用户名必须在3~15之间！");
		}
		//判断新密码
		if(newpwd1==null || newpwd1.trim().isEmpty()) {
			errors.put("newpwd1", "新密码不能为空！");
		} else if (newpwd1.length()<3 || newpwd1.length()>15) {
			errors.put("newpwd1", "新密码必须在3~15之间！");
		}
		/*
		 * 3.判断是否存在错误信息
		 */
		if(errors.size()>0) {
			request.setAttribute("errors", errors);
			request.setAttribute("admin",adminService.load(adm_id) );//用于回显
			return "f:/adminjsps/admin/desc.jsp";
		}
		// 1.创建一个Map，用来封装修改信息，其中key为表单字段名称，值为修改信息
		Map<String,String> edit=new HashMap<String,String>();
		edit.put("newname", newname);
		edit.put("newpwd", newpwd1);//到了这里，两次新密码肯定一样了
		
		adminService.edit(adm_id,edit);//完成编辑
		//重新设置session域的session_admin
		request.getSession().setAttribute("session_admin", adminService.load(adm_id));
		return "f:/adminjsps/main.jsp";
		
	}
	
	/*
	 * 查看所有管理员
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//使用session域的session_admin.adm_id进行判断，判断当前是否为超级管理员，
			//我个人认为：request域可以通过地址栏来伪造adm_id，而session域只有当登陆成功和修改密码成功时才会有adm_id
			Admin admin=(Admin) request.getSession().getAttribute("session_admin");
			//System.out.println(admin);
			/*
			 * 如果不是超级管理员那么将错误转发到msg.jsp
			 */
			if(admin==null || admin.isIssuper()==false) {
				request.setAttribute("msg", "您不是超级管理员，没有权限查看其他人的信息！");
				return "f:/adminjsps/admin/msg.jsp";//转发到列表页面显示
			}
			List<Admin> adminList=adminService.findAll(admin.getAdm_id());//参数用于检验
			request.setAttribute("adminList", adminList);
			//System.out.println(adminList);
			return "f:/adminjsps/admin/list.jsp";//转发到列表页面显示
		} catch(AdminException e) {
			request.setAttribute("msg", e);
			return "f:/adminjsps/admin/msg.jsp";
		}
	}
	/*
	 * 添加管理员,newname,newpwd1,newpwd2
	 */
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("运行了add!");
		String newname=request.getParameter("newname");
		String newpwd1=request.getParameter("newpwd1");
		String newpwd2=request.getParameter("newpwd2");

		//判断是否为超级管理员
		Boolean issuper=false;
		if(request.getParameter("issuper")!=null) {
			issuper=true;
		}
		
		/*
		 * 校验
		 */
		//System.out.println(issuper);
		//System.out.println(request.getParameter("issuper"));
		//校验两次密码是否一致
		if(!newpwd1.equals(newpwd2)) {
			request.setAttribute("msg", "两次密码不一致，请重新设置");
			//System.out.println("两次密码不一致，请重新设置");
			return "f:/adminjsps/admin/add.jsp";
		}
		//校验新用户名、密码的长度和非空
		// 创建一个Map，用来封装错误信息，其中key为表单字段名称，值为错误信息
		Map<String,String> errors=new HashMap<String,String>();
		//判断新用户名
		if(newname==null || newname.trim().isEmpty()) {
			errors.put("newname", "用户名不能为空！");
		} else if (newname.length()<3 || newname.length()>15) {
			errors.put("newname", "用户名必须在3~15之间！");
		}
		//判断新密码
		if(newpwd1==null || newpwd1.trim().isEmpty()) {
			errors.put("newpwd1", "新密码不能为空！");
		} else if (newpwd1.length()<3 || newpwd1.length()>15) {
			errors.put("newpwd1", "新密码必须在3~15之间！");
		}
		/*
		 * 3.判断是否存在错误信息
		 */
		if(errors.size()>0) {
			request.setAttribute("errors", errors);
			return "f:/adminjsps/admin/add.jsp";
		}
		//校验完成后，调用service完成add
		Map<String,Object> add=new HashMap<String,Object>();
		add.put("newname", newname);
		add.put("newpwd", newpwd1);//到了这里，两次新密码肯定一样了
		add.put("issuper",issuper);
		adminService.add(add);
		
		return findAll(request, response);		
	}
	
	/*
	 * 删除
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int adm_id=Integer.parseInt(request.getParameter("adm_id"));
		/*
		 * 如果删除的是自己，那么提示错误“您无法删除自己”
		 */
		String adminid=adm_id+"";
		String sessionadmin_id=request.getParameter("sessionadmin_id");
		if(sessionadmin_id.equals(adminid)) {
			//request.getSession().removeAttribute("session_admin");
			request.setAttribute("msg", "删除失败，您无法删除自己！");
			return "f:/adminjsps/admin/msg.jsp";
		}
		//完成删除
		adminService.delete(adm_id);
		
		return findAll(request,response);
	}
	/*
	 * 提升普通管理员成为超级管理员
	 */
	public String levelup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int adm_id=Integer.parseInt(request.getParameter("adm_id"));
		adminService.levelup(adm_id);
		return findAll(request,response);
	}
	
	/*
	 * 降级超级普通管理员成为 普通管理员
	 */
	public String leveldown(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int adm_id=Integer.parseInt(request.getParameter("adm_id"));
		adminService.leveldown(adm_id);
		return findAll(request,response);
	}
	
	/*
	 * 退出
	 */
	public String quit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("session_admin");
		return "f:/adminjsps/admin/login.jsp";
	}
	
}
