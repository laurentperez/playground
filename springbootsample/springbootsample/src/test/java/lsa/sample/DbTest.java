package lsa.sample;

import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {

    @Test
    public void loadDb() throws Exception {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.SimpleContextFactory");
        System.setProperty("org.osjava.sj.jndi.shared", "true");
        InitialContext ic = new InitialContext();

        ic.createSubcontext("java:/comp/env/jdbc");
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:foo");
        ds.setUser("sa");
        ds.setPassword("");
        ic.bind("java:/comp/env/jdbc/myDS", ds);
        DataSource ds1 = (DataSource) ic.lookup("java:/comp/env/jdbc/myDS");
        Connection c = ds1.getConnection();
        Statement s = c.createStatement();
        s.execute("create TABLE xxx (id VARCHAR(10))");
        s.execute("INSERT into xxx (id) VALUES('zzz')");
        ResultSet rs = s.executeQuery("select id from xxx");
        rs.next();
        String id = rs.getString("id");
        System.out.println(id);
    }
}
