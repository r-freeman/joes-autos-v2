/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRoleTableGateway {
    private static final String TABLE_NAME = "employees";
    private static final String COLUMN_ROLE_ID = "role_id";
    private static final String COLUMN_ROLE_TITLE = "role_title";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public EmployeeRoleTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * Returns an instance of the EmployeeRole class for a given employee id
     *
     * @param employeeId
     * @return
     * @throws SQLException
     */
    public EmployeeRole getEmployeeRole(int employeeId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        EmployeeRole employeeRole = null;
        int roleId;
        String roleTitle;

        query = "SELECT er.id as role_id, er.title as role_title " +
                "FROM " + TABLE_NAME + " e " +
                "INNER JOIN employee_roles er " +
                "ON e.role_id = er.id " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            roleId = rs.getInt(COLUMN_ROLE_ID);
            roleTitle = rs.getString(COLUMN_ROLE_TITLE);

            employeeRole = new EmployeeRole(roleId, roleTitle);
        }

        return employeeRole;
    }

    /**
     * Return a list of all employee roles
     * @return
     * @throws SQLException
     */
    public List<EmployeeRole> getEmployeeRoles() throws SQLException {
        String query, roleTitle;
        int roleId;
        Statement st;
        ResultSet rs;

        List<EmployeeRole> employeeRoles;
        EmployeeRole employeeRole;

        query = "SELECT er.id as role_id, er.title as role_title " +
                "FROM employee_roles er";
        st = this.mConnection.createStatement();

        rs = st.executeQuery(query);

        employeeRoles = new ArrayList<EmployeeRole>();

        while(rs.next()){
            roleId = rs.getInt(COLUMN_ROLE_ID);
            roleTitle = rs.getString(COLUMN_ROLE_TITLE);
            employeeRole = new EmployeeRole(roleId, roleTitle);
            employeeRoles.add(employeeRole);
        }

        return employeeRoles;
    }

    public EmployeeRole getEmployeeRoleById(int roleId) throws SQLException {
        String query, roleTitle;

        PreparedStatement st;
        ResultSet rs;

        EmployeeRole employeeRole = null;

        query = "SELECT er.title as role_title " +
                "FROM employee_roles er " +
                "WHERE er.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, roleId);
        rs = st.executeQuery();

        while(rs.next()){
            roleTitle = rs.getString(COLUMN_ROLE_TITLE);
            employeeRole = new EmployeeRole(roleId, roleTitle);
        }

        return employeeRole;
    }
}
