import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.category.MerchantCategoryApi;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryCreateRequest;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryDTO;
import com.pax.market.api.sdk.java.api.merchant.category.dto.MerchantCategoryUpdateRequest;

import java.util.ArrayList;
import java.util.List;

public class MerchantCategory {

    private MerchantCategoryApi merchantCategoryApi;

    MerchantCategory(){
        merchantCategoryApi=new MerchantCategoryApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    public Result<ArrayList<MerchantCategoryDTO>> getParticularMerchantCategories(String name){
        return merchantCategoryApi.getMerchantCategories(name);
    }

    public Result<MerchantCategoryDTO> createAMerchantCategory(String name, String remarks){
        MerchantCategoryCreateRequest request=new MerchantCategoryCreateRequest();
        request.setName(name);
        request.setRemarks(remarks);
        return merchantCategoryApi.createMerchantCategory(request);
    }

    public Result<MerchantCategoryDTO> updateAMerchantCategory(Long id, String name, String remarks){
        MerchantCategoryUpdateRequest request=new MerchantCategoryUpdateRequest();
        request.setName(name);
        request.setRemarks(remarks);
        return merchantCategoryApi.updateMerchantCategory(id, request);
    }

    public Result<String> deleteAMerchantCategory(Long id){
        return merchantCategoryApi.deleteMerchantCategory(id);
    }

    public Result<ArrayList<MerchantCategoryDTO>> createListOfMerchantCategories(List<MerchantCategoryCreateRequest> requests, boolean skip){
        return merchantCategoryApi.batchCreateMerchantCategory(requests, skip);
    }
}
