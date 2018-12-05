import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantSearchOrderBy;
import com.pax.market.api.sdk.java.api.merchant.MerchantApi.MerchantStatus;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageDTO;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantUpdateRequest;

import java.util.LinkedHashMap;
import java.util.List;

public class Merchant {

    private MerchantApi merchantApi;

    Merchant(){
        merchantApi=new MerchantApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    public Result<MerchantPageDTO> searchForMerchants(int pageNo, int pageSize, MerchantSearchOrderBy orderBy, String name, MerchantStatus status){
        return merchantApi.searchMerchant(pageNo,pageSize,orderBy,name,status);
    }

    public Result<MerchantDTO> searchForSpecificMerchant(Long merchantId){
        return merchantApi.getMerchant(merchantId);
    }

    public NameIdPairs getNameandIDofMerchants(){
        NameIdPairs pairs;
        Result<MerchantPageDTO> result=merchantApi.searchMerchant(1,100,null,null,null);
        List<MerchantPageDTO> pages=result.getPageInfo().getDataSet();
        pairs=new NameIdPairs(pages.size());
        int i=0;    //index
        for(MerchantPageDTO page:pages){
            pairs.setId(i,page.getId());
            pairs.setName(i,page.getName());
            i++;    //increment index
        }
        return pairs;
    }

    public Result<MerchantDTO> createAMerchant(MerchantCreateRequest request){
        return merchantApi.createMerchant(request);
    }

    public MerchantCreateRequest CreateMerchantRequest(String name, String email, String resellerName, String contact, String country, String phone, String postCode, String address, String description, Boolean createUserFlag, List<String> merchantCategoryCodes, LinkedHashMap<String,String> entityAttributeValues){
        MerchantCreateRequest request=new MerchantCreateRequest();
        request.setName(name);
        request.setEmail(email);
        request.setResellerName(resellerName);
        request.setContact(contact);
        request.setCountry(country);
        request.setPhone(phone);
        request.setPostcode(postCode);
        request.setAddress(address);
        request.setDescription(description);
        // No way to set the create user flag? no function present in merchant create request

        request.setMerchantCategoryNames(merchantCategoryCodes);
        request.setEntityAttributeValues(entityAttributeValues);
        return request;
    }

    public Result<MerchantDTO> updateAMerchant(Long merchantId, MerchantUpdateRequest request){
        return merchantApi.updateMerchant(merchantId,request);
    }

    public MerchantUpdateRequest createMerchantUpdateRequest(String name, String email, String resellerName, String contact, String country,String phone, String postCode, String address, String description, String createUserFlag, List<String> merchantCategoryNames, LinkedHashMap<String,String> entityAttributeValues){
        MerchantUpdateRequest request=new MerchantUpdateRequest();
        request.setName(name);
        request.setEmail(email);
        request.setResellerName(resellerName);
        request.setCountry(country);
        request.setPhone(phone);
        request.setPostcode(postCode);
        request.setAddress(address);
        request.setDescription(description);
        //No way to set create user flag? no function in merchant create request
        request.setMerchantCategoryNames(merchantCategoryNames);
        request.setEntityAttributeValues(entityAttributeValues);
        return request;
    }

    public Result<String> activateAMerchant(Long id){
        return merchantApi.activateMerchant(id);
    }

    public Result<String> disableAMerchant(Long id){
        return merchantApi.disableMerchant(id);
    }

    public Result<String> deleteAMerchant(Long id){
        return merchantApi.deleteMerchant(id);
    }

    /*
    // No supported on PAXStore API
    public Result<String> replaceMerchantsEmail(Long id,String email, boolean createUser){
        // Function deprecated?
    }*/
}
