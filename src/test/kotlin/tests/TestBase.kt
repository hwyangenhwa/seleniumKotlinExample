package tests

import Util.DriverFactory
import Util.NotifyService
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
    fun catchException(result: ITestResult) {
        if (ITestResult.FAILURE == result.status) {

            // Error Msg
            val errorInf = result.method
            //  Error Tyoe split (ElementException, NosuchElementException ...)
            val errorType = result.throwable.cause.toString().split(":")[0].split(".")[3]

            NotifyService.errorNoity(errorType, errorInf.toString())

            //  Take ScreenShot
            try {
                //TakesScreenshot screenshot=(TakesScreenshot)driver;
                val screenshot = driver as TakesScreenshot
                //File src=screenshot.getScreenshotAs(OutputType.FILE);
                val src: File = screenshot.getScreenshotAs(OutputType.FILE)
                FileUtils.copyFile(src, File("src/test/kotlin/screenshots/" + result.name + ".png"))

            } catch (errorType: Exception) {
                println("Error Exception :$errorType.")
            }
        }
    }

    @AfterTest
    fun driverClose(){
        driver.quit()
    }
}

