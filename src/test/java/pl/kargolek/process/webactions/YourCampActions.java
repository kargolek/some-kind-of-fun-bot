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

        initPages.getYourCampDetailPage()
                .open();

        recruitSoldierProcess(Soldier.Cavalry);
        recruitSoldierProcess(Soldier.Hoplite);
        recruitSoldierProcess(Soldier.Archer);
        recruitSoldierProcess(Soldier.Slinger);
        recruitSoldierProcess(Soldier.Acolyte);
        recruitSoldierProcess(Soldier.Militia);
    }

    private void recruitSoldierProcess(Soldier soldier) throws InterruptedException {
        log.info("Recruit: {}", soldier.toString());
        var isRecruitEnable = initPages.getYourCampDetailPage()
                .clickMaxButton(soldier)
                .isRecruitButtonEnable(soldier);

        if (isRecruitEnable){
            initPages.getYourCampDetailPage()
                    .clickRecruitButton(soldier);
            var isReviewModalOpened = initPages.reviewRecruitmentModalPage()
                    .isReviewRecruitModalOpened();

            if (isReviewModalOpened) {
                initPages.reviewRecruitmentModalPage()
                        .clickRecruitModalButton()
                        .clickCloseModalButton();
            }
        }
    }

    public void claimSoldiers() {
        tabSwitchToGame();
        initPages.getYourCampDetailPage()
                .open()
                .clickOpenQueueButton()
                .clickAllClaimButtons()
                .clickCloseModalButton();
    }

    public Integer sumUnits() throws InterruptedException {
        tabSwitchToGame();
        var yourCampDetailPage = initPages.getYourCampDetailPage()
                .open();

        Thread.sleep(1000);

        var militia = yourCampDetailPage.getAmountSoldierText(Soldier.Militia);
        var acolyte = yourCampDetailPage.getAmountSoldierText(Soldier.Acolyte);
        var slinger = yourCampDetailPage.getAmountSoldierText(Soldier.Slinger);
        var hoplite = yourCampDetailPage.getAmountSoldierText(Soldier.Hoplite);
        var archer = yourCampDetailPage.getAmountSoldierText(Soldier.Archer);
        var cavalry = yourCampDetailPage.getAmountSoldierText(Soldier.Cavalry);
        return militia + acolyte + slinger + (hoplite * 2) + archer + (cavalry * 3);
    }
}
