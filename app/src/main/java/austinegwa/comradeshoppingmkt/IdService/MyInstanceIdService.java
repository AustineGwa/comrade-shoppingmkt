package austinegwa.comradeshoppingmkt.IdService;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by gwaza on 1/20/2018.
 */

public class MyInstanceIdService extends InstanceIDListenerService {

    public void onTokenRefresh() {
        //refreshAllTokens();
    }
/*
    private void refreshAllTokens() {
        // assuming you have defined TokenList as
        // some generalized store for your tokens
        ArrayList<TokenList> tokenList = TokensList.get();
        InstanceID iid = InstanceID.getInstance(this);
        for(tokenItem : tokenList) {
            tokenItem.token =
                    iid.getToken(tokenItem.authorizedEntity,tokenItem.scope,tokenItem.options);
            // send this tokenItem.token to your server
        }
    }
    */
}
