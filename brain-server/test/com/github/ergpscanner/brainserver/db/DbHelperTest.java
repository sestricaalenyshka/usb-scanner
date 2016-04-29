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
package com.github.ergpscanner.brainserver.db;

import com.github.ergpscanner.brainserver.model.Folder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * @author sestricaalenyshka
 */
public class DbHelperTest {

    private static final String TEST_DB_DRIVER = "org.h2.Driver";
    private static final String TEST_DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    @BeforeClass
    public static void setUp() throws Exception {
        Class.forName(TEST_DB_DRIVER);
        try (Connection conn = DriverManager.getConnection(TEST_DB_URL, "", "");
             Statement stmt = conn.createStatement()) {
            stmt.execute("create table folders (no varchar(255), s_date date)");
            stmt.execute("insert into folders (no, s_date) values ('123', '2015-04-27')");
        }
    }

    @Test
    public void getFolderNull() throws Exception {
        DbHelper dbHelper = new DbHelper(TEST_DB_DRIVER, TEST_DB_URL, "", "");
        assertNull(dbHelper.getFolder("321"));
    }

    @Test
    public void getFolderName() throws Exception {
        DbHelper dbHelper = new DbHelper(TEST_DB_DRIVER, TEST_DB_URL, "", "");
        Folder folder = dbHelper.getFolder("123");
        assertEquals("123", folder.getName());
    }

    @Test
    public void getFolderNotNull() throws Exception {
        DbHelper dbHelper = new DbHelper(TEST_DB_DRIVER, TEST_DB_URL, "", "");
        assertNotNull(dbHelper.getFolder("123"));
    }

}