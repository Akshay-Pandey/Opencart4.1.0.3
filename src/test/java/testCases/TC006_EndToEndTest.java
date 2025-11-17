package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;




public class TC006_EndToEndTest extends BaseClass {
	
	@Test(groups= {"Master"})
	public void endToendTest() throws InterruptedException
	{
		//Soft Assertion
		SoftAssert myassert=new SoftAssert();
		
		//Account Registration
		System.out.println("Account Registration---------");
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		
		String email=randomString() + "@gmail.com";
		regpage.setEmail(email); //randomly generated the email
		
		regpage.setTelephone("1234567");
		regpage.setPassword("test123");
		regpage.setConfirmPassword("test123");
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		Thread.sleep(3000);
		
		String confmsg= regpage.getConfirmationMsg();
		System.out.println(confmsg);
		
		myassert.assertEquals(confmsg, "Yours Account has been created! "); //validation
		
		
		MyAccountPage mc =new MyAccountPage(driver);
		mc.clickLogout();
		Thread.sleep(3000);
		
		//Login
		
		System.out.println("Login to application ------- ");
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword("test123");
		lp.clickLogin();
		
		System.out.println("Going to My Account Page? "+ mc.isMyAccounPageExists());
		myassert.assertEquals(mc.isMyAccounPageExists(), true);
		
		
		//shipping cart
		
		System.out.println("Shoping cart----------");
		ShoppingCartPage sc = new ShoppingCartPage(driver);
		sc.ClickItemsToNavigateToCart();
		sc.ClickViewCart();
		Thread.sleep(3000);
		String totprice=sc.getTotalPrice();
		System.out.println("Total price is shopping cart: "+totprice);
		myassert.assertEquals(totprice, "$246.40"); //Validation
		sc.clickOnCheckout(); //navigate to checkout page
		Thread.sleep(3000);
		
		//Checkout Product
		
		System.out.println("Checkout Product----------");
		CheckoutPage ch=new CheckoutPage(driver);
		
		ch.setfirstName(randomeString().toUpperCase());
		Thread.sleep(1000);
		ch.setlastName(randomeString().toUpperCase());
		Thread.sleep(1000);
		ch.setaddress1("address1");
		Thread.sleep(1000);
		ch.setaddress2("address2");
		Thread.sleep(1000);
		ch.setcity("Delhi");
		Thread.sleep(1000);
		ch.setpin("500070");
		Thread.sleep(1000);
		ch.setCountry("India");
		Thread.sleep(1000);
		ch.setState("Delhi");
		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryAddress();
		Thread.sleep(1000);
		ch.setDeliveryMethodComment("testing---");
		Thread.sleep(1000);
		ch.clickOnContinueAfterDeliveryMethod();
		Thread.sleep(1000);
		ch.selectTermsAndConditions();
		Thread.sleep(1000);
		ch.ClickonContinueAfterPaymentMethod();
		Thread.sleep(2000);
		
		String total_price_inOrderPage= ch.getTotalPriceBeforeConfOrder();
		System.out.println("total price in confirmed order page:"+total_price_inOrderPage);
		myassert.assertEquals(total_price_inOrderPage, "$207.00"); //validation
		
		//Below code works only if you configure SMTP for emails
		/*ch.clickOnConfirmOrder();
		Thread.sleep(3000);
		
		boolean orderconf=ch.isOrderPlaced();
		System.out.println("Is Order Placed? "+orderconf);
		myassert.assertEquals(ch.isOrderplaced(),true); 
		*/
		
		myassert.assertAll(); //conclusion
		
		
		
		
		
		
		
	}

}
