package barqsoft.footballscores;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.GeneralSwipeAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/9/24
 * Email: vhly@163.com
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Activity mActivity;

    public MainActivityTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        mActivity = getActivity();
    }

    public void testPagerSwipe(){
        ViewInteraction interaction = Espresso.onView(ViewMatchers.withId(R.id.pager));

        interaction.perform(ViewActions.swipeRight());
    }



}
