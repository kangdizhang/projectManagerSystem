package com.ectrip.dao;

import com.ectrip.model.ModleDemand;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface ModleDemandDAO {

    void deleteModle(@Param("demandId") Integer demandId);

    void saveModle(ModleDemand modleDemand);
}
