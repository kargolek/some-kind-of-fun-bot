package pl.kargolek.process;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Karol Kuta-Orlowicz
 */
@Slf4j
public class SummaryService {
    private static SummaryService instance;
    private final List<Summary> summaries = new ArrayList<>();

    private SummaryService() {
    }

    public static synchronized SummaryService getInstance() {
        if (instance == null) {
            instance = new SummaryService();
        }
        return instance;
    }

    public void put(Summary summary) {
        summaries.add(summary);
    }

    public void logSummary() {
        final String baseTemplate = """
                @@@@@@@@@@@@@@@@ {} @@@@@@@@@@@@@@@@
                1. {}
                2. {}
                3. {}
                4. Buildings upgraded: {}
                5. Buildings level: {}
                6. Buildings queue: {}
                @@@@@@@@@@@@@@@@ END @@@@@@@@@@@@@@@@
                """;

        log.info("###### WARS SUMMARY ######");

        summaries.forEach(summary -> {

            var baseRequirements = getOptionalBaseRequirements(summary);
            var battleOutcome = getOptionalBattleOutcome(summary);

            log.info(baseTemplate,
                    summary.getWarName(),
                    baseRequirements.campResources(),
                    baseRequirements.campResourceRatio(),
                    battleOutcome,
                    summary.getBuildingsUpgraded(),
                    summary.getBuildingLevels(),
                    getFormattedBuildingQueue(summary));
        });
        log.info("###### END SUMMARY ######");
    }

    private BaseRequirements getOptionalBaseRequirements(Summary summary) {
        return Optional.ofNullable(summary.getBaseRequirements())
                .orElse(new BaseRequirements(
                        new CampResourceRatio(false, -1, -1, -1, -1),
                        new CampResources(-1, -1, -1, -1, -1),
                        new TreasuryMaxResource(-1, -1, -1)
                ));
    }

    private BattleOutcome getOptionalBattleOutcome(Summary summary) {
        return Optional.ofNullable(summary.getBattleOutcome())
                .orElse(new BattleOutcome(
                        false,
                        new CampResources(-1, -1, -1, -1, -1),
                        -1));
    }

    private String getFormattedBuildingQueue(Summary summary){
        return getOptionalBuildingQueue(summary).replace("If you want to queue more than 2 buildings, " +
                        "you need to have a premium account\n" +
                        "Premium Account", "")
                .replace("\n", " ")
                .replace("Builds in progress", "");
    }

    private String getOptionalBuildingQueue(Summary summary){
        return Optional.ofNullable(summary.getBuildingsQueue())
                .orElse(" ");
    }



}
