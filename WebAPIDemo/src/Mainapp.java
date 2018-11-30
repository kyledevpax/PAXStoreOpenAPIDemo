import java.util.Scanner;


public class Mainapp {

	public static void main(String[] args){
		
		
		//First screen will either add a new reseller or provide a list of all the current resellers(for modifying/ deleting)
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Choose an option:");
		System.out.println("1. Add a Reseller");
		System.out.println("2. List all Resellers");
		int choice = scan.nextInt();
		
		
		//Second screen will either ask for Reseller information if the choice was 1, or display all of the Resellers if the choice was 2
		if(choice == 1) {
			//Call the function to add a reseller
		}
		else if (choice ==2) {
			// call the function to List all of the resellers
			//choose a reseller
			
			//Create a function to Display the information for the Reseller and provide the option to Modify or Delete
			int resellerChoice = 1;
			if (resellerChoice == 1 ) {
				//Third screen will display options for a particular Reseller
				
				//Print the name of the Reseller at the top followed by:
				System.out.println("\nThese are the options for Test Reseller");//DELETE THIS LINE LATER
				System.out.println("1. Modify Reseller information");
				System.out.println("2. Delete Reseller");
				//Fourth screen will either show the data that can be modified for the chosen Reseller for 1, 
				//or show a confirmation page if the choice was 2.
				int choice2 = scan.nextInt();
				if (choice2 == 1) {
					//Display the information you can change for a Reseller, and what format you need to type to change the Reseller data
				}
				else if (choice2 == 2) {
					System.out.println("Are you sure? Y/N");
					String delChoice = scan.next();
					if(delChoice.equals("Y") || delChoice.equals("y")) {
						//Call function to delete a Reseller
					}
					else if (delChoice.equals("N") || delChoice.equals("n")) {	
						//Return back to Reseller page
					}
					else {
						System.out.println("Invalid Entry");
					}
				}
			}
		}
		else {
			System.out.println("Invalid Entry");
		}
		scan.close();
	
		
		
	}

}
