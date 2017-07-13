package liwei.com.newsapp.activitys;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import liwei.com.newsapp.R;
import liwei.com.newsapp.entity.QueryEntity;

public class ShowActivity extends ParentActivity implements View.OnClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_url)
    TextView tvUrl;
    @BindView(R.id.tv_src)
    TextView tvSrc;

    @Override
    public int onLayout() {
        return R.layout.activity_show;
    }

    @Override
    public void initView() {
        tvUrl.setOnClickListener(this);
        showData();
    }

    private void showData() {
        Intent intent = getIntent();
        QueryEntity.ResultBean resultBean= (QueryEntity.ResultBean) intent.getSerializableExtra("data");
        if(resultBean!=null){
            String content=resultBean.getContent();
            String title=resultBean.getFull_title();
            String image=resultBean.getImg();
            String url=resultBean.getUrl();
            String src=resultBean.getSrc();
            if(!TextUtils.isEmpty(content)){
                tvContent.setText(content);
            }
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }
            if(!TextUtils.isEmpty(image)){
                Glide.with(this).load(image).into(imageView);
            }
            if(!TextUtils.isEmpty(url)){
                tvUrl.setText(url);
            }
            if(!TextUtils.isEmpty(src)){
                tvSrc.setText("信息来源："+src);
            }

        }
    }

    @Override
    public void initData() {

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_url){
            Intent intent=new Intent(this,WebViewActivity.class);
            intent.putExtra("url",tvUrl.getText().toString());
            startActivity(this,intent,false);
        }
    }
}
