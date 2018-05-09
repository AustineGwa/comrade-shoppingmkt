package austinegwa.comradeshoppingmkt;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class post extends AppCompatActivity {
    TextView item_name, item_category, item_ID, item_description;
    ImageButton pic;
    Button submit;
    Uri uri = null;
    private static final int GALLERY_REQUEST = 2;
    StorageReference mStorage;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Details");
        mStorage = FirebaseStorage.getInstance().getReference();
        item_name = findViewById(R.id.item_name);
        item_category = findViewById(R.id.item_category);
        item_ID = findViewById(R.id.item_ID);
        item_description = findViewById(R.id.item_description);

        pic = findViewById(R.id.itemImage);
        submit = findViewById(R.id.post);

        pic.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
               startGallery();
            }
        });
        /*
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
        */
    }

    public void startGallery(){
        /*
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        // where to get the data
        galleryIntent.setType("Image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
        */

        // Create the Intent for Image Gallery.
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
        startActivityForResult(i, GALLERY_REQUEST);
    }
    public void post(){
        String name = item_name.getText().toString().trim();
        String category = item_category.getText().toString().trim();
        String ID = item_ID.getText().toString().trim();
        String description = item_description.getText().toString().trim();


        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(category) && !TextUtils.isEmpty(ID) && !TextUtils.isEmpty(description)){
            StorageReference filePath = mStorage.child("post_item_images").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost = databaseRef.push();
                    newPost.child("name").setValue(item_name);
                    newPost.child("category").setValue(item_category);
                    newPost.child("ID").setValue(item_ID);
                    newPost.child("description").setValue(item_description);
                    newPost.child("image").setValue((downloadUri).toString());
                    Toast.makeText(post.this, "upload succesfull", Toast.LENGTH_SHORT ).show();

                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /*
        if(requestCode== GALLERY_REQUEST && resultCode == RESULT_OK){
            uri = data.getData();
            pic.setImageURI(uri);
       }
       */
        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK && data != null) {
            // Let's read picked image data - its URI
            Uri pickedImage = data.getData();
            // Let's read picked image path using content resolver
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            // Now we need to set the GUI ImageView data with data read from the picked file.
            //Picasso.with(getBaseContext()).load(imagePath).fit().into(( pic));
            pic.setImageBitmap(BitmapFactory.decodeFile(imagePath));

            // At the end remember to close the cursor or you will end with the RuntimeException!
            cursor.close();
        }
    }



}
