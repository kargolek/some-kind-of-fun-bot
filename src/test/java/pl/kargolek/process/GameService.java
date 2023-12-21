package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.data.CampResources;
import pl.kargolek.process.data.TreasuryMaxResource;
import pl.kargolek.process.webactions.GameActions;
import pl.kargolek.process.webactions.WalletActions;

@Slf4j
@AllArgsConstructor
public class GameService {

    private GameActions gameActions;
    private WalletActions walletActions;

    public void connectAndLoginToGameAndWallet(String securePhrase, String password) throws InterruptedException {
        log.info("Start connect to wallet service");
        walletActions.walletImport(securePhrase, password);
        gameActions.openGameAndClickConnectWallet();
        walletActions.acceptConnectSite();
        gameActions.acceptTermsAndUserLicenseAgreements();
        gameActions.enterCityAfterConnectWallet();
        walletActions.signTransaction();
        log.info("Connect to wallet service has been complete");
    }

}
