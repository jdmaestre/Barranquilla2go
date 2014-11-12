package com.jdmaestre.uninorte.barranquilla2go;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jose on 05/11/2014.
 */
public class RestauranteMenuFragment extends Fragment {

    ExpandableListAdapter mlistAdapter;
    ExpandableListView mexpListView;
    List<String> mlistDataHeader;
    HashMap<String, List<String>> mlistDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_restaurantes, container, false);
        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();

        // get the listview
        mexpListView = (ExpandableListView) getView().findViewById(R.id.menuExpandableListView);
        // preparing list data
        prepareListData();
        mlistAdapter = new ExpandableListAdapter(getActivity(), mlistDataHeader, mlistDataChild);
        // setting list adapter
        mexpListView.setAdapter(mlistAdapter);

    }

    private void prepareListData() {
        mlistDataHeader = new ArrayList<String>();
        mlistDataChild = new HashMap<String, List<String>>();

        // Adding child data
        mlistDataHeader.add("Top 250");
        mlistDataHeader.add("Now Showing");
        mlistDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        mlistDataChild.put(mlistDataHeader.get(0), top250); // Header, Child data
        mlistDataChild.put(mlistDataHeader.get(1), nowShowing);
        mlistDataChild.put(mlistDataHeader.get(2), comingSoon);
    }

}

 class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap< String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.menuListItem);
        TextView txtListChildReview = (TextView) convertView
                .findViewById(R.id.menuListItemReview);
        ImageView imgView = (ImageView) convertView.findViewById(R.id.menuListImage);


        Picasso.with(this._context).load("http://4.bp.blogspot.com/_iYKXq-IhoiU/TO-ew5vnJcI/AAAAAAAAAHA/MAE6VbuM/s400/hamburguea.bmp").resize(60,60).into(imgView);

        imgView.setBackgroundColor(Color.BLACK);
        txtListChild.setText(childText);
        txtListChildReview.setText("Review del item en esta posición");


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.menuListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
