package web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import entity.Student;
import redis.clients.jedis.Jedis;

public class FindStudentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		String path = req.getServletPath();
		if("/FindStudentServlet.do".equals(path)){
			findAll(req,res);
		}else if("/delete.do".equals(path)){
			delete(req,res);
		}else if("/add.do".equals(path)){
			add(req,res);
		}else if("/update.do".equals(path)){
			update(req,res);
		}else if("/up.do".equals(path)){
			paging(req,res);
		}else if("/next.do".equals(path)){
			paging(req,res);
		}else if("/jump.do".equals(path)){
			paging(req,res);
		}
	}
	
	protected void findAll(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		StudentDao dao = new StudentDao();
		List<Student> list = dao.find();
		Jedis jedis = new Jedis("101.132.147.78",6379);
	    jedis.auth("admin");
	    HttpSession session = req.getSession();
	    long c = jedis.zcard("avgscore");
	    String count = String.valueOf(c+1);
	    session.setAttribute("count", count);
	    int page = 1;
	    req.setAttribute("page", page);
	    req.setAttribute("today", new Date());
		req.setAttribute("list", list);
		req.getRequestDispatcher("/findStudent.jsp").forward(req, res);
	}
	
	protected void paging(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		String path = req.getServletPath();
		//HttpSession session = req.getSession();
		StudentDao dao = new StudentDao();
		int page = Integer.valueOf(req.getParameter("page"));
        if("/up.do".equals(path)){
        	if(page<0 && page==1){
        		req.setAttribute("page",0);
        		res.sendRedirect("FindStudentServlet.do");
        		return;
        	}else{
        		 page = page - 1;
    		     req.setAttribute("page", page);
        	}
		}else if("/next.do".equals(path)){
			 page = page + 1;
			 req.setAttribute("page", page);	 
		}else if("/jump.do".equals(path)){
             page = page*1;
             req.setAttribute("page", page);
		}
        List<Student> list = dao.paging(page);
        req.setAttribute("list", list);
		req.getRequestDispatcher("/findStudent.jsp").forward(req, res);
	}
	
	protected void delete(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		StudentDao dao = new StudentDao();
		String id = req.getParameter("id");
		dao.delete(id);
		res.sendRedirect("FindStudentServlet.do");
	}
	
	protected void add(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		StudentDao dao = new StudentDao();
		String  id = req.getParameter("id");
		String  name = req.getParameter("name");
		String  birthday = req.getParameter("birthday");
		String  description = req.getParameter("description");
		Integer  avgscore = Integer.valueOf(req.getParameter("avgscore"));
		dao.add(id,name,birthday,description,avgscore);
		res.sendRedirect("FindStudentServlet.do");
	}
	
	protected void update(
			HttpServletRequest req,
			HttpServletResponse res)
				throws ServletException, IOException {
		StudentDao dao = new StudentDao();
		String  id = req.getParameter("id");
		String  name = req.getParameter("name");
		String  birthday = req.getParameter("birthday");
		String  description = req.getParameter("description");
		Integer  avgscore = Integer.valueOf(req.getParameter("avgscore"));
		dao.update(id,name,birthday,description,avgscore);
		res.sendRedirect("FindStudentServlet.do");
	}
}
