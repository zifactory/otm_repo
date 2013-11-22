/*
Copyright 2009-2010 Igor Polevoy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/


package zi.helper.test;

import org.javalite.activejdbc.Base;
import org.javalite.test.jspec.JSpecSupport;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public abstract class ActiveJDBCTest extends JSpecSupport {
    private final static Logger logger = LoggerFactory.getLogger(ActiveJDBCTest.class);
    static boolean schemaGenerated = false;
    PrintStream errOrig;
    PrintStream err;
    ByteArrayOutputStream bout;

    @Before
    public void before() throws Exception {
        Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/otm_repo", "root", "root");

        if (!schemaGenerated) {
            schemaGenerated = true;
            // System.out.println("DB: " + db() + ", Driver:" + driver());
        }
        Base.connection().setAutoCommit(false);
    }

    @After
    public void after() {

        try {
            Base.connection().rollback();
        } catch (SQLException e) {
            logger.info(e.getMessage(), e);
        }
        Base.close();
    }

    /**
     * Returns array of strings where text was separated by semi-colons.
     *
     * @return array of strings where text was separated by semi-colons.
     */
    public String[] getStatements(String delimiter, String file) {
        try {

            System.out.println("Getting statements from file: " + file);
            InputStreamReader isr = new InputStreamReader(ActiveJDBCTest.class.getClassLoader().getResourceAsStream(file));
            BufferedReader reader = new BufferedReader(isr);
            StringBuffer text = new StringBuffer();
            String t;
            while ((t = reader.readLine()) != null) {
                text.append(t + '\n');
            }
            return text.toString().split(delimiter);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Convenience method for testing.
     *
     * @param year
     * @param month - 1 through 12
     * @param day   - day of month
     * @return Timestamp instance
     */
    public Timestamp getTimestamp(int year, int month, int day) {
        return new Timestamp(getTime(year, month, day));
    }

    public Date getDate(int year, int month, int day) {
        return new Date(getTime(year, month, day));
    }

    /**
     * there is nothing more annoying than Java date/time APIs!
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public long getTime(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime().getTime();
    }

    private void executeStatements(List<String> statements) {
        String curStmt = "";
        try {
            for (String statement : statements) {
                curStmt = statement;
                Statement st;
                st = Base.connection().createStatement();
                try {
                    st.executeUpdate(statement);
                } catch (Exception e) {
                    throw e;
                }

                st.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            throw new RuntimeException(curStmt, e);
        }
    }

    protected void replaceSystemError() {
        errOrig = System.err;
        bout = new ByteArrayOutputStream();
        err = new PrintStream(bout);
        System.setErr(err);
    }

    protected String getSystemError() throws IOException {
        err.flush();
        bout.flush();
        return bout.toString();
    }
}
