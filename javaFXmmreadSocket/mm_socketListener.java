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
	 Logger logger = Logger.getLogger("in socket server" );
	
/* to send data from terminal set up a netcat to port 3333;
 * nc  localhost 3333
(non-Javadoc)

 */
	
	@Override
	public void run()  {
		 Thread.currentThread().setName("Jonas'sThreadName");
		 logger.debug("thread started and its name is "+Thread.currentThread().getName());
		 
		 bb = ByteBuffer.allocate(84);
		 logger.debug("L34:in thread not in thread now mk2 V/n"); 
		 
		 try {
			serverSocketChannel = ServerSocketChannel.open();
		} catch (IOException e) {
			logger.error("L39 opening serverSocketChannel problem");
			
			e.printStackTrace();
		}

		try {
			serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 3333));
		} catch (IOException e) {
			logger.error("L:47 serverSocketChannel bind error");
			e.printStackTrace();
		}

	try {
		socketChannel = serverSocketChannel.accept();
	} catch (IOException e) {
		logger.error("L:54 serverSocketChannel accept error ");
		e.printStackTrace();
	}

		if (socketChannel.isConnected()) {
			//mmSocket = socketChannel.socket();
			 
			try {
				logger.debug("L:62 print remote Address; "+socketChannel.getRemoteAddress());
			} catch (IOException e) {
				logger.error("L:64 getting address of remote client has run into an error");
				e.printStackTrace();
			}
			
		}//close if connected
		try {
			listening(socketChannel);
		} catch (IOException e) {
			logger.error("L:72 transferring to listening function has resulted in an error");
			e.printStackTrace();
		}
		
	}//close init
		
private  void listening(SocketChannel socketChannel2) throws IOException {
	// Logger logger = Logger.getLogger("sockListener");
	//BasicConfigurator.configure();
	logger.debug("L:81in listening method");
	while (socketChannel2.isConnected()) {
				
		if (socketChannel2.read(bb)>0) {
			
				
				//System.out.println("bytes read " + bytesRead );
				bb.flip();

				int tempInt=0;
				while (bb.hasRemaining()) {
tempInt =(int)bb.get();					
if (tempInt!=10) {
distance_mm.set(tempInt); 

logger.debug(distance_mm.getValue().toString() + "L93:in socketlistener module ");

}// close if socket 
}
					
				
					

				} // close while
				
				bb.clear();

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