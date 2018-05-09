package austinegwa.comradeshoppingmkt;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.login.LoginManager;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import austinegwa.comradeshoppingmkt.Interfaces.ItemClickListener;
import austinegwa.comradeshoppingmkt.Model.Category;
import austinegwa.comradeshoppingmkt.ViewHolder.MenuViewHolder;


public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     FirebaseDatabase database;
     DatabaseReference category;
    FirebaseAuth mAuth;
     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutmanager;
     StorageReference mStorageRef;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        category = database.getReference("categories");
        mAuth = FirebaseAuth.getInstance();

        recyclerView =  findViewById(R.id.menuList);
        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setHasFixedSize(true);
        loadMenu();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadMenu() {
        adapter = new  FirebaseRecyclerAdapter<Category, MenuViewHolder>(
                Category.class,
                R.layout.category_row,
                MenuViewHolder.class,
                category
        ){
            @Override
            public void populateViewHolder(MenuViewHolder viewHolder, Category model, int position){
                viewHolder.category_name.setText(model.getCategory_name());
                viewHolder.category_description.setText(model.getCategory_description());
                Picasso.with(getBaseContext()).load(model.getCategory_imagelink()).fit().placeholder(R.drawable.place_holder).into(( viewHolder.category_image));

                final Category clickItem= model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //get category id and send to new activity
                        Intent i = new Intent(home.this, CategoryDetailsActivity.class);
                        //get this category ID and pass as intent extra
                        i.putExtra("categoryID", adapter.getRef(position).getKey());
                        startActivity(i);


                    }
                });

            }
        };
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        int id = item.getItemId();

        if (id == R.id.categories) {
            // Handle the camera action

        } else if (id == R.id.sellers) {

        } else if (id == R.id.post) {
           Intent i = new Intent(this, post.class);
           startActivity(i);

        } else if (id == R.id.logout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            updateUi();


        } else if (id == R.id.nav_share) {

        } else if (id == R.id.about) {
            Intent i = new Intent(this, about.class);
            startActivity(i);



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateUi() {
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // Create an alert dialog
            //super.onBackPressed();
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Do you realy want to exit this app?");
            alertDialog.setCancelable(true);
            alertDialog.setMessage("Choose an action");
            alertDialog.setIcon(R.drawable.ic_warning_black_24dp);
            alertDialog.setPositiveButton("YES",new Dialog.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }) ;

            alertDialog.setNegativeButton("NO", new Dialog.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            alertDialog.show();

        }
    }

}
