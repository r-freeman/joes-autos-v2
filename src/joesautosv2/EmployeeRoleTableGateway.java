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
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Returns an instance of the Role class for a given employee id
     *
     * @param employeeId
     * @return
     * @throws SQLException
     */
    public Role getEmployeeRole(int employeeId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        Role role = null;
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

            role = new Role(roleId, roleTitle);
        }

        return role;
    }

    /**
     * Return a list of all employee roles
     * @return
     * @throws SQLException
     */
    public List<Role> getEmployeeRoles() throws SQLException {
        String query, roleTitle;
        int roleId;
        Statement st;
        ResultSet rs;

        List<Role> roles;
        Role role;

        query = "SELECT er.id as role_id, er.title as role_title " +
                "FROM employee_roles er";
        st = this.mConnection.createStatement();

        rs = st.executeQuery(query);

        roles = new ArrayList<Role>();

        while(rs.next()){
            roleId = rs.getInt(COLUMN_ROLE_ID);
            roleTitle = rs.getString(COLUMN_ROLE_TITLE);
            role = new Role(roleId, roleTitle);
            roles.add(role);
        }

        return roles;
    }

    public Role getRoleById(int roleId) throws SQLException {
        String query, roleTitle;

        PreparedStatement st;
        ResultSet rs;

        Role role = null;

        query = "SELECT er.title as role_title " +
                "FROM employee_roles er " +
                "WHERE er.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, roleId);
        rs = st.executeQuery();

        while(rs.next()){
            roleTitle = rs.getString(COLUMN_ROLE_TITLE);
            role = new Role(roleId, roleTitle);
        }

        return role;
    }
}
