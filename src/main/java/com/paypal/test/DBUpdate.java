package com.paypal.test;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.stream.Stream;

public class DBUpdate {
    void updateDB() throws Exception {
        String url="jdbc:mysql://prod-host:3306/testdb?useSSL=false";
        String user="testuser";
        String password="test623";
        Path file =Paths.get("test.csv");
        BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8);
        Stream<String> lines = br.lines();
        lines.forEach(
                line -> {
                    String[] splitted = line.split(",");
                    try {
                        Connection con = DriverManager.getConnection(url, user, password);
                        String sql = "INSERT INTO person (first_name, last_name, age) values (?, ?, ?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, splitted[0]);
                        pst.setString(2, splitted[1]);
                        pst.setString(3, splitted[2]);
                        pst.executeUpdate();
                    } catch (Exception e) {

                    }
                }
        );
    }
}
