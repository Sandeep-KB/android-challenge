package loktra.com.androidchallenge;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kbsan on 06-Apr-16.
 */
public class CustomRowAdapter extends BaseAdapter {
        Context context;
        List<ListModel> rowItems;

        public CustomRowAdapter(Context context, List<ListModel> items) {
            this.context = context;
            this.rowItems = items;
        }

        /*private view holder class*/
        private class ViewHolder {
           TextView name;
            TextView commit;
            TextView date;
            TextView message;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.commit = (TextView) convertView.findViewById(R.id.commit);
                holder.date = (TextView) convertView.findViewById(R.id.date);
                holder.message = (TextView) convertView.findViewById(R.id.message);

                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListModel rowItem = (ListModel) getItem(position);

            holder.name.setText(rowItem.getName());
            holder.commit.setText(rowItem.getCommit());
            holder.date.setText(rowItem.getDate());
            holder.message.setText(rowItem.getMessage());


            return convertView;
        }

        @Override
        public int getCount() {
            return rowItems.size();
        }

        @Override
        public Object getItem(int position) {
            return rowItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return rowItems.indexOf(getItem(position));
        }
    }

