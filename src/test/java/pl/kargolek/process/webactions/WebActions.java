package pl.kargolek.process.webactions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.util.WebDriverUtil;

@Slf4j
@Getter
abstract class WebActions {
    final InitPages initPages;

    public WebActions(InitPages initPages) {
        this.initPages = initPages;
    }

    public void refreshPage() {
        log.info("Refresh page");
        WebDriverUtil.refreshPage(initPages.driver());
    }

    public void tabSwitchToMetamask() {
        log.info("Switch to metamask tab");
        WebDriverUtil.switchToNextTab(initPages.driver(), "chrome-extension://", 5000);
    }

    public void tabSwitchToSpartaDex() {
        log.info("Switch to spartadex tab");
        WebDriverUtil.switchToNextTab(initPages.driver(), "app.spartadex.io", 5000);
    }
}
