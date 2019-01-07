package com.george.xinbaoli.service;

import java.util.HashMap;
import java.util.List;

public interface BaseServiceClient {

    /**
     * 保存
     *
     * @param obj
     * @return
     */
    public int insert(Object obj);

    /**
     * 根据long型主键查询
     *
     * @param id
     * @param c
     * @return
     */
    public HashMap query(long id, Class c);

    /**
     * 根据主键更新
     *
     * @param obj
     * @return
     */
    public int update(Object obj);

    /**
     * 根据主键删除
     *
     * @param obj
     * @return
     */
    public int delete(Object obj);

    /**
     * 通用批量保存
     *
     * @param sqlId
     * @param data
     * @return
     */
    public int batchInsert(String sqlId, List data);
}
