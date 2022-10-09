package com.r2dsolution.comein.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DashboardRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
    public List<Map<String, Object>> queryDashboard(long dashboardId, LocalDate dateFrom, LocalDate dateTo) throws ClassNotFoundException, SQLException {
    	String query = "SELECT dashboard_query FROM dashboard_query where dashboard_id = ?";
        
        Map<String, Object> map = jdbcTemplate.queryForMap(query, dashboardId);  
        
        List<Map<String,Object>> resultSet = 
                //Use a custom RowMapper
        		jdbcTemplate.query(map.get("dashboard_query").toString(), new RowMapper<Map<String,Object>>() {

            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                //The map per row
                Map<String,Object> map = new HashMap<String,Object>();
                //The target date format
                DateFormat nice = new SimpleDateFormat("yyyy-MM-dd");
                //Iterating the columns.
                ResultSetMetaData meta = rs.getMetaData();
                for(int i = 1; i <= meta.getColumnCount(); ++i) {
                    Object o = rs.getObject(i);
                    //If a date use formatted String instead
                    if(o instanceof Date) {
                        o = nice.format(o);
                    }
                    //put into map.
                    map.put(meta.getColumnName(i), o);
                }
                return map;
            }

        }, dateFrom, dateTo);
        resultSet.forEach(e -> System.out.println(e));
        
        return resultSet;
    }
    
    public List<Map<String, Object>> queryDashboard(long dashboardId, long tourCompanyId, LocalDate dateFrom, LocalDate dateTo) throws ClassNotFoundException, SQLException {
    	String query = "SELECT dashboard_query FROM dashboard_query where dashboard_id = ?";
        
        Map<String, Object> map = jdbcTemplate.queryForMap(query, dashboardId);  
        
        List<Map<String,Object>> resultSet = 
                //Use a custom RowMapper
        		jdbcTemplate.query(map.get("dashboard_query").toString(), new RowMapper<Map<String,Object>>() {

            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                //The map per row
                Map<String,Object> map = new HashMap<String,Object>();
                //The target date format
                DateFormat nice = new SimpleDateFormat("yyyy-MM-dd");
                //Iterating the columns.
                ResultSetMetaData meta = rs.getMetaData();
                for(int i = 1; i <= meta.getColumnCount(); ++i) {
                    Object o = rs.getObject(i);
                    //If a date use formatted String instead
                    if(o instanceof Date) {
                        o = nice.format(o);
                    }
                    //put into map.
                    map.put(meta.getColumnName(i), o);
                }
                return map;
            }

        }, tourCompanyId, dateFrom, dateTo);
        resultSet.forEach(e -> System.out.println(e));
        
        return resultSet;
    }
}

