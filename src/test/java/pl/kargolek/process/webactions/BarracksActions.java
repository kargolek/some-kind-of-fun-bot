package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.enums.Soldier;

@Slf4j
public class BarracksActions extends WebActions {

    public BarracksActions(InitPages initPages) {
        super(initPages);
    }

    public void recruitSoldiers() {
        tabSwitchToSpartaDex();
        log.info("Recruit soldiers");
        initPages.getBarracksDetailPage()
                .open()
                .clickMaxButton(Soldier.Acolyte)
                .clickRecruitButton(Soldier.Acolyte)
                .clickMaxButton(Soldier.Militia)
                .clickRecruitButton(Soldier.Militia);
    }

    public void claimSoldiers() {
        tabSwitchToSpartaDex();
        log.info("Claim soldiers");
        initPages.getBarracksDetailPage()
                .open()
                .clickOpenQueueButton()
                .clickAllClaimButtons()
                .clickCloseModalButton();
    }

    public Integer sumUnits() {
        tabSwitchToSpartaDex();
        log.info("Summarize all units");
        var barracksDetailPage = initPages.getBarracksDetailPage()
                .open();

        var militia = barracksDetailPage.getAmountSoldierText(Soldier.Militia);
        var acolyte = barracksDetailPage.getAmountSoldierText(Soldier.Acolyte);
        var slinger = barracksDetailPage.getAmountSoldierText(Soldier.Slinger);
        var hoplite = barracksDetailPage.getAmountSoldierText(Soldier.Hoplite);
        var archer = barracksDetailPage.getAmountSoldierText(Soldier.Archer);
        var cavalry = barracksDetailPage.getAmountSoldierText(Soldier.Cavalry);

        return militia + acolyte + slinger + hoplite + archer + cavalry;
    }
}
