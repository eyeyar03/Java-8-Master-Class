package com.masterclass.employee.directory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int input;
		System.out.println("Main Option\n" +
				"[1] List All Employee Records\n" +
				"[2] Add New Employee Record\n" +
				"[3] Delete Employee Record\n" +
				"[4] Search Employee Record\n" +
				"[-1] exit\n");
		System.out.print("Enter action type: ");
		input = scan.nextInt();


	}

}
