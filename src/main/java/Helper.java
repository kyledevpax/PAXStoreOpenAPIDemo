import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;

public class Helper {
    static boolean debug=false;
    static String systemAccessKey="AUJIL2QYP63CKY2AF501";
    static String systemAccessSecret="V4XTQ5DADRRC88GMZ97ETH5E47E5AHIUGH1LCPPL";
    static String url="https://www.paxstores.com/p-market-api";

    static void print(Result<ResellerDTO> result){
        ResellerDTO data=result.getData();
        System.out.println("ID:"+data.getId()+"\nName:"+data.getName()+"\nAddress:"+data.getAddress()+"\nCompany"+data.getCompany()+"\nContact:"+data.getContact()+"\nCountry"+data.getCountry()+"\nEmail"+data.getEmail()+"\nPhone"+data.getPhone()+"\nPostCode:"+data.getPostcode()+"\nStatus"+data.getStatus()+"\nEntity Attribute Values:"+data.getEntityAttributeValues());
    }
}
