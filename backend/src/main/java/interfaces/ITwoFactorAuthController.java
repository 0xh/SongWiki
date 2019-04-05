package interfaces;

import entities.TwoFactorAuth;

public interface ITwoFactorAuthController {
    TwoFactorAuth get2FAInfo(String username);
    void set2FA(String username, String key);
    void remove2FA(String username);
}
