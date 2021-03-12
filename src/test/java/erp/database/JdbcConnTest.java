package erp.database;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class JdbcConnTest {



	@Test
	public void testGetConnection() {
		System.out.printf("%s()%n", "testGetConnection");
		Connection con = JdbcConn.getConnection();
		System.out.println("con > "+ con);
		Assert.assertNotNull(con);
	}

}
