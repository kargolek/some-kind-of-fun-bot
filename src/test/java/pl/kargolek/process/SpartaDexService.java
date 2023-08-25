package pl.kargolek.process;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.kargolek.process.webactions.MetamaskActions;
import pl.kargolek.process.webactions.SpartaDexMainActions;

@Slf4j
@AllArgsConstructor
public class SpartaDexService {

    private SpartaDexMainActions spartaDexMainActions;
    private MetamaskActions metamaskActions;

    public void connectAndLoginSpartaDexMetamask(String securePhrase, String password) throws InterruptedException {
        metamaskActions.walletImport(securePhrase, password);
        spartaDexMainActions.openSpartaDexAndClickConnectMetamask();
        metamaskActions.acceptConnectSite();
        spartaDexMainActions.enterPolisAfterConnectMetamask();
        metamaskActions.signTransaction();
    }

}
