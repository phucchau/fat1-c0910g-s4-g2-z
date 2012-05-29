/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author TungDT
 */
public class EBuilding {
        private int  buildingID ;
	private String buildingName;
	private String buildingType ;
	private int totalFloors ;
	private int totalRooms;
        private String Image;
	private String datetime;
	private double price ;
        private int landID ;
        private String Description;
        private String status;

    public EBuilding() {
    }

    public EBuilding(int buildingID, String buildingName, String buildingType, int totalFloors, int totalRooms, String Image, String datetime, double price, int landID, String Description, String status) {
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.buildingType = buildingType;
        this.totalFloors = totalFloors;
        this.totalRooms = totalRooms;
        this.Image = Image;
        this.datetime = datetime;
        this.price = price;
        this.landID = landID;
        this.Description = Description;
        this.status = status;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(int totalRooms) {
        this.totalRooms = totalRooms;
    }
    
    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return Image;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }
        
}
