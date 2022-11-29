package fr.katsuo.launcher.auth;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;

public class Authenticate {

    private MicrosoftAuthResult result;

    public void authenticateMicrosoft(String email, String password) throws MicrosoftAuthenticationException {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        this.result = authenticator.loginWithCredentials(email, password);
    }

    public MicrosoftAuthResult getResult() {
        return result;
    }
}
