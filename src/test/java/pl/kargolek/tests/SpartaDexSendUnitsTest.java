package pl.kargolek.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.SpartaDexService;
import pl.kargolek.process.WarService;
import pl.kargolek.process.webactions.BarbariansCampActions;
import pl.kargolek.process.webactions.BarracksActions;
import pl.kargolek.process.webactions.MetamaskActions;
import pl.kargolek.process.webactions.SpartaDexMainActions;
import pl.kargolek.util.TestProperty;
import pl.kargolek.util.WebDriverUtil;

@SeleniumWebDriver
@InitPageObject
@TestProperties
public class SpartaDexSendUnitsTest {

    private String securePhrase;
    private String password;
    private WarService warService;
    private SpartaDexService spartaDexService;

    @BeforeEach
    public void setup(TestProperty testProperty, InitPages pages) {
        securePhrase = testProperty.getPhrase();
        password = testProperty.getPassword();

        var barracksActions = new BarracksActions(pages);
        var spartaDexMainActions = new SpartaDexMainActions(pages);
        var metaMaskActions = new MetamaskActions(pages);
        var barbariansCampActions = new BarbariansCampActions(pages);

        warService = new WarService(spartaDexMainActions,
                barracksActions,
                barbariansCampActions);
        spartaDexService = new SpartaDexService(spartaDexMainActions, metaMaskActions);
    }

    @AfterEach
    public void tearDown(WebDriver driver, InitPages pages) throws InterruptedException {
        pages.getSpartaDexMainPage()
                .open()
                .getSideNavigationPage();
        Thread.sleep(3000);
        WebDriverUtil.takeScreenshot(driver);
    }

    @Test
    public void test1() throws InterruptedException {
        spartaDexService.connectAndLoginSpartaDexMetamask(securePhrase, password);
        warService.prepareUnitsAttack();
    }

}
