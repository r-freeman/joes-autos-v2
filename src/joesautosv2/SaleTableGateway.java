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

public class SaleTableGateway {
    private static final String TABLE_NAME = "sales";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_COMMISSION = "commission";
    private static final String COLUMN_SALESMAN_ID = "salesman_id";
    private static final String COLUMN_MOTORBIKE_ID = "motorbike_id";
    private static final String COLUMN_SUM_SALES = "sum_sales";
    private static final String COLUMN_NUM_MOTORBIKES_SOLD = "num_motorbikes_sold";

    private Connection mConnection;
    
    public SaleTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * @return
     * @throws SQLException
     */
    public List<Sale> getSales() throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        List<Sale> sales = new ArrayList<>();
        Sale sale;
        
        String status;
        double total, commission;
        int saleId, salesmanId, motorbikeId;
        
        query = "SELECT * FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();
        rs = st.executeQuery(query);

        while (rs.next()) {
            saleId = rs.getInt(COLUMN_ID);
            status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            salesmanId = rs.getInt(COLUMN_SALESMAN_ID);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            sale = new Sale(status, total, commission, salesmanId, motorbikeId);
            sale.setId(saleId);
            sales.add(sale);
        }
        
        return sales;
    }

    /**
     * @param status
     * @return
     * @throws SQLException
     */
    public List<Sale> getSalesByStatus(String status) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        List<Sale> sales = new ArrayList<>();
        Sale sale;
        
        String _status;
        
        double total, commission;
        int saleId, motorbikeId, salesmanId;
        
        query = "SELECT * " +
                "FROM " + TABLE_NAME + " s " +
                "WHERE s.status=?";

        st = this.mConnection.prepareStatement(query);
        st.setString(1, status);
        rs = st.executeQuery();

        while (rs.next()) {
            saleId = rs.getInt(COLUMN_ID);
            _status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            salesmanId = rs.getInt(COLUMN_SALESMAN_ID);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            sale = new Sale(_status, total, commission, salesmanId, motorbikeId);
            sale.setId(saleId);
            sales.add(sale);
        }
        
        return sales;
    }

    /**
     * @param salesmanId
     * @return
     * @throws SQLException
     */
    public List<Sale> getSalesBySalesmanId(int salesmanId) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;

        List<Sale> sales = new ArrayList<>();
        Sale sale;
        String status;
        double total, commission;
        int saleId, motorbikeId;
        
        query = "SELECT * " +
                "FROM " + TABLE_NAME + " s " +
                "WHERE s.salesman_id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, salesmanId);
        rs = st.executeQuery();
        
        while (rs.next()) {
            saleId = rs.getInt(COLUMN_ID);
            status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            sale = new Sale(status, total, commission, salesmanId, motorbikeId);
            sale.setId(saleId);
            sales.add(sale);
        }
        
        return sales;
    }

    /**
     * @param sale
     * @throws SQLException
     */
    public void addSale(Sale sale) throws SQLException {
        String query, status;
        PreparedStatement st;
        
        double total, commission;
        int salesmanId, motorbikeId;
        
        status = sale.getStatus();
        total = sale.getTotal();
        commission = sale.getCommission();
        salesmanId = sale.getSalesmanId();
        motorbikeId = sale.getMotorbikeId();
        
        query = "INSERT INTO sales (status, total, commission, salesman_id, motorbike_id) VALUES (?, ?, ?, ?, ?)";
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, status);
        st.setDouble(2, total);
        st.setDouble(3, commission);
        st.setInt(4, salesmanId);
        st.setInt(5, motorbikeId);
        st.executeUpdate();
    }

    /**
     * @param saleId
     * @throws SQLException
     */
    public void deleteSale(int saleId) throws SQLException {
        String query;
        PreparedStatement st;
        int rows;
        
        query = "DELETE FROM sales WHERE id=?";
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, saleId);
        st.executeUpdate();
    }

    /**
     * @return
     * @throws SQLException
     */
    public double getTotalSumSales() throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        double totalSumSales = 0;
        
        query = "SELECT SUM(total) AS " + COLUMN_SUM_SALES + " FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();
        rs = st.executeQuery(query);
        
        while (rs.next()) {
            totalSumSales = rs.getDouble(COLUMN_SUM_SALES);
        }
        
        return totalSumSales;
    }

    /**
     * @return
     * @throws SQLException
     */
    public int getNumMotorbikesSold() throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        int numMotorbikes = 0;
        
        query = "SELECT COUNT(id) AS " + COLUMN_NUM_MOTORBIKES_SOLD + " FROM " + TABLE_NAME + " s " + " WHERE NOT status='void'";
        st = this.mConnection.createStatement();
        rs = st.executeQuery(query);
        
        while (rs.next()) {
            numMotorbikes = rs.getInt(COLUMN_NUM_MOTORBIKES_SOLD);
        }
        
        return numMotorbikes;
    }
}
