package pl.kargolek.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.javascript.JavascriptDriverExecutor;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.*;
import pl.kargolek.process.webactions.*;
import pl.kargolek.util.TestProperty;

@SeleniumWebDriver
@InitPageObject
@TestProperties
@JavascriptDriverExecutor
@Slf4j
public class GameTest {

    private String password;
    private WarService warService;
    private GameService gameService;
    private CampResourceService campResourceService;
    private UpgradeBuildingsService upgradeBuildingsService;

    @BeforeEach
    public void setup(TestProperty testProperty, InitPages pages) {
        password = testProperty.getPassword();

        var yourCampActions = new YourCampActions(pages);
        var gameActions = new GameActions(pages);
        var walletActions = new WalletActions(pages);
        var opponentsCampActions = new OpponentsCampActions(pages);
        var buildingQueueActions = new BuildingQueueActions(pages);
        var upgradeBuildingActions = new UpgradeBuildingsAction(pages);

        warService = new WarService(gameActions,
                yourCampActions,
                opponentsCampActions);
        gameService = new GameService(gameActions, walletActions);
        campResourceService = new CampResourceService(gameActions);
        upgradeBuildingsService = new UpgradeBuildingsService(buildingQueueActions, upgradeBuildingActions);
    }

    @AfterEach
    public void tearDownEach(InitPages pages) throws InterruptedException {
        var itemLevelActions = new ItemLevelActions(pages);
        new ItemLevelService(itemLevelActions).logItemsLevels();
        upgradeBuildingsService.logBuildingQueueList();
    }

//    @Test
//    public void test_war_1() throws InterruptedException {
//        log.info("Starting test war 1");
//        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE"), password);
//
//        var canWeAttackOpponents = warService.canWeAttackOpponents();
//        warService.recruitSoldiers(canWeAttackOpponents);
//
//        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.5);
//        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
//        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
//    }
//
//    @Test
//    public void test_war_2() throws InterruptedException {
//        log.info("Starting test war 2");
//        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_2"), password);
//
//        var canWeAttackOpponents = warService.canWeAttackOpponents();
//        warService.recruitSoldiers(canWeAttackOpponents);
//
//        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.5);
//        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
//        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
//    }
//
//    @Test
//    public void test_war_3() throws InterruptedException {
//        log.info("Starting test war 3");
//        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_3"), password);
//
//        var canWeAttackOpponents = warService.canWeAttackOpponents();
//        warService.recruitSoldiers(canWeAttackOpponents);
//
//        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.5);
//        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
//        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
//    }
//
//    @Test
//    public void test_war_4() throws InterruptedException {
//        log.info("Starting test war 4");
//        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_4"), password);
//
//        var canWeAttackOpponents = warService.canWeAttackOpponents();
//        warService.recruitSoldiers(canWeAttackOpponents);
//
//        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.4);
//        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
//        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
//    }
//
//    @Test
//    public void test_war_5() throws InterruptedException {
//        log.info("Starting test war 5");
//        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_5"), password);
//
//        var canWeAttackOpponents = warService.canWeAttackOpponents();
//        warService.recruitSoldiers(canWeAttackOpponents);
//
//        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.3);
//        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
//        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
//    }

    @Test
    public void test_war_6() throws InterruptedException {
        log.info("Starting test war 6");
        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_6"), password);

        var canWeAttackOpponents = warService.canWeAttackOpponents();
        warService.recruitSoldiers(canWeAttackOpponents);

        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.3);
        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
    }

    @Test
    public void test_war_7() throws InterruptedException {
        log.info("Starting test war 7");
        gameService.connectAndLoginToGameAndWallet(System.getenv("SEC_PHRASE_7"), password);

        var canWeAttackOpponents = warService.canWeAttackOpponents();
        warService.recruitSoldiers(canWeAttackOpponents);

        var exceedMinRequirements = campResourceService.isExceedMinRequirements(0.4);
        upgradeBuildingsService.runUpgradeProcess(exceedMinRequirements);
        warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, exceedMinRequirements);
    }
}
