package com.github.veeshostak;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Student 1 - 4
//Books: 1 - 7
//
//problem: May be a situation when two students need to read the same book

public class App {
	
	public static void main(String[] args) {
		
		Student[] students = null;
		Book[] books = null;
		
		ExecutorService executor = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);
		
		try{	
			books = new Book[Constants.NUMBER_OF_BOOKS];
			students = new Student[Constants.NUMBER_OF_STUDENTS];
			
			// initialize books
			for(int i=0;i<Constants.NUMBER_OF_BOOKS;i++){
				books[i] = new Book(i);
			}
			
			// initialize and execute students
			for(int i = 0;i < Constants.NUMBER_OF_STUDENTS;i++){
				students[i] = new Student(i,books);
				executor.execute(students[i]);
			}						
		}catch(Exception e){
			e.printStackTrace();
			executor.shutdown();
		}finally{
			executor.shutdown();
		}
	}
}