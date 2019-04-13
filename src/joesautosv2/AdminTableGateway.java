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

public class AdminTableGateway {
    private static final String TABLE_NAME = "admin";
    private static final String COLUMN_ADMIN_ID = "admin_id";
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
    public AdminTableGateway(Connection connection) {
        mConnection = connection;
    }

    public Admin getAdminByEmployeeId(int employeeId) throws SQLException {
        int adminId, roleId;
        String query, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;
        PreparedStatement st;
        ResultSet rs;

        Admin admin = null;

        query = "SELECT a.id as admin_id, e.first_name, e.last_name, " +
                "e.email, e.telephone, e.address, e.town, e.city, e.country, e.post_code, e.salary, e.role_id " +
                "FROM " + TABLE_NAME + " a " +
                "INNER JOIN employees e " +
                "ON a.employee_id = e.id " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            adminId = rs.getInt(COLUMN_ADMIN_ID);
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

            admin = new Admin(firstName, lastName, email, telephone, address, town,
                    city, country, postCode, salary, roleId, adminId);
            admin.setEmployeeId(employeeId);
        }

        return admin;
    }

    public void addAdmin(Admin admin) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;

        int roleId, lastInsertedId = -1;

        firstName = admin.getFirstName();
        lastName = admin.getLastName();
        email = admin.getEmail();
        telephone = admin.getTelephone();
        address = admin.getAddress();
        town = admin.getTown();
        city = admin.getCity();
        country = admin.getCountry();
        postCode = admin.getPostCode();
        salary = admin.getSalary();
        roleId = admin.getRoleId();

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
            // we need the employee.id primary key from above query
            lastInsertedId = rs.getInt(1);
        }

        query = "INSERT INTO " + TABLE_NAME + " (employee_id) " +
                "VALUES (?)";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, lastInsertedId);
        st.executeUpdate();
    }

    /**
     *
     * @param admin
     * @throws SQLException
     */
    public void updateAdmin(Admin admin) throws SQLException {
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
        st.setString(1, admin.getFirstName());
        st.setString(2, admin.getLastName());
        st.setString(3, admin.getEmail());
        st.setString(4, admin.getTelephone());
        st.setString(5, admin.getAddress());
        st.setString(6, admin.getTown());
        st.setString(7, admin.getCity());
        st.setString(8, admin.getCountry());
        st.setString(9, admin.getPostCode());
        st.setDouble(10, admin.getSalary());
        st.setInt(11, admin.getEmployeeId());
        st.executeUpdate();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Admin> getAdmins () throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Admin> admins = new ArrayList<Admin>();
        Admin admin;

        String firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        double salary;
        int adminId, employeeId;

        query = "SELECT a.id as admin_id, e.id as employee_id, e.* " +
                "FROM employees e " +
                "INNER JOIN " + TABLE_NAME + " a " +
                "ON a.employee_id = e.id " +
                "WHERE e.role_id=1";
        st = this.mConnection.prepareStatement(query);

        rs = st.executeQuery();

        while (rs.next()) {
            adminId = rs.getInt(COLUMN_ADMIN_ID);
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

            admin = new Admin(firstName, lastName, email, telephone, address,
                    town, city, country, postCode, salary, 1, adminId);

            admin.setEmployeeId(employeeId);
            admins.add(admin);
        }

        return admins;
    }

    /**
     * @param admin
     * @throws SQLException
     */
    public void deleteAdmin (Admin admin) throws SQLException {
        String query;
        PreparedStatement st;

        query = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE admin.employee_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, admin.getEmployeeId());

        st.executeUpdate();

        query = "DELETE FROM employees " +
                "WHERE employees.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, admin.getEmployeeId());

        st.executeUpdate();
    }
}
