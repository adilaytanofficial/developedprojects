package com.adilaytan.medicaldictionarypro;


import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.adilaytan.medicaldictionarypro.Adapters.MainTermAdapter;
import com.adilaytan.medicaldictionarypro.Adapters.SubListAdapter;
import com.adilaytan.medicaldictionarypro.Adapters.ViewPagerAdapter;
import com.adilaytan.medicaldictionarypro.Async.ContentASYNC;
import com.adilaytan.medicaldictionarypro.Classes.Class;
import com.adilaytan.medicaldictionarypro.DBHelpers.DatabaseHelper;
import com.adilaytan.medicaldictionarypro.DBHelpers.SubDatabaseHelper;

import java.io.Serializable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Serializable {

    private TabLayout.Tab main_words, sub_term, search_content;

    public String araniyor,theme_name;

    private Class Sınıf;

    private boolean press_twice;

    public SearchView searchView;
    public ViewPagerAdapter adapter;
    private ViewPager viewPager;

    Toolbar toolbar;
    ContentASYNC contentASYNC;


    ////////////////////////////////////////////////////////////
    public SubListAdapter subAdapter;
    public MainTermAdapter mainTermAdapter;

    public ArrayList main_list = new ArrayList();
    ArrayList parentHeader = new ArrayList();

    /////////////////////////////////////////////////////////

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Sınıf = new Class();

        Sınıf.setProgramThemeNoAction(this);



        setContentView(R.layout.main_screen);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.search_word));
        toolbar.setSubtitle(getResources().getString(R.string.welcome));
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        if (savedInstanceState != null)
        {
            Sınıf.SetLanguage(this);
            toolbar.setTitle(getResources().getString(R.string.search_word));
            toolbar.setSubtitle(getResources().getString(R.string.welcome));
        }

        DefLists();
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        araniyor = "";

        main_words = tabLayout.newTab();
        sub_term = tabLayout.newTab();
        search_content = tabLayout.newTab();

        theme_name = Sınıf.getThemeName(this);

        UpdateTABTheme(theme_name);

        tabLayout.addTab(main_words, 0);
        tabLayout.addTab(sub_term, 1);
        tabLayout.addTab(search_content, 2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new ViewPagerAdapter (getSupportFragmentManager(), tabLayout.getTabCount(), tabLayout);

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabSelected(TabLayout.Tab tab) {

                ToolbarUpdate(tab.getPosition(),toolbar);

                if (searchView != null)
                {
                    if (searchView.getQuery().toString().isEmpty() == true)
                    {
                        UpdateFragments(getItem());
                    }
                    else
                    {
                        SearchFragments(getItem());
                    }
                }

                viewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {

            }

            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        FloatingActionButton kelimekaydetme = (FloatingActionButton) findViewById(R.id.fab_kelimeekle);


        kelimekaydetme.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent cagir = new Intent("com.adilaytan.medicaldictionarypro.termsprocess.SAVETERM");
                startActivity(cagir);
            }
        });

         }



    private int getItem() {
        return viewPager.getCurrentItem();
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            araniyor = "";
            if (press_twice == true) {
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
            }
            press_twice = true;
            Toast.makeText(this, getResources().getString(R.string.for_exit), Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    press_twice = false;
                }
            }, 2000);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            FormAc("com.adilaytan.medicaldictionarypro.leftmenu.LASTSEARCHES");
        } else if (id == R.id.nav_saved) {
            FormAc("com.adilaytan.medicaldictionarypro.leftmenu.SAVEDTERM");
        } else if (id == R.id.nav_favourite) {
            FormAc("com.adilaytan.medicaldictionarypro.leftmenu.FAVS");
        } else if (id == R.id.nav_destekol) {
            RateUs();
        } else if (id == R.id.nav_hakkinda) {
            FormAc("com.adilaytan.medicaldictionarypro.theothers.ABOUTUS");
        } else if (id == R.id.nav_ayarlar) {
            FormAc("com.adilaytan.medicaldictionarypro.settings.SETTINGSSCREEN");
        }
        else if (id == R.id.nav_email) {
            Email();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void FormAc(String intent) {
        Intent baslat = new Intent(intent);
        startActivity(baslat);
    }

    private void Email(){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","info.adilaytan@gmail.com", null));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject_email));
        intent.putExtra(Intent.EXTRA_TEXT, getResources().getString(R.string.content_mail));
        startActivity(intent);
    }

    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    protected void onResume() {
        Sınıf.SetLanguage(this);
        super.onResume();
    }

    private void RateUs() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + getPackageName()));
        if (!MyStartActivity(intent)) {
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        searchView = (SearchView) menu.findItem(R.id.search_button).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        searchView.setMaxWidth(Integer.MAX_VALUE);

        final ImageView hinticon;

        EditText se = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        hinticon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);

        hinticon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                searchView.setQuery("",true);
                araniyor = "";
            }
        });

        hinticon.setImageDrawable(getResources().getDrawable(R.drawable.searchview_close));
        se.setTextColor(Color.WHITE);
        se.setHintTextColor(Color.WHITE);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            public boolean onQueryTextSubmit(String query) {
                araniyor = query.toString();
                return true;
            }

            public boolean onQueryTextChange(String newText) {
                araniyor = newText.toString();
                int current = getItem();
                switch (current) {
                    case 0:
                        if (newText.length() > 0)
                        {
                            mainTermAdapter.filterData(newText.toString());
                        }
                        else
                        {
                            mainTermAdapter.filterData("");
                        }
                        break;
                    case 1:
                        if (newText.length() > 0)
                        {
                            subAdapter.filterData(newText.toString());
                        }
                        else
                        {
                            subAdapter.filterData("");
                        }
                        break;
                    case 2:
                        contentASYNC = new ContentASYNC(MainActivity.this);
                        contentASYNC.execute("search",newText.toString());
                        break;
                }
                return true;
            }
        });

        return true;
    }


    private void DefLists() {
        DatabaseHelper myDB = new DatabaseHelper(this);
        SubDatabaseHelper sub = new SubDatabaseHelper(this);

        main_list = myDB.MainList();
        parentHeader = sub.parentHeader();

        mainTermAdapter = new MainTermAdapter(MainActivity.this, main_list);
        subAdapter = new SubListAdapter(MainActivity.this,parentHeader);
    }


    private void ToolbarUpdate(int position,Toolbar toolbar)
    {
        switch (position)
        {
            case 0:
                toolbar.setTitle(getResources().getString(R.string.search_word));
                break;
            case 1:
                toolbar.setTitle(getResources().getString(R.string.search_expand));
                break;
            case 2:
                toolbar.setTitle(getResources().getString(R.string.search_content));
                break;
            default:
                toolbar.setTitle(getResources().getString(R.string.search_word));
                break;
        }
    }

    private void UpdateFragments(int position)
    {
        switch (position)
        {
            case 0:
                mainTermAdapter.filterData("");
                break;
            case 1:
                subAdapter.filterData("");
                break;
            case 2:
                contentASYNC = new ContentASYNC(MainActivity.this);
                contentASYNC.execute("not_yet");
                break;
        }
    }

    private void SearchFragments(int position) {
        switch (position)
        {
            case 0:
                mainTermAdapter.filterData(searchView.getQuery().toString());
                break;
            case 1:
                subAdapter.filterData(searchView.getQuery().toString());
                break;
            case 2:
                contentASYNC = new ContentASYNC(MainActivity.this);
                contentASYNC.execute("search",searchView.getQuery().toString());
                break;
        }
    }



    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Sınıf.SetLanguage(this);
    }

    private void UpdateTABTheme(String name_theme)
    {
        switch (name_theme) {
            case "2131361933":
                main_words.setIcon(R.drawable.tab1_select_default);
                sub_term.setIcon(R.drawable.tab2_select_default);
                search_content.setIcon(R.drawable.tab3_select_default);
                break;
            case "2131362063":
                main_words.setIcon(R.drawable.tab1_select_green);
                sub_term.setIcon(R.drawable.tab2_select_green);
                search_content.setIcon(R.drawable.tab3_select_green);
                break;
            case "2131361981":
                main_words.setIcon(R.drawable.tab1_select_black);
                sub_term.setIcon(R.drawable.tab2_select_black);
                search_content.setIcon(R.drawable.tab3_select_black);
                break;
            case "2131362065":
                main_words.setIcon(R.drawable.tab1_select_red);
                sub_term.setIcon(R.drawable.tab2_select_red);
                search_content.setIcon(R.drawable.tab3_select_red);
                break;
            case "2131361983":
                main_words.setIcon(R.drawable.tab1_select_pink);
                sub_term.setIcon(R.drawable.tab2_select_pink);
                search_content.setIcon(R.drawable.tab3_select_pink);
                break;
        }
    }
}
