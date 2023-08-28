package pl.kargolek.util;

import pl.kargolek.util.constant.BrowserType;
import pl.kargolek.util.constant.HeadlessMode;
import pl.kargolek.util.constant.NetworkReporter;
import pl.kargolek.util.constant.ParallelTest;

import javax.sound.midi.SysexMessage;
import java.util.Optional;

public class TestProperty {
    private static final TestProperty instance = new TestProperty();
    private static final PropertiesLoader PROPERTIES_LOADER = new PropertiesLoader(PathResolver.TEST_RESOURCES_PATH,
            "conf.properties");

    private TestProperty() {}

    public static TestProperty getInstance() {
        return instance;
    }

    public String getSeleniumHubURL(){
        return PROPERTIES_LOADER.getPropertyValue("selenium.hub.url");
    }

    public BrowserType getBrowserType(){
        var browser = Optional.ofNullable(System.getProperty("browser"))
                .orElse("chrome");
        return switch (browser) {
            case "firefox" -> BrowserType.FIREFOX;
            case "edge" -> BrowserType.EDGE;
            case "mobile-chrome" -> BrowserType.MOBILE_CHROME;
            case "local-chrome" -> BrowserType.LOCAL_CHROME;
            default -> BrowserType.CHROME;
        };
    }

    public HeadlessMode getHeadlessMode(){
        var headlessMode = Optional.ofNullable(System.getProperty("headless"))
                .orElse("disable");
        return headlessMode.equals("enable") ? HeadlessMode.ENABLE : HeadlessMode.DISABLE;
    }

    public String getPhrase(){
        return System.getenv("SEC_PHRASE");
    }

    public String getPassword(){
        return System.getenv("SEC_PASSWORD");
    }

    public String getGameURL(){
        return System.getenv("SEC_GAME_URL");
    }

    public String getGameCampURL(){
        return System.getenv("SEC_GAME_URL_CAMP");
    }

    public String getGameOpponentsCampURL(){
        return System.getenv("SEC_GAME_URL_OPPONENTS_CAMP");
    }

}
