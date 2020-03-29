package journal.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import journal.model.application;

public class ApplicationDAO {

	private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;
     
    public ApplicationDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = "jdbc:mysql://localhost:3306/rits_db?useSSL=false";
        this.jdbcUsername = "root";
        this.jdbcPassword = "wangkhei123";
    }
     
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                                        jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
     
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
    
    public boolean addApplication(application app) throws SQLException 
    {
    	
    	
    	
        String INSERT_APP_SQL = "INSERT INTO application" +
            "  (id, refno, ptitle, jrname, doi, jcat, firstauthor, fauthor, seauthor, secondauthor, sauthor, thauthor, thirdauthor, tauthor, otherauthor, incentiveF, incentiveS, incentiveT, totalincentive) VALUES " +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );";

        

        connect();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = jdbcConnection.prepareStatement(INSERT_APP_SQL);
        	
            //preparedStatement.setInt(1, 1);
            preparedStatement.setString(1, app.getId());
            preparedStatement.setString(2, app.getREFNO());
            preparedStatement.setString(3, app.getTitle());
            preparedStatement.setString(4, app.getjournal());
            preparedStatement.setString(5, app.getDOI());
            preparedStatement.setString(6, app.getJCAT());
            preparedStatement.setString(7, app.getFIRSTAUTHOR());
            preparedStatement.setString(8, app.getFAUTHOR());
            preparedStatement.setString(9, app.getSEAUTHOR());
            preparedStatement.setString(10, app.getSECONDAUTHOR());
            preparedStatement.setString(11, app.getSAUTHOR());
            preparedStatement.setString(12, app.getTHAUTHOR());
            preparedStatement.setString(13, app.getTHIRDAUTHOR());
            preparedStatement.setString(14, app.getTAUTHOR());
            preparedStatement.setString(15, app.getOTHERAUTHOR());
            preparedStatement.setString(16, app.getINCENTIVEF());
            preparedStatement.setString(17, app.getINCENTIVES());
            preparedStatement.setString(18, app.getINCENTIVET());
            preparedStatement.setString(19, app.getTOTALINCENTIVE());

            boolean rowInserted = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
            disconnect();
            return rowInserted;
    }
    
    
    public List<application> listAllApplications() throws SQLException {
        List<application> listApplication = new ArrayList<>();
         
        String sql = "SELECT * FROM application";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String id = resultSet.getString("id");
            String refno = resultSet.getString("refno");
            String ptitle = resultSet.getString("pititle");
            String jrname = resultSet.getString("jrname");
            String doi = resultSet.getString("doi");
            String jcat = resultSet.getString("jcat");
            String firstauthor = resultSet.getString("firstauthor");
            String fauthor = resultSet.getString("fauthor");
            String seauthor = resultSet.getString("seauthor");
            String secondauthor = resultSet.getString("secondauthor");
            String sauthor = resultSet.getString("sauthor");
            String thauthor = resultSet.getString("thauthor");
            String thirdauthor = resultSet.getString("thirdauthor");
            String tauthor = resultSet.getString("tauthor");
            String incentiveF = resultSet.getString("incentiveF");
            String incentiveS = resultSet.getString("incentiveS");
            String incentiveT = resultSet.getString("incentiveT");
            String totalincentive = resultSet.getString("totalincentive");
            String otherauthor = resultSet.getString("otherauthor");
            
            
            
             
            application application = new application(id, refno, ptitle, jrname, doi, jcat, firstauthor, fauthor, seauthor, secondauthor, sauthor, thauthor, thirdauthor, tauthor, incentiveF, incentiveS, incentiveT, totalincentive, otherauthor);
            listApplication.add(application);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listApplication;
    }
    

    
    }
