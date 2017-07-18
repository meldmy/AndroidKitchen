package com.wroclaw.restoraunt.restoraunt.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import com.wroclaw.restoraunt.restoraunt.R;
import com.wroclaw.restoraunt.restoraunt.Shakespeare;
import com.wroclaw.restoraunt.restoraunt.activity.VerticalDetailsActivity;
import com.wroclaw.restoraunt.restoraunt.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmytro Melnychuk
 */
public class DetailsFragment extends Fragment {

    public static final String INDEX = "index";
    public static final String DESCRIPTIONS = "descriptions";

    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt(INDEX, index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt(INDEX, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }
        if (getActivity() instanceof VerticalDetailsActivity) {
            TextView text = (TextView) getActivity().findViewById(R.id.description_form);
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    4, getActivity().getResources().getDisplayMetrics());
            text.setPadding(padding, padding, padding, padding);

            List<String> descriptions = getActivity().getIntent().getExtras().getStringArrayList(DESCRIPTIONS);
            String orderDescription = descriptions.get(getShownIndex());
            text.setText(orderDescription);

            setUpDeleteSelectedDishButton();

            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            ScrollView scroller = new ScrollView(getActivity());
            TextView text = new TextView(getActivity());
            int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    4, getActivity().getResources().getDisplayMetrics());
            text.setPadding(padding, padding, padding, padding);
            scroller.addView(text);
            text.setText(Shakespeare.DIALOGUE[getShownIndex()]);
            List<String> descriptions;
            if (getActivity() instanceof MainActivity) {
                descriptions = ((MainActivity) getActivity()).getDescriptions();
            } else {
                descriptions = getActivity().getIntent().getExtras().getStringArrayList(DESCRIPTIONS);
            }
            String orderDescription = descriptions.get(getShownIndex());
            text.setText(orderDescription);


            return scroller;

        }
    }

    private void setUpDeleteSelectedDishButton() {
        final Button deleteButton = (Button) getActivity().findViewById(R.id.detailsDeleteOrderButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> descriptions = getActivity().getIntent().getExtras().getStringArrayList(DESCRIPTIONS);
                descriptions.remove(getShownIndex());
                startMainActivity(descriptions);
            }
        });
    }

    private void startMainActivity(ArrayList<String> descriptions) {
        VerticalDetailsActivity activity = (VerticalDetailsActivity) getActivity();
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("deletedOrder", getShownIndex());
        intent.putStringArrayListExtra(DESCRIPTIONS, descriptions);
        activity.startActivity(intent);
        activity.finish();
    }
}
