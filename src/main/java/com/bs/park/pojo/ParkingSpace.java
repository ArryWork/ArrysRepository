package com.bs.park.pojo;

public class ParkingSpace {
    private int fid;

    private String status;

    private String parkingid;

    private String spaceorder;

    private double chargeScale;

    public double getChargeScale() {
        return chargeScale;
    }

    public void setChargeScale(double chargeScale) {
        this.chargeScale = chargeScale;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid ;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid == null ? null : parkingid.trim();
    }

    public String getSpaceorder() {
        return spaceorder;
    }

    public void setSpaceorder(String spaceorder) {
        this.spaceorder = spaceorder == null ? null : spaceorder.trim();
    }



}