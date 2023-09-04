package pl.kargolek.process.webactions;

import lombok.extern.slf4j.Slf4j;
import pl.kargolek.pages.InitPages;
import pl.kargolek.process.enums.Soldier;

@Slf4j
public class YourCampActions extends WebActions {

    public YourCampActions(InitPages initPages) {
        super(initPages);
    }

    public void recruitSoldiers() throws InterruptedException {
        tabSwitchToGame();
        log.info("Recruit soldiers");
        initPages.getYourCampDetailPage()
                .open()
                .clickMaxButton(Soldier.Cavalry)
                .clickRecruitButton(Soldier.Cavalry)
                .clickMaxButton(Soldier.Hoplite)
                .clickRecruitButton(Soldier.Hoplite)
                .clickMaxButton(Soldier.Archer)
                .clickRecruitButton(Soldier.Archer)
                .clickMaxButton(Soldier.Slinger)
                .clickRecruitButton(Soldier.Slinger)
                .clickMaxButton(Soldier.Acolyte)
                .clickRecruitButton(Soldier.Acolyte)
                .clickMaxButton(Soldier.Militia)
                .clickRecruitButton(Soldier.Militia);
    }

    public void claimSoldiers() {
        tabSwitchToGame();
        log.info("Claim soldiers");
        initPages.getYourCampDetailPage()
                .open()
                .clickOpenQueueButton()
                .clickAllClaimButtons()
                .clickCloseModalButton();
    }

    public Integer sumUnits() throws InterruptedException {
        tabSwitchToGame();
        log.info("Summarize all units");
        var yourCampDetailPage = initPages.getYourCampDetailPage()
                .open();

        var militia = yourCampDetailPage.getAmountSoldierText(Soldier.Militia);
        var acolyte = yourCampDetailPage.getAmountSoldierText(Soldier.Acolyte);
        var slinger = yourCampDetailPage.getAmountSoldierText(Soldier.Slinger);
        var hoplite = yourCampDetailPage.getAmountSoldierText(Soldier.Hoplite);
        var archer = yourCampDetailPage.getAmountSoldierText(Soldier.Archer);
        var cavalry = yourCampDetailPage.getAmountSoldierText(Soldier.Cavalry);
        var sumUnits = militia + acolyte + slinger + (hoplite * 2) + archer + (cavalry * 3);
        log.info("Sum available units: {}", sumUnits);
        return sumUnits;
    }
}
