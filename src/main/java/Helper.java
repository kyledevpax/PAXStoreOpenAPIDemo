import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;

public class Helper {
    static boolean debug=false;
    static String systemAccessKey="AUJIL2QYP63CKY2AF501";
    static String systemAccessSecret="V4XTQ5DADRRC88GMZ97ETH5E47E5AHIUGH1LCPPL";
    static String url="https://www.paxstores.com/p-market-api";

    static void printResellerResult(Result<ResellerDTO> result){
        ResellerDTO data=result.getData();
        System.out.println("\n\n\n\nHere is the information for "+data.getName());
        System.out.println( "ID: "+data.getId()+
                            "\nName: "+data.getName()+
                            "\nEmail: "+data.getEmail()+
                            "\nCountry: "+data.getCountry()+
                            "\nContact: "+data.getContact()+
                            "\nPhone: "+data.getPhone()+
                            "\nPostCode: "+data.getPostcode()+
                            "\nAddress: "+data.getAddress()+
                            "\nCompany: "+data.getCompany()+
                            "\nStatus: "+data.getStatus()+
                            "\nEntity Attribute Values: "+data.getEntityAttributeValues());
    }

    static void printMerchantResult(Result<MerchantDTO> result){
        MerchantDTO data=result.getData();
        System.out.println("\n\n\n\nHere is the information for "+data.getName());
        System.out.println( "ID: "+data.getId()+
                            "\nName: "+data.getName()+
                            "\nEmail: "+data.getEmail()+
                            "\nReseller: "+data.getReseller().getName()+
                            "\nContact: "+data.getContact()+
                            "\nCountry: "+data.getCountry()+
                            "\nPhone: "+data.getPhone()+
                            "\nPost Code: "+data.getPostcode()+
                            "\nAddress: "+data.getAddress()+
                            "\nDescription: "+data.getDescription()+
                            "\nStatus: "+data.getStatus()+
                            "\nMerchantCategory: "+data.getMerchantCategory()+
                            "\nEntityAttributeValues: "+data.getEntityAttributeValues());
    }

    static void printTerminalResult(Result<TerminalDTO> result){
        TerminalDTO data=result.getData();
        System.out.println("\n\n\n\nHere is the information for "+data.getName());
        System.out.println( "ID: "+data.getId()+
                "\nName: "+data.getName()+
                "\nTID: "+data.getTid()+
                "\nSerial Number: "+data.getSerialNo()+
                "\nMerchant Name: "+data.getMerchantName()+
                "\nReseller Name: "+data.getResellerName()+
                "\nModel Name: "+data.getModelName()+
                "\nLocation: (can't be returned)"+
                "\nTerminal Status: "+data.getStatus());
    }


    //for java ui
    static String printResellerResultString(Result<ResellerDTO> result){
        if(result.getData()!=null) {
            ResellerDTO data = result.getData();
            String s = "ID: " + data.getId() +
                    "\nName: " + data.getName() +
                    "\nEmail: " + data.getEmail() +
                    "\nCountry: " + data.getCountry() +
                    "\nContact: " + data.getContact() +
                    "\nPhone: " + data.getPhone() +
                    "\nPostCode: " + data.getPostcode() +
                    "\nAddress: " + data.getAddress() +
                    "\nCompany: " + data.getCompany() +
                    "\nStatus: " + data.getStatus() +
                    "\nEntity Attribute Values: " + data.getEntityAttributeValues();
            return s;
        }
        return "";
    }

    static String printMerchantResultString(Result<MerchantDTO> result){
        if(result.getData()!=null) {
            MerchantDTO data = result.getData();
            String s = "ID: " + data.getId() +
                    "\nName: " + data.getName() +
                    "\nEmail: " + data.getEmail() +
                    "\nReseller: " + data.getReseller().getName() +
                    "\nContact: " + data.getContact() +
                    "\nCountry: " + data.getCountry() +
                    "\nPhone: " + data.getPhone() +
                    "\nPost Code: " + data.getPostcode() +
                    "\nAddress: " + data.getAddress() +
                    "\nDescription: " + data.getDescription() +
                    "\nStatus: " + data.getStatus() +
                    "\nMerchantCategory: " + data.getMerchantCategory() +
                    "\nEntityAttributeValues: " + data.getEntityAttributeValues();
            return s;
        }
        return "";
    }

    static String printTerminalResultString(Result<TerminalDTO> result){
        if(result.getData()!=null) {
            TerminalDTO data = result.getData();
            String s = "ID: " + data.getId() +
                    "\nName: " + data.getName() +
                    "\nTID: " + data.getTid() +
                    "\nSerial Number: " + data.getSerialNo() +
                    "\nMerchant Name: " + data.getMerchantName() +
                    "\nReseller Name: " + data.getResellerName() +
                    "\nModel Name: " + data.getModelName() +
                    "\nLocation: (can't be returned)" +
                    "\nTerminal Status: " + data.getStatus();
            return s;
        }
        return "";
    }

}
