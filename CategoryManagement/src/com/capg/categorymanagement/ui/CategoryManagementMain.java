package com.capg.categorymanagement.ui;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


import com.capg.categorymanagement.bean.Bean;
import com.capg.categorymanagement.exception.CategoryManagementException;
import com.capg.categorymanagement.service.ServiceClass;

public class CategoryManagementMain {

	public static void main(String[] args) throws Exception {
         
		
		Scanner scanner = new Scanner(System.in);
		ServiceClass service = new ServiceClass();
		/*
		 * Bean bean=new Bean(); boolean
		 * emailcheckflag=false; boolean passwordcheck=false; boolean em=false;
		 */
		/*
		 * do { System.out.println("Enter the Email"); String admin_email = s.next();
		 * bean.setAdmin_email(admin_email); emailcheckflag=sc.emailpattern(bean);
		 * 
		 * em=sc.emailcheck(bean); System.out.println(em); } while(!emailcheckflag ||
		 * !em);
		 * 
		 * 
		 * boolean ps=false; do { System.out.println("Enter the password"); String
		 * admin_password=s.next(); bean.setAdmin_password(admin_password);
		 * passwordcheck=sc.passwordpattern(bean);
		 * 
		 * ps=sc.passwordcheck(bean);
		 * 
		 * } while(!passwordcheck || !ps);
		 */

		
		try {
		

			int option = 0;
			boolean bc = true;
			boolean continueValue = false;
			String continueChoice = null;
			boolean deleteFlag=true;
			boolean editFlag=false;
			boolean patternFlag = false;
			boolean Flag1=false;
			do {
				do {
					System.out.println("   CATEGORY MANAGEMENT ");

					Bean bean = new Bean();
					System.out.println("_______________________________\n");

					System.out.println("1.Listing Categories ");
					System.out.println("2.Creating Category");
					System.out.println("3.Editing Category");
					System.out.println("4.Deleting Category");
					System.out.println("________________________________");
					System.out.println("Select an option:");

					try {
						
						option = scanner.nextInt();

						switch (option) {

						case 1:
							System.out.println("Listing ");

							try {
								List<Bean> list = service.takeDetails();
								if(list!=null)
							{
									Iterator<Bean> iterator=list.iterator();
									while(iterator.hasNext())
									{
										System.out.println(iterator.next());
									}
							}
							} catch (CategoryManagementException e) {
								System.err.println(e.getMessage());
							}
							continueValue = true;
							break;

						case 2:
							int createFlag = 0;
                      	    String createCategory=null;
							do {

								try {
                                    do {
                                    	
                                    
									System.out.println("Enter the category Name");
									createCategory = scanner.next();
								
									editFlag=service.validateName(createCategory);
									
                                    } while(!editFlag);
									bean.setCategory_name(createCategory);
									
									 createFlag =service.createCategory(bean);
									 System.out.println(editFlag);
									 if(createFlag!=0)
									 {    
										 Flag1=true;
										 System.out.println("New Category is successfully created with categoryId " + createFlag );
									
									 }
									 
									 else
									 {
										 Flag1=false;
										 System.out.println("Category is already existing");
									 }
								} catch (CategoryManagementException e) {
									
									System.err.println(e.getMessage());
								}
                              } while(!Flag1);
							
							break;
						case 3:
							System.out.println("Editing ");
                          
                            //boolean editFlag = false;
                            boolean existFlag = false;
							do {
								
								Bean bean1= new Bean();
								System.out.println("Enter the name of the category to Edit");
								try {

							       String originalName = scanner.next();
								
							      
								
									bean1.setCategory_name(originalName);
									 patternFlag =service.validateName(originalName);
									 
                                    existFlag=service.existingCategory(bean1);
									
									if(existFlag)
							{
										System.out.println("Enter the new Name");
									 String newName=scanner.next();
									 bean1.setNewCategoryName(newName);
										System.out.println("erhuzh");
										editFlag=service.editCategory(bean1);
									}
									
									
								} catch (CategoryManagementException e) {
									System.err.println(e.getMessage());
								}
							} while (!patternFlag || !existFlag);
							
									
								
							
							
							continueValue = true;
							break;

						case 4:
							do {
							System.out.println("deleting ");
							System.out.println("Enter the name of the category to delete");
							String deleteName= scanner.next();
							deleteFlag=service.deleteCategory(deleteName);
							if(deleteFlag) {
								System.out.println("The category is deleted successfully");}
							else {
								System.out.println("This category is not existing");}
							} while(!deleteFlag);
							break;
						case 5:

							System.exit(0);
							continueValue = true;
							break;
						default:
							System.out.println("Enter only digits");
							continueValue = true;
							break;

						}

					} catch (InputMismatchException e) {

						System.out.println("Input Mismatch ");
						bc = false;
						// throw new CategoryManagementException("Input Mismatch");

					}
				} while (!bc);

				do {
					

					System.out.println("do you want to continue again [yes/no]");
					continueChoice = scanner.nextLine();
					if (continueChoice.equalsIgnoreCase("yes")) {
						continueValue = true;
						break;
					} else if (continueChoice.equalsIgnoreCase("no")) {
						System.out.println("thank you");
						continueValue = false;
						break;
					} else {
						System.out.println("enter yes or no");
						continueValue = false;
						continue;
					}
				} while (!continueValue);
			} while (continueValue);
		} catch (StackOverflowError e) {
			System.out.println(e);
		}
	}
}
