package info.devexchanges.draggablegridview;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import org.askerov.dynamicgrid.DynamicGridView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private DynamicGridView gridView;
    private String[] vietnameseAlphabets = {"A", "Ă", "Â", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "Ô", "Ơ", "P", "Q", "R", "S", "T", "U", "V", "X", "Y"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        gridView = (DynamicGridView) findViewById(R.id.dynamic_grid);

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(vietnameseAlphabets));

        GridViewAdapter gridViewAdpter = new GridViewAdapter(this, arrayList, 3);
        gridView.setAdapter(gridViewAdpter);

        //Active dragging mode when long click at each Grid view item
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                gridView.startEditMode(position);

                return true;
            }
        });

        //Handling click event of each Grid view item
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Item information")
                        .setMessage("You clicked at position: " + position +"\n"
                                + "The letter is: " + parent.getItemAtPosition(position).toString())
                        .setPositiveButton(android.R.string.yes, null)

                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (gridView.isEditMode()) {
            gridView.stopEditMode();
        } else {
            super.onBackPressed();
        }
    }
}