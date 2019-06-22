package com.capg.categorymanagement.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.connection.DbConnection;
import com.capg.categorymanagement.exception.CategoryManagementException;
import com.capg.categorymanagement.service.ServiceClass;

public class DaoClass implements DaoInterface {
	Bean bean = new Bean();

	Logger logger = Logger.getRootLogger();
	Scanner scanner = new Scanner(System.in);

	Connection con = null;
	PreparedStatement ps = null;

	public DaoClass() {
		PropertyConfigurator.configure("resources//log4j.properties");

	}

	public List<Bean> takeDetails() throws CategoryManagementException {

		con = DbConnection.getConnection();
		List<Bean> list = new ArrayList<>();

		ResultSet rs = null;

		try {
			ps = con.prepareStatement(QueryMapper.LISTING_DETAILS_QUERY);
			rs = ps.executeQuery();
			while (rs.next()) {
				Bean bean = new Bean();
				bean.setCategory_id(rs.getInt(1));
				bean.setCategory_name(rs.getString(2));
				list.add(bean);
			}
		} catch (SQLException e) {
			throw new CategoryManagementException("problem occured while creating ps");
		}
		return list;
	}

	public int createCategory(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		 int createFlag= 0;
		 
		try {
		
			PreparedStatement ps = null;
			PreparedStatement ps1 = null;
		    ResultSet rs=null;

			ps = con.prepareStatement(QueryMapper.CREATE_CATEGORY_QUERY);
			ps.setString(1,bean.getCategory_name());
			int i = ps.executeUpdate();
		

			if (i == 1) {

				//System.out.print("A new Category is created with category_id ");
               
				ps1 = con.prepareStatement(QueryMapper.DISPLAYING_CATEGORY_ID_QUERY);
				
				rs= ps1.executeQuery();
				while(rs.next())
				{   
					
				//	int categoryId=rs.getInt(1);
					createFlag=rs.getInt(1);
					
				}
			}

			
		
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println("Already existing category");
			createFlag= 0;

		}
		return createFlag;

	}

	public boolean editCategory(Bean bean) throws CategoryManagementException {
		con = DbConnection.getConnection();
		PreparedStatement ps1=null;
	try {
				ps1 = con.prepareStatement(QueryMapper.EDITING_CATEGORY_QUERY);
				ps1.setString(1, bean.getNewCategoryName());
				ps1.setString(2, bean.getCategory_name());
				ps1.executeUpdate();
				
			}

		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("The name of the category is already existing");

		}
		return true;

	}
	
	
	public boolean existingCategory(Bean bean) throws CategoryManagementException, SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		boolean existFlag=false;
		int i=0;
		con = DbConnection.getConnection();
		ps = con.prepareStatement(QueryMapper.CHECKING_THE_CATEGORY_QUERY);
		ps.setString(1, bean.getCategory_name());
		try {
			i = ps.executeUpdate();
			if(i==1)
			{
				System.out.println("ggggg");
				existFlag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existFlag;
	}

	public boolean deleteCategory(String deleteName) throws CategoryManagementException {

		con = DbConnection.getConnection();
		boolean deleteFlag = true;

		/*try {

			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_THE_CATEGORY_QUERY);
			ps.setString(1, deleteName);
			//ResultSet rs = ps.executeQuery();
            int i=ps.executeUpdate();

			if (i==1) {
				// System.out.println("this category is not existing");
				deleteFlag=true;
			}
			else
			{
				deleteFlag=false;
			}*/

			/*if (deleteFlag) {*/
		try {
				PreparedStatement ps1 = con.prepareStatement(QueryMapper.DELETING_CATEGORY_QUERY);
				ps1.setString(1, deleteName);
				int i=ps1.executeUpdate();
				if (i==1) {
					// System.out.println("this category is not existing");
					deleteFlag=true;
				}
				else
				{
					deleteFlag=false;
				}
				// System.out.println("The category is deleted successfully");
	//}
				System.out.println(i);
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteFlag;

	}

	public boolean admin_email(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		boolean b1 = false;

		try {

			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_ADMIN_EMAIL_QUERY);
			ps.setString(1, bean.getAdmin_email());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				b1 = true;
			}
		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;

	}

	public boolean admin_password(Bean bean) throws CategoryManagementException {
		// TODO Auto-generated method stub
		con = DbConnection.getConnection();
		boolean b1 = false;

		try {

			PreparedStatement ps = con.prepareStatement(QueryMapper.CHECKING_ADMIN_PASSWORD_QUERY);
			ps.setString(1, bean.getAdmin_email());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				b1 = true;
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b1;
	}

	

}
