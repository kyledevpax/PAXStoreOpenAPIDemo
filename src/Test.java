import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;

public class Test {
    public Test()
    {
        ResellerApi reseller = new ResellerApi(Helper.url, Helper.systemAccessKey, Helper.systemAccessSecret);

        String resellerName = "PAXUS Test Reseller";
        String resellerEmail = "kyle.liu@pax.us";
        String resellerCountry = "USA";
        String resellerContact = "Kyle";
        String resellerPhone = "1234567890";
        String resellZipCode = "32256";
        String resellerAddress = "Freedom Crossing Trail";
        String resellerCompany = "PAX";
        String resellerParent = "";
        String resellerExtAttributes = "";

        ResellerCreateRequest resellerCreateRequest = new ResellerCreateRequest();
        resellerCreateRequest.setName(resellerName);
        resellerCreateRequest.setEmail(resellerEmail);
        resellerCreateRequest.setCountry(resellerCountry);
        resellerCreateRequest.setContact(resellerContact);
        resellerCreateRequest.setPhone(resellerPhone);
        resellerCreateRequest.setPostcode(resellZipCode);
        resellerCreateRequest.setAddress(resellerAddress);
        resellerCreateRequest.setCompany(resellerCompany);
        resellerCreateRequest.setParentResellerName(null);
        resellerCreateRequest.setEntityAttributeValues(null);

        Result<ResellerDTO> createResellerResult = reseller.createReseller(resellerCreateRequest);
    }
}
