package activities.twitter.com.crashlyticsassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView title,body;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title=(TextView)findViewById(R.id.title);
        body=(TextView)findViewById(R.id.body);
        Bundle bundle = getIntent().getExtras();
        String id=bundle.getString("position");
       title.setText( MainActivityFragment.completeUserList.get(Integer.parseInt(id)).get("title"));
        body.setText( MainActivityFragment.completeUserList.get(Integer.parseInt(id)).get("body"));


    }
}
