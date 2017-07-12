package liwei.com.newsapp.activitys;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import liwei.com.newsapp.R;
import liwei.com.newsapp.adapter.HotSportsAdapter;
import liwei.com.newsapp.constant.Constant;
import liwei.com.newsapp.entity.HotSports;
import liwei.com.newsapp.entity.QueryEntity;
import liwei.com.newsapp.utils.ActivityUtils;
import liwei.com.newsapp.utils.DialogUtils;
import liwei.com.newsapp.utils.GsonUtils;
import liwei.com.newsapp.utils.HttpUtils;
import liwei.com.newsapp.utils.NetUtils;
import liwei.com.newsapp.utils.ToastUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends ParentActivity implements View.OnClickListener {

    //EditText搜索
    @BindView(R.id.edit_search)
    EditText editSearch;
    //搜索按钮
    @BindView(R.id.tv_search)
    TextView tvSearch;
    //水平布局
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    //新闻列表
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_app)
    TextView tvApp;
    @BindView(R.id.tv_no_net)
    TextView tvNoNet;
    //recyclerview列表项集合
    private List<String> stringList=new ArrayList<>();
    private Context activity;
    private HotSportsAdapter adapter;
    @Override
    public int onLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        activity=this;
        initEditText();
        initRecyclerView();
        initLisenter();
    }

    /**
     * 初始化监听器
     */
    private void initLisenter() {
        tvSearch.setOnClickListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        adapter=new HotSportsAdapter(activity,stringList);
        LinearLayoutManager manager=new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化EditText
     */
    private void initEditText() {
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvApp.setVisibility(View.GONE);
                tvSearch.setVisibility(View.VISIBLE);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 获取热点新闻
     */
    private void sendRequestWithHotSports() {
        DialogUtils.showDialog("正在加载中……",activity);
        HttpUtils.sendHttpRequest(Constant.HOTSPOTS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ToastUtils.showMessage("加载失败",activity);
                DialogUtils.dismiss();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String string = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ToastUtils.showMessage("加载成功",activity);
                            DialogUtils.dismiss();
                            HotSports hotSport = GsonUtils.fromJson(string, HotSports.class);
                            stringList.clear();
                            if(hotSport.getResult()!=null){
                                stringList.addAll(hotSport.getResult());
                                adapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!NetUtils.isNetAvailable()){
            tvNoNet.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }else{
            tvNoNet.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            sendRequestWithHotSports();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:
                getData();
                break;
        }
    }

    /**
     * 搜索精确查询
     */
    private void getData() {
        if(!NetUtils.isNetAvailable()){
            ToastUtils.showMessage("当前网络不可用，请检查网络",this);
            startActivity(this,Settings.ACTION_SETTINGS,false);
        }else{
            String queryContentText = editSearch.getText().toString();
            if(TextUtils.isEmpty(queryContentText)){
                ToastUtils.showMessage("查询结果不能为空",activity);
                return;
            }
            String queryUrl=Constant.QUERY_URL+"q="+queryContentText+Constant.QUERY_URL_PARAMS;
            DialogUtils.showDialog("正在查询请稍后...",activity);
            HttpUtils.sendHttpRequest(queryUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtils.showMessage("查询失败，请稍后重试",activity);
                    DialogUtils.dismiss();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    DialogUtils.dismiss();
                    String string = response.body().string();
                    QueryEntity queryEntity = GsonUtils.fromJson(string, QueryEntity.class);
                    int errorCode = queryEntity.getError_code();
                    List<QueryEntity.ResultBean> results = queryEntity.getResult();
                    if(errorCode==0 && results!=null && results.size()!=0){
                        stringList.clear();
                        for(QueryEntity.ResultBean resultBean :results){
                            stringList.add(resultBean.getTitle());
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                                editSearch.setText("");
                            }
                        });
                    }else{
                        ToastUtils.showMessage("未查到您搜索的相关新闻，请重新输入",activity);
                        editSearch.setText("");
                    }
                }
            });
        }
    }
}
