package pl.kargolek.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.javascript.JavascriptDriverExecutor;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.*;
import pl.kargolek.process.data.Summary;
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
    private BuildingsUpgradeService buildingsUpgradeService;

    private BuildingLevelService buildingLevelService;

    private static final SummaryService summaryService = SummaryService.getInstance();
    private Summary summary;

    @BeforeEach
    public void setup(TestProperty testProperty, InitPages pages) {
        password = testProperty.getPassword();

        var yourCampActions = new YourCampActions(pages);
        var gameActions = new GameActions(pages);
        var walletActions = new WalletActions(pages);
        var opponentsCampActions = new OpponentsCampActions(pages);
        var buildingQueueActions = new BuildingQueueActions(pages);
        var upgradeBuildingActions = new BuildingsUpgradeAction(pages);
        var itemLevelActions = new BuildingLevelActions(pages);

        warService = new WarService(gameActions,
                yourCampActions,
                opponentsCampActions);
        gameService = new GameService(gameActions, walletActions);
        campResourceService = new CampResourceService(gameActions);
        buildingsUpgradeService = new BuildingsUpgradeService(buildingQueueActions, upgradeBuildingActions);
        buildingLevelService = new BuildingLevelService(itemLevelActions);

        summary = new Summary();
    }

    @AfterEach
    public void tearDownEach() throws InterruptedException {
        var buildingsLevels = buildingLevelService.getBuildingsLevels();
        var buildingsQueue = buildingsUpgradeService.getBuildingQueueList();

        summary.setBuildingLevels(buildingsLevels);
        summary.setBuildingsQueue(buildingsQueue);

        summaryService.put(summary);
    }

    @AfterAll
    public static void tearDownClass(){
        summaryService.logSummary();
    }

    @Test
    public void test_war_1() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE"), password, "test war 1", 0.3);
    }

    @Test
    public void test_war_2() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_2"), password, "test war 2", 0.3);
    }

    @Test
    public void test_war_3() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_3"), password, "test war 3", 0.4);
    }

    @Test
    public void test_war_4() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_4"), password, "test war 4", 0.3);
    }

    @Test
    public void test_war_5() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_5"), password, "test war 5", 0.3);
    }

    @Test
    public void test_war_6() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_6"), password, "test war 6", 0.3);
    }

    @Test
    public void test_war_7() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_7"), password, "test war 7", 0.3);
    }

    private void testWarProcedure(String phrase, String password, String warName, double minResourceRatio)
            throws InterruptedException {
        log.info("Starting {}", warName);
        summary.setWarName(warName);

        gameService.connectAndLoginToGameAndWallet(phrase, password);

        var canWeAttackOpponents = warService.canWeAttackOpponents();
        warService.recruitSoldiers(canWeAttackOpponents);

        var baseRequirements = campResourceService.getBaseRequirementsResult(minResourceRatio);
        var isRatioExceeded = baseRequirements.campResourceRatio().isRatioExceeded();
        summary.setBaseRequirements(baseRequirements);

        summary.setBuildingsUpgraded(
                buildingsUpgradeService.runUpgradeProcess(isRatioExceeded)
        );

        summary.setBattleOutcome(
                warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, isRatioExceeded)
        );
    }
}
