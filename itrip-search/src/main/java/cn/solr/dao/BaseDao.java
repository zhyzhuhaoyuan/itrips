package cn.solr.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class BaseDao<T> {
    private HttpSolrClient httpSolrClient = null;
    private QueryResponse queryResponse = null;

    public BaseDao(String url) {
        //初始化HttpSolrClient
        httpSolrClient = new HttpSolrClient(url);
        // 设置响应解析器
        httpSolrClient.setParser(new XMLResponseParser());
        // 建立连接的最长时间
        httpSolrClient.setConnectionTimeout(500);
    }

    public List<T> queryList(SolrQuery query, Class clazz) {
        List<T> list = null;
        try {
            queryResponse = httpSolrClient.query(query);
            list = queryResponse.getBeans(clazz);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
