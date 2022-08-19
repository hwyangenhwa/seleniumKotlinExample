package tests

import Util.DriverFactory
import Util.UtilResources
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import java.util.concurrent.TimeUnit

abstract class TestBase {

    lateinit var driver: WebDriver
    private set

    @BeforeTest
    fun setup(){
        driver = DriverFactory.browser

        /*
        System.setProperty(
            UtilResources.getProperties("nameDriver"),
            UtilResources.getProperties("pathDriver") + UtilResources.getProperties("exeDriver"))

        driver = ChromeDriver()
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()
        driver.get(UtilResources.getProperties("pageURL")).toString()
        */
    }

    @AfterTest
    fun driverClose(){
        driver.quit()
    }
}