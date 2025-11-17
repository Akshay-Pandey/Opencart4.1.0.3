package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class TC005_AddToCartPageTest extends BaseClass{
	
	@Test(groups={"Master"})
	public void verify_addToCart() throws InterruptedException 
	{
		logger.info("Starting TC005_AddToCartPageTest ");
		
		try
		{
			HomePage hp=new HomePage(driver);
			hp.clickSearch();
			
			
			SearchPage sp=new SearchPage(driver);
			
			if(sp.isProductExist("iPhone"))
			{
				sp.selectProduct();
				sp.setQuantity("2");
				sp.addToCart();
			}
			Assert.assertEquals(sp.checkConMsg(), true);
			
		} catch(Exception e) {
			
			Assert.fail();
		}
		logger.info(" Starting TC005_AddToCartPageTest ");
	}
	
}
