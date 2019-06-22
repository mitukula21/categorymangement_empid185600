package com.capg.categorymanagement.dao;

import java.sql.SQLException;
import java.util.List;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface DaoInterface {

	public List<Bean> takeDetails() throws CategoryManagementException;

	public int createCategory(Bean bean) throws CategoryManagementException;

	public boolean editCategory(Bean bean) throws CategoryManagementException;
	
	
	public boolean deleteCategory(String deleteName) throws CategoryManagementException;
	

	public boolean admin_email(Bean bean) throws CategoryManagementException;

	public boolean admin_password(Bean bean) throws CategoryManagementException;
	
	public boolean existingCategory(Bean bean) throws CategoryManagementException, SQLException;

}
