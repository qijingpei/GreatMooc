package com.greatmooc.service;

import java.util.List;
import java.util.Map;

import com.greatmooc.dao.AdminDao;
import com.greatmooc.domain.Admin;
import com.greatmooc.exception.AdminException;


/*
 * Admin的业务层
 */
public class AdminService {
	private AdminDao adminDao=new AdminDao();
	/*
	 * 登陆
	 * 1.调用AdminDao的findByAdminname(String username),获得一个Admin对象
	 *   >如果用户名不存在，抛出异常
	 *   >对比密码，如果密码错误，抛出异常
	 * 2.返回Admin对象，完成登录
	 */
	public Admin admin_login(Admin form) throws AdminException {
		Admin admin=adminDao.findByAdm_name(form.getAdm_name());
		if(admin==null) throw new AdminException("此管理员不存在，或已被超级管理员删除");
		if(!admin.getAdm_pwd().equals(form.getAdm_pwd())) throw new AdminException("密码错误");
		return admin;
	}
	
	/*
	 * 加载
	 */
	public Admin load(String adm_id) {
		return adminDao.load(adm_id);
		
	}

	/*
	 * 编辑
	 */
	public void edit(String adm_id, Map<String, String> edit) {
		adminDao.edit(adm_id,edit);
	}

	/*
	 * 查询所有
	 */
	public List<Admin> findAll(int adm_id) throws AdminException {
		if (!adminDao.issuper(adm_id)) {//校验是否超级管理员
			throw new AdminException("您不是超级管理员，无法查看所有管理员~~啦啦啦");
		}
		return adminDao.findAll();
		
	}

	/*
	 * 添加
	 */
	public void add(Map<String, Object> add) {
		adminDao.add(add);
		
	}

	/*
	 * 删除
	 */
	public void delete(int adm_id) {
		adminDao.delete(adm_id);		
	}

	/*
	 * 升级
	 */
	public void levelup(int adm_id) {
		adminDao.levelup(adm_id);
		
	}

	/*
	 * 降级
	 */
	public void leveldown(int adm_id) {
		adminDao.leveldown(adm_id);
		
	}
	
	/*
	 * 判断是否为超级管理员
	 */
	
	
}
