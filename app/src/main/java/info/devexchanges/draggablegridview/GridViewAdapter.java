package info.devexchanges.draggablegridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.askerov.dynamicgrid.BaseDynamicGridAdapter;

import java.util.List;

public class GridViewAdapter extends BaseDynamicGridAdapter {

    public GridViewAdapter(Context context, List<?> items, int columnCount) {
        super(context, items, columnCount);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.build(getItem(position).toString());
        return convertView;
    }

    private class ViewHolder {
        private TextView letterText;

        private ViewHolder(View view) {
            letterText = (TextView) view.findViewById(R.id.text);
        }

        void build(String title) {
            letterText.setText(title);
        }
    }
}
