package com.george.xinbaoli.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.george.xinbaoli.entity.Demo;
import com.george.xinbaoli.mapper.DemoMapper;
import com.george.xinbaoli.service.DemoService;
@Service(value = "demoServie")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<Demo> getDemos() {
        return demoMapper.getDemos();
    }

}
