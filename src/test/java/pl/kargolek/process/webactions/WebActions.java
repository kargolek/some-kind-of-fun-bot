package pl.kargolek.process.webactions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.util.TestProperty;
import pl.kargolek.util.WebDriverUtil;

@Slf4j
@Getter
abstract class WebActions {
    final InitPages initPages;

    public WebActions(InitPages initPages) {
        this.initPages = initPages;
    }

    public void refreshPage() {
        WebDriverUtil.refreshPage(initPages.driver());
    }

    public void tabSwitchToWallet() {
        WebDriverUtil.switchToNextTab(initPages.driver(), "chrome-extension://", 5000);
    }

    public void tabSwitchToGame() {
        WebDriverUtil.switchToNextTab(initPages.driver(), TestProperty.getInstance().getGameURL(), 5000);
    }
}
