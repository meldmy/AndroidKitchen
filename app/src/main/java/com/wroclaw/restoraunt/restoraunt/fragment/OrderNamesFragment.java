package com.wroclaw.restoraunt.restoraunt.fragment;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.wroclaw.restoraunt.restoraunt.R;
import com.wroclaw.restoraunt.restoraunt.activity.MainActivity;
import com.wroclaw.restoraunt.restoraunt.activity.VerticalDetailsActivity;

import java.util.ArrayList;

/**
 * @author Dmytro Melnychuk
 */
public class OrderNamesFragment extends ListFragment {
    private boolean isHorizontalDetailsActivity;
    private int selectedOrderNamePosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Populate list with our static array of titles.
        MainActivity activity = getMainActivity();
        setListAdapter(new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_activated_1, activity.getOrderNames()));

        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        View detailsFrame = activity.findViewById(R.id.order_description);
        isHorizontalDetailsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;


        if (savedInstanceState != null) {
            // Restore last state for checked position.
            selectedOrderNamePosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (isHorizontalDetailsActivity) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(selectedOrderNamePosition);
            setUpDeleteSelectedDishButton();
        }

    }

    private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    private void setUpDeleteSelectedDishButton() {
        Button deleteButton = (Button) getMainActivity().findViewById(R.id.deleteOrderButton);
        deleteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity activity = getMainActivity();
                activity.removeOrder(selectedOrderNamePosition);
                startMainActivity();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", selectedOrderNamePosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        MainActivity activity = getMainActivity();
        View detailsFrame = activity.findViewById(R.id.order_description);
        isHorizontalDetailsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;
        showDetails(position);
    }

    private void startMainActivity() {
        MainActivity activity = getMainActivity();
        activity.finish();
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    void showDetails(int index) {
        selectedOrderNamePosition = index;

        if (isHorizontalDetailsActivity) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            DetailsFragment details = (DetailsFragment) getFragmentManager().findFragmentById(R.id.order_description);
            if (details == null || details.getShownIndex() != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.order_description, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            ArrayList<String> descriptions = new ArrayList<String>((getMainActivity()).getDescriptions());
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            Intent intent = new Intent();
            intent.setClass(getActivity(), VerticalDetailsActivity.class);
            intent.putExtra("index", index);
            intent.putStringArrayListExtra("descriptions", descriptions);
            startActivity(intent);
        }
    }
}
