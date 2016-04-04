package ca.myseneca.rmi.server;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

/**
* The DAMServer class provides the essentials 
* for hosting the RMI service.
* 
* 
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
public class DAMServer {
	public DAMServer() {
		try {
			LocateRegistry.createRegistry(1199);
			DAMgrIntf d = new DAMgrImpl();
		
		Naming.rebind("rmi://localhost:1199/DAMService", d);
		} catch (Exception e) {
		System.out.println("Trouble: " + e);
		}
		}
		public static void main(String args[]) {
			new DAMServer();
			System.out.println("Server has been started......");
		}
}
