# DroidConKE
Source code for the DroidConKE 

## Step 1

Create new Android Studio project with an empty activity

## Step 2

Add the following dependencies in the build.grandle(Module:app)

```
// Retrofit

implementation 'com.squareup.retrofit2:retrofit:2.3.0'
implementation 'com.squareup.retrofit2:converter-gson:2.2.0'
implementation 'com.squareup.retrofit2:converter-scalars:2.3.0'
```

## Step 3

Add internet permissions in the AndroidManifest.xml  (just after the manifest file)


```
    <uses-permission android:name="android.permission.INTERNET" />

```

## Step 4

We need to create a package called Api and an java class called Api 

```
    -com.example.peter.droidconpractice
    ---api(package)
    -----Api(java class)

```

## Step 5

We now create the api interface where we can do http requests such as POST, PUT and DELETE

```
package com.kotani.eosaccounts.Api;

import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "https://mainnet.eosnairobi.io/";

    @POST("/v1/chain/get_account")
    Call<ResponseBody> getAccount(@Body RequestBody params);

}

```
## Step 6

We create the layout for our application. A title, a text field for entering account names, a button, when clicked fetches all information about a given account and a text field to present the output.

```
<LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:gravity="center_horizontal"
            android:text="@string/account_details"
            android:layout_marginTop="18dp"
            android:textAppearance="@style/TextAppearance.AppCompat.Title"/>
        <EditText
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="@string/hint"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:inputType="textMultiLine"
            android:id="@+id/account_name" />
        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="18dp"
            android:background="@color/colorPrimary"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:id="@+id/get_account"
            android:text="@string/get_account"
            android:textColor="@android:color/white"
            />
        <TextView
            android:layout_marginTop="18dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/info"
            android:layout_marginRight="10dp"
            android:layout_marginLeft="10dp"
            />

    </LinearLayout>

```
## Step 7

```
```

## NB

Depending on the type of data you are fetching from the blockchain, the other requests one can make are:
In this case, observables are used

```
    @POST("/v1/chain/{infoType}")
    Observable<EosChainInfo> readInfo(@Path("infoType") String infoType);

    @POST("/v1/chain/get_account")
    Observable<JsonObject> getAccountInfo(@Body AccountInfoRequest body);

    @POST("/v1/chain/get_table_rows")
    Observable<JsonObject> getTable(@Body GetTableRequest body);

    @POST("/v1/chain/push_transaction")
    Observable<PushTxnResponse> pushTransaction(@Body PackedTransaction body);

    @POST("/v1/chain/push_transaction")
    Observable<JsonObject> pushTransactionRetJson(@Body PackedTransaction body);

    @POST("/v1/chain/get_required_keys")
    Observable<RequiredKeysResponse> getRequiredKeys(@Body GetRequiredKeys body);



    @POST("/v1/chain/get_currency_balance")
    Observable<JsonArray> getCurrencyBalance(@Body GetBalanceRequest body);

    @POST("/v1/chain/get_currency_stats")
    Observable<JsonObject> getCurrencyStats(@Body GetRequestForCurrency body);



    @POST("/v1/chain/abi_json_to_bin")
    Observable<JsonToBinResponse> jsonToBin(@Body JsonToBinRequest body);

    @POST("/v1/chain/get_code")
    Observable<GetCodeResponse> getCode(@Body GetCodeRequest body);

    @POST("/v1/history/get_controlled_accounts")
    Observable<JsonObject> getServants(@Body JsonObject body);

    @POST("/v1/history/get_transactions")
    Observable<JsonObject> getTransactions( @Body JsonObject body);

```

## References

Tutorial on using Retrofi https://code.tutsplus.com/tutorials/sending-data-with-retrofit-2-http-client-for-android--cms-27845
Github repo, a demo application illustrating all api interactions with eos https://github.com/playerone-id/EosCommander
