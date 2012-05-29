/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author VietDuc
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
public class ERooms {

    private int roomID;
    private String buildingName;
    private int roomNo;
    private int TotalSquare;
    private BigDecimal totalPrice;
    private BigDecimal totalremain;
    private String typeRoom;
    private String Description;
    private String status;

    public ERooms() {
    }

    public ERooms(int roomID, String buildingName, int roomNo, int TotalSquare, BigDecimal totalPrice,BigDecimal totalremain, String typeRoom, String Description, String status) {
        this.roomID = roomID;
        this.buildingName = buildingName;
        this.roomNo = roomNo;
        this.TotalSquare = TotalSquare;
        this.totalPrice = totalPrice;
        this.totalremain = totalremain;
        this.typeRoom = typeRoom;
        this.Description = Description;
        this.status = status;
    }

    public BigDecimal getTotalremain() {
        return totalremain;
    }

    public void setTotalremain(BigDecimal totalremain) {
        this.totalremain = totalremain;
    }
    
    public int getTotalSquare() {
        return TotalSquare;
    }

    public void setTotalSquare(int TotalSquare) {
        this.TotalSquare = TotalSquare;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
