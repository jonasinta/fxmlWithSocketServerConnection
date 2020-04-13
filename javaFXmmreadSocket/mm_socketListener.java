package javaFXmmreadSocket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class mm_socketListener extends Thread{
	private static Socket mmSocket;
	private static ServerSocketChannel serverSocketChannel;
	private static SocketChannel socketChannel;
	private static ByteBuffer bb;

	 private DoubleProperty distance_mm = new SimpleDoubleProperty();
	
	
/* to send data from terminal set up a netcat to port 3333;
 * nc  localhost 3333
(non-Javadoc)

 */
	 @Override
	public void run()  {
		 Thread.currentThread().setName("Jonas'sThreadName");
		 Logger logger = Logger.getLogger("Thread" + Thread.currentThread().getName());
		 bb = ByteBuffer.allocate(84);
		 logger.debug("in thread "+Thread.currentThread().getName()); 
		 
		 try {
			serverSocketChannel = ServerSocketChannel.open();
		} catch (IOException e) {
			logger.error("opening serverSocketChannel problem");
			
			e.printStackTrace();
		}

		try {
			serverSocketChannel.socket().bind(new InetSocketAddress(3333));
		} catch (IOException e) {
			logger.error("serverSocketChannel bind error");
			e.printStackTrace();
		}

	try {
		socketChannel = serverSocketChannel.accept();
	} catch (IOException e) {
		logger.error("serverSocketChannel accept error ");
		e.printStackTrace();
	}

		if (socketChannel.isConnected()) {
			mmSocket = socketChannel.socket();
			 
			try {
				logger.debug("print remote Address; "+socketChannel.getRemoteAddress());
			} catch (IOException e) {
				logger.error(" getting address of remote client has run into an error");
				e.printStackTrace();
			}
			
		}//close if connected
		try {
			listening(mmSocket);
		} catch (IOException e) {
			logger.error(" transferring to listening function has resulted in an error");
			e.printStackTrace();
		}
		
	}//close init
		
private  void listening(Socket mmSocket) throws IOException {
	 Logger logger = Logger.getLogger("sockListener");
	BasicConfigurator.configure();
	logger.debug("Sample debug message");
	double bytesRead;
			while (mmSocket.isConnected()) {
				
				
				try {

					bytesRead = socketChannel.read(bb);
					//System.out.println("bytes read " + bytesRead );
					bb.flip();

					int tempInt=0;
					while (bb.hasRemaining()) {
	tempInt =(int)bb.get();					
if (tempInt!=10) {
	distance_mm.set(tempInt); 
	logger.debug(distance_mm.doubleValue() + "in socketlistener module ");
	
	
}
						
					
						

					} // close while
					
					bb.clear();
				} catch (IOException e) {
					logger.error("reading socket bytes has run into an error");
					serverSocketChannel.close();
					e.printStackTrace();
				}//close catch

			}//close while

		} // close listening

/**
 * @return the mm but wrapped in such a way that javaFX can observe the value
 */
public final DoubleProperty distance_mmProperty() {
	return this.distance_mm;
}


public final double getDistance_mm() {
	return this.distance_mmProperty().get();
}


public final void setDistance_mm(final double distance_mm) {
	this.distance_mmProperty().set(distance_mm);
}


	}// close class