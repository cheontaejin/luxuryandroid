package com.example.luxuryshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private List<ProductDto> list = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        Button btnInsertForm = findViewById(R.id.btnInsertForm);
        Button btnMain = findViewById(R.id.btnCancel);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

//                intent.putExtra("productCd", list.get(i).getProductCd());
                intent.putExtra("productName", list.get(i).getProductName());
//                intent.putExtra("barCode", list.get(i).getBarCode());
//                intent.putExtra("won_Amt", list.get(i).getWon_Amt());
//                intent.putExtra("mae_Amt", list.get(i).getMae_Amt());
//                intent.putExtra("useYN", list.get(i).getUseYN());

                startActivity(intent);
            }
        });

        btnInsertForm.setOnClickListener(btnListener);
        btnMain.setOnClickListener(btnListener);

        callMovieList();
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnInsertForm: // 등록 폼으로 이동
                    Intent insertForm = new Intent(getApplicationContext(), InsertActivity.class);
                    startActivity(insertForm);
                    break;
                case R.id.btnCancel: // 메인으로 이동
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    break;
            }
        }
    };

    private void callMovieList() {
        ProductService service = RetrofitClient.getClient().create(ProductService.class);
        Call<List<ProductDto>> call = service.listProduct();
        call.enqueue(mRetrofitCallback);
    }

    // 통신 요청 및 응답 콜백
    private final Callback<List<ProductDto>> mRetrofitCallback = new Callback<List<ProductDto>>() {

        @Override
        public void onResponse(@NonNull Call<List<ProductDto>> call, Response<List<ProductDto>> response) {
            if (!response.isSuccessful()) {
                Log.d(TAG, "msg : " + response.code());
                return;
            }

            // 정상 응답
            list = response.body();
            listView.setAdapter(new ListViewAdapter(ListActivity.this, R.layout.activity_listview_item, list));
        }

        @Override
        public void onFailure(@NonNull Call<List<ProductDto>> call, Throwable t) {
            Log.d(TAG, "Fail msg : " + t.getMessage());
        }
    };
}
