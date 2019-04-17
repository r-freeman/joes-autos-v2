package joesautosv2;

public class EmployeeRole extends Employee implements Printable{
    public EmployeeRole(Integer roleId, String roleTitle) {
        super(roleId, roleTitle);
    }

    @Override
    public void printName() {
        // not implemented
    }

    @Override
    public void printDetails() {
        System.out.println(this.getRoleId() + " : " + this.getRoleTitle());
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
        // not implemented
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
