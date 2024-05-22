package basicTestCases;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import reusableComponent.ListenersImplementation;





public class ExecutableMavenJar {

	static TestNG testng;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
		    // body of main method goes here, including any other error handling
		  
		// TODO Auto-generated method stub
		ListenersImplementation ext =new ListenersImplementation();
		
		  TestListenerAdapter tla = new TestListenerAdapter(); 
		  testng = new TestNG();
		  testng.setTestClasses(new Class[] {AWC_Login.class});
		 // String path=System.getProperty("user.dir")+"/testng.xml";//path to xml..
		 List<String> xmlList=new ArrayList<String>();
		// xmlList.add(path);
		  testng.addListener(ext);;
		  testng.run();
		 
		} catch (Throwable t) {
		    JOptionPane.showMessageDialog(
		        null, t.getClass().getSimpleName() + ": " + t.getMessage());
		    throw t; // don't suppress Throwable
		  }
		}

}
