package ui.pages;

import java.util.List;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class EventsPage {

    private static final String MENU_ACCORDEON = "#menu";
    private static final String ODDS_TABLE = "//table[child::thead]";
    private static final String LOADER = "//div[@auloading]";
    private static final String EVENTS_BY_RANGE = "//td[@class='odds-box']//span[text()>%s and text()<%s]/ancestor::tr//div[@id]";

    public void isMenuDisplayed() {
        $(MENU_ACCORDEON).shouldBe(interactable);
    }

    public void isOddsTableDisplayed() {
        $x(ODDS_TABLE).shouldBe(visible);
    }

    public void verifyLoaderDisappeared() {
        $x(LOADER).shouldNotBe(visible);
    }

    public List<String> getEventsNamesByOddsRange(double lowerRangeValue, double higherRangeValue) {
        $$x(String.format(EVENTS_BY_RANGE, lowerRangeValue, higherRangeValue)).last().shouldBe(visible);
        return $$x(String.format(EVENTS_BY_RANGE, lowerRangeValue, higherRangeValue)).texts();
    }
}
