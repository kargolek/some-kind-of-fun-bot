package pl.kargolek.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kargolek.extension.driver.SeleniumWebDriver;
import pl.kargolek.extension.javascript.JavascriptDriverExecutor;
import pl.kargolek.extension.pages.InitPageObject;
import pl.kargolek.extension.properties.TestProperties;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.*;
import pl.kargolek.process.data.BuildingLevel;
import pl.kargolek.process.data.Summary;
import pl.kargolek.process.enums.Building;
import pl.kargolek.process.webactions.*;
import pl.kargolek.util.TestProperty;

import java.util.List;

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
    private List<BuildingLevel> maxBuildingLevels;

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

        maxBuildingLevels = setMaxBuildingLevels(9, 9, 9, 1, 9, 9, 9,
                1, 1);
    }

    @AfterEach
    public void tearDownEach() {
        summaryService.put(summary);
    }

    @AfterAll
    public static void tearDownClass() {
        summaryService.logSummary();
    }

    @Test
    public void test_war_1() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE"), password, "test war 1", 0.5,
                maxBuildingLevels);
    }

    @Test
    public void test_war_2() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_2"), password, "test war 2", 0.5,
                maxBuildingLevels);
    }

    @Test
    public void test_war_3() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_3"), password, "test war 3", 0.5,
                maxBuildingLevels);
        gameService.runLotteryProcess();
    }

    @Test
    public void test_war_4() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_4"), password, "test war 4", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_5() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_5"), password, "test war 5", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_6() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_6"), password, "test war 6", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_7() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_7"), password, "test war 7", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_8() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_8"), password, "test war 8", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_9() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_9"), password, "test war 9", 0.6,
                maxBuildingLevels);
    }

    @Test
    public void test_war_10() throws InterruptedException {
        testWarProcedure(System.getenv("SEC_PHRASE_10"), password, "test war 10", 0.6,
                maxBuildingLevels);
    }

    private void testWarProcedure(String phrase,
                                  String password,
                                  String warName,
                                  double minResourceRatio,
                                  List<BuildingLevel> maxBuildingLevels) throws InterruptedException {
        log.info("Starting {}", warName);
        summary.setWarName(warName);

        gameService.connectAndLoginToGameAndWallet(phrase, password);

        var canWeAttackOpponents = warService.canWeAttackOpponents();
        warService.recruitSoldiers(canWeAttackOpponents);

        var baseRequirements = campResourceService.getBaseRequirementsResult(minResourceRatio);
        var isRatioExceeded = baseRequirements.campResourceRatio().isRatioExceeded();
        summary.setBaseRequirements(baseRequirements);

        var buildingsLevels = buildingLevelService.getBuildingsLevels();
        summary.setBuildingLevels(buildingsLevels.toString());

        summary.setBuildingsUpgraded(
                buildingsUpgradeService.runUpgradeProcess(isRatioExceeded, buildingsLevels, maxBuildingLevels)
        );

        summary.setBattleOutcome(
                warService.attackOpponentsCampAndRecruit(canWeAttackOpponents, isRatioExceeded)
        );

        var buildingsQueue = buildingsUpgradeService.getBuildingQueueList();
        summary.setBuildingsQueue(buildingsQueue);
    }

    private List<BuildingLevel> setMaxBuildingLevels(int academy, int barracks, int goldmine, int market, int quarry,
                                                     int timber, int treasury, int temple, int wall) {

        return List.of(new BuildingLevel(Building.ACADEMY, academy),
                new BuildingLevel(Building.BARRACKS, barracks),
                new BuildingLevel(Building.GOLDMINE, goldmine),
                new BuildingLevel(Building.MARKET, market),
                new BuildingLevel(Building.QUARRY, quarry),
                new BuildingLevel(Building.TIMBERCAMP, timber),
                new BuildingLevel(Building.TREASURY, treasury),
                new BuildingLevel(Building.TEMPLE, temple),
                new BuildingLevel(Building.WALL, wall)
        );
    }
}
