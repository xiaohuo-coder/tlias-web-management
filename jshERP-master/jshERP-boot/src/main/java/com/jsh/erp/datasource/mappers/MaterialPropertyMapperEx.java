package com.jsh.erp.datasource.mappers;

import com.jsh.erp.datasource.entities.MaterialProperty;
import com.jsh.erp.datasource.entities.MaterialPropertyExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MaterialPropertyMapperEx {

    List<MaterialProperty> selectByConditionMaterialProperty(
            @Param("name") String name);

    int batchDeleteMaterialPropertyByIds(@Param("updateTime") Date updateTime, @Param("updater") Long updater, @Param("ids") String ids[]);

    int getCountByNativeName(@Param("nativeName") String nativeName);

    void updateMaterialPropertyByNativeName(
            @Param("nativeName") String nativeName,
            @Param("anotherName") String anotherName);
}