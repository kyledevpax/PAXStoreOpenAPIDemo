import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.merchant.dto.MerchantPageDTO;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalSearchOrderBy;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;

import java.util.List;

public class Terminal {

    private TerminalApi terminalApi;

    Terminal(){
        terminalApi=new TerminalApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    //What is TID? WHat does this function do?
    public Result<TerminalDTO> searchTerminals(int pageNum, int pageSize, TerminalSearchOrderBy orderBy, TerminalStatus status, String tid){
        return terminalApi.searchTerminal(pageNum,pageSize,orderBy,status,tid);
    }

    public Result<TerminalDTO> searchForSpecificTerminal(Long id){
        return terminalApi.getTerminal(id);
    }

    public NameIdPairs getNameandIDofTerminals(){
        NameIdPairs pairs;
        Result<TerminalDTO> result = terminalApi.searchTerminal(1,100,null,null,null);
        List<TerminalDTO> pages=result.getPageInfo().getDataSet();
        pairs=new NameIdPairs(pages.size());
        int i=0;    //index
        for(TerminalDTO page:pages){
            pairs.setId(i,page.getId());
            pairs.setName(i,page.getName());
            i++;    //increment index
        }
        return pairs;
    }

    public Result<TerminalDTO> createATerminal(TerminalCreateRequest request){
        return terminalApi.createTerminal(request);
    }


    public TerminalCreateRequest CreateTerminalCreateRequest(String name, String tid, String serialNo, String merchantName, String resellerName, String modelName, String location, TerminalStatus status){
        TerminalCreateRequest request=new TerminalCreateRequest();
        request.setName(name);
        request.setTid(tid);
        request.setSerialNo(serialNo);
        request.setMerchantName(merchantName);
        request.setResellerName(resellerName);
        request.setModelName(modelName);
        request.setLocation(location);
        request.setStatus(status);
        return  request;
    }

    public Result<TerminalDTO> updateATerminal(Long id, TerminalUpdateRequest request){
        return terminalApi.updateTerminal(id,request);
    }

    public TerminalUpdateRequest createTerminalUpdateRequest(String name, String tid, String serialNo, String merchantName,String resellerName, String modelName, String location){
        TerminalUpdateRequest request=new TerminalUpdateRequest();
        request.setName(name);
        request.setTid(tid);
        request.setSerialNo(serialNo);
        request.setMerchantName(merchantName);
        request.setResellerName(resellerName);
        request.setModelName(modelName);
        request.setLocation(location);
        return request;
    }

    public Result<String> activateATerminal(Long id){
        return terminalApi.activateTerminal(id);
    }

    public Result<String> disableATerminal(Long id){
        return terminalApi.disableTerminal(id);
    }

    public Result<String> deleteATerminal(Long id){
        return terminalApi.deleteTerminal(id);
    }

}
