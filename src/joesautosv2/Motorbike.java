/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

public class Motorbike implements Printable {
    private Integer motorbikeId;
    private String make;
    private String model;
    private String engine;
    private String colour;
    private Integer year;
    private double price;
    private Integer stock;
    private Integer frameId;
    private String frameName;

    public Motorbike() {
    }

    public Motorbike(String make, String model, String engine, String colour, Integer year, double price, Integer stock, Integer frameId) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.colour = colour;
        this.year = year;
        this.price = price;
        this.stock = stock;
        this.frameId = frameId;
    }

    public Motorbike(String make, String model, String engine, String colour, Integer year, double price, Integer stock, Integer frameId, String frameName) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.colour = colour;
        this.year = year;
        this.price = price;
        this.stock = stock;
        this.frameId = frameId;
        this.frameName = frameName;
    }

    public Motorbike(Integer frameId, String frameName) {
        this.frameId = frameId;
        this.frameName = frameName;
    }

    public Integer getMotorbikeId() {
        return motorbikeId;
    }

    public void setMotorbikeId(Integer motorbikeId) {
        this.motorbikeId = motorbikeId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public String getFrameName() {
        return frameName;
    }

    public void setFrameName(String frameName) {
        this.frameName = frameName;
    }

    @Override
    public void printName() {
        System.out.println(this.getMake() + " " + this.getModel());
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Motorbike ID : " + this.getMotorbikeId() + " ******");
        System.out.println("Make : " + this.getMake());
        System.out.println("Model : " + this.getModel());
        System.out.println("Frame : " + this.getFrameName());
        System.out.println("Engine : " + this.getEngine());
        System.out.println("Colour : " + this.getColour());
        System.out.println("Year : " + this.getYear());
        System.out.println("Price : " + this.getPrice());
        System.out.println("Stock : " + this.getStock());
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
