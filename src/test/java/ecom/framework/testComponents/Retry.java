package ecom.framework.testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 1;
	int maxTry = 2;

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

}
