package activities.twitter.com.crashlyticsassignment;
import android.os.AsyncTask;

/**
 * Created by Neethi on 5/21/2016.
 */
public class DownloadAsyncTask extends AsyncTask<Void, Void, String>{
    IAsynchronousTask asynchronousTask;

    public DownloadAsyncTask(IAsynchronousTask activity) {
        this.asynchronousTask = activity;

    }
    @Override
    protected void onPreExecute() {
        if(asynchronousTask!=null)
            asynchronousTask.showProgressLoader();
    }

    @Override
    protected String doInBackground(Void... cap) {
        try{
            if(asynchronousTask!=null)
                return asynchronousTask.doBackgroundPorcess();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String data) {
        if(asynchronousTask!=null){
            asynchronousTask.hideProgressLoader();
            asynchronousTask.processDataAfterDownload(data);
        }
    }
}
