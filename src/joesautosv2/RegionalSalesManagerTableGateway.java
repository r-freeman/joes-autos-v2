/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import com.mysql.jdbc.Statement;

import javax.swing.plaf.synth.Region;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegionalSalesManagerTableGateway {
    private static final String TABLE_NAME = "regional_sales_managers";
    private static final String COLUMN_REGIONAL_SALES_MANAGER_ID = "regional_sales_manager_id";
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_TOWN = "town";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_POST_CODE = "post_code";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_ROLE_ID = "role_id";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public RegionalSalesManagerTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @param employeeId
     * @return
     * @throws SQLException
     */
    public RegionalSalesManager getRegionalSalesManagerByEmployeeId(int employeeId) throws SQLException {
        int regionalSalesManagerId, roleId;
        String query, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;
        PreparedStatement st;
        ResultSet rs;

        RegionalSalesManager regionalSalesManager = null;

        query = "SELECT rsm.id as regional_sales_manager_id, e.first_name, e.last_name, " +
                "e.email, e.telephone, e.address, e.town, e.city, e.country, e.post_code, e.salary, e.role_id " +
                "FROM " + TABLE_NAME + " rsm " +
                "INNER JOIN employees e " +
                "ON rsm.employee_id = e.id " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            regionalSalesManagerId = rs.getInt(COLUMN_REGIONAL_SALES_MANAGER_ID);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            email = rs.getString(COLUMN_EMAIL);
            telephone = rs.getString(COLUMN_TELEPHONE);
            address = rs.getString(COLUMN_ADDRESS);
            town = rs.getString(COLUMN_TOWN);
            city = rs.getString(COLUMN_CITY);
            country = rs.getString(COLUMN_COUNTRY);
            postCode = rs.getString(COLUMN_POST_CODE);
            salary = rs.getDouble(COLUMN_SALARY);
            roleId = rs.getInt(COLUMN_ROLE_ID);


            regionalSalesManager = new RegionalSalesManager(firstName, lastName, email,
                    telephone, address, town, city, country, postCode, salary, roleId, regionalSalesManagerId);
            regionalSalesManager.setEmployeeId(employeeId);
        }

        return regionalSalesManager;
    }

    /**
     * @param regionalSalesManager
     * @throws SQLException
     */
    public void addRegionalSalesManager(RegionalSalesManager regionalSalesManager) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;

        int roleId, lastInsertedId = -1;

        firstName = regionalSalesManager.getFirstName();
        lastName = regionalSalesManager.getLastName();
        email = regionalSalesManager.getEmail();
        telephone = regionalSalesManager.getTelephone();
        address = regionalSalesManager.getAddress();
        town = regionalSalesManager.getTown();
        city = regionalSalesManager.getCity();
        country = regionalSalesManager.getCountry();
        postCode = regionalSalesManager.getPostCode();
        salary = regionalSalesManager.getSalary();
        roleId = regionalSalesManager.getRoleId();

        query = "INSERT INTO employees (first_name, last_name, email, " +
                "telephone, address, town, city, country, post_code, salary, role_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        st = this.mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, firstName);
        st.setString(2, lastName);
        st.setString(3, email);
        st.setString(4, telephone);
        st.setString(5, address);
        st.setString(6, town);
        st.setString(7, city);
        st.setString(8, country);
        st.setString(9, postCode);
        st.setDouble(10, salary);
        st.setInt(11, roleId);
        st.executeUpdate();

        rs = st.getGeneratedKeys();
        if (rs.next()) {
            lastInsertedId = rs.getInt(1);
        }

        query = "INSERT INTO " + TABLE_NAME + " (employee_id) " +
                "VALUES (?)";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, lastInsertedId);
        st.executeUpdate();
    }

    /**
     * @param regionalSalesManager
     * @throws SQLException
     */
    public void updateRegionalSalesManager(RegionalSalesManager regionalSalesManager) throws SQLException {
        String query;
        PreparedStatement st;

        query = "UPDATE employees e " +
                "SET first_name=?, " +
                "last_name=?, " +
                "email=?, " +
                "telephone=?, " +
                "address=?, " +
                "town=?, " +
                "city=?, " +
                "country=?, " +
                "post_code=?, " +
                "salary=?" +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setString(1, regionalSalesManager.getFirstName());
        st.setString(2, regionalSalesManager.getLastName());
        st.setString(3, regionalSalesManager.getEmail());
        st.setString(4, regionalSalesManager.getTelephone());
        st.setString(5, regionalSalesManager.getAddress());
        st.setString(6, regionalSalesManager.getTown());
        st.setString(7, regionalSalesManager.getCity());
        st.setString(8, regionalSalesManager.getCountry());
        st.setString(9, regionalSalesManager.getPostCode());
        st.setDouble(10, regionalSalesManager.getSalary());
        st.setInt(11, regionalSalesManager.getEmployeeId());
        st.executeUpdate();
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<RegionalSalesManager> getRegionalSalesManagers () throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<RegionalSalesManager> regionalSalesManagers = new ArrayList<>();
        RegionalSalesManager regionalSalesManager;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;
        int regionalSalesManagerId, employeeId;

        query = "SELECT rsm.id as regional_sales_manager_id, e.id as employee_id, e.* " +
                "FROM employees e " +
                "INNER JOIN " + TABLE_NAME + " rsm " +
                "ON rsm.employee_id = e.id " +
                "WHERE e.role_id=2";
        st = this.mConnection.prepareStatement(query);

        rs = st.executeQuery();

        while (rs.next()) {
            regionalSalesManagerId = rs.getInt(COLUMN_REGIONAL_SALES_MANAGER_ID);
            employeeId = rs.getInt(COLUMN_EMPLOYEE_ID);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            email = rs.getString(COLUMN_EMAIL);
            telephone = rs.getString(COLUMN_TELEPHONE);
            address = rs.getString(COLUMN_ADDRESS);
            town = rs.getString(COLUMN_TOWN);
            city = rs.getString(COLUMN_CITY);
            country = rs.getString(COLUMN_COUNTRY);
            postCode = rs.getString(COLUMN_POST_CODE);
            salary = rs.getDouble(COLUMN_SALARY);

            regionalSalesManager = new RegionalSalesManager(firstName, lastName, email, telephone, address,
                    town, city, country, postCode, salary, 2, regionalSalesManagerId);

            regionalSalesManager.setEmployeeId(employeeId);
            regionalSalesManagers.add(regionalSalesManager);
        }

        return regionalSalesManagers;
    }

    public void deleteRegionalSalesManager (RegionalSalesManager regionalSalesManager) throws SQLException {
        String query;
        PreparedStatement st;

        // delete from regional sales manager table
        query = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE regional_sales_managers.employee_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, regionalSalesManager.getEmployeeId());

        st.executeUpdate();

        // delete from employees table
        query = "DELETE FROM employees " +
                "WHERE employees.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, regionalSalesManager.getEmployeeId());

        st.executeUpdate();

        // remove any relationships to sales managers
        query = "UPDATE sales_managers " +
                "SET regional_sales_manager_id=null " +
                "WHERE regional_sales_manager_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, regionalSalesManager.getEmployeeId());

        st.executeUpdate();
    }
}
