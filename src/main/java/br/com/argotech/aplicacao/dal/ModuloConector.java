/*
 * The MIT License
 *
 * Copyright 2024 pnot0.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.argotech.aplicacao.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão com o banco
 * @author DiogoNucci
 * @version 1.0
 * @return connection
 */

public class ModuloConector {

    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String DATABASE_URL = "";
/**
 * Tenta uma conexao com o banco, retorna uma exception caso falhe
 */
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
