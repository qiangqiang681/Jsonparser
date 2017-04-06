package hgq.example.com.jsontobean;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import hgq.example.com.jsontobean.bean.BarberShop;
import hgq.example.com.jsontobean.bean.CardType;
import hgq.example.com.jsontobean.bean.CostItem;
import hgq.example.com.jsontobean.bean.Employee;
import hgq.example.com.jsontobean.bean.Hairdresser;
import hgq.example.com.jsontobean.bean.ServiceItem;
import hgq.example.com.jsontobean.http.HttpClient;
import hgq.example.com.jsontobean.http.OnResponseCallBack;
import hgq.example.com.jsontobean.http.Paramer;
import hgq.example.com.jsontobean.http.ResponseHandler;
import hgq.example.com.jsontobean.http.ResponseMethod;
import hgq.example.com.jsontobean.http.ResponseParamer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpClient.getInstance().post(Constants.API.login,
                new Paramer()
                        .add("mobile", "18553101693")
                        .add("deviceId", "111111111")
                        .add("verifyCode", "8888"),
                loginCallback);
    }

    private OnResponseCallBack loginCallback = new OnResponseCallBack() {
        @ResponseHandler(ResponseMethod.FINISH)
        public void onFinish() {
//            Toast.makeText(MainActivity.this, "finish", Toast.LENGTH_LONG).show();
        }

        @ResponseHandler(ResponseMethod.FAILUE)
        public void onFailue(int status, String msg) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
        }

        @ResponseHandler(ResponseMethod.SUCCESS)
        public void onSuccess(@ResponseParamer("accessToken") String accessToken,
                              @ResponseParamer("isRegister") String isRegister,
                              @ResponseParamer("employee") Employee employee,
                              @ResponseParamer("barberShop") BarberShop barberShop,
                              @ResponseParamer("cardTypes") ArrayList<CardType> cardTypes,
                              @ResponseParamer("serviceItems") ArrayList<ServiceItem> serviceItems,
                              @ResponseParamer("hairdressers") ArrayList<Hairdresser> hairdressers,
                              @ResponseParamer("costItems") ArrayList<CostItem> costItems) {
            Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_LONG).show();
        }
    };
}
