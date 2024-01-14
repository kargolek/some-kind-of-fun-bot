package pl.kargolek.process.enums;

/**
 * @author Karol Kuta-Orlowicz
 */
public enum Building {
    GOLDMINE("Gold Mine"),
    TREASURY("Treasury"),
    ACADEMY("Academy"),
    TIMBERCAMP("Timber Camp"),
    QUARRY("Quarry"),
    MARKET("Market"),
    TEMPLE("Temple"),
    WALL("Wall"),
    BARRACKS("Barracks"),
    SENATE("Senate"),
    BARBARIAN_CAMP("BARBARIAN CAMP"),
    NOT_FOUND("NOT_FOUND");

    private final String name;

    Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
