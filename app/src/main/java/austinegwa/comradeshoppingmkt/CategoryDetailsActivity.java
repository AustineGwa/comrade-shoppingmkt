package austinegwa.comradeshoppingmkt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import austinegwa.comradeshoppingmkt.Interfaces.ItemClickListener;
import austinegwa.comradeshoppingmkt.Model.CategoryDetails;
import austinegwa.comradeshoppingmkt.ViewHolder.DetailsViewHolder;

public class CategoryDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference mRef;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;

    RecyclerView recyclerView;
    String categoryID ="";
    FirebaseRecyclerAdapter<CategoryDetails, DetailsViewHolder> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);

        recyclerView = findViewById(R.id.category_detailslist);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Details");


        //get the intent extra
        if(getIntent() != null){
            categoryID = getIntent().getStringExtra("categoryID");
            if(!categoryID.isEmpty() && categoryID != null){
                loadDetailsList(categoryID);
            }
        }

       /* progressDialog = new ProgressDialog(CategoryDetailsActivity.this);
        progressDialog.setMessage("Loading Data from Firebase Database");
        progressDialog.show();
        progressDialog.dismiss();

        */

            }

    private void loadDetailsList(String categoryID) {

        adapter = new FirebaseRecyclerAdapter<CategoryDetails, DetailsViewHolder>(
                CategoryDetails.class,
                R.layout.category_details_row,
                DetailsViewHolder.class,
                mRef //.orderByChild("categoryID").equalTo(categoryID) // select * from details where category id = ...
        ) {
            @Override
            protected void populateViewHolder(DetailsViewHolder viewHolder, CategoryDetails model, int position) {
                viewHolder.details_name.setText(model.getDetailsName());
                viewHolder.details_description.setText(model.getDetailsDescription());
                Picasso.with(getBaseContext()).load(model.getDetailsimage()).fit().placeholder(R.drawable.place_holder).into(viewHolder.details_image);

                final CategoryDetails local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                       Intent i = new Intent(CategoryDetailsActivity.this, singleItem.class) ;
                       i.putExtra("ID", adapter.getRef(position).getKey());
                       startActivity(i);
                    }
                });
            }
        };
        Log.d("TAG",""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);
    }
}
