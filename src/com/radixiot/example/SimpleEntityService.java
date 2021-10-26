/*
 * Copyright (C) 2021  Radix IoT Inc. All rights reserved.
 */
package com.radixiot.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radixiot.example.db.tables.SimpleEntity;

@Service
public class SimpleEntityService implements ServiceInterface<SimpleEntityVO> {

    private final SimpleEntityDao dao;

    @Autowired
    public SimpleEntityService(SimpleEntityDao dao) {
        this.dao = dao;
    }

    @Override
    public List<SimpleEntityVO> getAll() {
        return dao.getAll();
    }

    @Override
    public SimpleEntityVO get(int id) {
        SimpleEntityVO vo = dao.get(id);
        if(vo == null) {
            throw new SimpleEntityNotFoundException(id);
        }else {
            return vo;
        }
    }

    @Override
    public void insert(SimpleEntityVO vo) {
        dao.insert(vo);
    }

    @Override
    public void update(SimpleEntityVO vo) {
        //Ensure exists
        get(vo.getId());
        dao.update(vo);
    }

    @Override
    public boolean delete(int id) {
        //Ensure exists
        get(id);
        return dao.delete(id);
    }
}
