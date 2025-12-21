package pages;

import com.microsoft.playwright.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import static utils.TestConfig.getBaseUrl;

public class BasePage {
    protected final Page page;
    protected final String baseUrl = "http://localhost:3000/";

    public BasePage(Page page) {
        this.page = page;
    }

    public void clickElement(String selector) {
        page.waitForSelector(selector);
        page.click(selector);
        page.waitForTimeout(1000);
    }

    public void fillElement(String selectors, String inputValue) {
        page.waitForSelector(selectors);
        page.fill(selectors, inputValue);
        page.waitForTimeout(1000);
    }

    public void navigatetoWebsite() {
        String baseUrl = getBaseUrl();
        page.navigate(baseUrl);
        page.waitForLoadState();
    }
}
