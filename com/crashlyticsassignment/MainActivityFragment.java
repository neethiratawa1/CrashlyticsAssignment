package activities.twitter.com.crashlyticsassignment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import 	android.content.Intent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import 	java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import 	java.util.Date;
import java.text.DateFormat;
import 	java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IAsynchronousTask{
    DownloadAsyncTask asyncTask;
  public static ArrayList<HashMap<String, String>> completeUserList = new ArrayList<HashMap<String, String>>();
    ListView lv;


    public MainActivityFragment() {


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
         lv=(ListView)view.findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long id) {


                Intent in1 = new Intent(getActivity(),DetailActivity.class);
                in1.putExtra("position", String.valueOf(position));
                startActivity(in1);
            }
        });
        LoadInformation();
return view;
    }

    @Override
    public void hideProgressLoader() {

    }

    @Override
    public String doBackgroundPorcess() {
    if(CommonConstants.isOnline(getActivity())){
        String response= CommonConstants.openHttpConnection(CommonConstants.githubAPI);
        try {
            JSONArray jsonarray = new JSONArray(response);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String title = jsonobject.getString("title");
                String url=jsonobject.getString("url");
                String created_at=jsonobject.getString("created_at");
                String updated_at=jsonobject.getString("updated_at");
                String body = jsonobject.getString("body");
                HashMap<String, String> userList = new HashMap<String, String>();
                userList.put("url", url);
                userList.put("title",title );
                userList.put("created_at", created_at);
                userList.put("updated_at", updated_at);
                userList.put("body",body);
                completeUserList.add(userList);
            }



        }

        catch (JSONException e){

        }
        Log.d("response",response);

    }
        return null;
    }

    @Override
    public void processDataAfterDownload(String data) {
        ListAdapter adapter=new SimpleAdapter(getActivity(), completeUserList,
                R.layout.list_row, new String[] {"title","body"}, new int[] { R.id.issueTitle,
                R.id.issueBody});
        lv.setAdapter(adapter);
    }

    @Override
    public void showProgressLoader() {

    }
    private void LoadInformation() {
        if (asyncTask != null)
            asyncTask.cancel(true);
        asyncTask = new DownloadAsyncTask(this);
        asyncTask.execute();
    }
//    protected ArrayList<HashMap<String, String>> setListOrderByName(final ArrayList<HashMap<String, String>> menuItems2) {
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US);
//        Date  date=null;
//        ArrayList<Date> dates=new ArrayList<Date>();
//        for(int i=0;i<menuItems2.size();i++){
//            date = formatter.parse(menuItems2.get(i).get("updated_at").toString());
//            date = formatter.parse(menuItems2.get(i).get("updated_at").toString());
//             dates.add(date);
//        }
//
//
//
//
//        Collections.sort(dates, new Comparator<HashMap<String, String>>() {
//            public int compare(Date mapping1,
//                              Date mapping2) {
//
//            Date date1=null,date2=null;
//                try {
//                     date1 =mapping1;
//                     date2 = mapping2;
//                }
//                catch (ParseException e){
//                    Log.d("exception",e.getMessage());
//
//                }
//
//                if(date1.after(date2)){
//                    String s= mapping1.get("updated_at");
//                    return Integer.parseInt(s);
//                }else{
//                    String b= mapping2.get("updated_at");
//                    return Integer.parseInt(b);
//                }
//            }
//        });
//
//        return menuItems2;
//    }
}
