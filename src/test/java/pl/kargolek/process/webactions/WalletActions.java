package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;

@Slf4j
public class WalletActions extends WebActions {

    public WalletActions(InitPages initPages) {
        super(initPages);
    }

    public void walletImport(String securePhrase, String password) throws InterruptedException {
        tabSwitchToWallet();
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

        initPages.getWalletPrimaryPage()
                .clickActivityTab();

        log.info("Add arbitrum chain");
        initPages.getWalletPrimaryPage()
                .clickNetworkDisplayDropdown()
                .clickAddNetworkButton()
                .clickAddNetworkByName("Arbitrum One")
                .clickApproveButton();

        log.info("Switch to arbitrum");
        initPages.getNetworkAddedPopoverPage()
                .clickSwitchToNetworkButton();
    }

    public void acceptConnectSite() {
        tabSwitchToWallet();
        refreshPage();
        log.info("Accept connection to site");
        initPages.getWalletPrimaryPage()
                .getConnectWebAppPage()
                .clickNextButton()
                .clickConnectButton()
                .waitForPage();
    }

    public void signTransaction() throws InterruptedException {
        tabSwitchToWallet();

        log.info("Close random popover");
        initPages.getInfoPopoverPage()
                .clickCloseIfExist();

        log.info("Sign transaction");
        initPages.getWalletPrimaryPage()
                .clickActivityTab()
                .clickSignatureUnconfirmedTransaction()
                .clickSignButton()
                .waitForPage();
        log.info("Sleep 2s after sign in");
        Thread.sleep(2000);
    }

}
