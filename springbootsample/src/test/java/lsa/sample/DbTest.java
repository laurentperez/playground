package lsa.sample;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.NewCookie;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {

    static DataSource ds1;

    @BeforeClass
    public static void setup() throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.SimpleContextFactory");
        InitialContext ic = new InitialContext();
        ic.createSubcontext("java:/comp/env/jdbc");
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:foo;INIT=runscript from 'classpath:create.sql'\\;runscript from 'classpath:populate.sql'");
        ds.setUser("sa");
        ds.setPassword("");
        ic.bind("jdbc/orages", ds);
        ds1 = (DataSource) ic.lookup("jdbc/orages");
    }

    @Test
    public void loadDb() throws Exception {

        Connection c = ds1.getConnection();
        c.setAutoCommit(true);
        Statement s = c.createStatement();
        ResultSet rs = s.executeQuery("select id from xxx");
        rs.next();
        c.commit();
        String id = rs.getString("id");
        System.out.println(id);
        String m = HttpMethod.DELETE;
        System.out.println("m = " + m);
        NewCookie nc = new NewCookie("name","value","path","domain","comment",3600,true, true);
        System.out.println("nc = " + nc);
    }
}
