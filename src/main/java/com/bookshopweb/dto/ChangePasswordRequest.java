package com.bookshopweb.dto;

public class ChangePasswordRequest {
    private Long userId;
    private String currentPassword;
    private String newPassword;
    private String newPasswordAgain;

    public ChangePasswordRequest(Long userId, String currentPassword, String newPassword, String newPasswordAgain) {
        this.userId = userId;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }

    public ChangePasswordRequest(String currentPassword, String newPassword, String newPasswordAgain) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordAgain = newPasswordAgain;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public String getNewPasswordAgain() {
        return newPasswordAgain;
    }

    public void setNewPasswordAgain(String newPasswordAgain) {
        this.newPasswordAgain = newPasswordAgain;
    }
}
