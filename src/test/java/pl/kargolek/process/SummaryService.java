package pl.kargolek.process;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.Summary;

import java.util.ArrayList;
import java.util.List;

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
                {}
                {}
                {}
                {}
                Buildings upgraded: {}
                Buildings level: {}
                Buildings queue: {}
                """;

        log.info("###### WARS SUMMARY ######");

        summaries.forEach(summary -> {
        log.info(baseTemplate, summary.getWarName(),
                summary.getBaseRequirements().campResources(),
                summary.getBaseRequirements().campResourceRatio(),
                summary.getBattleOutcome(),
                summary.getBuildingsUpgraded(),
                summary.getBuildingLevels(),
                summary.getBuildingsQueue());
        });

        log.info("###### END SUMMARY ######");
    }

}
