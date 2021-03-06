import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.reseller.ResellerApi;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerCreateRequest;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerPageDTO;
import com.pax.market.api.sdk.java.api.reseller.dto.ResellerUpdateRequest;

import java.util.LinkedHashMap;
import java.util.List;

public class Reseller {

    private ResellerApi reseller;

    Reseller(){
        reseller=new ResellerApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    public Result<ResellerPageDTO> searchforResellers(int pageNo, int pageSize, ResellerApi.ResellerSearchOrderBy orderBy,String name, ResellerApi.ResellerStatus status){
        Result<ResellerPageDTO> result=reseller.searchReseller(pageNo,pageSize,orderBy,name,status);
        return result;
    }

    public NameIdPairs getNameandIDofResellers(){
        Result<ResellerPageDTO> result=reseller.searchReseller(1,100,null,null,null);
        List<ResellerPageDTO> pages=result.getPageInfo().getDataSet();
        NameIdPairs pairs = new NameIdPairs(pages.size());
        int i=0;
        for(ResellerPageDTO page:pages){
           pairs.setId(i,page.getId());
           pairs.setName(i,page.getName());
           i++;
        }
        return pairs;
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
        System.out.println("Reseller Activated");
        return reseller.activateReseller(id);
    }

    public Result<String> disableAReseller(Long id){
        System.out.println("Reseller Deactivated");
        return reseller.disableReseller(id);
    }

    public Result<String> deleteAReseller(Long id){
        System.out.println("Reseller Deleted");
        return reseller.deleteReseller(id);
    }
}