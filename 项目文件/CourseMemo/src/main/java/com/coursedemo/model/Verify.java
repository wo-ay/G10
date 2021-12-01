package com.coursedemo.model;

public class Verify {
    private int verify_id;
    private String user_identity;
    private String user_id;
    private String user_name;
    private String password;
    private String user_identitycard;
    private String phone;
    private String email;
    private String verify_status;

    public Verify(int verify_id, String user_identity, String user_id, String user_name, String password, String user_identitycard, String phone, String email, String verify_status) {
        this.verify_id = verify_id;
        this.user_identity = user_identity;
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.user_identitycard = user_identitycard;
        this.phone = phone;
        this.email = email;
        this.verify_status = verify_status;
    }

    public Verify() {
    }

    public int getVerify_id() {
        return verify_id;
    }

    public void setVerify_id(int verify_id) {
        this.verify_id = verify_id;
    }

    public String getUser_identity() {
        return user_identity;
    }

    public void setUser_identity(String user_identity) {
        this.user_identity = user_identity;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_identitycard() {
        return user_identitycard;
    }

    public void setUser_identitycard(String user_identitycard) {
        this.user_identitycard = user_identitycard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(String verify_status) {
        this.verify_status = verify_status;
    }
}
