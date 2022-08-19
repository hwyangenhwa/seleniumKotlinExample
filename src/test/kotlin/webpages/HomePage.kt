package webpages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory

class HomePage(driver: WebDriver) {

    private var driver: WebDriver?= null

    // pageElement를 정보를 놓는 곳
    @FindBy(id = "header")
    var header: WebElement? = null

    init {
        this.driver = driver
        PageFactory.initElements(AjaxElementLocatorFactory(driver, 40), this)
    }

    fun openURL(): Unit{
        println(driver)
        driver!!.get("https://www.naver.com")

        print("실행완료")
        println(header!!.isDisplayed)
    }
}