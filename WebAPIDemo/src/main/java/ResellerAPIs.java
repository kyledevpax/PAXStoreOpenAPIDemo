import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;

import java.util.LinkedHashMap;

public class ResellerAPIs {

    private ResellerApi reseller;

    public static void main(String args[]){
        if(Helper.debug)
        System.out.println("Inside Main");

        ResellerAPIs test=new ResellerAPIs();
        Result<ResellerPageDTO> result=test.searchforReseller(1,10,null,null,null);
        System.out.println("Business Code:"+result.getBusinessCode());
        long id=1000000512;
        Result<ResellerDTO> searchResult=test.searchForSpecificReseller(id);
        /*System.out.println("Umm..."+searchResult.getPageInfo());*/

    }

    ResellerAPIs(){
        reseller=new ResellerApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    public Result<ResellerPageDTO> searchforReseller(int pageNo, int pageSize, ResellerApi.ResellerSearchOrderBy orderBy,String name, ResellerApi.ResellerStatus status){
        Result<ResellerPageDTO> result=reseller.searchReseller(pageNo,pageSize,orderBy,name,status);
        return result;
    }

    public Result<ResellerDTO> searchForSpecificReseller(Long id){
        return reseller.getReseller(id);
    }

    public Result<ResellerDTO> createAReseller(ResellerCreateRequest request){
        return reseller.createReseller(request);
    }

    public ResellerCreateRequest createResellerRequest(String name,String email, String country, String contact, String phone,String postCode, String address, String company,String parentResellerName, LinkedHashMap<String,String> entityAttributeKeyValues){
        ResellerCreateRequest request=new ResellerCreateRequest();
        request.setName(name);
        request.setEmail(email);
        request.setCountry(country);
        request.setContact(contact);
        request.setPhone(phone);
        request.setPostcode(postCode);
        request.setAddress(address);
        request.setCompany(company);
        request.setParentResellerName(parentResellerName);
        request.setEntityAttributeValues(entityAttributeKeyValues);
        return request;
    }

    public Result<ResellerDTO> updateAReseller(Long id, ResellerUpdateRequest request){
        return reseller.updateReseller(id,request);
    }

    public ResellerUpdateRequest createResellerUpdateRequest(String name,String email,String country,String contact,String phone,String postCode,String address, String company, String parentResellerName, LinkedHashMap<String,String> entityAttributeKeyValues){
        ResellerUpdateRequest request=new ResellerUpdateRequest();
        request.setName(name);
        request.setEmail(email);
        request.setCountry(country);
        request.setContact(contact);
        request.setPhone(phone);
        request.setPostcode(postCode);
        request.setAddress(address);
        request.setCompany(company);
        request.setParentResellerName(parentResellerName);
        request.setEntityAttributeValues(entityAttributeKeyValues);
        return request;
    }

    public Result<String> activateAReseller(Long id){
        return reseller.activateReseller(id);
    }

    public Result<String> disableAReseller(Long id){
        return reseller.disableReseller(id);
    }

    public Result<String> deleteAReseller(Long id){
        return reseller.deleteReseller(id);
    }

    /*  //Function has been Removed?
    public Result<String> updateResellersEmail(Long id, String email){
        return reseller.
    }*/


}
