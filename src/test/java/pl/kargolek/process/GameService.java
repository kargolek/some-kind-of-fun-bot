package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.webactions.WalletActions;
import pl.kargolek.process.webactions.GameActions;

@Slf4j
@AllArgsConstructor
public class GameService {

    private GameActions gameActions;
    private WalletActions walletActions;

    public void connectAndLoginToGameAndWallet(String securePhrase, String password) throws InterruptedException {
        walletActions.walletImport(securePhrase, password);
        gameActions.openGameAndClickConnectWallet();
        walletActions.acceptConnectSite();
        gameActions.acceptTermsAndUserLicenseAgreements();
        gameActions.enterCityAfterConnectWallet();
        walletActions.signTransaction();
    }

}
