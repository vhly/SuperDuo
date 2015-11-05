package barqsoft.footballscores.service;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;
import barqsoft.footballscores.model.MatchInfo;
import barqsoft.footballscores.provider.DatabaseContract;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Widget Service for ListView adapter
 */
public class MyWidgetService extends RemoteViewsService {
    public MyWidgetService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MatchRemoteViewsFactory(getApplicationContext(), intent);
    }

    class MatchRemoteViewsFactory implements RemoteViewsFactory {
        private Context context;
        private Intent intent;

        private List<MatchInfo> matchInfos;

        public MatchRemoteViewsFactory(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        @Override
        public void onCreate() {
            matchInfos = new LinkedList<MatchInfo>();
        }

        @Override
        public void onDataSetChanged() {
            // Load Data to Object once

            ContentResolver contentResolver =
                    context.getContentResolver();

            Uri uri = DatabaseContract.scores_table.buildScoreWithDate();

            Cursor cursor = contentResolver.query(
                    uri,
                    null,
                    null,
                    new String[]{"2015-11-05"},
                    null
            );

            if (cursor != null) {

                matchInfos.clear();

                while (cursor.moveToNext()) {
                    MatchInfo info = new MatchInfo();
                    info.parseCursor(cursor);
                    matchInfos.add(info);
                }

                cursor.close();
            }
        }

        @Override
        public void onDestroy() {
            matchInfos.clear();
            matchInfos = null;
        }

        @Override
        public int getCount() {
            return matchInfos.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {

            Log.d("Widget", "getViewAt(" + position + ")");

            RemoteViews ret =
                    new RemoteViews(
                            context.getPackageName(),
                            R.layout.item_match_widget
                    );

            if (matchInfos != null) {

                MatchInfo info = matchInfos.get(position);

                if (info != null) {
                    String homeName = info.getHomeName();
                    String awayName = info.getAwayName();

                    Log.d("Widget", homeName + " : " + awayName);

                    ret.setTextViewText(R.id.item_match_widget_home_name, homeName);
                    ret.setTextViewText(R.id.item_match_widget_away_name, awayName);

                    int homeScore = info.getHomeScore();
                    int awayScore = info.getAwayScore();

                    if (homeScore > -1 && awayScore > -1) {

                        int directionFromLocale = TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault());

                        StringBuilder sb = new StringBuilder();

                        switch (directionFromLocale) {
                            case ViewCompat.LAYOUT_DIRECTION_LTR:
                                sb.append(homeScore).append(":").append(awayScore);
                                break;
                            case ViewCompat.LAYOUT_DIRECTION_RTL:
                                sb.append(awayScore).append(":").append(homeScore);
                                break;
                        }
                        String str = sb.toString();
                        sb = null;
                        ret.setTextViewText(R.id.item_match_widget_container, str);

                    }
                }
            }

            Bundle extras = new Bundle();
            extras.putInt("position", position);

            Intent intent = new Intent();
            intent.putExtras(extras);



            ret.setOnClickFillInIntent(
                    R.id.item_match_widget_score,
                    intent
            );

            Log.d("Widget", "view ret " + ret);

            return ret;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
