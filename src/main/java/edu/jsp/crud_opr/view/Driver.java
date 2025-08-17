package edu.jsp.crud_opr.view;

import java.util.ArrayList;
import java.util.Scanner;

import edu.jsp.crud_opr.controller.CandidateController;
import edu.jsp.crud_opr.model.Candidate;

public class Driver {
	
	static {
		System.out.println("-------Welcome to Candidate Management System---------\n\n");
	}
	static Scanner userInput = new Scanner(System.in);
	
	public static void main(String[] args) {
		do {
			System.out.println();
			System.out.println("1. Add Candidaate");
			System.out.println("2. View All Candidate");
			System.out.println("3. View Candidate by ID");
			System.out.println("4. Edit Candidate details");
			System.out.println("5. Delete Candidate");
			System.out.println("0. Exit");
			System.out.print("Enter the choice you want to execute: ");
			byte userChoice = userInput.nextByte();
			userInput.nextLine();
			
			switch (userChoice) {
			case 0:
				System.exit(0);
				break;
			case 1:
				Candidate candidate  = captureCandidateDetails();
				int insertCandidate = CandidateController.insertCandidate(candidate);
				if(insertCandidate != -1 && insertCandidate==1) {
					System.out.println("\nCandidate Added Successfully!\n\n");
				}
				else {
					System.out.println("\n\nUnable To Add Candidate!\n\n");
				}
				break;
			case 2:
				ArrayList<Candidate> fetchCandidatedetails = CandidateController.fetchCandidatedetails();
				if (fetchCandidatedetails!=null) {
					System.out.printf("%-15s%-15s%-15s%n","id","name","fee");
					System.out.println("------------------------------------");
					for(Candidate c:fetchCandidatedetails) {
						System.out.printf("%-15s",c.getId());
						System.out.printf("%-15s",c.getName());
						System.out.printf("%-15s",c.getFee());
						System.out.printf("%n");
					}
				} else {
					System.out.println("No Data To Display!");
				}
				break;
			case 3:
				int id = getCandidateById();
				Candidate candiadateById = CandidateController.ReadCandidateById(id);
				if (candiadateById!=null) {
					System.out.printf("%-15s%-15s%-15s%n","id","name","fee");
					System.out.println("------------------------------------");
					System.out.printf("%-15s", candiadateById.getId());
					System.out.printf("%-15s", candiadateById.getName());
					System.out.printf("%-15s", candiadateById.getFee());
					System.out.printf("%n");
				} else {
					System.out.println("Invalid id!\n\n");
				}
				
				break;
				
			case 4:
				int candidateID = getCandidateById();
				Candidate candidateByIdForUpdate = CandidateController.ReadCandidateById(candidateID);
				if(candidateByIdForUpdate != null) {
					//candidate with specified id exist
					
					System.out.println("What you want to update:");
					System.out.println("1.name\n2.fee");
					System.out.print("Enter your choice: ");
					byte updateChoice = userInput.nextByte();
					userInput.nextLine();
					
					switch(updateChoice) {
					case 1:
						System.out.println("Enter candidate name to update: ");
						String nameToUpdate = userInput.nextLine();
						CandidateController.updateCandidate(updateChoice, nameToUpdate, candidateID);
						break;
						
					case 2:
						System.out.println("Enter candidate fee to update: ");
						double feeToUpdate = userInput.nextDouble();
						userInput.nextLine();
						CandidateController.updateCandidate(updateChoice, feeToUpdate, candidateID);
						break;
			
					default:
						System.out.println("Invalid user input.");
						break;
					}
				}else {
					//candidate with specified id does not exist
					System.out.println("given id does not exist. try again.");
				}
				break;
			case 5:
				int idForDelete = getCandidateById();
				int row = CandidateController.deleteCandidateById(idForDelete);
				
				if(row > 0) {
					System.out.println("Record deleete successfully.");
				}else {
					System.out.println("No candidate found with given id.");
				}
				break;

			default:
				System.out.println("Invalid choice. Enter the correct choice from given option.");
				break;
			}
			
		} while (true); //TODO :termination of the loop
		
		}
	public static Candidate captureCandidateDetails() {
		Candidate c = new Candidate();
		System.out.print("Enter Candidate name: ");
		String name = userInput.nextLine();
		c.setName(name);
		System.out.print("Enter Fee of Candidate: ");
		double fee = userInput.nextDouble();
		c.setFee(fee);
		userInput.nextLine();
		return c;
	}
	
	public static int getCandidateById() {
		System.out.print("Enter the id of the candidate: ");
		int id = userInput.nextInt();
		userInput.nextLine();
		return id;
	}
}
