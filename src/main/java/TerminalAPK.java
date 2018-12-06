import com.pax.market.api.sdk.java.api.base.dto.Result;
import com.pax.market.api.sdk.java.api.terminalApk.TerminalApkApi;
import com.pax.market.api.sdk.java.api.terminalApk.dto.CreateTerminalApkRequest;

import java.util.Map;

public class TerminalAPK {
    //Need more information about this!
    private TerminalApkApi terminal;

    TerminalAPK(){
        terminal=new TerminalApkApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }

    public Result<String> pushAPK(CreateTerminalApkRequest request){
        return terminal.createTerminalApk(request);
    }

    public CreateTerminalApkRequest createPushAPKRequest(String tid, String serialNo, String packageName, String version, String templateName, Map<String,String> parameters){
        CreateTerminalApkRequest request=new CreateTerminalApkRequest();
        request.setTid(tid);
        request.setSerialNo(serialNo);
        request.setPackageName(packageName);
        request.setVersion(version);
        request.setTemplateName(templateName);
        request.setParameters(parameters);
        return request;
    }
}