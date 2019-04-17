package joesautosv2;

public class Admin extends Employee implements Printable {
    private Integer adminId;

    public Admin() {
    }

    public Admin(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
    }

    public Admin(String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode, double salary, Integer roleId, Integer adminId) {
        super(firstName, lastName, email, telephone, address, town, city, country, postCode, salary, roleId);
        this.adminId = adminId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public void printName() {
        System.out.println(this.getFirstName() + " " + this.getLastName());
    }

    @Override
    public void printDetails() {
        System.out.println("\n****** Employee ID : " + this.getEmployeeId() + " ******");
        System.out.println("Admin ID : " + this.getAdminId());
        System.out.println("Name : " + this.getFirstName() + " " + this.getLastName());
        System.out.println("Address : " + this.getAddress());
        System.out.println("Town : " + this.getTown());
        System.out.println("City : " + this.getCity());
        System.out.println("Post code : " + this.getPostCode());
        System.out.println("Country : " + this.getCountry());
    }

    @Override
    public void printSalesManagers() {
        // not implemented
    }

    @Override
    public void printSalesmen() {
        // not implemented
    }

    @Override
    public void printSalary() {
        System.out.println("Salary : " + this.getSalary());
    }

    @Override
    public void printQuarterlyBonus() {
        // not implemented
    }

    @Override
    public void printCommissionRate() {
        // not implemented
    }
}
