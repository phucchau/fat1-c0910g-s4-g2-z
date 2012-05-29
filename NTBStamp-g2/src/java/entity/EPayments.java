/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author VietDuc
 */
public class EPayments {

    private int paymentID;
    private int roomID;
    private String customerID;
    private double payment;
    private String paydate;
    private String payCharge;
    private String Status;

    public EPayments() {
    }

    public EPayments(int paymentID, int roomID, String customerID, double payment, String paydate, String payCharge, String Status) {
        this.paymentID = paymentID;
        this.roomID = roomID;
        this.customerID = customerID;
        this.payment = payment;
        this.paydate = paydate;
        this.payCharge = payCharge;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPayCharge() {
        return payCharge;
    }

    public void setPayCharge(String payCharge) {
        this.payCharge = payCharge;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
}
