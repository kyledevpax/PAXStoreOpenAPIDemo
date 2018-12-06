import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;
import sun.awt.image.ImageWatched;

import java.util.LinkedHashMap;
import java.util.Scanner;

import static com.pax.market.api.sdk.java.api.terminal.TerminalApi.*;


public class Main {

    public static void main(String[] args) {


        //First screen will either add a new reseller or provide a list of all the current resellers(for modifying/ deleting)
        Scanner scan = new Scanner(System.in);

        boolean programRunning = true;
        Reseller res = new Reseller();
        NameIdPairs resNIDP;
        NameIdPairs mercNIDP;
        NameIdPairs termNIDP;

        Merchant merc = new Merchant();
        Terminal term = new Terminal();
        TerminalAPK termApk = new TerminalAPK();

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
                    Long chosenResID = new Long(resNIDP.getId(resNum-1));
                    String chosenResName = resNIDP.getName(resNum-1);

                    Helper.printResellerResult(res.searchForSpecificReseller(chosenResID));


                    //Print the name of the Reseller at the top followed by:
                     System.out.println("\nThese are the options for " + chosenResName );
                     System.out.println("1. Modify Reseller information");
                     System.out.println("2. Activate Reseller");
                     System.out.println("3. Deactivate Reseller");
                     System.out.println("4. Add a merchant");
                     System.out.println("5. List of Merchants");
                     System.out.println("6. Delete Reseller");
                     System.out.println("7. Return to main menu");

                     String choiceForAReseller = scan.next();

                     //Modify Reseller information
                     if(choiceForAReseller.equals("1")){
                         System.out.println("The current information for " + chosenResName + " is:");
                         Helper.printResellerResult(res.searchForSpecificReseller(chosenResID));
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
                         res.updateAReseller(chosenResID, request);
                     }
                     //Activate Reseller
                     else if(choiceForAReseller.equals("2")){
                         res.activateAReseller(chosenResID);
                     }
                     //Deactivate Reseller
                     else if(choiceForAReseller.equals("3")){
                         res.disableAReseller(chosenResID);
                     }
                     //Add a merchant to the selected Reseller
                     else if(choiceForAReseller.equals("4")){
                         System.out.println("Please enter the following information about the merchant:");

                         System.out.print("Name: ");
                         String name = scan.next();

                         System.out.print("Email: ");
                         String email = scan.next();

                         String resellerName = chosenResName;

                         System.out.print("Contact (company phone");
                         String contact = scan.next();

                         System.out.print("Country: ");
                         String country = scan.next();

                         System.out.print("Phone: ");
                         String phone = scan.next();

                         System.out.print("Post Code: ");
                         String postCode = scan.next();

                         System.out.print("Address: ");
                         String address = scan.next();

                         System.out.print("Description: ");
                         String description = scan.next();

                         boolean createUserFlag = true;

                         MerchantCreateRequest mercRequest = merc.CreateMerchantRequest(name, email, resellerName , contact, country, phone, postCode, address, description, createUserFlag, null, null);
                         merc.createAMerchant(mercRequest);


                     }
                     //List the merchants for the chosen Reseller
                     else if(choiceForAReseller.equals("5")){

                         mercNIDP = merc.getNameandIDofMerchants();
                         System.out.println("\n\n\n\nList of merchants for " + chosenResName+ " (Pick one)");
                         for (int i =0; i<= mercNIDP.getTopIndex();i++){
                             System.out.println((i+1) +". "+ mercNIDP.getName(i));
                         }

                         String merchantChoice = scan.next();
                         int mercNum = Integer.parseInt(merchantChoice);
                         Long chosenMercID = new Long(mercNIDP.getId(mercNum-1));
                         String chosenMercName = mercNIDP.getName(mercNum-1);

                         Helper.printMerchantResult(merc.searchForSpecificMerchant(chosenMercID));

                         System.out.println("\n\nThese are the options for " + chosenMercName);
                         System.out.println("1. Modify Merchant information");
                         System.out.println("2. Activate Merchant");
                         System.out.println("3. Deactivate Merchant");
                         System.out.println("4. Add a Terminal");
                         System.out.println("5. List of Terminals");
                         System.out.println("6. Delete Merchant");
                         System.out.println("7. Return to main menu");

                         String choiceForAMerchant = scan.next();

                         //Modify merchant information
                         if(choiceForAMerchant.equals("1")){
                             System.out.println("The current information for " + chosenMercName + " is:");
                             Helper.printMerchantResult(merc.searchForSpecificMerchant(chosenMercID));
                             System.out.println("Please Enter the updated information for the Merchant:");

                             System.out.print("Name: ");
                             String name = scan.next();

                             System.out.print("Email: ");
                             String email = scan.next();

                             String resellerName = chosenResName;

                             System.out.print("Contact (company phone");
                             String contact = scan.next();

                             System.out.print("Country: ");
                             String country = scan.next();

                             System.out.print("Phone: ");
                             String phone = scan.next();

                             System.out.print("Post Code: ");
                             String postCode = scan.next();

                             System.out.print("Address: ");
                             String address = scan.next();

                             System.out.print("Description: ");
                             String description = scan.next();

                             String createUserFlag = "true";

                             MerchantUpdateRequest mercRequest = merc.createMerchantUpdateRequest(name, email, resellerName , contact, country, phone, postCode, address, description, createUserFlag, null, null);
                             merc.updateAMerchant(chosenMercID, mercRequest);
                         }
                         //Activate Merchant
                         else if(choiceForAMerchant.equals("2")){
                            merc.activateAMerchant(chosenMercID);
                         }
                         //Deactivate Merchant
                         else if(choiceForAMerchant.equals("3")){
                             merc.disableAMerchant(chosenMercID);
                         }
                         //Add a terminal
                         else if(choiceForAMerchant.equals("4")){
                            System.out.println("Please enter the following information about the terminal:");

                            System.out.print("Name: ");
                            String name = scan.next();

                            System.out.print("TID: ");
                            String tid = scan.next();

                            System.out.print("Serial Number: ");
                            String serialNo = scan.next();

                            String merchantName = chosenMercName;

                            String resellerName = chosenResName;

                            System.out.print("Model Name: ");
                            String modelName = scan.next();

                            System.out.print("Location: ");
                            String location = scan.next();

                            TerminalCreateRequest termRequest = term.CreateTerminalCreateRequest(name, tid, serialNo, merchantName, resellerName, modelName, location, TerminalStatus.Inactive);
                            term.createATerminal(termRequest);
                         }
                         //List of Terminals
                         else if(choiceForAMerchant.equals("5")){
                             termNIDP = term.getNameandIDofTerminals();
                             System.out.println("\n\n\n\nList of terminals for " + chosenMercName+ " (Pick one)");
                             for (int i =0; i<= termNIDP.getTopIndex();i++){
                                 System.out.println((i+1) +". "+ termNIDP.getName(i));
                             }

                             String terminalChoice = scan.next();
                             int termNum = Integer.parseInt(terminalChoice);
                             Long chosenTermID = new Long(termNIDP.getId(termNum-1));
                             String chosenTermName = termNIDP.getName(termNum-1);

                             Helper.printTerminalResult(term.searchForSpecificTerminal(chosenTermID));

                             System.out.println("\n\nThese are the options for " + chosenTermName);
                             System.out.println("1. Modify Terminal information");
                             System.out.println("2. Activate Terminal");
                             System.out.println("3. Deactivate Terminal");
                             System.out.println("4. Delete Terminal");
                             System.out.println("5. Push an application");
                             System.out.println("6. Return to main menu");

                             String choiceForATerminal = scan.next();

                             //Modify terminal information
                             if(choiceForATerminal.equals("1")){
                                 System.out.println("The current information for " + chosenTermName + " is:");
                                 Helper.printTerminalResult(term.searchForSpecificTerminal(chosenTermID));
                                 System.out.println("Please Enter the updated information for the Terminal:");

                                 System.out.print("Name: ");
                                 String name = scan.next();

                                 System.out.print("TID: ");
                                 String tid = scan.next();

                                 System.out.print("Serial Number: ");
                                 String serialNo = scan.next();

                                 System.out.println("Merchant Name: ");
                                 String merchantName = scan.next();

                                 System.out.println("Reseller Name: ");
                                 String resellerName = scan.next();

                                 System.out.print("Model Name: ");
                                 String modelName = scan.next();

                                 System.out.print("Location: ");
                                 String location = scan.next();

                                 TerminalUpdateRequest termRequest = term.createTerminalUpdateRequest( name, tid, serialNo, merchantName, resellerName, modelName, location);
                                 term.updateATerminal(chosenTermID, termRequest);

                             }
                             //Activate a terminal
                             else if(choiceForATerminal.equals("2")){
                                 term.activateATerminal(chosenTermID);
                             }
                             //Deactivate a terminal
                             else if(choiceForATerminal.equals("3")){
                                 term.disableATerminal(chosenTermID);
                             }
                             //delete a terminal
                             else if(choiceForATerminal.equals("4")){
                                 System.out.println("Are you sure? Y/N");
                                 String delChoice = scan.next();
                                 if (delChoice.equals("Y") || delChoice.equals("y")) {
                                     //Call function to delete a terminal
                                     term.deleteATerminal(chosenTermID);
                                 } else if (delChoice.equals("N") || delChoice.equals("n")) {
                                     //Return back to Main page
                                     continue;
                                 } else {
                                     System.out.println("Invalid Entry");
                                 }
                             }
                             //push an application
                             else if(choiceForATerminal.equals("5")){

                                 System.out.println("Enter the following information about the app you want to push: ");

                                 System.out.print("TID: ");
                                 String tid = scan.next();

                                 System.out.print("Serial Number");
                                 String serialNo = scan.next();

                                 System.out.print("Package Name: ");
                                 String packageName = scan.next();

                                 System.out.print("Version: ");
                                 String version = scan.next();

                                 System.out.print("Template Name: ");
                                 String templateName = scan.next();

                                 System.out.print("Parameters: (skip)");

                                 CreateTerminalApkRequest termRequest = termApk.createPushAPKRequest( tid, serialNo, packageName, version, templateName, map);
                                 termApk.pushAPK(termRequest);

                             }
                             //Return to main menu
                             else if(choiceForATerminal.equals("6")){
                                 continue;
                             }
                             else{
                                 System.out.println("Invalid choice. Returning to main menu.");
                                 continue;
                             }
                         }
                         //Delete Merchant
                         else if(choiceForAMerchant.equals("6")){
                             System.out.println("Are you sure? Y/N");
                             String delChoice = scan.next();
                             if (delChoice.equals("Y") || delChoice.equals("y")) {
                                 //Call function to delete a Merchant
                                 merc.deleteAMerchant(chosenMercID);
                             } else if (delChoice.equals("N") || delChoice.equals("n")) {
                                 //Return back to Main page
                                 continue;
                             } else {
                                 System.out.println("Invalid Entry");
                             }
                         }
                         //Return to Main Menu
                         else if(choiceForAMerchant.equals("7")){
                             continue;
                         }
                         else{
                             System.out.println("Invalid choice. Returning to main menu.");
                             continue;
                         }
                     }
                     //Delete a Reseller
                     else if(choiceForAReseller.equals("6")){
                         System.out.println("Are you sure? Y/N");
                         String delChoice = scan.next();
                         if (delChoice.equals("Y") || delChoice.equals("y")) {
                             //Call function to delete a Reseller
                             res.deleteAReseller(chosenResID);
                         } else if (delChoice.equals("N") || delChoice.equals("n")) {
                             //Return back to Main page
                             continue;
                         } else {
                             System.out.println("Invalid Entry");
                         }
                     }
                     else if(choiceForAReseller.equals("7")){
                         continue;
                     }
                     else{
                         System.out.println("Invalid choice. Returning to main menu");
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
