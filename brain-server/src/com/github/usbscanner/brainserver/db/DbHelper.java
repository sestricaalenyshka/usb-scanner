/*
 * Copyright 2016 sestricaalenyshka
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.github.usbscanner.brainserver.db;

import com.github.usbscanner.brainserver.model.Folder;

import java.sql.*;

/**
 * Набор утилитарных методов для работы с базами данных.
 *
 * @author sestricaalenyshka
 */
public class DbHelper {
    private String url;
    private String userName;
    private String password;
    private String driverClassName;

    public DbHelper(String driverClassName, String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driverClassName = driverClassName;
    }

    public Folder getFolder(String folderName) throws SQLException, ClassNotFoundException {

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement("select * from folders where no = ?")) {
            stmt.setString(1, folderName);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Folder folder = new Folder();
                    folder.setName(resultSet.getString("no"));
                    folder.setCreateDate(resultSet.getDate("s_date"));
                    return folder;
                }
            }
        }
        return null;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driverClassName);
        Connection conn = DriverManager.getConnection(url, userName, password);
        conn.setReadOnly(true);
        return conn;
    }
}
