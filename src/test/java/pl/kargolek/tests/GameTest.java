package pl.kargolek.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.GameService;
import pl.kargolek.process.WarService;
import pl.kargolek.process.webactions.GameActions;
import pl.kargolek.process.webactions.OpponentsCampActions;
import pl.kargolek.process.webactions.WalletActions;
import pl.kargolek.process.webactions.YourCampActions;
import pl.kargolek.util.TestProperty;

@SeleniumWebDriver
@InitPageObject
@TestProperties
@Slf4j
public class GameTest {

    private String password;
    private WarService warService;
    private GameService gameService;

    @BeforeEach
    public void setup(TestProperty testProperty, InitPages pages) {
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
    public void test_war_1() throws InterruptedException {
        log.info("Starting test war 1");
        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE"), password);
        warService.prepareUnitsAttack();
    }

    @Test
    public void test_war_2() throws InterruptedException {
        log.info("Starting test war 2");
        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_2"), password);
        warService.prepareUnitsAttack();
    }

    @Test
    public void test_war_3() throws InterruptedException {
        log.info("Starting test war 3");
        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_3"), password);
        warService.prepareUnitsAttack();
    }

}
