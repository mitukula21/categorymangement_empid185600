package com.capg.categorymanagement.service;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.dao.DaoClass;
import com.capg.categorymanagement.exception.CategoryManagementException;

public class ServiceClass implements ServiceInterface {
	
	DaoClass dao = new DaoClass();
	Bean bean=new Bean();

	public List<Bean> takeDetails() throws CategoryManagementException{
		return dao.takeDetails();
	}

	public int createCategory(Bean bean) throws CategoryManagementException {

		int createFlag = dao.createCategory(bean);
		
		return createFlag;
		

	}

	public boolean editCategory(Bean bean) throws CategoryManagementException {
		System.out.println(bean.getNewCategoryName());
		boolean editFlag= dao.editCategory(bean);
		
		return editFlag;
	}
	
	public boolean existingCategory(Bean bean) throws CategoryManagementException, SQLException {
		System.out.println(123);
	    boolean existFlag=dao.existingCategory(bean);
	    System.out.println(bean.getCategory_name());
	    return existFlag;
	}
	
	

	public boolean deleteCategory(String deleteName) throws CategoryManagementException {
		// TODO Auto-generated method stub
		
		boolean deleteFlag=dao.deleteCategory(deleteName);
		return deleteFlag;

	}

	public boolean emailpattern(Bean bean) {

		boolean emailcheck = false;
		String emailpattern = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String email = bean.getAdmin_email();
		if (email.matches(emailpattern)) {
			emailcheck = true;
		} else {
			System.out.println("Email pattern is wrong");
		}
		return emailcheck;
	}

	public boolean passwordpattern(Bean bean) {
		boolean passwordcheckflag = false;
		if (bean.getAdmin_password().matches("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})")) {
			passwordcheckflag = true;
		} else {
			System.out.println("Enter the password correctly");
			passwordcheckflag = false;
		}
		return passwordcheckflag;
	}

	@Override
	public boolean emailcheck(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		boolean em = dao.admin_email(bean);
		return em;
	}

	@Override
	public boolean passwordcheck(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		boolean ps = dao.admin_password(bean);
		return ps;
	}

	public boolean validateName(String originalName) throws CategoryManagementException {
	     
		boolean editFlag=true;
		String nameRegEx = "[A-Z]{1}[a-zA-Z]{4,9}";
		if (!Pattern.matches(nameRegEx, originalName)) {
			 editFlag=false;
System.err.println("first letter should be capital and length must be in between 5 to 10 \n");
			//throw new CategoryManagementException(
					//"first letter should be capital and length must be in between 5 to 10 \n");
	      
		}
		else
			editFlag=true;
		return editFlag;
		
		

	}
}
