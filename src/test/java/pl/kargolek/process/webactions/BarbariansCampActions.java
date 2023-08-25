package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;

@Slf4j
public class BarbariansCampActions extends WebActions{

    public BarbariansCampActions(InitPages initPages) {
        super(initPages);
    }

    public void attackBarbarianCamp(){
        log.info("Attack barbarians by your units");
        tabSwitchToSpartaDex();
        initPages.getBarbariansCampPage()
                .open()
                .clickAttackButton()
                .clickAllMaxButtons()
                .clickAttackButton();
    }
}
