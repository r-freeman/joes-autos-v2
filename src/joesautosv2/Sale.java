/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

public class Sale implements Printable{
    private int id;
    private String status;
    private double total, commission;
    private int salesmanId;
    private int motorbikeId;

    public Sale(String status, double total, double commission, int salesmanId, int motorbikeId) {
        this.status = status;
        this.total = total;
        this.commission = commission;
        this.salesmanId = salesmanId;
        this.motorbikeId = motorbikeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public int getMotorbikeId() {
        return motorbikeId;
    }

    public void setMotorbikeId(int motorbikeId) {
        this.motorbikeId = motorbikeId;
    }

    @Override
    public void printName() {
        // not implemented
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Sale ID : " + this.getId() + " ******");
        System.out.println("Status : " + this.getStatus());
        System.out.println("Total incl VAT : €" + this.getTotal());
    }

    @Override
    public void printQuarterlyBonus() {
        // not implemented
    }

    @Override
    public void printCommissionRate() {
        // not implemented
    }

    @Override
    public void printSalary() {
        // not implemented
    }

    @Override
    public void printSalesManagers() {
        // not implemented
    }

    @Override
    public void printSalesmen() {
        // not implemented
    }
}
