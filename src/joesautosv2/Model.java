/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The model class interfaces with our database
 *
 * @author ryan
 */
public class Model {
    private static Model instance = null;

    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    private EmployeeTableGateway employeeTableGateway;

    private EmployeeRole employeeRole;
    private List<EmployeeRole> employeeRoles;
    private EmployeeRoleTableGateway employeeRoleTableGateway;

    private Admin admin;
    private List<Admin> admins;
    private AdminTableGateway adminTableGateway;

    private RegionalSalesManager regionalSalesManager;
    private List<RegionalSalesManager> regionalSalesManagers;
    private RegionalSalesManagerTableGateway regionalSalesManagerTableGateway;

    private SalesManager salesManager;
    private List<SalesManager> salesManagers;
    private SalesManagerTableGateway salesManagerTableGateway;

    private Salesman salesman;
    private List<Salesman> salesmen;
    private SalesmanTableGateway salesmanTableGateway;

    private Sale sale;
    private List<Sale> sales;
    private SaleTableGateway saleTableGateway;

    private double totalSumSales;
    private int totalNumMotorbikesSold;

    private Motorbike motorbike;
    private List<Motorbike> motorbikes;
    private MotorbikeTableGateway motorbikeTableGateway;

    private MotorbikeFrame motorbikeFrame;
    private List<MotorbikeFrame> motorbikeFrames;
    private MotorbikeFrameTableGateway motorbikeFrameTableGateway;

    private Model() {
        try {
            Connection conn = DBConnection.getInstance();

            this.employeeTableGateway = new EmployeeTableGateway(conn);
            this.employeeRoleTableGateway = new EmployeeRoleTableGateway(conn);
            this.adminTableGateway = new AdminTableGateway(conn);
            this.regionalSalesManagerTableGateway = new RegionalSalesManagerTableGateway(conn);
            this.salesManagerTableGateway = new SalesManagerTableGateway(conn);
            this.saleTableGateway = new SaleTableGateway(conn);
            this.salesmanTableGateway = new SalesmanTableGateway(conn);
            this.motorbikeTableGateway = new MotorbikeTableGateway(conn);
            this.motorbikeFrameTableGateway = new MotorbikeFrameTableGateway(conn);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create an instance of employeeRole class for given employee id
     *
     * @param employeeId
     * @return employeeRole
     */
    public EmployeeRole getEmployeeRole(int employeeId) {
        try {
            this.employeeRole = employeeRoleTableGateway.getEmployeeRole(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.employeeRole;
    }

    /**
     * Create an instance of an Admin sub class for given employee id
     *
     * @param employeeId
     * @return admin
     */
    public Admin getAdminByEmployeeId(int employeeId) {
        try {
            this.admin = adminTableGateway.getAdminByEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.admin;
    }

    /**
     * @param motorbikeId
     * @return
     */
    public Motorbike getMotorbikeById(int motorbikeId) {
        try {
            this.motorbike = motorbikeTableGateway.getMotorbikeById(motorbikeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.motorbike;
    }

    /**
     * @param sale
     */
    public void addSale(Sale sale) {
        try {
            saleTableGateway.addSale(sale);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param employeeId
     * @return
     */
    public RegionalSalesManager getRegionalSalesManagerByEmployeeId(int employeeId) {
        try {
            this.regionalSalesManager = regionalSalesManagerTableGateway.getRegionalSalesManagerByEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.regionalSalesManager;
    }

    /**
     * @param employeeId
     * @return
     */
    public SalesManager getSalesManagerByEmployeeId(int employeeId) {
        try {
            this.salesManager = salesManagerTableGateway.getSalesManagerByEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.salesManager;
    }

    /**
     * @param employeeId
     * @return
     */
    public Salesman getSalesmanByEmployeeId(int employeeId) {
        try {
            this.salesman = salesmanTableGateway.getSalesmanByEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.salesman;
    }

    /**
     * Return a list of employee employeeRoles
     *
     * @return employeeRoles
     */
    public List<EmployeeRole> getEmployeeRoles() {
        try {
            this.employeeRoles = employeeRoleTableGateway.getEmployeeRoles();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.employeeRoles;
    }

    /**
     * @return
     */
    public List<MotorbikeFrame> getMotorbikeFrames() {
        try {
            this.motorbikeFrames = motorbikeFrameTableGateway.getMotorbikeFrames();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.motorbikeFrames;
    }

    /**
     * @param admin
     */
    public void addAdmin(Admin admin) {
        try {
            this.adminTableGateway.addAdmin(admin);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param motorbike
     */
    public void storeMotorbike(Motorbike motorbike) {
        try {
            this.motorbikeTableGateway.storeMotorbike(motorbike);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param regionalSalesManager
     */
    public void addRegionalSalesManager(RegionalSalesManager regionalSalesManager) {
        try {
            this.regionalSalesManagerTableGateway.addRegionalSalesManager(regionalSalesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesManager
     */
    public void addSalesManager(SalesManager salesManager) {
        try {
            this.salesManagerTableGateway.addSalesManager(salesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesman
     */
    public void addSalesman(Salesman salesman) {
        try {
            this.salesmanTableGateway.addSalesman(salesman);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param roleId
     * @return
     */
    public EmployeeRole getEmployeeRoleById(int roleId) {
        try {
            this.employeeRole = this.employeeRoleTableGateway.getEmployeeRoleById(roleId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.employeeRole;
    }

    /**
     * @param frameId
     * @return
     */
    public MotorbikeFrame getMotorbikeFrameById(int frameId) {
        try {
            this.motorbikeFrame = this.motorbikeFrameTableGateway.getMotorbikeFrameById(frameId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.motorbikeFrame;
    }

    /**
     * @return
     */
    public List<Admin> getAdmins() {
        try {
            this.admins = adminTableGateway.getAdmins();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.admins;
    }

    /**
     * @return
     */
    public List<Motorbike> getMotorbikes() {
        try {
            this.motorbikes = motorbikeTableGateway.getMotorbikes();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.motorbikes;
    }

    /**
     * @return
     */
    public List<Sale> getSales() {
        try {
            this.sales = saleTableGateway.getSales();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.sales;
    }

    /**
     * @return
     */
    public double getTotalSumSales() {
        try {
            this.totalSumSales = saleTableGateway.getTotalSumSales();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.totalSumSales;
    }

    /**
     * @return
     */
    public int getTotalNumMotorbikesSold() {
        try {
            this.totalNumMotorbikesSold = saleTableGateway.getNumMotorbikesSold();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.totalNumMotorbikesSold;
    }

    /**
     * @param status
     * @return
     */
    public List<Sale> getSalesByStatus(String status) {
        try {
            this.sales = saleTableGateway.getSalesByStatus(status);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.sales;
    }

    /**
     * @param salesmanId
     * @return
     */
    public List<Sale> getSalesBySalesmanId(int salesmanId) {
        try {
            this.sales = saleTableGateway.getSalesBySalesmanId(salesmanId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.sales;
    }

    /**
     * @param frameId
     * @return
     */
    public List<Motorbike> getMotorbikesByFrame(int frameId) {
        try {
            this.motorbikes = motorbikeTableGateway.getMotorbikesByFrame(frameId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.motorbikes;
    }

    /**
     * @return
     */
    public List<RegionalSalesManager> getRegionalSalesManagers() {
        try {
            this.regionalSalesManagers = regionalSalesManagerTableGateway.getRegionalSalesManagers();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.regionalSalesManagers;
    }

    /**
     * @return
     */
    public List<SalesManager> getSalesManagers(boolean listUnsupervised) {
        try {
            this.salesManagers = salesManagerTableGateway.getSalesManagers(listUnsupervised);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.salesManagers;
    }

    /**
     * @return
     */
    public List<Salesman> getSalesmen(boolean listUnsupervised) {
        try {
            this.salesmen = salesmanTableGateway.getSalesmen(listUnsupervised);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.salesmen;
    }

    /**
     * @param admin
     */
    public void updateAdmin(Admin admin) {
        try {
            this.adminTableGateway.updateAdmin(admin);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param motorbike
     */
    public void updateMotorbike(Motorbike motorbike) {
        try {
            this.motorbikeTableGateway.updateMotorbike(motorbike);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param regionalSalesManager
     */
    public void updateRegionalSalesManager(RegionalSalesManager regionalSalesManager) {
        try {
            this.regionalSalesManagerTableGateway.updateRegionalSalesManager(regionalSalesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesManager
     */
    public void updateSalesManager(SalesManager salesManager) {
        try {
            this.salesManagerTableGateway.updateSalesManager(salesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesman
     */
    public void updateSalesman(Salesman salesman) {
        try {
            this.salesmanTableGateway.updateSalesman(salesman);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param employeeId
     * @return
     */
    public int validateEmployeeId(int employeeId) {
        int rowCount = 0;
        try {
            rowCount = employeeTableGateway.validateEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    /**
     * @param motorbikeId
     * @return
     */
    public int validateMotorbikeId(int motorbikeId) {
        int rowCount = 0;
        try {
            rowCount = motorbikeTableGateway.validateMotorbikeId(motorbikeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }

    /**
     * @param admin
     */
    public void deleteAdmin(Admin admin) {
        try {
            adminTableGateway.deleteAdmin(admin);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param motorbike
     */
    public void _deleteMotorbike(Motorbike motorbike) {
        try {
            motorbikeTableGateway.deleteMotorbike(motorbike);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param regionalSalesManager
     */
    public void deleteRegionalSalesManager(RegionalSalesManager regionalSalesManager) {
        try {
            regionalSalesManagerTableGateway.deleteRegionalSalesManager(regionalSalesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesManager
     */
    public void deleteSalesManager(SalesManager salesManager) {
        try {
            salesManagerTableGateway.deleteSalesManager(salesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesman
     */
    public void deleteSalesman(Salesman salesman) {
        try {
            salesmanTableGateway.deleteSalesman(salesman);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param regionalSalesManager
     */
    public void saveSalesManagers(RegionalSalesManager regionalSalesManager) {
        try {
            regionalSalesManagerTableGateway.saveSalesManagers(regionalSalesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param salesManager
     */
    public void saveSalesmen(SalesManager salesManager) {
        try {
            salesManagerTableGateway.saveSalesmen(salesManager);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
