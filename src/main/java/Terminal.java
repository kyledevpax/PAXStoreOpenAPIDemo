import com.pax.market.api.sdk.java.api.terminal.TerminalApi;

public class Terminal {

    private TerminalApi terminalApi;

    Terminal(){
        terminalApi=new TerminalApi(Helper.url,Helper.systemAccessKey,Helper.systemAccessSecret);
    }



    //Functions: searchterminalsbypage, searchforspecificterminal, createaterminal, updateaterminal, activateaterminal, disableaterminal, deleteaterminal,
}
