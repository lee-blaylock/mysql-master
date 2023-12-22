package diyproject;

import diyproject.entity.Project;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import diyproject.entity.Project;
import diyproject.exception.DbException;
import diyproject.service.ProjectService;

public class ProjectsApp {

	private Scanner scanner = new Scanner(System.in);

	private ProjectService projectService = new ProjectService();

	// @formatter:off
	private List<String> operations = List.of(
		"1) Create project"
	); 
	// @formatter:on

	
	public static void main(String[] args) {
		new ProjectsApp().processUserSelection();
	} 
	
	private void processUserSelection() {
		boolean done = false;

		while (!done) {
			try {
				int selection = getUserSelection();
				
				switch (selection) {
				 case -1:
					done = exitMenu();
					break;
				  case 1:
					createProject();
					break;
				default:
					System.out.println("\n" + selection + " is not a valid selection. Try again.");
				} 
			} catch (Exception e) {
				System.out.println("\nError: " + e + " Try again.");
			}
		} 
	} 
	
	private int getUserSelection() {
		printOperations();

		Integer input = getIntInput(
				"\nEnter a menu selection");

		return Objects.isNull(input) ? -1 : input;
	} 
	
	private void printOperations() {
		System.out.println("\nThese are the available selections. Press the Enter key to quit: ");

		operations.forEach(line -> System.out.println("  " + line));
	} 
	
	private Integer getIntInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid number.");
		}
	} 
	
	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}
	
	private BigDecimal getDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		} catch (NumberFormatException e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	} 
	
	private void createProject() {
		String projectName = getStringInput("Enter the project name");
		String notes = getStringInput("Enter the project notes");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		
		

		Project project = new Project();

		project.setProjectName(projectName);
		project.setNotes(notes);
		project.setDifficulty(difficulty);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		
		

		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created project: " + dbProject);
	} 
	
	
	private boolean exitMenu() {
		System.out.println("Exiting menu");
		return true;
	}

} 