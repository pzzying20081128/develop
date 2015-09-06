package cn.zy.apps.tools.jpa.privates ;

import java.sql.Connection ;
import java.sql.SQLException ;
import java.util.Map ;

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider ;
import org.hibernate.service.spi.Configurable ;
import org.hibernate.service.spi.Stoppable ;

public class MYHB4ProxoolConnectionProvider implements ConnectionProvider, Configurable, Stoppable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L ;

    @Override
    public boolean isUnwrappableAs(Class arg0) {
        // TODO Auto-generated method stub
        return false ;
    }

    @Override
    public <T> T unwrap(Class<T> arg0) {
        // TODO Auto-generated method stub
        return null ;
    }

    @Override
    public void stop() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void configure(Map arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeConnection(Connection arg0) throws SQLException {
     
        
    }

    @Override
    public Connection getConnection() throws SQLException {
        // TODO Auto-generated method stub
        return null ;
    }

    @Override
    public boolean supportsAggressiveRelease() {
        // TODO Auto-generated method stub
        return false ;
    }

}
