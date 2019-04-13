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

public class SalesmanTableGateway {
    private static final String TABLE_NAME = "salesmen";
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
    private static final String COLUMN_COMMISSION_RATE = "commission_rate";
    private static final String COLUMN_SALES_MANAGER_ID = "sales_manager_id";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public SalesmanTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @param employeeId
     * @return
     * @throws SQLException
     */
    public Salesman getSalesmanByEmployeeId(int employeeId) throws SQLException {
        int salesmanId, roleId, salesManagerId;
        String query, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, commissionRate;

        PreparedStatement st;
        ResultSet rs;

        Salesman salesman = null;

        query = "SELECT s.id as salesman_id, s.commission_rate, s.sales_manager_id, e.first_name, " +
                "e.last_name, e.email, e.telephone, e.address, e.town, e.city, e.country, " +
                "e.post_code, e.salary, e.role_id " +
                "FROM " + TABLE_NAME + " s " +
                "INNER JOIN employees e " +
                "ON s.employee_id = e.id " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            salesmanId = rs.getInt(COLUMN_SALESMAN_ID);
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
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
            salesManagerId = rs.getInt(COLUMN_SALES_MANAGER_ID);

            salesman = new Salesman(firstName, lastName, email, telephone, address, town,
                    city, country, postCode, salary, roleId, salesmanId, commissionRate, salesManagerId);
            salesman.setEmployeeId(employeeId);
        }

        return salesman;
    }

    /**
     * @param salesman
     * @throws SQLException
     */
    public void addSalesman(Salesman salesman) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, commissionRate;

        int roleId, lastInsertedId = -1;

        firstName = salesman.getFirstName();
        lastName = salesman.getLastName();
        email = salesman.getEmail();
        telephone = salesman.getTelephone();
        address = salesman.getAddress();
        town = salesman.getTown();
        city = salesman.getCity();
        country = salesman.getCountry();
        postCode = salesman.getPostCode();
        salary = salesman.getSalary();
        roleId = salesman.getRoleId();
        commissionRate = salesman.getCommissionRate();

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

        // get employee primary key
        rs = st.getGeneratedKeys();
        if (rs.next()) {
            lastInsertedId = rs.getInt(1);
        }

        query = "INSERT INTO " + TABLE_NAME + " (employee_id, commission_rate) " +
                "VALUES (?, ?)";

        st = this.mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, lastInsertedId);
        st.setDouble(2, commissionRate);
        st.executeUpdate();

        rs = st.getGeneratedKeys();
        if (rs.next()) {
            lastInsertedId = rs.getInt(1);
        }
    }

    /**
     * @param salesman
     * @throws SQLException
     */
    public void updateSalesman(Salesman salesman) throws SQLException {
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
        st.setString(1, salesman.getFirstName());
        st.setString(2, salesman.getLastName());
        st.setString(3, salesman.getEmail());
        st.setString(4, salesman.getTelephone());
        st.setString(5, salesman.getAddress());
        st.setString(6, salesman.getTown());
        st.setString(7, salesman.getCity());
        st.setString(8, salesman.getCountry());
        st.setString(9, salesman.getPostCode());
        st.setDouble(10, salesman.getSalary());
        st.setInt(11, salesman.getEmployeeId());
        st.executeUpdate();

        // update the salesman table as well
        query = "UPDATE " + TABLE_NAME + " s " +
                "SET commission_rate=? " +
                "WHERE s.employee_id=?";

        st = this.mConnection.prepareStatement(query);
        st.setDouble(1, salesman.getCommissionRate());
        st.setInt(2, salesman.getEmployeeId());

        st.executeUpdate();
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<Salesman> getSalesmen() throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Salesman> salesmen = new ArrayList<>();
        Salesman salesman;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary, commissionRate;
        int salesmanId, salesManagerId, employeeId;

        query = "SELECT s.id as salesman_id, s.commission_rate, s.sales_manager_id, e.id as employee_id, e.* " +
                "FROM employees e " +
                "INNER JOIN " + TABLE_NAME + " s " +
                "ON s.employee_id = e.id " +
                "WHERE e.role_id=4";
        st = this.mConnection.prepareStatement(query);

        rs = st.executeQuery();

        while (rs.next()) {
            salesmanId = rs.getInt(COLUMN_SALESMAN_ID);
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
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
            salesManagerId = rs.getInt(COLUMN_SALES_MANAGER_ID);

            salesman = new Salesman(firstName, lastName, email, telephone, address,
                    town, city, country, postCode, salary, 4, salesmanId, commissionRate, salesManagerId);

            salesman.setEmployeeId(employeeId);
            salesmen.add(salesman);
        }

        return salesmen;
    }

    public void deleteSalesman(Salesman salesman) throws SQLException {
        String query;
        PreparedStatement st;

        // delete from salesman table
        query = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE salesmen.employee_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesman.getEmployeeId());

        st.executeUpdate();

        // delete from employees table
        query = "DELETE FROM employees " +
                "WHERE employees.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesman.getEmployeeId());

        st.executeUpdate();

        // set any sales associated with this salesman to null
        query = "UPDATE sales " +
                "SET sales.salesman_id=null " +
                "WHERE sales.salesman_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesman.getEmployeeId());

        st.executeUpdate();
    }
}
