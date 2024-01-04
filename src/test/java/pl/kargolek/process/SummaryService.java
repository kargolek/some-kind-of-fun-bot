package pl.kargolek.process;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.BattleOutcome;
import pl.kargolek.process.data.CampResourceRatio;
import pl.kargolek.process.data.CampResources;
import pl.kargolek.process.data.Summary;

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
        log.info(baseTemplate, summary.getWarName(),
                Optional.ofNullable(summary.getBaseRequirements().campResources())
                        .orElse(new CampResources(-1d, -1d, -1d, -1d, -1)),
                Optional.ofNullable(summary.getBaseRequirements().campResourceRatio())
                        .orElse(new CampResourceRatio(false, -1d, -1d, -1d, -1d)),
                Optional.ofNullable(summary.getBattleOutcome())
                        .orElse(new BattleOutcome(false,
                                new CampResources(-1d, -1d, -1d, -1d, -1), -1)),
                summary.getBuildingsUpgraded(),
                summary.getBuildingLevels(),
                Optional.ofNullable(summary.getBuildingsQueue()).orElse(" ")
                        .replace("If you want to queue more than 2 buildings, you need to have a premium account\n" +
                                "Premium Account", "")
                        .replace("\n", " ")
                        .replace("Builds in progress", ""));
        });
        log.info("###### END SUMMARY ######");
    }

}
