package com.hokagelab.www.yodu.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hokagelab.www.yodu.R;
import com.hokagelab.www.yodu.util.api.BaseApiService;
import com.hokagelab.www.yodu.util.api.UtilsApi;


import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahMatkulActivity2 extends AppCompatActivity {

    @BindView(R.id.etNamaDosen)
    EditText etNamaDosen;
    @BindView(R.id.etNamaMatkul)
    EditText etNamaMatkul;
    @BindView(R.id.btnSimpanMatkul)
    Button btnSimpanMatkul;
    ProgressDialog loading;

    BaseApiService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matkul_2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah vocabulary");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnSimpanMatkul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestSimpanMatkul();
            }
        });
    }

    private void requestSimpanMatkul(){
        loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);

        mApiService.simpanMatkulRequest(etNamaDosen.getText().toString(), etNamaMatkul.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            Toast.makeText(mContext, "Data Berhasil Ditambahkan", Toast.LENGTH_LONG).show();
                        } else {
                            loading.dismiss();
                            Toast.makeText(mContext, "Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        loading.dismiss();
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
