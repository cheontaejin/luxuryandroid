package com.example.luxuryshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private EditText etProductName, etBarCode, etWon_Amt, etMae_Amt;
    private RadioGroup RgUseYN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        etProductName = findViewById(R.id.etProductName);
//        etBarCode = findViewById(R.id.etBarCode);
//        etWon_Amt = findViewById(R.id.etWonAmt);
//        etMae_Amt = findViewById(R.id.etMaeAmt);
//        RgUseYN = findViewById(R.id.RgUseYN);
//        RadioButton RbUseY = findViewById(R.id.RbUseY);
//        RbUseY.setChecked(true);
        Button btnWrite = findViewById(R.id.btnWrite);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnWrite.setOnClickListener(btnListener);
        btnCancel.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnWrite: // 등록후 목록으로 이동
                    addProduct();
                    break;
                case R.id.btnCancel: // 목록으로 이동
                    Intent Main = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(Main);
                    break;
            }
        }
    };

    public void addProduct() {
        String productName = etProductName.getText().toString();
//        String barCode = etBarCode.getText().toString();
//        String won_Amt = etWon_Amt.getText().toString();
//        String mae_Amt = etMae_Amt.getText().toString();
        int id = RgUseYN.getCheckedRadioButtonId();
        RadioButton rb = findViewById(id);
        String useYN = rb.getText().toString();

        Map<String, String> map = new HashMap<>();
        map.put("productName", productName);
//        map.put("barCode", barCode);
//        map.put("won_Amt", won_Amt);
//        map.put("mae_Amt", mae_Amt);
//        map.put("useYN", useYN);

        ProductService service = RetrofitClient.getClient().create(ProductService.class);
        Call<ProductDto> call = service.addProduct(map);
        call.enqueue(mRetrofitCallback);
    }

    // 통신 요청 및 응답 콜백
    private final Callback<ProductDto> mRetrofitCallback = new Callback<ProductDto>() {

        @Override
        public void onResponse(@NonNull Call<ProductDto> call, Response<ProductDto> response) {
            if (TextUtils.isEmpty(etProductName.getText()) ||
                    TextUtils.isEmpty(etBarCode.getText()) ||
                    TextUtils.isEmpty(etWon_Amt.getText()) ||
                    TextUtils.isEmpty(etMae_Amt.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsertActivity.this);
                AlertDialog dialog = builder.setMessage("공백이 있습니다")
                        .setNegativeButton("확인", null)
                        .create();
                dialog.show();
            } else {

                // 정상 응답
                Intent Main = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(Main);
                startToast("등록되었습니다");
            }
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
