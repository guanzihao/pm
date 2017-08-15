package com.pm.cms.busin.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.cms.bean.CMSBoard;
import com.pm.cms.busin.CMSBoardBusin;
import com.pm.core.busin.BusinApi;

@Service
public class CMSBoardBusinImpl implements CMSBoardBusin {

    @Autowired
    private BusinApi businApi;

    @Override
    public CMSBoard getCMSBoard(String id) {

        return (CMSBoard) businApi.get(CMSBoard.class, id);
    }

    @Override
    public void saveCMSBoard(CMSBoard cmsBoard) {
        if (cmsBoard != null) {
            businApi.save(cmsBoard);
        }
    }               

    @Override
    public void updateCMSBoard(String id) {
        CMSBoard boar = (CMSBoard) businApi.get(CMSBoard.class, id);
        if (boar != null) {
            boar.setIsRead(1);
            boar.setReadDate(new Date());
            businApi.save(boar);
        }
    }
}
