package loktra.com.androidchallenge;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.eclipse.egit.github.core.RepositoryCommit;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.service.CommitService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity {
    ListView listView;
    List<ListModel> rowItems;
    CustomRowAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("Sandeep", "app started");
        listView = (ListView) findViewById(R.id.listView);
        rowItems = new ArrayList<ListModel>();
        adapter = new CustomRowAdapter(this, rowItems);
        GetGitTask task = new GetGitTask();
        task.execute();
        listView.setAdapter(adapter);


       // listView.setOnItemClickListener(this);
    }

  private class GetGitTask extends AsyncTask<String, Void, String> {

      ProgressDialog progressDialog;
      //declare other objects as per your need
      @Override
      protected void onPreExecute()
      {
          progressDialog= ProgressDialog.show(MainActivity.this, "Loading","Loading git commits", true);
      };


      @Override
      protected void onPostExecute(String result)
      {
          super.onPostExecute(result);
          progressDialog.dismiss();
          if(rowItems.size()==0) {
              Log.d("Sandeep", "Making toast");
              Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
          }
          adapter.notifyDataSetChanged();
      };

      @Override
      protected String doInBackground(String... params) {

          try {
              final RepositoryId repo = new RepositoryId("rails", "rails");
              final CommitService service = new CommitService();
               int cnt = 0;
              int pages = 1;
              for (Collection<RepositoryCommit> commits : service.pageCommits(repo,
                      25)) {
                  Log.d("Sandeep","Commit Page " + pages++);
                  for (RepositoryCommit commit : commits) {
                      String sha = commit.getSha().substring(0, 7);
                      String author = commit.getCommit().getAuthor().getName();
                      Date date = commit.getCommit().getAuthor().getDate();
                      String message = commit.getCommit().getMessage();
                      ListModel item = new ListModel(author,sha,date.toString(),message);
                      rowItems.add(item);
                      Log.d("Sandeep", "Item="+item.toString());
                      if(++cnt>25)
                          return null;

                  }

              }

          }catch (Exception  e){
              e.printStackTrace();

              Log.e("Sandeep",e.getMessage());
                return  e.getMessage();
          }

          return null;
      }

  }
}
