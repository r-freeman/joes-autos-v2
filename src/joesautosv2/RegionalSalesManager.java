package joesautosv2;

import java.util.ArrayList;
import java.util.List;

public class RegionalSalesManager extends Employee implements Printable{
    private Integer regionalSalesManagerId;
    private List<SalesManager> salesManagers = new ArrayList<>();

    public RegionalSalesManager() {
    }

    public RegionalSalesManager(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
    }

    public RegionalSalesManager(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, Integer regionalSalesManagerId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.regionalSalesManagerId = regionalSalesManagerId;
    }

    public Integer getRegionalSalesManagerId() {
        return regionalSalesManagerId;
    }

    public void setRegionalSalesManagerId(Integer regionalSalesManagerId) {
        this.regionalSalesManagerId = regionalSalesManagerId;
    }

    public List<SalesManager> getSalesManagers() {
        return salesManagers;
    }

    public void setSalesManagers(List<SalesManager> salesManagers) {
        this.salesManagers = salesManagers;
    }

    @Override
    public void printName() {
        System.out.println(this.getFirstName() + " " + this.getLastName());
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Employee ID : " + this.getEmployeeId() + " ******");
        System.out.println("Regional Sales Manager ID : " + this.getRegionalSalesManagerId());
        System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Town : " + this.getTown());
        System.out.println("City : " + this.getCity());
        System.out.println("Post code : " + this.getPostCode());
        System.out.println("Country : " + this.getCountry());
    }

    @Override
    public void printSalary() {

    }

    @Override
    public void printQuarterlyBonus() {

    }

    @Override
    public void printCommissionRate() {

    }
}
