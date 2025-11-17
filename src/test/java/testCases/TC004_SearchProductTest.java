package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass{
	
	@Test(groups= {"Master"})
	public void verify_productSeach() throws InterruptedException
	{
		logger.info(" Starting TC004_SearchProductTest ");
		
		try
		{
			HomePage hm=new HomePage(driver);
			
			hm.enterProductName("iPhone");
			//hm.enterProductName("mac");
			hm.clickSearch();
			
			SearchPage sp =new SearchPage(driver);
			sp.isProductExist("iPhone");
			Assert.assertEquals(sp.isProductExist("iPhone"), true);
			
		}
		catch (Exception e) 
		{
			Assert.fail();
			
		}
		logger.info(" Finished TC004_SearchProductTest ");
		
	}
		
		

}
