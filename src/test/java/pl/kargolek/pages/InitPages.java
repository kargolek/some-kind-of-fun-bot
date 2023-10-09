package pl.kargolek.pages;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.game.modal.ReviewRecruitmentModalPage;
import pl.kargolek.pages.wallet.onboarding.WelcomePage;
import pl.kargolek.pages.wallet.primary.main.WalletPrimaryPage;
import pl.kargolek.pages.wallet.primary.popover.InfoPopoverPage;
import pl.kargolek.pages.wallet.primary.popover.NetworkAddedPopoverPage;
import pl.kargolek.pages.game.GameMainPage;
import pl.kargolek.pages.game.details.OpponentsCampPage;
import pl.kargolek.pages.game.details.YourCampDetailPage;
import pl.kargolek.pages.game.modal.ChooseCityModalPage;

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

    public ReviewRecruitmentModalPage reviewRecruitmentModalPage(){
        return new ReviewRecruitmentModalPage(this.driver);
    }

}
