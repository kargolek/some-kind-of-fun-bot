package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.pages.game.details.buildings.YourCampDetailPage;
import pl.kargolek.process.data.CampResources;
import pl.kargolek.process.data.TreasuryMaxResource;
import pl.kargolek.util.WebDriverUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class GameActions extends WebActions {

    public GameActions(InitPages initPages) {
        super(initPages);
    }

    public void openGameAndClickConnectWallet() {
        WebDriverUtil.switchToNextTab(initPages.driver(), "data", 5000);
        initPages.getGameMainPage()
                .open()
                .getSideNavigationPage()
                .clickConnectWalletButton()
                .clickWalletButton();
    }

    public CampResources openGameLogExperienceInfo() throws InterruptedException {
        tabSwitchToGame();
        var experiencePanelPage = initPages.getGameMainPage()
                .open()
                .getExperiencePanelPage();

        Thread.sleep(3000);

        var wood = experiencePanelPage.getWoodValue();
        var stone = experiencePanelPage.getStoneValue();
        var gold = experiencePanelPage.getGoldValue();
        var gem = experiencePanelPage.getGemValue();
        var experience = experiencePanelPage.getExperienceValue();

        return new CampResources(
                Double.parseDouble(wood),
                Double.parseDouble(stone),
                Double.parseDouble(gold),
                Double.parseDouble(gem),
                getExperience(experience)
        );
    }

    private Integer getExperience(String experience) {
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(experience);
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }
        return 0;
    }

    public TreasuryMaxResource openTreasuryAndGetMaxResourcesValue() throws InterruptedException {
        tabSwitchToGame();
        var treasuryDetailPage = initPages.getGameMainPage()
                .open()
                .getSideNavigationPage()
                .clickTreasuryButton();

        Thread.sleep(1500);

        var wood = treasuryDetailPage.getWoodValue();
        var stone = treasuryDetailPage.getStoneValue();
        var gold = treasuryDetailPage.getGoldValue();

        return new TreasuryMaxResource(
                Double.parseDouble(wood),
                Double.parseDouble(stone),
                Double.parseDouble(gold)
        );
    }

    public void acceptTermsAndUserLicenseAgreements() {
        tabSwitchToGame();
        initPages.getGameMainPage()
                .getAgreementsModalPage()
                .clickTermsUserLicenseCheckbox()
                .clickAgreementCheckbox()
                .clickAcceptButton();
    }

    public void enterCityAfterConnectWallet() {
        tabSwitchToGame();
        initPages.getChooseCityModalPage()
                .clickEnterCityButton()
                .getSideNavigationPage();
    }

    public YourCampDetailPage openYourCamp() {
        tabSwitchToGame();
        var barracksDetailsPage = initPages.getGameMainPage()
                .getSideNavigationPage()
                .clickCampButton();
        barracksDetailsPage.waitForTitleDetailViewHeader("barracks", "Barracks");
        return barracksDetailsPage;
    }
}
