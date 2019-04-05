package api;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import controllers.TwoFactorAuthController;
import entities.TwoFactorAuth;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("2fa")
public class TwoFactorAuthEndpoint {

    /**
     * Documentation for the GoogleAuth library used can be found here:
     * https://drive.google.com/drive/u/0/folders/0BxZtP9CHH-Q6TzRSaWtkQ0pEYk0
     */

    @Inject
    private GoogleAuthenticator gAuth;

    @Inject
    private TwoFactorAuthController authController;

    /**
     * Register an account with the given username for 2FA using a QR code
     * @param username username of account to register
     * @return the URL of the QR code that has to be scanned for validation
     */
    @GET
    @Path("/{username}")
    public String registerFor2FA(@PathParam("username") String username) {
        final GoogleAuthenticatorKey key = gAuth.createCredentials();

        authController.set2FA(username, key.getKey());

        return GoogleAuthenticatorQRGenerator.getOtpAuthURL("SongWiki", username, key);
    }

    /**
     * Register an account with the given username for 2FA using a key
     * @param username username of account to register
     * @return the key that has to be entered to set up the authenticator
     */
    @GET
    @Path("/key/{username}")
    public String registerFor2FAKey(@PathParam("username") String username) {
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        authController.set2FA(username, key.getKey());

        return key.getKey();
    }

    /**
     * Checks the returned 2FA code against the expected code using the secret
     * @param authResponse object containing confirmation information from the user
     */
    @POST
    public boolean checkValidityOfVerificationCode(TwoFactorAuth authResponse) {
        TwoFactorAuth authInfo = authController.get2FAInfo(authResponse.getUsername());
        return gAuth.authorize(authInfo.getKey(), authResponse.getVerificationCode());
    }

    // TODO: add JWT check so you can't disable 2FA without login credentials
    /**
     * Removes 2FA from the account with the specified username
     * @param username username of account to disable 2FA on
     */
    @DELETE
    @Path("/{username}")
    public void remove2FA(@PathParam("username") String username) {
        authController.remove2FA(username);
    }
}
