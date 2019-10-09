package com.javatpoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.javatpoint.beans.Emp;

public class EmpDao {
	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Emp p) {
		String sql = "insert into emp99(NAME,SALARY,DESIGNATION) values('" + p.getName() + "'," + p.getSalary() + ",'"
				+ p.getDesignation() + "')";
		return template.update(sql);
	}

	public List<Emp> getEmployees() {
		return template.query("select * from emp99", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int row) throws SQLException {
				Emp e = new Emp();
				e.setId(rs.getInt("ID"));
				e.setName(rs.getString("NAME"));
				e.setSalary(rs.getDouble("SALARY"));
				e.setDesignation(rs.getString("DESIGNATION"));
				return e;
			}
		});
	}
	
	public int delete(int id){    
	    String sql="delete from emp99 where id="+id+"";    
	    return template.update(sql);    
	}   
	
	public Emp getEmpById(int id){    
	    String sql="select * from Emp99 where id=?";    
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));    
	}  
	
	public int update(Emp p){   
		System.out.println("Hello update");
		Double salary = Double.valueOf(p.getSalary());
	    String sql="update emp99 set NAME='"+p.getName()+"', SALARY="+salary+",DESIGNATION='"+p.getDesignation()+"' where ID="+p.getId()+"";    
	    return template.update(sql);    
	} 
}
