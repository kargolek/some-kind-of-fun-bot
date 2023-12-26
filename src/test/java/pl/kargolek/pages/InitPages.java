package pl.kargolek.pages;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.GameMainPage;
import pl.kargolek.pages.game.details.buildings.*;
import pl.kargolek.pages.game.modal.ChooseCityModalPage;
import pl.kargolek.pages.game.modal.ReviewRecruitmentModalPage;
import pl.kargolek.pages.wallet.onboarding.WelcomePage;
import pl.kargolek.pages.wallet.primary.main.WalletPrimaryPage;
import pl.kargolek.pages.wallet.primary.popover.InfoPopoverPage;
import pl.kargolek.pages.wallet.primary.popover.NetworkAddedPopoverPage;

/**
 * @author Karol Kuta-Orlowicz
 */
public record InitPages(WebDriver driver) {
    public WelcomePage getWelcomePage() {
        return new WelcomePage(this.driver);
    }

    public WalletPrimaryPage getWalletPrimaryPage() {
        return new WalletPrimaryPage(this.driver);
    }

    public InfoPopoverPage getInfoPopoverPage() {
        return new InfoPopoverPage(this.driver);
    }

    public NetworkAddedPopoverPage getNetworkAddedPopoverPage() {
        return new NetworkAddedPopoverPage(this.driver);
    }

    public GameMainPage getGameMainPage() {
        return new GameMainPage(this.driver);
    }

    public ChooseCityModalPage getChooseCityModalPage() {
        return new ChooseCityModalPage(this.driver);
    }

    public YourCampDetailPage getYourCampDetailPage() {
        return new YourCampDetailPage(this.driver);
    }

    public OpponentsCampPage getOpponentsCampPage() {
        return new OpponentsCampPage(this.driver);
    }

    public AcademyDetailViewPage getAcademyDetailViewPage() {
        return new AcademyDetailViewPage(this.driver);
    }

    public GoldDetailViewPage getGoldDetailViewPage(){
        return new GoldDetailViewPage(this.driver);
    }

    public MarketDetailViewPage getMarketDetailViewPage() {
        return new MarketDetailViewPage(this.driver);
    }

    public QuarryDetailViewPage getQuarryDetailViewPage(){
        return new QuarryDetailViewPage(this.driver);
    }

    public TempleDetailViewPage getTempleDetailViewPage(){
        return new TempleDetailViewPage(this.driver);
    }

    public TimberCampDetailViewPage getTimberCampDetailViewPage(){
        return new TimberCampDetailViewPage(this.driver);
    }

    public TreasuryDetailViewPage getTreasuryDetailViewPage() {
        return new TreasuryDetailViewPage(this.driver);
    }

    public WallDetailViewPage getWallDetailViewPage() {
        return new WallDetailViewPage(this.driver);
    }

    public SenateDetailViewPage getSenateDetailViewPage() {
        return new SenateDetailViewPage(this.driver);
    }

    public ReviewRecruitmentModalPage reviewRecruitmentModalPage() {
        return new ReviewRecruitmentModalPage(this.driver);
    }

}
