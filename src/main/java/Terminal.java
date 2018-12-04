import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalSearchOrderBy;
import com.pax.market.api.sdk.java.api.terminal.TerminalApi.TerminalStatus;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalCreateRequest;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalDTO;
import com.pax.market.api.sdk.java.api.terminal.dto.TerminalUpdateRequest;

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
