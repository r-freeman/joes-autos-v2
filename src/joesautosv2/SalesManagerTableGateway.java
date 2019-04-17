/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesManagerTableGateway {
    private static final String TABLE_NAME = "sales_managers";
    private static final String COLUMN_SALES_MANAGER_ID = "sales_manager_id";
    private static final String COLUMN_SALESMAN_ID = "salesman_id";
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
    private static final String COLUMN_QUARTERLY_BONUS = "quarterly_bonus";
    private static final String COLUMN_COMMISSION_RATE = "commission_rate";
    private static final String COLUMN_REGIONAL_SALES_MANAGER_ID = "regional_sales_manager_id";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public SalesManagerTableGateway(Connection connection) {
        mConnection = connection;
    }

    public SalesManager getSalesManagerByEmployeeId(int employeeId) throws SQLException {
        int salesManagerId, roleId, regionalSalesManagerId, salesmanId;
        String query, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, quarterlyBonus, commissionRate;
        List<Salesman> salesmen = new ArrayList<>();

        PreparedStatement st;
        ResultSet rs;

        SalesManager salesManager = null;

        query = "SELECT sm.id as sales_manager_id, sm.quarterly_bonus, sm.regional_sales_manager_id, e.first_name, " +
                "e.last_name, e.email, e.telephone, e.address, e.town, e.city, e.country, " +
                "e.post_code, e.salary, e.role_id " +
                "FROM " + TABLE_NAME + " sm " +
                "INNER JOIN employees e " +
                "ON sm.employee_id = e.id " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            salesManagerId = rs.getInt(COLUMN_SALES_MANAGER_ID);
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
            quarterlyBonus = rs.getDouble(COLUMN_QUARTERLY_BONUS);
            regionalSalesManagerId = rs.getInt(COLUMN_REGIONAL_SALES_MANAGER_ID);

            salesManager = new SalesManager(firstName, lastName, email, telephone, address, town,
                    city, country, postCode, salary, roleId, salesManagerId, quarterlyBonus, regionalSalesManagerId);
            salesManager.setEmployeeId(employeeId);
        }

        assert salesManager != null;

        // retrieve the salesmen if any
        query = "SELECT s.id as salesman_id, s.commission_rate, e.id as employee_id, e.first_name, e.last_name, " +
                "e.email, e.telephone, e.address, e.town, e.city, e.country, e.post_code, e.salary, e.role_id " +
                "FROM salesmen s " +
                "INNER JOIN employees e " +
                "ON e.id = s.employee_id " +
                "WHERE s.sales_manager_id=? ";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getSalesManagerId());
        rs = st.executeQuery();

        while (rs.next()) {
            salesmanId = rs.getInt(COLUMN_SALESMAN_ID);
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
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
            roleId = rs.getInt(COLUMN_ROLE_ID);

            Salesman salesman = new Salesman(firstName, lastName, email, telephone, address, town,
                    city, country, postCode, salary, roleId, salesmanId,
                    commissionRate, salesManager.getSalesManagerId());

            salesman.setEmployeeId(employeeId);
            salesmen.add(salesman);
        }

        salesManager.setSalesmen(salesmen);
        return salesManager;
    }

    /**
     *
     * @param salesManager
     * @throws SQLException
     */
    public void addSalesManager(SalesManager salesManager) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, quarterlyBonus;

        int roleId, lastInsertedId = -1;

        firstName = salesManager.getFirstName();
        lastName = salesManager.getLastName();
        email = salesManager.getEmail();
        telephone = salesManager.getTelephone();
        address = salesManager.getAddress();
        town = salesManager.getTown();
        city = salesManager.getCity();
        country = salesManager.getCountry();
        postCode = salesManager.getPostCode();
        salary = salesManager.getSalary();
        roleId = salesManager.getRoleId();
        quarterlyBonus = salesManager.getQuarterlyBonus();

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

        query = "INSERT INTO " + TABLE_NAME + " (employee_id, quarterly_bonus) " +
                "VALUES (?, ?)";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, lastInsertedId);
        st.setDouble(2, quarterlyBonus);
        st.executeUpdate();
    }

    /**
     * @param salesManager
     * @throws SQLException
     */
    public void updateSalesManager(SalesManager salesManager) throws SQLException {
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
                "salary=? " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setString(1, salesManager.getFirstName());
        st.setString(2, salesManager.getLastName());
        st.setString(3, salesManager.getEmail());
        st.setString(4, salesManager.getTelephone());
        st.setString(5, salesManager.getAddress());
        st.setString(6, salesManager.getTown());
        st.setString(7, salesManager.getCity());
        st.setString(8, salesManager.getCountry());
        st.setString(9, salesManager.getPostCode());
        st.setDouble(10, salesManager.getSalary());
        st.setInt(11, salesManager.getEmployeeId());
        st.executeUpdate();

        // update the sales manager table as well
        query = "UPDATE " + TABLE_NAME + " sm " +
                "SET quarterly_bonus=? " +
                "WHERE sm.employee_id=?";

        st = this.mConnection.prepareStatement(query);
        st.setDouble(1, salesManager.getQuarterlyBonus());
        st.setInt(2, salesManager.getEmployeeId());

        st.executeUpdate();
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<SalesManager> getSalesManagers (boolean listUnsupervised) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<SalesManager> salesManagers = new ArrayList<>();
        SalesManager salesManager;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, quarterlyBonus;
        int salesManagerId, employeeId, regionalSalesManagerId;

        query = "SELECT sm.id as sales_manager_id, sm.quarterly_bonus, sm.regional_sales_manager_id, e.id as employee_id, e.* " +
                "FROM employees e " +
                "INNER JOIN " + TABLE_NAME + " sm " +
                "ON sm.employee_id = e.id " +
                "WHERE e.role_id=3 ";

        if(listUnsupervised) {
            query += "AND sm.regional_sales_manager_id IS NULL";
        }

        st = this.mConnection.prepareStatement(query);

        rs = st.executeQuery();

        while (rs.next()) {
            salesManagerId = rs.getInt(COLUMN_SALES_MANAGER_ID);
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
            quarterlyBonus = rs.getDouble(COLUMN_QUARTERLY_BONUS);
            regionalSalesManagerId = rs.getInt(COLUMN_REGIONAL_SALES_MANAGER_ID);

            salesManager = new SalesManager(firstName, lastName, email, telephone, address,
                    town, city, country, postCode, salary, 3, salesManagerId, quarterlyBonus, regionalSalesManagerId);

            salesManager.setEmployeeId(employeeId);
            salesManagers.add(salesManager);
        }

        return salesManagers;
    }

    /**
     * @param salesManager
     * @throws SQLException
     */
    public void saveSalesmen(SalesManager salesManager) throws SQLException {
        List<Salesman> salesmen;
        String query;
        PreparedStatement st;

        salesmen = salesManager.getSalesmen();
        query = "UPDATE salesmen s " +
                "SET sales_manager_id=? " +
                "WHERE s.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getSalesManagerId());

        // loop through each salesman
        for(Salesman s : salesmen) {
            st.setInt(2, s.getSalesmanId());
            st.executeUpdate();
        }
    }

    /**
     * @param salesManager
     * @throws SQLException
     */
    public void deleteSalesManager(SalesManager salesManager) throws SQLException {
        String query;
        PreparedStatement st;

        // remove any relationships to salesmen
        query = "UPDATE salesmen s " +
                "SET sales_manager_id=null " +
                "WHERE s.sales_manager_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getSalesManagerId());

        st.executeUpdate();

        // remove any relationship to employees table
        query = "UPDATE " + TABLE_NAME + " sm " +
                "SET sm.employee_id=null " +
                "WHERE sm.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getSalesManagerId());

        st.executeUpdate();

        // delete from sales manager table
        query = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE sales_managers.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getSalesManagerId());

        st.executeUpdate();

        // delete from employees table
        query = "DELETE FROM employees " +
                "WHERE employees.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesManager.getEmployeeId());

        st.executeUpdate();
    }
}
