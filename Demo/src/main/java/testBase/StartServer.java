package testBase;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;

public class StartServer {
	@BeforeSuite
	public void StartServer() throws InterruptedException, IOException {
		// Execute command

		String command ="C:\\Users\\fbtcuser\\Desktop\\start-Jboss.bat";

		Process child = Runtime.getRuntime().exec(command);
		
		child.waitFor(); 
	}

}
