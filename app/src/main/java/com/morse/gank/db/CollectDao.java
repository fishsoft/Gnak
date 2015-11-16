package com.morse.gank.db;

import com.morse.gank.beans.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Morse on 2015/11/16 15:05
 * 邮箱：zm902485jgsurjgc@163.com
 */
public interface CollectDao {

    /**
     * 插入文章
     *
     * @param bean
     */
    public void insert(Bean bean);

    /**
     * 插入文章
     *
     * @param beans
     */
    public void insert(ArrayList<Bean> beans);


    /**
     * 删除文章
     *
     * @param bean
     */
    public void delete(Bean bean);


    /**
     * 查找博客列表
     *
     * @return
     */
    public List<Bean> query();

    /**
     * 查找博客列表
     *
     * @return
     */
    public List<Bean> query(String type);

}
