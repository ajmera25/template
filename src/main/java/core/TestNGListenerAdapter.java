package core;


import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import utilities.DriverUtils;



public class TestNGListenerAdapter extends TestListenerAdapter implements ITestListener {

	private BaseTest baseTest = null;
	
	public TestNGListenerAdapter() {
		super();
	}
	
	public TestNGListenerAdapter(BaseTest baseTest) {
		super();
		this.baseTest = baseTest;
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			DriverUtils.takeScreenShot(result, baseTest.getDriver());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		try {
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		super.onTestSuccess(result);
	}
	
	
	
	
	
	
}
