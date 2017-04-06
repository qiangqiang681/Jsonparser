package hgq.example.com.jsontobean.http;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/6.
 */
public class HttpClient {

    private OkHttpClient client;

    private HttpClient(){

        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        client = builder.connectTimeout(3, TimeUnit.MINUTES).build();

    }

    public static HttpClient getInstance(){
        return MyHttpClient.a;
    }

    private static class MyHttpClient{
       static HttpClient a=new HttpClient();
    }

    public void post(String url,Paramer paramers,OnResponseCallBack callBack){
        Request.Builder requestBuilder=new Request.Builder().url(url);

        FormBody.Builder form=new FormBody.Builder();
        for (Map.Entry<String, Object> paramer : paramers.entrySet()) {
            form.add(paramer.getKey(),paramer.getValue().toString());
        }
        RequestBody formBody = form.build();
        requestBuilder.post(formBody);
        Request request = requestBuilder.build();
        Call call = client.newCall(request);
        call.enqueue(new ResponseCallBack(callBack));

    }

    private class ResponseCallBack implements Callback {

        private  OnResponseCallBack callback;

        Method successMethod,startMethod,failMethod,finishMethod;

        Handler handler=new Handler();

        public ResponseCallBack(OnResponseCallBack c) {
            this.callback=c;
            if (c!=null){
                Method[] methods = c.getClass().getMethods();
                for (Method m :
                        methods) {
                    ResponseHandler handler = m.getAnnotation(ResponseHandler.class);

                    if(handler!=null) {
                        if (handler.value() == ResponseMethod.SUCCESS) {
                            successMethod = m;
                        } else if (handler.value() == ResponseMethod.FINISH) {
                            finishMethod = m;
                        } else if (handler.value() == ResponseMethod.START) {
                            startMethod = m;

                            try {
                                m.invoke(callback);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        } else if (handler.value() == ResponseMethod.FAILUE) {
                            failMethod = m;
                        }
                    }
                }
            }

        }

        @Override
        public void onFailure(Call call, IOException e) {


            final Object[] params=new Object[]{500,"服务器错误"};
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(failMethod!=null){
                        try {
                            failMethod.invoke(callback,params);
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InvocationTargetException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

            if(!response.isSuccessful()){
                final Object[] params=new Object[]{response.code(),response.message()};
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        if(failMethod!=null){
                            try {
                                failMethod.invoke(callback,params);
                            } catch (IllegalAccessException e1) {
                                e1.printStackTrace();
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });

                return;
            }

            JSONObject jsonObject = JSON.parseObject(response.body().string());
            if(jsonObject.getIntValue("status")==0){
                if(successMethod!=null){
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    Annotation[][] parameterAnnotations = successMethod.getParameterAnnotations();
                    Class<?>[] parameterTypes = successMethod.getParameterTypes();
                    Type[] types = successMethod.getGenericParameterTypes();
                    final Object[] params=new Object[parameterTypes.length];
                    if(parameterAnnotations.length>0&&parameterAnnotations.length==parameterTypes.length){
                        for (int i = 0; i < parameterAnnotations.length; i++) {
                            Class<?> parameterType = parameterTypes[i];
                            String value = "";
                            for (int j = 0; j < parameterAnnotations[i].length; j++) {//只有一个注解
                                Annotation annotation = parameterAnnotations[i][j];
                                if(annotation instanceof ResponseParamer){
                                    ResponseParamer rp= (ResponseParamer) annotation;
                                    value = rp.value();
                                }
                            }
                            if(dataObject.containsKey(value)){
                                if(parameterType==String.class){
                                    params[i]=dataObject.getString(value);
                                }else if(parameterType==Integer.class){
                                    params[i]=dataObject.getIntValue(value);
                                }else if(parameterType==Long.class){
                                    params[i]=dataObject.getLongValue(value);
                                }else if(parameterType==Double.class){
                                    params[i]=dataObject.getDoubleValue(value);
                                }else if(parameterType==Boolean.class){
                                    params[i]=dataObject.getBooleanValue(value);
                                }else if(parameterType==Float.class){
                                    params[i]=dataObject.getFloatValue(value);
                                }else if(parameterType==ArrayList.class||parameterType==List.class||parameterType==LinkedList.class){
                                    if(types[i] instanceof ParameterizedType){
                                        Type trueType = ((ParameterizedType) types[i]).getActualTypeArguments()[0];
                                        params[i]=JSON.parseArray(dataObject.getString(value),(Class) trueType);
                                    }
                                }else {
                                    params[i]=JSON.parseObject(dataObject.getString(value),parameterType);
                                }
                            }

                        }


                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    successMethod.invoke(callback,params);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }finally {
                                    if(finishMethod!=null){
                                        try {
                                            finishMethod.invoke(callback);
                                        } catch (IllegalAccessException e) {
                                            e.printStackTrace();
                                        } catch (InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        });


                    }
                }

            }else {
                if(failMethod!=null){
                    final Object[] params=new Object[]{111,"未知错误"};
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                failMethod.invoke(callback,params);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        }
    }
}
