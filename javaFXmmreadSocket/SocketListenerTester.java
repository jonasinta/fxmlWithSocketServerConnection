package javaFXmmreadSocket;



public class SocketListenerTester {

public static void main(String[] args) {
	mm_socketListener bedoingListener = new mm_socketListener();
	Integer mmGot = 0;
	bedoingListener.start();
	//mmGot = bedoingListener.getMm();


/*while(true) {
	mmGot = bedoingListener.getMm();
	System.out.println(Thread.currentThread().getName()); 
System.out.println(mmGot);
}//close while treue
*/
	}//close main
public static  void heresANewMM(int mm) {
	System.out.println("her it is and in main"+mm);
}
}//close class
