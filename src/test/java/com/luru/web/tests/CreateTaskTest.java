package com.luru.web.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.luru.web.pages.CreateTaskPage;
import com.luru.web.pages.LuruLoginPage;
import com.luru.web.pages.PipeLinePage;
import com.luru.web.tests.BaseLuruAutomationTest.WEB_DRIVER;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class CreateTaskTest extends BaseLuruAutomationTest {

	private WebDriver driver = null;
	private PipeLinePage pipelinePage;
	private CreateTaskPage createTaskPage;
	private static final Logger logger = Logger.getLogger(CreateTaskTest.class.getName());

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
		this.createTaskPage = new CreateTaskPage(driver);
		super.loginToTheLuruSite(strUserName, strPassword);

		logger.info("Ending of initLuruSiteLogin method");
	}

	

	@Test(priority = 2, description = "Verify Task Marked Completed")
	@Description("Test Description: Verify Task Marked Completed ")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify Task Marked Completed")
	public void testMarkedCompletedTask() {
		logger.info("Startitng of testTaskmarkedCompletedTask method");

		try {
			createTaskPage.clickOnCompleteTaskIcon();

			// Assertion for Delete Task
			Assert.assertEquals(createTaskPage.getTaskCreatedMessage(),
					expectedAssertionsProp.getProperty("text.task.marked.completed"));

		} catch (Exception e) {
			Assert.fail("Exception occured while testing task marked completed : " + e.getMessage());
			logger.error("Error occured while task marked completed ", e);
		}

		logger.info("Ending of testTaskmarkedCompletedTask method");
	}

	@Test(priority = 3, description = "Verify User Can Update Task ")
	@Description("Test Description: Verify User Can Update Task ")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Update Task")
	public void testUpdateTask() {
		logger.info("Startitng of testUpdateTask method");

		try {
			createTaskPage.clickOnCreatedTask();

			Assert.assertEquals(createTaskPage.getUpdateTaskTiltle(),
					expectedAssertionsProp.getProperty("text.update.task"));
			// createTaskPage.clickOnTaskAssignButton();

			createTaskPage.clickOnTaskAssignButton();
			createTaskPage.clickOnAssignedLead();

			createTaskPage.clickOnUpdateButton();

			/*
			 * Assert.assertEquals(createTaskPage.getTaskCreatedMessage(),
			 * expectedAssertionsProp.getProperty("text.task.updated.successfully"));
			 */

		} catch (Exception e) {
			Assert.fail("Exception occured while updating task : " + e.getMessage());
			logger.error("Error occured while updating task ", e);
		}

		logger.info("Ending of testTaskmarkedCompletedTask method");
	}

	@Test(priority = 4, description = "Verify User Can Delete Task")
	@Description("Test Description: Verify User Can Delete Task")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verify User Can Delete Task")
	public void testDeleteTask() {
		logger.info("Startitng of testDeleteTask method");

		try {
			createTaskPage.clickOnDeleteIcon();

			// Assertion for Delete Task
			/*
			 * Assert.assertEquals(createTaskPage.getTaskCreatedMessage(),
			 * expectedAssertionsProp.getProperty("text.task.deleted"));
			 */
		} catch (Exception e) {
			Assert.fail("Exception occured while deleting task : " + e.getMessage());
			logger.error("Error occured while deleting task ", e);
		}

		logger.info("Ending of testDeleteTask method");
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
