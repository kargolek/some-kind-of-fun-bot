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

        initPages.getWalletPrimaryPage()
                .clickNetworkDisplayDropdown()
                .clickAddNetworkButton()
                .clickAddNetworkByName("Arbitrum One")
                .clickApproveButton();

        initPages.getNetworkAddedPopoverPage()
                .clickSwitchToNetworkButton();
    }

    public void acceptConnectSite() {
        tabSwitchToWallet();
        refreshPage();
        initPages.getWalletPrimaryPage()
                .getConnectWebAppPage()
                .clickNextButton()
                .clickConnectButton()
                .waitForPage();
    }

    public void signTransaction() throws InterruptedException {
        tabSwitchToWallet();

        initPages.getInfoPopoverPage()
                .clickCloseIfExist();

        initPages.getWalletPrimaryPage()
                .clickActivityTab()
                .clickSignatureUnconfirmedTransaction()
                .clickSignButton()
                .waitForPage();
        Thread.sleep(2000);
    }

}
