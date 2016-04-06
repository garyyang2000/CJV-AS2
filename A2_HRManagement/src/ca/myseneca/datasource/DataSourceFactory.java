package ca.myseneca.datasource;

import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.mysql.jdbc.Driver;

public class DataSourceFactory {
	public static DataSource getMySQLDataSource() {
		MysqlDataSource mysqlDS = null;
		mysqlDS = new MysqlDataSource();
		mysqlDS.setURL("jdbc:mysql://zenit.senecac.on.ca/cjv805_161a19");
		mysqlDS.setUser("cjv805_161a19");
		mysqlDS.setPassword("fpNA6842");
		return mysqlDS;
	}

	public static DataSource getOracleDataSource(String driverType /* "thin" or "oci" */) {
		OracleDataSource oracleDS = null;

		try {
			oracleDS = new OracleDataSource();
			if ("oci".equalsIgnoreCase(driverType))
				oracleDS.setURL("jdbc:oracle:oci:@//zenit.senecac.on.ca:1521/neptune");
			else
				oracleDS.setURL("jdbc:oracle:thin:@//zenit.senecac.on.ca:1521/neptune");
			oracleDS.setUser("cjv805_161a19");
			oracleDS.setPassword("fpNA6842");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oracleDS;
	}
}