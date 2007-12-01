dataSource {
	pooled = true
	
	
	environments {
	  development {
	    dataSource {
	    	driverClassName = "org.hsqldb.jdbcDriver"
	    	username = "sa"
	    	password = ""
	    	dbCreate = "update"
	    	url = "jdbc:hsqldb:file:airDb;shutdown=true"
	    }
	  }
	  test {
	    dataSource {
	    	driverClassName = "org.hsqldb.jdbcDriver"
	    	username = "sa"
	    	password = ""
	    	dbCreate = "update"
		    url = "jdbc:hsqldb:file:airDb;shutdown=true"
	    }
	  }
	  production {
	    dataSource {
	    	driverClassName = "com.mysql.jdbc.Driver"
	    	username = "test"
	    	password = "test"
	    	dbCreate = "update"
		    url = "jdbc:mysql://166.111.247.247/test"
	    }
	  }
	}
}