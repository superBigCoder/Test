import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;

public class Test {
    public static Connection getConnection() throws Exception{
        ConnectionFactory c= new ConnectionFactory() {
            @Override
            public Connection createConnection() throws AmqpException {
                return null;
            }

            @Override
            public String getHost() {
                return null;
            }
//git测试
            @Override
            public int getPort() {
                return 0;
            }

            @Override
            public String getVirtualHost() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public void addConnectionListener(ConnectionListener connectionListener) {

            }

            @Override
            public boolean removeConnectionListener(ConnectionListener connectionListener) {
                return false;
            }

            @Override
            public void clearConnectionListeners() {

            }
        };

        Connection connection = c.createConnection();
        return connection;
    }

}
