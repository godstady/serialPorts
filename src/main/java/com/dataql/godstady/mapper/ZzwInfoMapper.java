package com.dataql.godstady.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by godstady on 2020/11/16.
 */
@Mapper
public interface ZzwInfoMapper {

    @Select("select substr(ccwz,5,2) as zngbh from zzw_info where sjwpbh = #{sjwpbh}")
    String getZngbh(String sjwpbh);


    @Select("select substr(ccwz,5,2) as zngbh,a.ajmc as ajmc from zzw_info z left join  aj_info a on a.ajbh = z.ajbh where   ccwz like 'ZNG-%' ")
    List<Map> lsdata();

    @Select("select substr(zswz,4,2) as zngbh ,a.ajmc as ajmc from zzw_info z left join  aj_info a on a.ajbh = z.ajbh  where wzzt = '1' and zswz like 'LS-%'  ")
    List<Map> kfdata();
}
