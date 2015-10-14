package barqsoft.footballscores.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class MatchAppWidget extends AppWidgetProvider {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static String dateStr;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Date currentDate = new Date();

        dateStr = format.format(currentDate);

        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.match_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);


        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 998, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        ContentResolver resolver = context.getContentResolver();

        Cursor cursor = resolver.query(
                DatabaseContract.scores_table.buildScoreWithDate(), // uri
                null,  // project
                null,  // selection
                new String[]{dateStr},  // selectionArgs
                null   // orderBy
        );

        if (cursor != null) {

            if (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex(DatabaseContract.scores_table.DATE_COL);
                if (columnIndex != -1) {
                    String string = cursor.getString(columnIndex);
                    if (string != null) {
                        views.setTextViewText(R.id.appwidget_text, string);
                    }
                }
            }

            cursor.close();
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}


