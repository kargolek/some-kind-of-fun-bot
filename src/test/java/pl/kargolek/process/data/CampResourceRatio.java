package pl.kargolek.process.data;

/**
 * @author Karol Kuta-Orlowicz
 */
public record CampResourceRatio(boolean isRatioExceeded, double wood, double stone, double gold, double ratio) {
}
