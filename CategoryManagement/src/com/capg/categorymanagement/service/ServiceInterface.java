package com.capg.categorymanagement.service;

import java.sql.SQLException;
import java.util.List;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;

public interface ServiceInterface {

	List<Bean> takeDetails() throws CategoryManagementException;

	int createCategory(Bean bean ) throws CategoryManagementException;

	boolean editCategory(Bean bean) throws CategoryManagementException;
	
	boolean existingCategory(Bean bean) throws CategoryManagementException, SQLException;
 
	boolean deleteCategory( String deleteName) throws CategoryManagementException;

	public boolean emailpattern(Bean bean)  throws CategoryManagementException;

	public boolean passwordpattern(Bean bean) throws CategoryManagementException;

	public boolean emailcheck(Bean bean) throws CategoryManagementException;

	public boolean passwordcheck(Bean bean) throws CategoryManagementException;

	public boolean validateName(String name) throws CategoryManagementException;
}
