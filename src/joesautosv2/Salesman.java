package joesautosv2;

public class Salesman extends Employee implements Printable {
    private Integer salesmanId;
    private double commissionRate;
    private Integer salesManagerId;

    public Salesman() {
    }

    public Salesman(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, Integer salesmanId, double commissionRate, Integer salesManagerId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.salesmanId = salesmanId;
        this.commissionRate = commissionRate;
        this.salesManagerId = salesManagerId;
    }

    public Salesman(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, double commissionRate) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.commissionRate = commissionRate;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public Integer getSalesManagerId() {
        return salesManagerId;
    }

    public void setSalesManagerId(Integer salesManagerId) {
        this.salesManagerId = salesManagerId;
    }

    @Override
    public void printName() {
        System.out.println(this.getFirstName() + " " + this.getLastName());
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Employee ID : " + this.getEmployeeId() + " ******");
        System.out.println("Salesman ID : " + this.getSalesmanId());
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
