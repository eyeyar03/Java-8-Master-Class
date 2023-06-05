package com.masterclass.employee.directory;

import com.masterclass.employee.directory.menu.DisplaySelection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		DisplaySelection displaySelection = new DisplaySelection();
			displaySelection.showMainMenu();
	}
}
