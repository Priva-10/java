package com.example.insti2021;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.insti2021.dao.DataBaseRoom;
import com.example.insti2021.dao.ProductRoomDao;
import com.example.insti2021.databinding.ActivityListProductBinding;
import com.example.insti2021.entity.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private List<Product> products=new ArrayList<>();
    ProductAdapter productAdapter;
    ListView productList;
    FloatingActionButton floatBtnAdd;
    private  ProductRoomDao productRoomDao;



    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Product product = (Product)data.getExtras().get("product");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                products.add(product);
                                runOnUiThread(()->{
                                    productAdapter.notifyDataSetChanged();
                                });

                            }
                        }).start();

                    }
                }
            });
    ActivityResultLauncher<Intent> showProductResultLauncher =registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==Activity.RESULT_OK){
                        Intent intent = result.getData();
                        Product product = (Product) intent.getExtras().get("product");
                        int index = products.indexOf(product);


                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                if(intent.getBooleanExtra("modify",false))
                                {
                                    int index = products.indexOf(product);
                                    products.set(index,product);
                                }else {

                                    products.remove(product);
                                }

                                runOnUiThread(()->{
                                    productAdapter.notifyDataSetChanged();
                                });
                            }
                        }).start();


                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_product);
       productRoomDao = DataBaseRoom.getInstance(getApplicationContext()).productRoomDao();
        productList= findViewById(R.id.product_list);
        floatBtnAdd = findViewById(R.id.float_btn_add);
        generateRoom();
        useCustumAdapter();




        floatBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ProductListActivity.this,AddProductActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Product product= (Product) productList.getItemAtPosition(position);
              Intent intent = new Intent(ProductListActivity.this,ShowProductActivity.class);
              intent.putExtra("product",product);
              showProductResultLauncher.launch(intent);
            }
        });
    }

    public void useCustumAdapter(){

        productAdapter =new ProductAdapter(products,this);
        productList.setAdapter(productAdapter);
    }



    private void generateProducts() {

        List<Product>  products1= new ArrayList<>();
//        products=productDao.findAll();
        products= productRoomDao.findAll();
        if (!products.isEmpty()) {
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy Note4 10", "Samsung Galaxy Note 10", 800000, 100, 10));
            products1.add(new Product("Redmi S11", "Xiaomi Redmi S11", 300000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy Note 10", "Samsung Galaxy Note 10", 800000, 100, 10));
            products1.add(new Product("Redmi S11", "Xiaomi Redmi S11", 300000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));
            products1.add(new Product("Galaxy S21", "Samsung Galaxy S21", 800000, 100, 10));


//            for(int i=0;i<products1.size();i++){
//                productDao.insert(products1.get(i));
//            }

            products= productRoomDao.findAll();
            useCustumAdapter();
        }

    }
    public void generateRoom(){
//
        Thread thread = new Thread(new Runnable() {
           final List<Product>  products1= new ArrayList<>();

            @Override
            public void run() {
                  products1.addAll(productRoomDao.findAll());




                if (products1.isEmpty()) {
                    productRoomDao.insert(new Product("Galaxy S552551", "Samsung Galaxy S21", 800000, 100, 10));
                    productRoomDao.insert(new Product("Galaxy Note554 10", "Samsung Galaxy Note 10", 800000, 100, 10));
                    productRoomDao.insert(new Product("Redmi S15551", "Xiaomi Redmi S11", 300000, 100, 10));
                    productRoomDao.insert(new Product("Galaxy S2555551", "Samsung Galaxy S21", 800000, 100, 10));
                    productRoomDao.insert(new Product("Galaxy S552551", "Samsung Galaxy S21", 800000, 100, 10));
                    productRoomDao.insert(new Product("Galaxy Note554 10", "Samsung Galaxy Note 10", 800000, 100, 10));
                    productRoomDao.insert(new Product("Redmi S15551", "Xiaomi Redmi S11", 300000, 100, 10));
                    productRoomDao.insert(new Product("Galaxy S2555551", "Samsung Galaxy S21", 800000, 100, 10));

                    products1.addAll(productRoomDao.findAll());



                }
                runOnUiThread(()->{
                    products.addAll(products1);
                    productAdapter.notifyDataSetChanged();

                });
            }
        });

        thread.start();
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

//    @Override
//    protected void onRestart() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Product> productList2 = new ArrayList<>();
//                productList2.addAll(productRoomDao.findAll());
//                products.clear();
//                products.addAll(productList2);
//
//                runOnUiThread(()->{
//
//
//                    productAdapter.notifyDataSetChanged();
//
//                });
//
//            }
//        }).start();
//        super.onRestart();
//    }

    private void notifyDelete(Product product){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ProductListActivity.this);
        dialog.setTitle("Supression de produit");
        dialog.setMessage("le produit '"+product.getName()+"' a été supprimé avec succès");
        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



}
