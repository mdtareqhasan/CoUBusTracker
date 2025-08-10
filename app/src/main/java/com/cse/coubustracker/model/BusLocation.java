package com.cse.coubustracker.model;

public class BusLocation {
    private String driverName;
    private String mobileNumber;
    private String locationLink;
    private String busTitle;

    public BusLocation() {}

    public BusLocation(String driverName, String mobileNumber, String locationLink, String busTitle) {
        this.driverName = driverName;
        this.mobileNumber = mobileNumber;
        this.locationLink = locationLink;
        this.busTitle = busTitle;

    }

    public String getDriverName() { return driverName; }
    public String getMobileNumber() { return mobileNumber; }
    public String getLocationLink() { return locationLink; }
    public String getBusTitle() {return  busTitle; }

    public void setDriverName(String driverName) { this.driverName = driverName; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setLocationLink(String locationLink) { this.locationLink = locationLink; }
    public void setBusTitle(String busTitle) { this.busTitle = busTitle; }
}
