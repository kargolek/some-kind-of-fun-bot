package pl.kargolek.pages.game;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.kargolek.pages.BasePage;
import pl.kargolek.pages.game.details.buildings.OpponentsCampPage;
import pl.kargolek.pages.game.details.buildings.TheatreDetailViewPage;
import pl.kargolek.pages.game.details.buildings.TreasuryDetailViewPage;
import pl.kargolek.pages.game.details.buildings.YourCampDetailPage;
import pl.kargolek.pages.game.modal.BuildingQueueModalPage;
import pl.kargolek.pages.game.modal.ConnectWalletModalPage;

public class SideNavigationPage extends BasePage {

    @FindBy(xpath = "//ul//div[text()='Barracks']")
    private WebElement campButton;

    @FindBy(xpath = "//ul//div[text()='Barbarian Camp']")
    private WebElement opponentsCampButton;

    @FindBy(xpath = "//button[text()='Connect wallet']")
    private WebElement connectWalletButton;

    @FindBy(xpath = "//ul//div[text()='Timber Camp']")
    private WebElement timberCamp;

    @FindBy(xpath = "//ul//div[text()='Quarry']")
    private WebElement quarryButton;

    @FindBy(xpath = "//ul//div[text()='Gold Mine']")
    private WebElement goldMineButton;

    @FindBy(xpath = "//ul//div[text()='Wall']")
    private WebElement wallButton;

    @FindBy(xpath = "//ul//div[text()='Academy']")
    private WebElement academyButton;

    @FindBy(xpath = "//ul//div[text()='Temple']")
    private WebElement templeButton;

    @FindBy(xpath = "//ul//div[text()='Treasury']")
    private WebElement treasuryButton;

    @FindBy(xpath = "//ul//div[text()='Market']")
    private WebElement marketButton;

    @FindBy(xpath = "//ul//div[text()='Building Queue']")
    private WebElement buildingQueueButton;


    @FindBy(xpath = "//ul//div[text()='Amphitheatre']")
    private WebElement theatreButton;

    public SideNavigationPage(WebDriver driver) {
        super(driver);
    }

    public ConnectWalletModalPage clickConnectWalletButton() {
        this.waitForElementClickable(connectWalletButton, getTimeoutDefault()).click();
        return new ConnectWalletModalPage(this.driver);
    }

    public YourCampDetailPage clickCampButton() {
        this.waitForElementClickable(campButton, getTimeoutDefault())
                .click();
        return new YourCampDetailPage(this.driver);
    }

    public OpponentsCampPage clickOpponentsCampButton() {
        this.waitForElementClickable(opponentsCampButton, getTimeoutDefault())
                .click();
        return new OpponentsCampPage(this.driver);
    }

    public TreasuryDetailViewPage clickTreasuryButton () throws InterruptedException {
        this.waitForElementPresent(By.cssSelector(".loaderContainerDisplayNone"), getTimeoutDefault());
        this.waitForElementClickable(treasuryButton, getTimeoutDefault())
                .click();
        return new TreasuryDetailViewPage(this.driver);
    }

    public BuildingQueueModalPage clickBuildingQueueButton() throws InterruptedException {
        this.waitForElementPresent(By.cssSelector(".loaderContainerDisplayNone"), getTimeoutDefault());
        this.waitForElementClickable(buildingQueueButton, getTimeout5Sec())
                .click();
        Thread.sleep(2000);
        return new BuildingQueueModalPage(driver);
    }

    public TheatreDetailViewPage clickTheatreButton(){
        this.waitForElementPresent(By.cssSelector(".loaderContainerDisplayNone"), getTimeoutDefault());
        this.waitForElementClickable(theatreButton, getTimeout5Sec())
                .click();
        return new TheatreDetailViewPage(driver);
    }
}
