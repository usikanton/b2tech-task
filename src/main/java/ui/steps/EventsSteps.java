package ui.steps;

import com.google.inject.Inject;
import ui.pages.EventsPage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EventsSteps {

    @Inject
    private EventsPage eventsPage;

    public void getEventsByOdds(double lowerRangeValue, double higherRangeValue) {
        eventsPage.isMenuDisplayed();
        eventsPage.isOddsTableDisplayed();
        eventsPage.verifyLoaderDisappeared();
        List<String> events = eventsPage.getEventsNamesByOddsRange(lowerRangeValue, higherRangeValue);
        assertEventsAndPrint(events);
    }

    private void assertEventsAndPrint(List<String> events) {
        assertThat(events).hasSizeGreaterThan(0);
        final int[] counter = {1};
        events.forEach(event -> {
            System.out.printf("\nEvent %s by selected odds: \n %s \n\n", counter[0], event.replace("\n", " vs. "));
            counter[0]++;
        });
    }
}
