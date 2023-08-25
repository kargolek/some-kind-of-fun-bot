package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;

@Slf4j
public class MetamaskActions extends WebActions {

    public MetamaskActions(InitPages initPages) {
        super(initPages);
    }

    public void walletImport(String securePhrase, String password) throws InterruptedException {
        tabSwitchToMetamask();
        refreshPage();

        log.info("Recover wallet for secure phrase and set password");
        initPages.getWelcomePage()
                .clickOnBoardingCheckbox()
                .clickImportWalletButton()
                .clickDisagreeButton()
                .inputRecoveryPhrase(securePhrase)
                .setupPassword(password)
                .confirmCompleteWalletCreate()
                .clickCloseButton();

        initPages.getMetamaskMainPage()
                .clickActivityTab();

        log.info("Add arbitrum chain to metamask");
        initPages.getMetamaskMainPage()
                .clickNetworkDisplayDropdown()
                .clickAddNetworkButton()
                .clickAddNetworkByName("Arbitrum One")
                .clickApproveButton();

        log.info("Switch to arbitrum");
        initPages.getNetworkAddedPopoverPage()
                .clickSwitchToNetworkButton();
    }

    public void acceptConnectSite() {
        tabSwitchToMetamask();
        refreshPage();
        log.info("Accept connection to site");
        initPages.getMetamaskMainPage()
                .getConnectWebAppPage()
                .clickNextButton()
                .clickConnectButton()
                .waitForPage();
    }

    public void signTransaction() throws InterruptedException {
        tabSwitchToMetamask();

        log.info("Close random popover");
        initPages.getInfoPopoverPage()
                .clickCloseIfExist();

        log.info("Sign transaction");
        initPages.getMetamaskMainPage()
                .clickActivityTab()
                .clickSignatureUnconfirmedTransaction()
                .clickSignButton()
                .waitForPage();
        log.info("Sleep 2s after sign in");
        Thread.sleep(2000);
    }

}
