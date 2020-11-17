package com.dataql.godstady.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by godstady on 2020/11/16.
 */
@Mapper
public interface ZzwInfoMapper {

    @Select("select substr(ccwz,5,2) as zngbh from zzw_info where sjwpbh = #{sjwpbh}")
    String getZngbh(String sjwpbh);
}
