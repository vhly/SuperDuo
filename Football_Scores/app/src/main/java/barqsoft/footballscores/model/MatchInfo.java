package barqsoft.footballscores.model;

import android.database.Cursor;
import barqsoft.footballscores.provider.DatabaseContract;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/5
 * Email: vhly@163.com
 */
public class MatchInfo {

    private String homeName;
    private String awayName;
    private String date;
    private int homeScore = -1;
    private int awayScore = -1;

    public void parseCursor(Cursor cursor){
        if (cursor != null) {
            int index = cursor.getColumnIndex(DatabaseContract.scores_table.HOME_COL);
            if (index > -1){
                homeName = cursor.getString(index);
            }
            index = cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_COL);
            if (index > -1){
                awayName = cursor.getString(index);
            }
            index = cursor.getColumnIndex(DatabaseContract.scores_table.DATE_COL);
            if (index > -1){
                date = cursor.getString(index);
            }
            index = cursor.getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL);
            if (index > -1){
                homeScore = cursor.getInt(index);
            }
            index = cursor.getColumnIndex(DatabaseContract.scores_table.AWAY_GOALS_COL);
            if (index > -1){
                awayScore = cursor.getInt(index);
            }
        }else{
            throw new IllegalArgumentException("Cursor must not be null");
        }
    }

    public String getHomeName() {
        return homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public String getDate() {
        return date;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
}
