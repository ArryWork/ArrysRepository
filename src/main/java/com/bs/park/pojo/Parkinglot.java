package com.bs.park.pojo;

public class Parkinglot {
    private String fid;

    private Integer spacenumber;

    private String areaid;

    private Double chargscale;

    private Integer availablespace;

    private String detailaddress;

    private String lotorder;

    private String area;

    private String longtitude;

    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public Integer getSpacenumber() {
        return spacenumber;
    }

    public void setSpacenumber(Integer spacenumber) {
        this.spacenumber = spacenumber;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid == null ? null : areaid.trim();
    }

    public Double getChargscale() {
        return chargscale;
    }

    public void setChargscale(Double chargscale) {
        this.chargscale = chargscale;
    }

    public Integer getAvailablespace() {
        return availablespace;
    }

    public void setAvailablespace(Integer availablespace) {
        this.availablespace = availablespace;
    }

    public String getDetailaddress() {
        return detailaddress;
    }

    public void setDetailaddress(String detailaddress) {
        this.detailaddress = detailaddress == null ? null : detailaddress.trim();
    }

    public String getLotorder() {
        return lotorder;
    }

    public void setLotorder(String lotorder) {
        this.lotorder = lotorder == null ? null : lotorder.trim();
    }
}