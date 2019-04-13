package joesautosv2;

import java.util.List;

public class SalesManager extends Employee implements Printable{
    private Integer salesManagerId;
    private double quarterlyBonus;
    private Integer regionalSalesManagerId;
    private List<Salesman> salesMen;

    public SalesManager() {
    }

    public SalesManager(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
    }

    public SalesManager(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, Integer salesManagerId, double quarterlyBonus, Integer regionalSalesManagerId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.salesManagerId = salesManagerId;
        this.quarterlyBonus = quarterlyBonus;
        this.regionalSalesManagerId = regionalSalesManagerId;
    }

    public SalesManager(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, double quarterlyBonus) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.quarterlyBonus = quarterlyBonus;
    }

    public Integer getSalesManagerId() {
        return salesManagerId;
    }

    public void setSalesManagerId(Integer salesManagerId) {
        this.salesManagerId = salesManagerId;
    }

    public double getQuarterlyBonus() {
        return quarterlyBonus;
    }

    public void setQuarterlyBonus(double quarterlyBonus) {
        this.quarterlyBonus = quarterlyBonus;
    }

    public Integer getRegionalSalesManagerId() {
        return regionalSalesManagerId;
    }

    public void setRegionalSalesManagerId(Integer regionalSalesManagerId) {
        this.regionalSalesManagerId = regionalSalesManagerId;
    }

    public List<Salesman> getSalesMen() {
        return salesMen;
    }

    public void setSalesMen(List<Salesman> salesMen) {
        this.salesMen = salesMen;
    }

    @Override
    public void printName() {
        System.out.println(this.getFirstName() + " " + this.getLastName());
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Employee ID : " + this.getEmployeeId() + " ******");
        System.out.println("Sales Manager ID : " + this.getSalesManagerId());
        System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Town : " + this.getTown());
        System.out.println("City : " + this.getCity());
        System.out.println("Post code : " + this.getPostCode());
        System.out.println("Country : " + this.getCountry());
    }

    @Override
    public void printSalary() {
        System.out.println("Salary : " + this.getSalary());
    }

    @Override
    public void printQuarterlyBonus() {

    }

    @Override
    public void printCommissionRate() {

    }
}
