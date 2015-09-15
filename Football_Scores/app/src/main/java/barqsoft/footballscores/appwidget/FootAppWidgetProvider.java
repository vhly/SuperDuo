package barqsoft.footballscores.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import barqsoft.footballscores.R;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/9/8
 * Email: vhly@163.com
 */

/**
 * AppWidget Provider class.
 */
public class FootAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_foot);

        appWidgetManager.updateAppWidget(appWidgetIds, views);

    }
}
