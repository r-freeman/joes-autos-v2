/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautosv2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotorbikeFrameTableGateway {
    private static final String TABLE_NAME = "motorbikes";
    private static final String COLUMN_FRAME_ID = "frame_id";
    private static final String COLUMN_FRAME_NAME = "frame_name";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public MotorbikeFrameTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @param motorbikeId
     * @return
     * @throws SQLException
     */
    public MotorbikeFrame getMotorbikeFrame(int motorbikeId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        MotorbikeFrame motorbikeFrame = null;
        int frameId;
        String frameName;

        query = "SELECT mf.id as frame_id, mf.name as frame_name " +
                "FROM " + TABLE_NAME + " m " +
                "INNER JOIN motorbike_frames mf " +
                "ON m.frame_id = mf.id " +
                "WHERE m.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, motorbikeId);
        rs = st.executeQuery();

        while (rs.next()) {
            frameId = rs.getInt(COLUMN_FRAME_ID);
            frameName = rs.getString(COLUMN_FRAME_NAME);

            motorbikeFrame = new MotorbikeFrame(frameId, frameName);
        }

        return motorbikeFrame;
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<MotorbikeFrame> getMotorbikeFrames() throws SQLException {
        String query, frameName;
        int frameId;
        Statement st;
        ResultSet rs;

        List<MotorbikeFrame> motorbikeFrames;
        MotorbikeFrame motorbikeFrame;

        query = "SELECT mf.id as frame_id, mf.name as frame_name " +
                "FROM motorbike_frames mf";
        st = this.mConnection.createStatement();

        rs = st.executeQuery(query);

        motorbikeFrames = new ArrayList<>();

        while(rs.next()){
            frameId = rs.getInt(COLUMN_FRAME_ID);
            frameName = rs.getString(COLUMN_FRAME_NAME);
            motorbikeFrame = new MotorbikeFrame(frameId, frameName);
            motorbikeFrames.add(motorbikeFrame);
        }

        return motorbikeFrames;
    }

    /**
     * @param frameId
     * @return
     * @throws SQLException
     */
    public MotorbikeFrame getMotorbikeFrameById(int frameId) throws SQLException {
        String query, frameName;

        PreparedStatement st;
        ResultSet rs;

        MotorbikeFrame motorbikeFrame = null;

        query = "SELECT mf.name as frame_name " +
                "FROM motorbike_frames mf " +
                "WHERE mf.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, frameId);
        rs = st.executeQuery();

        while(rs.next()){
            frameName = rs.getString(COLUMN_FRAME_NAME);
            motorbikeFrame = new MotorbikeFrame(frameId, frameName);
        }

        return motorbikeFrame;
    }
}
