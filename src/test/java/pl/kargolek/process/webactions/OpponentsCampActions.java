package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;

@Slf4j
public class OpponentsCampActions extends WebActions{

    public OpponentsCampActions(InitPages initPages) {
        super(initPages);
    }

    public void attackOpponentsCamp() throws InterruptedException {
        tabSwitchToGame();
        initPages.getOpponentsCampPage()
                .open()
                .clickAttackButton()
                .clickSelectAllUnits()
                .clickAttackButton();
    }
}
