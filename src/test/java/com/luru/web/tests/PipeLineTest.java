package com.luru.web.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luru.web.pages.LuruLoginPage;
import com.luru.web.pages.PipeLinePage;
import com.luru.web.tests.BaseLuruAutomationTest.WEB_DRIVER;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PipeLineTest extends BaseLuruAutomationTest {

	private WebDriver driver = null;
	private PipeLinePage pipelinePage;
	// private static int i=0;
	private static final Logger logger = Logger.getLogger(PipeLineTest.class.getName());

	@BeforeClass
	@Parameters({ "browser", "siteURL", "email", "password" })
	public void initLuruSiteLogin(@Optional("Chrome") String browser, @Optional("https:www.luru.app/") String siteURL,
			@Optional("shailajav@b2btesters.com") String strUserName, @Optional("Sh@ilaja1996") String strPassword)
			throws Exception {
		logger.info("Starting of initLuruSiteLogin methond");

		this.driver = this.getWebDriver(browser, WEB_DRIVER.LOGIN_DRIVER_TEST);
		this.goToSite(driver, siteURL);
		this.luruLoginPage = new LuruLoginPage(driver);
		this.pipelinePage = new PipeLinePage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	@Test(priority = 1, description = "Verify User Can Create Lead Pipeline")
	@Description("Test Description:Verify User Can Create Lead Pipeline")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Create Lead Pipeline")
	public void testCreateLeadPipeline() {
		logger.info("Startitng of testCreateLeadPipeline method");

		try {
			pipelinePage.clickOnPipelineTab();
			pipelinePage.clickOnLead();
			pipelinePage.clickOnCreateAndSearchFiled();
			pipelinePage.clickOnCreateLead();

			// Assertion for Create title
			String lblCreateLead = pipelinePage.getTitleCreateLead();
			Assert.assertEquals(lblCreateLead, expectedAssertionsProp.getProperty("text.create.lead"));

			pipelinePage.setCreateLeadInputFields(testDataProp.getProperty("pipeline.last.names"));
			pipelinePage.clickOnDropdownStatus();
			pipelinePage.clickOnNotContactedButton();
			pipelinePage.clickOnDropdownLeadSource();
			pipelinePage.clickOnWebButton();

			pipelinePage.clickOnCreateButton();

			// Assertion for Created lead successfully message title
			Assert.assertEquals(pipelinePage.getMessageText(),
					expectedAssertionsProp.getProperty("text.created.lead.successfully"));

		} catch (Exception e) {
			Assert.fail("Exception occured while creating lead pipeline  : " + e.getMessage());
			logger.error("Error occured while creating lead pipeline  ", e);
		}

		logger.info("Ending of testCreateLeadPipeline method");
	}

	@Test(priority = 2, description = "Verify User Can Update Pipeline")
	@Description("Test Description:Verify User Can Update Pipeline")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Update Pipeline")
	public void testUpdatefromRecentlyViewedLeadsPipeline() {
		logger.info("Startitng of testUpdatefromRecentlyViewedLeadsPipeline method");

		try {
			String companyName = testDataProp.getProperty("edit.company.name");
			driver.navigate().refresh();
			pipelinePage.editCompanyName(companyName);
			Assert.assertEquals(pipelinePage.getEditedCompanyName(), companyName);

		} catch (Exception e) {
			Assert.fail("Exception occured while testing recently viewed leads : " + e.getMessage());
			logger.error("Error occured while testing recently viewed leads  ", e);
		}

		logger.info("Ending of testUpdatefromRecentlyViewedLeadsPipeline method");
	}

	@Test(priority = 3, description = "Verify User Can Update Lead Pipeline")
	@Description("Test Description:Verify User Can Update Lead Pipeline")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Update Lead Pipeline")
	public void testUpdateLeadPipeline() {
		logger.info("Startitng of testUpdateLeadPipeline method");

		try {

			this.pipelinePage.updateLead();
			this.pipelinePage.editLastNameInUpdateScreen(testDataProp.getProperty("edit.pipeline.last.names"));
			this.pipelinePage.clickOnUpdateButton();

			// Assertion for Created lead successfully message title
			Assert.assertTrue(pipelinePage.getUpdatedLeadMessage(testDataProp.getProperty("company.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while updating Lead pipeline: " + e.getMessage());
			logger.error("Error occured while updating Lead pipeline  ", e);
		}

		logger.info("Ending of testUpdateLeadPipeline method");
	}
	
	@Test(priority = 4, description = "Verify User Can Update Lead Pipeline")
	@Description("Test Description:Verify User Can Update Lead Pipeline")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Update Lead Pipeline")
	public void testUpdateLeadPipeline2() {
		logger.info("Startitng of testUpdateLeadPipeline method");

		try {

			this.pipelinePage.updateLead();
			this.pipelinePage.editLastNameInUpdateScreen(testDataProp.getProperty("edit.pipeline.last.names"));
			this.pipelinePage.clickOnUpdateButton();

			// Assertion for Created lead successfully message title
			Assert.assertTrue(pipelinePage.getUpdatedLeadMessage(testDataProp.getProperty("company.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while updating Lead pipeline: " + e.getMessage());
			logger.error("Error occured while updating Lead pipeline  ", e);
		
		}

		logger.info("Ending of testUpdateLeadPipeline method");
	}

	@Test(priority = 4, description = "Verify User Can Update Lead Pipeline")
	@Description("Test Description:Verify User Can Update Lead Pipeline")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Update Lead Pipeline")
	public void testUpdateLeadPipeline3() {
		logger.info("Startitng of testUpdateLeadPipeline method");

		try {

			this.pipelinePage.updateLead();
			this.pipelinePage.editLastNameInUpdateScreen(testDataProp.getProperty("edit.pipeline.last.names"));
			this.pipelinePage.clickOnUpdateButton();

			// Assertion for Created lead successfully message title
			Assert.assertTrue(pipelinePage.getUpdatedLeadMessage(testDataProp.getProperty("company.name")));

		} catch (Exception e) {
			Assert.fail("Exception occured while updating Lead pipeline: " + e.getMessage());
			logger.error("Error occured while updating Lead pipeline  ", e);
		
		}

		logger.info("Ending of testUpdateLeadPipeline method");
	}
	@AfterClass
	public void quitDriver() {
		logger.info("Starting of quitDriver method");

		try {
			hardWait(3);
			luruLoginPage.clickOnSignOut();
			if (this.driver != null) {
				this.quitDriver(this.driver, WEB_DRIVER.LOGIN_DRIVER_TEST);
				logger.debug("Driver quit successfully");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		logger.info("Ending of quitDriver method");
	}

}
