package pl.kargolek.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.GameService;
import pl.kargolek.process.WarService;
import pl.kargolek.process.webactions.OpponentsCampActions;
import pl.kargolek.process.webactions.YourCampActions;
import pl.kargolek.process.webactions.WalletActions;
import pl.kargolek.process.webactions.GameActions;
import pl.kargolek.util.TestProperty;
import pl.kargolek.util.WebDriverUtil;

@SeleniumWebDriver
@InitPageObject
@TestProperties
public class GameTest {

    private String securePhrase;
    private String password;
    private WarService warService;
    private GameService gameService;

    @BeforeEach
    public void setup(TestProperty testProperty, InitPages pages) {
        securePhrase = testProperty.getPhrase();
        password = testProperty.getPassword();

        var yourCampActions = new YourCampActions(pages);
        var gameActions = new GameActions(pages);
        var walletActions = new WalletActions(pages);
        var opponentsCampActions = new OpponentsCampActions(pages);

        warService = new WarService(gameActions,
                yourCampActions,
                opponentsCampActions);
        gameService = new GameService(gameActions, walletActions);
    }

    @Test
    public void test_war() throws InterruptedException {
        gameService.connectAndLoginToGameAndWallet(securePhrase, password);
        warService.prepareUnitsAttack();
    }

}
