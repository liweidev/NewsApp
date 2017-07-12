package liwei.com.newsapp.entity;

import java.util.List;

/**
 * Created by liwei on 2017/7/11.
 */
//实时热点
public class HotSports extends ParentEntity{

    //返回结果
    private List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
