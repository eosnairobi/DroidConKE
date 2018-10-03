package com.kotani.eosaccounts;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kotani.eosaccounts.Api.Api;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        final EditText nameTxt = (EditText) findViewById(R.id.account_name);
        Button getAccount = (Button) findViewById(R.id.get_account);
        final Button getInfo = (Button) findViewById(R.id.server_info);

        //calling the method to display the info
        getInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getInfo();            }
        });
        getAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTxt.getText().toString().trim();
                if(!TextUtils.isEmpty(name)) {
                    getAccount(name);
                }
            }
        });
    }

    private void getAccount(String account_name){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://mainnet.eosnairobi.io/")
                .build();

        Api api = retrofit.create(Api.class);
        Map<String, Object> jsonParams = new ArrayMap<>();
        jsonParams.put("account_name", account_name);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
        Call<ResponseBody> response = api.getAccount( body);
        response.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
            {
                try
                {
                    //get your response....
                    info.setText(rawResponse.body().string());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    try {
                        info.setText("Error Occurred" + e.getMessage() + '\n' + rawResponse.body().string());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable)
            {
                info.setText(throwable.getMessage());
            }
        });

    }

    private void getInfo() {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://mainnet.eosnairobi.io/")
                .build();

        Api api = retrofit.create(Api.class);

        Call<String> call = api.getInfo();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    // todo: do something with the response string
                    info.setText(responseString);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                info.setText(t.getMessage());
            }
        });
    }
}
