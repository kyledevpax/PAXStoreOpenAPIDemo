import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import sun.awt.image.ImageWatched;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        //First screen will either add a new reseller or provide a list of all the current resellers(for modifying/ deleting)
        Scanner scan = new Scanner(System.in);

        boolean programRunning = true;
        Reseller res = new Reseller();

        //============delete=============
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("abc", "xyz");
        //===========delete=============

        while (programRunning) {

                System.out.println("\nChoose an option:");
                System.out.println("1. Add a Reseller");
                System.out.println("2. List all Resellers");
                String choice = scan.next();


                //Second screen will either ask for Reseller information if the choice was 1, or display all of the Resellers if the choice was 2
                if (choice.equals("1")) {
                    //Call the function to add a reseller

                    System.out.println("Please Enter the following information about the Reseller:");

                    System.out.print("Name: ");
                    String name = scan.next();

                    System.out.print("Email: ");
                    String email = scan.next();

                    System.out.print("Country: ");
                    String country = scan.next();

                    System.out.print("Contact (Company phone): ");
                    String contact = scan.next();

                    System.out.print("Phone number: ");
                    String phone = scan.next();

                    System.out.print("Postal Code: ");
                    String postCode = scan.next();

                    System.out.print("Address: ");
                    String address = scan.next();

                    System.out.print("Company: ");
                    String company = scan.next();

                    //System.out.print("Parent Reseller Name: ");
                    //String parentResellerName = scan.next();

                    //System.out.println("Entity Attribute Key Values: (skip)");

                    //call function to add this reseller
                    ResellerCreateRequest request = res.createResellerRequest(name, email, country, contact, phone, postCode, address, company, null, map);
                    res.createAReseller(request);


                } else if (choice.equals("2")) {
                    // call the function to List all of the resellers
                    //choose a reseller

                    //Create a function to Display the information for the Reseller and provide the option to Modify or Delete
                    int resellerChoice = 1;
                    if (resellerChoice == 1) {
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
                        } else if (choice2 == 2) {
                            System.out.println("Are you sure? Y/N");
                            String delChoice = scan.next();
                            if (delChoice.equals("Y") || delChoice.equals("y")) {
                                //Call function to delete a Reseller
                            } else if (delChoice.equals("N") || delChoice.equals("n")) {
                                //Return back to Reseller page
                            } else {
                                System.out.println("Invalid Entry");
                            }
                        }
                    }
                } else {
                    System.out.println("Invalid Entry");
                }


            }

        scan.close();



    }

}
