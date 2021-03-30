package com.shgbit.android.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shgbit.android.demo.di.Car;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * @author hexj
 * @createDate 2021/3/29 10:12
 **/
@AndroidEntryPoint
public class MainFragment  extends Fragment {
    private static final String TAG = "MainFragment";
    RecyclerView recyclerView;
    ListAdapter listAdapter;
    String[] item = new String[] {"OKHttp","service","依赖注入","状态保存"};
    @Inject
    Car mCar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: "+savedInstanceState);
        recyclerView = view.findViewById(R.id.listview);
        listAdapter = new ListAdapter(Arrays.asList(item));
        if (savedInstanceState != null) {
            listAdapter.updateSelectPos(savedInstanceState.getInt("lastPos",0));
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

    }

    /**
     * 页面跳转
     * @param index
     */
    private void changePage(int index) {

        if (index<item.length) {
            switch (index) {
                case 0:
                    ((MainActivity)getActivity()).changeFragment(LoginFragment.newInstance());
                    break;
                case 1:
                    ((MainActivity)getActivity()).changeFragment(ServiceFragment.newInstance(null,null));
                    break;
                case 2:
                    Toast.makeText(getContext(),"car have wheels "+mCar.getWheels().length,Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(getContext(),"旋转屏幕后数据将保持",Toast.LENGTH_SHORT).show();

            }
        }
    }
    class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

        int selectPos;
        List<String> data;
        public ListAdapter(List data) {
            this.data = data;
        }

        public void updateSelectPos(int pos) {
            selectPos = pos;
        }
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setTextSize(18);
            textView.setPadding(10,10,10,5);
            return new Holder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {

            holder.textView.setText(data.get(position));
            if (selectPos == position) {
                holder.textView.setBackgroundColor(Color.GRAY);
            } else {
                holder.textView.setBackgroundColor(Color.TRANSPARENT);
            }
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changePage(position);
                    notifyItemChanged(position);
                    notifyItemChanged(selectPos);
                    selectPos = position;
                }
            });
        }

        @Override
        public int getItemCount() {
            return data == null?0:data.size();
        }

        class Holder extends RecyclerView.ViewHolder {
            private TextView textView;
            public Holder(@NonNull TextView itemView) {
                super(itemView);
                textView = itemView;
            }
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: ");
        outState.putInt("lastPos",listAdapter.selectPos);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored: "+savedInstanceState);
    }

}
