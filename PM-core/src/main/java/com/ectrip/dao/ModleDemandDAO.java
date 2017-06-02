package com.ectrip.dao;

import com.ectrip.model.ModleDemand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface ModleDemandDAO {

    /**
     * 根据需求ID查找模块ID列表
     * @param demandId
     * @return
     */
    List<ModleDemand> queryModleList(@Param("demandId") Integer demandId);

    void deleteModle(@Param("demandId") Integer demandId);

    void saveModle(ModleDemand modleDemand);
}
