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

public class MotorbikeTableGateway {
    private static final String TABLE_NAME = "motorbikes";
    private static final String COLUMN_MOTORBIKE_ID = "id";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_ENGINE = "engine";
    private static final String COLUMN_COLOUR = "colour";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STOCK = "stock";
    private static final String COLUMN_FRAME_ID = "frame_id";
    private static final String COLUMN_FRAME_NAME = "frame_name";
    private static final String COLUMN_ROW_COUNT = "row_count";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     *
     * @param connection
     */
    public MotorbikeTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @param motorbikeId
     * @return
     * @throws SQLException
     */
    public Motorbike getMotorbikeById(int motorbikeId) throws SQLException {
        int frameId, year, stock;
        String query, make, model, engine, colour, frameName;
        double price;
        PreparedStatement st;
        ResultSet rs;

        Motorbike motorbike = null;

        query = "SELECT m.*, mf.name as frame_name " +
                "FROM " + TABLE_NAME + " m " +
                "INNER JOIN motorbike_frames mf " +
                "ON m.frame_id = mf.id " +
                "WHERE m.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, motorbikeId);
        rs = st.executeQuery();

        while (rs.next()) {
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getDouble(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            frameId = rs.getInt(COLUMN_FRAME_ID);
            frameName = rs.getString(COLUMN_FRAME_NAME);

            motorbike = new Motorbike(make, model, engine, colour, year, price, stock, frameId, frameName);
            motorbike.setMotorbikeId(motorbikeId);
        }

        return motorbike;
    }

    /**
     * Storing a new motorbike
     * @param motorbike
     * @throws SQLException
     */
    public void storeMotorbike(Motorbike motorbike) throws SQLException {
        String query, make, model, engine, colour;
        PreparedStatement st;

        double price;
        int year, stock, frameId;

        make = motorbike.getMake();
        model = motorbike.getModel();
        engine = motorbike.getEngine();
        colour = motorbike.getColour();
        year = motorbike.getYear();
        price = motorbike.getPrice();
        stock = motorbike.getStock();
        frameId = motorbike.getFrameId();

        query = "INSERT INTO motorbikes (make, model, engine, " +
                "colour, year, price, stock, frame_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        st = this.mConnection.prepareStatement(query);
        st.setString(1, make);
        st.setString(2, model);
        st.setString(3, engine);
        st.setString(4, colour);
        st.setInt(5, year);
        st.setDouble(6, price);
        st.setInt(7, stock);
        st.setInt(8, frameId);
        st.executeUpdate();
    }


    /**
     * Updating existing motorbike
     * @param motorbike
     * @throws SQLException
     */
    public void updateMotorbike(Motorbike motorbike) throws SQLException {
        String query;
        PreparedStatement st;

        query = "UPDATE motorbikes m " +
                "SET make=?, " +
                "model=?, " +
                "engine=?, " +
                "colour=?, " +
                "year=?, " +
                "price=?, " +
                "stock=? " +
                "WHERE m.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setString(1, motorbike.getMake());
        st.setString(2, motorbike.getModel());
        st.setString(3, motorbike.getEngine());
        st.setString(4, motorbike.getColour());
        st.setInt(5, motorbike.getYear());
        st.setDouble(6, motorbike.getPrice());
        st.setInt(7, motorbike.getStock());
        st.setInt(8, motorbike.getMotorbikeId());
        st.executeUpdate();
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<Motorbike> getMotorbikes () throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Motorbike> motorbikes = new ArrayList<>();
        Motorbike motorbike;

        String make, model, engine, colour, frameName;
        double price;
        int motorbikeId, frameId, year, stock;

        query = "SELECT m.*, mf.name as frame_name " +
                "FROM " + TABLE_NAME + " m " +
                "INNER JOIN motorbike_frames mf " +
                "ON m.frame_id = mf.id ";
        st = this.mConnection.prepareStatement(query);

        rs = st.executeQuery();

        while (rs.next()) {
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getDouble(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            frameId = rs.getInt(COLUMN_FRAME_ID);
            frameName = rs.getString(COLUMN_FRAME_NAME);

            motorbike = new Motorbike(make, model, engine, colour, year, price, stock, frameId, frameName);
            motorbike.setMotorbikeId(motorbikeId);

            motorbikes.add(motorbike);
        }

        return motorbikes;
    }

    /**
     * @param frameId
     * @return
     * @throws SQLException
     */
    public List<Motorbike> getMotorbikesByFrame (int frameId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Motorbike> motorbikes = new ArrayList<>();
        Motorbike motorbike;

        String make, model, engine, colour, frameName;
        double price;
        int motorbikeId, year, stock;

        query = "SELECT m.*, mf.name as frame_name " +
                "FROM " + TABLE_NAME + " m " +
                "INNER JOIN motorbike_frames mf " +
                "ON m.frame_id = mf.id " +
                "WHERE mf.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, frameId);

        rs = st.executeQuery();

        while (rs.next()) {
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getDouble(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            frameName = rs.getString(COLUMN_FRAME_NAME);

            motorbike = new Motorbike(make, model, engine, colour, year, price, stock, frameId, frameName);
            motorbike.setMotorbikeId(motorbikeId);

            motorbikes.add(motorbike);
        }

        return motorbikes;
    }


    /**
     * @param motorbike
     * @throws SQLException
     */
    public void deleteMotorbike (Motorbike motorbike) throws SQLException {
        String query;
        PreparedStatement st;

        query = "DELETE FROM " + TABLE_NAME + " " +
                "WHERE motorbikes.id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, motorbike.getMotorbikeId());

        st.executeUpdate();
    }

    /**
     * @param motorbikeId
     * @return
     * @throws SQLException
     */
    public int validateMotorbikeId(int motorbikeId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        int rowCount = 0;

        query = "SELECT COUNT(*) as row_count " +
                "FROM " + TABLE_NAME + " m " +
                "WHERE m.id=?";

        st = this.mConnection.prepareStatement(query);
        st.setInt(1, motorbikeId);
        rs = st.executeQuery();

        while (rs.next()) {
            rowCount = rs.getInt(COLUMN_ROW_COUNT);
        }

        return rowCount;
    }
}
