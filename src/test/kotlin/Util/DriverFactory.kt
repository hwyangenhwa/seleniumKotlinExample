package Util

import Util.UtilResources
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.opera.OperaDriver
import java.util.concurrent.TimeUnit

object DriverFactory {

    val browser: WebDriver
        // 데이터 변조를 위해서 getter를 사용함
        get() {

            // webDriver 정보를 담음
            val driver: WebDriver

            // BrowserInf (CHROME, FIREFOX, OPERA, EDGE)
            val browserName = System.getProperty("browser", DriverType.CHROME.toString()).toUpperCase()
            val driverType = DriverType.valueOf(browserName)

            // when 조건문으로 분기 처리
            when (driverType) {
                DriverType.CHROME -> {
                    WebDriverManager.chromedriver().setup()
                    driver = ChromeDriver()
                }
                DriverType.FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup()
                    driver = FirefoxDriver()
                }
                DriverType.EDGE -> {
                    WebDriverManager.edgedriver().setup()
                    driver = EdgeDriver()
                }
                DriverType.OPERA -> {
                    WebDriverManager.operadriver().setup()
                    driver = OperaDriver()
                }
            }

            driver.manage().window().maximize()
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS)
            return driver
        }
}