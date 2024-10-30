/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.argotech.aplicacao.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DiogoNucci
 */
public class ModuloConector {

    private static final String USERNAME = "db_operator";
    private static final String PASSWORD = "AVNS_xR3MhLBXeV_Bz0nrGir";
    private static final String DATABASE_URL = "jdbc:mysql://argotechteste-argotech.h.aivencloud.com:14053/argotechteste";

    //Tenta uma conexao com o banco, retorna uma exception caso falhe
    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }
}
