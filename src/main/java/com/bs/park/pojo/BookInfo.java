package com.bs.park.pojo;

import java.util.Date;

public class BookInfo {
    private String fid;

    private String status;

    private String userid;

    private String parkingid;

    private Date booktime;

    private Date parktime;

    private String ispayed;

    private double payamt;

    private Date endtime;

    private String spaceord;

    private String parkinglot;

    private String hasParkedTime;

    private String area;

    private String username;

    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    private double scale;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public String getHasParkedTime() {
        return hasParkedTime;
    }

    public void setHasParkedTime(String hasParkedTime) {
        this.hasParkedTime = hasParkedTime;
    }

    public String getParkinglot() {
        return parkinglot;
    }

    public void setParkinglot(String parkinglot) {
        this.parkinglot = parkinglot;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid == null ? null : parkingid.trim();
    }

    public Date getBooktime() {
        return booktime;
    }

    public String getIspayed() {
        return ispayed;
    }

    public void setIspayed(String ispayed) {
        this.ispayed = ispayed == null ? null : ispayed.trim();
    }

    public double getPayamt() {
        return payamt;
    }

    public void setPayamt(double payamt) {
        this.payamt = payamt;
    }

    public String getSpaceord() {
        return spaceord;
    }

    public void setSpaceord(String spaceord) {
        this.spaceord = spaceord == null ? null : spaceord.trim();
    }

    public void setBooktime(Date booktime) {
        this.booktime = booktime;
    }

    public Date getParktime() {
        return parktime;
    }

    public void setParktime(Date parktime) {
        this.parktime = parktime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}