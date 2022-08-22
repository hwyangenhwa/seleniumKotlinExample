package tests

import Util.DriverFactory
import java.io.File
import org.apache.commons.io.FileUtils

import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest

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

    @AfterMethod(alwaysRun = true)
    fun catchException(result: ITestResult){
        if (ITestResult.FAILURE == result.status) {
            try {

                //TakesScreenshot screenshot=(TakesScreenshot)driver;
                val screenshot = driver as TakesScreenshot
                //File src=screenshot.getScreenshotAs(OutputType.FILE);
                val src: File = screenshot.getScreenshotAs(OutputType.FILE)
                FileUtils.copyFile(src, File("src/test/kotlin/screenshots/" + result.name + ".png"))
                //FileUtils.copyFile(src, File("src/test/kotlin/screenshots/" + result.instanceName + ".png"))
                //FileUtils.copyFile(src, File("src/test/kotlin/screenshots/" + result.testClass + ".png"))
                println(" Screenshot is success")

            } catch (e: Exception) {
                println("Error Exception :$e")
            }
        }
    }

    @AfterTest
    fun driverClose(){
        driver.quit()
    }
}