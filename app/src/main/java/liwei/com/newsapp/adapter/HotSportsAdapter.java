package liwei.com.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import liwei.com.newsapp.R;
import liwei.com.newsapp.activitys.MainActivity;
import liwei.com.newsapp.activitys.ShowActivity;
import liwei.com.newsapp.constant.Constant;
import liwei.com.newsapp.entity.QueryEntity;
import liwei.com.newsapp.utils.DialogUtils;
import liwei.com.newsapp.utils.GsonUtils;
import liwei.com.newsapp.utils.HttpUtils;
import liwei.com.newsapp.utils.LogUtils;
import liwei.com.newsapp.utils.ToastUtils;
import liwei.com.newsapp.view.MyTextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by liwei on 2017/7/11.
 */

public class HotSportsAdapter extends RecyclerView.Adapter<HotSportsAdapter.ViewHolder> {

    private Context mContext;
    private List<String> stringList = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Integer.MAX_VALUE:
                    notifyDataSetChanged();
                    break;
            }
        }
    };
    private List<QueryEntity.ResultBean> results;

    public HotSportsAdapter(Context mContext, List<String> stringList) {
        this.mContext = mContext;
        this.stringList = stringList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String title = stringList.get(position);
        holder.newsTitle.setText(title);
        holder.newsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(results!=null){
                    QueryEntity.ResultBean resultBean = results.get(position);
                    Intent intent=new Intent(mContext, ShowActivity.class);
                    intent.putExtra("data",resultBean);
                    if(mContext instanceof MainActivity){
                        ((MainActivity) mContext).startActivity((MainActivity) mContext,intent,false);
                    }
                }else {
                    getData(title);
                }
            }
        });
    }

    /**
     * 发送网络获取数据
     *
     * @param title
     */
    private void getData(String title) {
        DialogUtils.showDialog("正在查询...", mContext);
        String url = Constant.QUERY_URL + "q=" + title + Constant.QUERY_URL_PARAMS;
        LogUtils.d("url=" + url);
        HttpUtils.sendHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                DialogUtils.dismiss();
                ToastUtils.showMessage("查询失败，稍后重试", mContext);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                DialogUtils.dismiss();
                String string = response.body().string();
                QueryEntity queryEntity = GsonUtils.fromJson(string, QueryEntity.class);
                if (null != queryEntity && queryEntity.getError_code() == 0 && queryEntity.getResult().size() > 0) {
                    results = queryEntity.getResult();
                    stringList.clear();
                    for (QueryEntity.ResultBean resultBean : results) {
                        stringList.add(resultBean.getTitle());
                    }
                    handler.sendEmptyMessage(Integer.MAX_VALUE);
                } else {
                    ToastUtils.showMessage("没有查询到相关结果，稍后重试", mContext);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.newsTitle)
        MyTextView newsTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
