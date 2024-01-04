package pl.kargolek.process.data;

/**
 * @author Karol Kuta-Orlowicz
 */
public record BattleOutcome(boolean wasBattle, CampResources gainResources, Integer unitsLost) {
}
