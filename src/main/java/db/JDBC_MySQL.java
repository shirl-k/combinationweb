package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_MySQL {
    public static void main(String[] args) {
        //JDBC Driver 등록
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //연결하기
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/combweb_db",
                    "sehee",
                    "0musroze!"
            );
            System.out.println("연결 성공");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn != null) {
                try {
                    //연결 끊기
                    conn.close();
                    System.out.println("연결 끊기");
                    }catch (SQLException e) {}
                }
            }
        }
    }

