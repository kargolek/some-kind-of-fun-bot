package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;

@Slf4j
public class OpponentsCampActions extends WebActions{

    public OpponentsCampActions(InitPages initPages) {
        super(initPages);
    }

    public void attackOpponentsCamp() throws InterruptedException {
        log.info("Attack opponents by your units");
        tabSwitchToGame();
        initPages.getOpponentsCampPage()
                .open()
                .clickAttackButton()
                .clickSelectAllUnits()
                .clickAttackButton();
    }
}
