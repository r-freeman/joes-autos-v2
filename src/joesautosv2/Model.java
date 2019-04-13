/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import javax.swing.plaf.synth.Region;
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

    private Role role;
    private List<Role> roles;
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

    private Model() {
        try {
            Connection conn = DBConnection.getInstance();

            this.employeeTableGateway = new EmployeeTableGateway(conn);
            this.employeeRoleTableGateway = new EmployeeRoleTableGateway(conn);
            this.adminTableGateway = new AdminTableGateway(conn);
            this.regionalSalesManagerTableGateway = new RegionalSalesManagerTableGateway(conn);
            this.salesManagerTableGateway = new SalesManagerTableGateway(conn);
            this.salesmanTableGateway = new SalesmanTableGateway(conn);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Create an instance of role class for given employee id
     *
     * @param employeeId
     * @return role
     */
    public Role getEmployeeRole(int employeeId) {
        try {
            this.role = employeeRoleTableGateway.getEmployeeRole(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.role;
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
     * Return a list of employee roles
     *
     * @return roles
     */
    public List<Role> getEmployeeRoles() {
        try {
            this.roles = employeeRoleTableGateway.getEmployeeRoles();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.roles;
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
    public Role getRoleById(int roleId) {
        try {
            this.role = this.employeeRoleTableGateway.getRoleById(roleId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.role;
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
    public List<Salesman> getSalesmen() {
        try {
            this.salesmen = salesmanTableGateway.getSalesmen();
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
}
