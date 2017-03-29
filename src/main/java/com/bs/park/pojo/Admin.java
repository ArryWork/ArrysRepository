package com.bs.park.pojo;

public class Admin {
    private String fid;

    private String name;

    private String account;

    private String password;

    private String areaid;

    private String phone;

    private String admintype;

    private String headurl;

    private String adminmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAdmintype() {
        return admintype;
    }

    public void setAdmintype(String admintype) {
        this.admintype = admintype == null ? null : admintype.trim();
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl == null ? null : headurl.trim();
    }

    public String getAdminmail() {
        return adminmail;
    }

    public void setAdminmail(String adminmail) {
        this.adminmail = adminmail == null ? null : adminmail.trim();
    }
}