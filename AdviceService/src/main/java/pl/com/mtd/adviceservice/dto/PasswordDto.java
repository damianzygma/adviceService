package pl.com.mtd.adviceservice.dto;

import org.springframework.stereotype.Component;

@Component
public class PasswordDto {
    private String currentPassword;
    private String newPassword;
    private String reenteredNewPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReenteredNewPassword() {
        return reenteredNewPassword;
    }

    public void setReenteredNewPassword(String reenteredNewPassword) {
        this.reenteredNewPassword = reenteredNewPassword;
    }
}
