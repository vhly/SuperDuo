package barqsoft.footballscores.service;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class MyWidgetService extends RemoteViewsService {
    public MyWidgetService() {
    }

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MatchRemoteViewsFactory(intent);
    }

    class MatchRemoteViewsFactory implements RemoteViewsFactory {

        private Intent intent;

        public MatchRemoteViewsFactory(Intent intent) {
            this.intent = intent;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
