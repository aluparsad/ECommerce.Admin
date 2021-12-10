package com.icmi.ecommerceadmin.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.icmi.ecommerceadmin.Model.Items;
import com.icmi.ecommerceadmin.Model.Product;
import com.icmi.ecommerceadmin.R;
import com.icmi.ecommerceadmin.Utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddProductContract.view {
    private AddProductContract.Presenter presenter;
    //private LottieAnimationView lottieAnimationView;

    private final int REQUEST_CODE = 1;
    private ImageButton imgBtn;
    private EditText priceET;
    private EditText nameET;
    private Spinner category;
    private Button submitBtn;
    private Product product;
    private List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();

        imgBtn.setOnClickListener(v -> {
            Intent i = new Intent();
            i.setType("image/*");
            i.setAction(Intent.ACTION_GET_CONTENT);
            i.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(i, "fotu chugle 2 3"), REQUEST_CODE);
        });


        submitBtn.setOnClickListener(v -> {
            ShowLoading();
            presenter.RegisterProduct(getProduct(), images);
        });

    }

    private void initializeView() {
        presenter = new AddProductPresenter(this);
        product = new Product();
        //lottieAnimationView = findViewById(R.id.animationView);
        submitBtn = findViewById(R.id.submitBtn);
        imgBtn = findViewById(R.id.imageButton);
        category = (Spinner) findViewById(R.id.spinner_category);
        nameET = findViewById(R.id.itemnameinput);
        priceET = findViewById(R.id.itempriceinput);

        images = new ArrayList<>();
        ArrayAdapter<CharSequence> aa = ArrayAdapter.createFromResource(this, R.array.category, R.layout.support_simple_spinner_dropdown_item);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(aa);
    }

    private Product getProduct() {
        Log.d("Spinner", category.getSelectedItem().toString());
        product.setCategory(Constants.Items.valueOf(category.getSelectedItem().toString())); //(Items) category.getSelectedItem());
        product.setItemName(nameET.getText().toString());
        product.setItemPrice(priceET.getText().toString());
        return product;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            assert data != null;
            if (data.getData() != null) {

                Uri u = data.getData();

                Picasso.get().load(u).into(imgBtn);

                Log.d("imgs", "onActivityResult 1: " + u);
                product.setItemImage(data.getData().toString());

            } else {
                ClipData u = data.getClipData();

                Uri uri = u.getItemAt(data.getClipData().getItemCount() - 1).getUri();

                Picasso.get()
                        .load(uri)
                        .into(imgBtn);

                product.setItemImage(uri.toString());

//                List<Uri> bnm = new ArrayList<>();

                for (int i = 0; i < u.getItemCount(); i++) {

                    if( !   uri.equals(u.getItemAt(i).getUri())){
                        images.add(u.getItemAt(i).getUri().toString());
                    }
                }

//                    Log.d("URI", "---> " + u.getItemCount());
//                    if (!bnm.contains(u.getItemAt(i).getUri()))
//                        bnm.add(u.getItemAt(i).getUri());
//
//
//                    images.add(u.getItemAt(i).getUri().toString());
//                }

//                Log.d("URI", "--->1 " + bnm.size());

                Log.d("imgs", "onActivityResult: " + u.getItemAt(u.getItemCount() - 1).getUri());
            }


        }

    }

    @Override
    public void ShowLoading() {
//        lottieAnimationView.setVisibility(View.VISIBLE);
//        lottieAnimationView.setAnimation(R.raw.loading_json);
//        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);
//        lottieAnimationView.playAnimation();
    }

    @Override
    public void HideLoading() {
//        lottieAnimationView.setVisibility(View.GONE);
//        lottieAnimationView.pauseAnimation();
    }

    @Override
    public void OnSuccess() {
        HideLoading();
        Toast.makeText(this, "Successfull", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnFailure(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


}