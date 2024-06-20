package com.example.ag.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ServerHelper {
    public static String login(String log, String pas, Callback<LoginResponse> c ) {
            Retrofit r = RetrofitClient.getBasicAuthRetrofit();
            RetrofitService s = r.create(RetrofitService.class);
            Call<LoginResponse> call = s.login("client_credentials", "backend","backend");
            call.enqueue(c);
        return null;
    }

    public static ArrayList<Route> getRoute(String token,  Callback<ArrayList<RouteResponse>> c) {

        Retrofit r = RetrofitClient.getAuthUserRetrofit();
        RetrofitService s = r.create(RetrofitService.class);
        Call<ArrayList<RouteResponse>> call = s.getRoutes( token, new RoutesBody());
        call.enqueue(c);
        return new ArrayList<>();
    }
    public static String getRouteString(String token,  Callback<Void> c) {

        Retrofit r = RetrofitClient.getAuthUserRetrofit();
        RetrofitService s = r.create(RetrofitService.class);
        Call<Void> call = s.getRoutesString( token, new RoutesBody());
        call.enqueue(c);
        return "new ArrayList<>()";
    }
    public static ArrayList<Route> getRouteOld(String token) {
        ArrayList<Route> arrayList = new ArrayList<Route>();

        String res =
                "    {" +
                        "        \"result\": {" +
                        "            \"records\": [" +
                        "                {" +
                        "                    \"ID\": \"3f00022a-f02a-482a-b764-7a484c95d6d5\"," +
                        "                    \"C_Name\": \"Тестовый маршрут 1\"," +
                        "                    \"F_Provider\": \"7294c42a-61f6-4823-a957-2fcbc29e68d1\"," +
                        "                    \"F_Provider___C_Name\": \"Общество с ограниченной ответственностью «Попутчик» \"," +
                        "                    \"N_Limit\": 10" +
                        "                }" +
                        "            ]" +
                        "        }," +
                        "        \"meta\": {" +
                        "            \"success\": true," +
                        "            \"msg\": null," +
                        "            \"fullMsg\": null" +
                        "        }," +
                        "        \"action\": \"Domain.school_tourism_cs_routes\"," +
                        "        \"method\": \"query\"," +
                        "        \"type\": \"rpc\"," +
                        "        \"tid\": \"c55dcb69-2293-bacd-8c9a-b9a56704cbf4\"" +
                        "    }";

        try {
            JSONObject json = new JSONObject(res);
            JSONObject result = json.getJSONObject("result");
            JSONArray records = result.getJSONArray("records");


            for (int i = 0; i < records.length(); i++) {
                Route r = new Route();
                JSONObject o = records.getJSONObject(i);
                r.setID(o.getString("ID"));
                r.setName(o.getString("C_Name"));
                r.setComname(o.getString("F_Provider___C_Name"));
                r.setIdcom(o.getString("F_Provider"));
                r.setLimit(o.getInt("N_Limit"));


                arrayList.add(r);
            }

        } catch (JSONException e) {
            e.printStackTrace();

        }


        return arrayList;

    }

}
