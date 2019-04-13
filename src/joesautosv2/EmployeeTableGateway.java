/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTableGateway {
    private static final String TABLE_NAME = "employees";
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_ROW_COUNT = "row_count";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public EmployeeTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @param employeeId
     * @return
     * @throws SQLException
     */
    public int validateEmployeeId(int employeeId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        int rowCount = 0;

        query = "SELECT COUNT(*) as row_count " +
                "FROM " + TABLE_NAME + " e " +
                "WHERE e.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, employeeId);
        rs = st.executeQuery();

        while (rs.next()) {
            rowCount = rs.getInt(COLUMN_ROW_COUNT);
        }

        return rowCount;
    }
}
