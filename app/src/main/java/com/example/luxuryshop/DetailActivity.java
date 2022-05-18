package com.example.luxuryshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private TextView tvProductPrice, tvProductName, tvBarCode, tvWonAmt, tvMaeAmt, tvUseYN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductName = findViewById(R.id.tvProductName);
//        tvBarCode = findViewById(R.id.tvBarCode);
//        tvWonAmt = findViewById(R.id.tvWonAmt);
//        tvMaeAmt = findViewById(R.id.tvMaeAmt);
//        tvUseYN = findViewById(R.id.tvUseYN);

        Button btnUpdateForm = findViewById(R.id.btnUpdateForm);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnUpdateForm.setOnClickListener(btnListener);
        btnDelete.setOnClickListener(btnListener);
        btnCancel.setOnClickListener(btnListener);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        tvProductPrice.setText(String.valueOf(intent.getIntExtra("productPrice", 0)));
        tvProductName.setText(intent.getStringExtra("productName"));
//        tvBarCode.setText(intent.getStringExtra("barCode"));
//        tvWonAmt.setText(String.valueOf(intent.getIntExtra("won_Amt", 0)));
//        tvMaeAmt.setText(String.valueOf(intent.getIntExtra("mae_Amt", 0)));
//        tvUseYN.setText(intent.getStringExtra("useYN"));
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnUpdateForm: // 수정 폼으로 이동
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    intent.putExtra("productPrice", tvProductPrice.getText().toString());
                    intent.putExtra("productName", tvProductName.getText().toString());
//                    intent.putExtra("barCode", tvBarCode.getText().toString());
//                    intent.putExtra("won_Amt", tvWonAmt.getText().toString());
//                    intent.putExtra("mae_Amt", tvMaeAmt.getText().toString());
//                    intent.putExtra("useYN", tvUseYN.getText().toString());
                    startActivity(intent);
                    break;
                case R.id.btnDelete: // 삭제후 목록으로 이동
                    deleteProduct();
                    break;
                case R.id.btnCancel: // 목록으로 이동
                    Intent list = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(list);
                    break;
            }
        }
    };

    public void deleteProduct() {
        String productCd = tvProductPrice.getText().toString();
        ProductService service = RetrofitClient.getClient().create(ProductService.class);
        Call<ProductDto> call = service.deleteProduct(productCd);
        call.enqueue(mRetrofitCallback);
    }

    // 통신 요청 및 응답 콜백
    private final Callback<ProductDto> mRetrofitCallback = new Callback<ProductDto>() {

        @Override
        public void onResponse(@NonNull Call<ProductDto> call, Response<ProductDto> response) {
            // 정상 응답
            Intent Main = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(Main);
            startToast("삭제되었습니다");
        }

        @Override
        public void onFailure(@NonNull Call<ProductDto> call, Throwable t) {
            Log.d(TAG, "Fail msg : " + t.getMessage());
        }
    };

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

