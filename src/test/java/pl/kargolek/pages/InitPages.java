package pl.kargolek.pages;

import org.openqa.selenium.WebDriver;
import pl.kargolek.pages.metamask.onboarding.WelcomePage;
import pl.kargolek.pages.metamask.wallet.main.MetamaskMainPage;
import pl.kargolek.pages.metamask.wallet.popover.InfoPopoverPage;
import pl.kargolek.pages.metamask.wallet.popover.NetworkAddedPopoverPage;
import pl.kargolek.pages.spartadex.SpartaDexAppMainPage;
import pl.kargolek.pages.spartadex.details.BarbariansCampPage;
import pl.kargolek.pages.spartadex.details.BarracksDetailPage;
import pl.kargolek.pages.spartadex.modal.ChooseCityModalPage;

/**
 * @author Karol Kuta-Orlowicz
 */
public record InitPages(WebDriver driver) {
    public WelcomePage getWelcomePage() {
        return new WelcomePage(this.driver);
    }

    public MetamaskMainPage getMetamaskMainPage() {
        return new MetamaskMainPage(this.driver);
    }

    public InfoPopoverPage getInfoPopoverPage() {
        return new InfoPopoverPage(this.driver);
    }

    public NetworkAddedPopoverPage getNetworkAddedPopoverPage() {
        return new NetworkAddedPopoverPage(this.driver);
    }

    public SpartaDexAppMainPage getSpartaDexMainPage() {
        return new SpartaDexAppMainPage(this.driver);
    }

    public ChooseCityModalPage getChooseCityModalPage() {
        return new ChooseCityModalPage(this.driver);
    }

    public BarracksDetailPage getBarracksDetailPage() {
        return new BarracksDetailPage(this.driver);
    }

    public BarbariansCampPage getBarbariansCampPage() {
        return new BarbariansCampPage(this.driver);
    }

}
