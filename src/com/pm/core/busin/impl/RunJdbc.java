package com.pm.core.busin.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import com.pm.core.util.SqlserverConnUtil;

/**
 * 执行存储过程
 * @author Administrator
 *
 */
public class RunJdbc {
    
    /**
     * 执行存储过程
     * @param sql 存储过程名称
     */
 public static void runchunchu(String sql,String orderid){
        
        Connection conn = SqlserverConnUtil.getConnection();  
        if(conn!=null){
            CallableStatement callStmt = null;  
            try {  
                callStmt = conn.prepareCall("{call  "+sql+"}");
                callStmt.setString(1, orderid);
                callStmt.execute();  
            } catch (Exception e) {  
                e.printStackTrace(System.out);  
            } finally {  
                SqlserverConnUtil.closeConnect(null, null, conn);   
            }  
        }
    }
}
