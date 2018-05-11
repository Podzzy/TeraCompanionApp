package com.jjpods.teracompanionapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.jjpods.teracompanionapp.Adapter.CustomExpandableListAdapter;
import com.jjpods.teracompanionapp.Helper.FragmentNavigationManager;
import com.jjpods.teracompanionapp.Interface.NavigationManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Broker extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavMenu;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView eListView;
    private ExpandableListAdapter adapter;
    private List<String> lstTitle;
    private Map<String,List<String>> lstChild;
    private NavigationManager navigationManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker);

        mDrawerLayout = findViewById(R.id.drawerLayout);
        mActivityTitle = getTitle().toString();
        eListView = findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getmInstance(this);

        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.navigation_header,null,false);
        eListView.addHeaderView(listHeaderView);

        genData();

        addDrawerItem();
        setupDrawer();

        /*mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

    }

    private void addDrawerItem(){
        adapter = new CustomExpandableListAdapter(this, lstTitle,lstChild);
        eListView.setAdapter(adapter);
        eListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(lstTitle.get(groupPosition).toString());
            }
        });

        eListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                getSupportActionBar().setTitle("Brokerage");
            }
        });

        eListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                String selectedItem = ((List) (lstChild.get(lstTitle.get(groupPosition)))).get(childPosition).toString();
                getSupportActionBar().setTitle(selectedItem);

                if(items[0].equals(lstTitle.get(groupPosition)))
                    navigationManager.showFragment(selectedItem);
                else
                    throw new IllegalArgumentException("Not supported fragment");

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    private void setupDrawer() {

    }

    private void genData() {
        List<String> title = Arrays.asList("Enchanting","Weapons","Armor","Etching");
        List<String> childItem = Arrays.asList("1","2","3");

        lstChild = new TreeMap<>();
        lstChild.put(title.get(0),childItem);
        lstChild.put(title.get(1),childItem);
        lstChild.put(title.get(2),childItem);
        lstChild.put(title.get(4),childItem);

        lstTitle = new ArrayList<>(lstChild.keySet());
    }

    private void initItems() {
        items = new String[]{"Enchanting","Weapons","Armor","Etching"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}