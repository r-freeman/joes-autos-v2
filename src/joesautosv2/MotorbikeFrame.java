package joesautosv2;

public class MotorbikeFrame extends Motorbike implements Printable{
    public MotorbikeFrame(Integer frameId, String frameName) {
        super(frameId, frameName);
    }

    @Override
    public void printName() {
        // not implemented
    }

    @Override
    public void printDetails() {
        System.out.println(this.getFrameId() + " : " + this.getFrameName());
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
