import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
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
        ResellersNameIdPairs resNIDP;

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


                }
                else if (choice.equals("2")) {
                    // call the function to List all of the resellers
                    //choose a reseller

                    resNIDP = res.getNameandIDofResellers();
                    System.out.println("\nList of Resellers (Pick one)");
                    for (int i =0; i<= resNIDP.getTopIndex();i++){
                        System.out.println((i+1) +". "+ resNIDP.getName(i));
                    }
                    //Create a function to Display the information for the Reseller and provide the option to Modify or Delete

                    String resellerChoice = scan.next();
                    int resNum = Integer.parseInt(resellerChoice);


                    Helper.print(res.searchForSpecificReseller(new Long(resNIDP.getId(resNum -1))));

                    System.out.println("\n======================\nNEED TO PRINT THE RESELLER MERCHANTS\n====================\n");


                    //Third screen will display options for a particular Reseller

                    //Print the name of the Reseller at the top followed by:
                     System.out.println("\nThese are the options for " + resNIDP.getName(resNum - 1) );//DELETE THIS LINE LATER
                     System.out.println("1. Modify Reseller information");
                     System.out.println("2. Activate Reseller");
                     System.out.println("3. Deactivate Reseller");
                     System.out.println("4. Add a merchant");
                     System.out.println("5. Delete Reseller");
                     System.out.println("6. Return to main menu");

                     String choiceForAReseller = scan.next();

                     if(choiceForAReseller.equals("1")){
                         System.out.println("The current information for " + resNIDP.getName(resNum -1) + " is:");
                         Helper.print(res.searchForSpecificReseller(new Long(resNIDP.getId(resNum-1))));
                         System.out.println("Please Enter the updated information for the Reseller:");

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

                         ResellerUpdateRequest request = res.createResellerUpdateRequest(name, email, country, contact, phone, postCode, address, company, null, map);
                         res.updateAReseller(new Long(resNIDP.getId(resNum -1)), request);
                     }
                     else if(choiceForAReseller.equals("2")){
                         res.activateAReseller(new Long(resNIDP.getId(resNum -1)));
                     }
                     else if(choiceForAReseller.equals("3")){
                         res.disableAReseller(new Long(resNIDP.getId(resNum -1)));
                     }
                     else if(choiceForAReseller.equals("4")){
                        //add a merchant
                     }
                     else if(choiceForAReseller.equals("5")){
                         //Delete a reseller
                         System.out.println("Are you sure? Y/N");
                         String delChoice = scan.next();
                         if (delChoice.equals("Y") || delChoice.equals("y")) {
                             //Call function to delete a Reseller
                             res.deleteAReseller(new Long(resNIDP.getId(resNum -1)));
                         } else if (delChoice.equals("N") || delChoice.equals("n")) {
                             //Return back to Reseller page
                             continue;
                         } else {
                             System.out.println("Invalid Entry");
                         }
                     }
                     else if(choiceForAReseller.equals("6")){
                         continue;
                     }
                     else{
                         System.out.println("Invalid choice returning to main menu");
                         continue;
                     }

                }
                else {
                    System.out.println("Invalid Entry");
                }


            }

        scan.close();



    }


}
