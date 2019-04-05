package controllers;

import entities.TwoFactorAuth;
import interfaces.ITwoFactorAuthController;
import repositories.TwoFactorAuthRepository;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class TwoFactorAuthController implements ITwoFactorAuthController {

    @Inject
    private TwoFactorAuthRepository authRepository;

    @Override
    public TwoFactorAuth get2FAInfo(String username) {
        return authRepository.find(username);
    }

    @Override
    public void set2FA(String username, String key) {
        TwoFactorAuth authObject = new TwoFactorAuth();
        authObject.setUsername(username);
        authObject.setKey(key);

        authRepository.save(authObject);
    }

    @Override
    public void remove2FA(String username) {
        TwoFactorAuth authObject = get2FAInfo(username);
        authRepository.delete(authObject);
    }

}
