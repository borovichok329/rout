package com.example.ag.ui.notifications;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ag.databinding.FragmentNotificationsBinding;
import com.example.ag.helpers.MyRouteAdapter;
import com.example.ag.helpers.Route;
import com.example.ag.helpers.RouteResponse;
import com.example.ag.helpers.ServerHelper;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private ArrayList<Route> items;
    private MyRouteAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ListView lv = binding.lv;
        SharedPreferences preferences = getActivity().getSharedPreferences("app2", MODE_PRIVATE);
        String savedText = preferences.getString("token", "");

        items = new ArrayList<>();
        adapter = new MyRouteAdapter(this.getContext(), items);
        ServerHelper.getRoute("Bearer " + savedText, new Callback<ArrayList<RouteResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<RouteResponse>> call, Response<ArrayList<RouteResponse>> response) {

                ArrayList<RouteResponse> body = response.body();
                System.out.println(savedText);
                System.out.println(response);
                items.clear();
                if (body != null && body.size() > 0) {
                    RouteResponse routeResponse = body.get(0);

                    if (routeResponse.getResult() != null) {
                        items.addAll(routeResponse.getResult().getRecords());
                    }
                }
                adapter.notifyDataSetChanged();

                try {
                    System.out.println(response.errorBody().string());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RouteResponse>> call, Throwable t) {

            }
        });
        lv.setAdapter(adapter);


        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}