package pl.kargolek.process.data;

/**
 * @author Karol Kuta-Orlowicz
 */
public class Summary {
    private String warName;
    private BattleOutcome battleOutcome;
    private BaseRequirements baseRequirements;
    private String buildingsUpgraded;
    private String buildingLevels;
    private String buildingsQueue;
    public Summary() {
    }

    public Summary(String warName, BattleOutcome battleOutcome, BaseRequirements baseRequirements,
                   String buildingsUpgraded, String buildingLevels, String buildingsQueue) {
        this.warName = warName;
        this.battleOutcome = battleOutcome;
        this.baseRequirements = baseRequirements;
        this.buildingsUpgraded = buildingsUpgraded;
        this.buildingLevels = buildingLevels;
        this.buildingsQueue = buildingsQueue;
    }

    public String getWarName() {
        return warName;
    }

    public void setWarName(String warName) {
        this.warName = warName;
    }

    public BattleOutcome getBattleOutcome() {
        return battleOutcome;
    }

    public void setBattleOutcome(BattleOutcome battleOutcome) {
        this.battleOutcome = battleOutcome;
    }

    public BaseRequirements getBaseRequirements() {
        return baseRequirements;
    }

    public void setBaseRequirements(BaseRequirements baseRequirements) {
        this.baseRequirements = baseRequirements;
    }

    public String getBuildingsUpgraded() {
        return buildingsUpgraded;
    }

    public void setBuildingsUpgraded(String buildingsUpgraded) {
        this.buildingsUpgraded = buildingsUpgraded;
    }

    public String getBuildingLevels() {
        return buildingLevels;
    }

    public void setBuildingLevels(String buildingLevels) {
        this.buildingLevels = buildingLevels;
    }

    public String getBuildingsQueue() {
        return buildingsQueue;
    }

    public void setBuildingsQueue(String buildingsQueue) {
        this.buildingsQueue = buildingsQueue;
    }
}
