package pl.kargolek.process;

import pl.kargolek.process.webactions.ItemLevelActions;

/**
 * @author Karol Kuta-Orlowicz
 */
public class ItemLevelService {

    private final ItemLevelActions itemLevelActions;

    public ItemLevelService(ItemLevelActions itemLevelActions) {
        this.itemLevelActions = itemLevelActions;
    }

    public void logItemsLevels(){
        itemLevelActions.getItemsPages()
                .forEach(itemLevelActions::getItemLevel);
        itemLevelActions.logItemsLevelsList();
    }
}
