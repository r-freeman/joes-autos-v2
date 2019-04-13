package joesautosv2;

public class Role extends Employee implements Printable{
    public Role(Integer roleId, String roleTitle) {
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
