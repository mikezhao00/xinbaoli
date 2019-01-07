package com.george.xinbaoli.mapper;

import java.util.HashMap;
import java.util.Map;

public interface BaseMapper {
    int insert(Map params);

    int update(Map params);

    int delete(Map params);

    HashMap queryForObject(Map params);

}
