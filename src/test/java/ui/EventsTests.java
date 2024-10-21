package ui;

import com.google.inject.Inject;
import org.testng.annotations.Test;
import ui.steps.EventsSteps;

public class EventsTests extends BaseUITest {

    @Inject
    private EventsSteps eventsSteps;

    @Test
    public void getEventsByOddsRangeTest() {
        eventsSteps.getEventsByOdds(1.5, 3.34);
    }
}
