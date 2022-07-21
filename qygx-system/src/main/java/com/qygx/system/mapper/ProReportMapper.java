package com.qygx.system.mapper;
import com.qygx.system.domain.ProInspect;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProReportMapper {

    /**
     * 查询统计
     *
     *
     */
    public List<ProInspect> selectInspectList(ProInspect proInspect);


    /**
     * 查询品种（规格+材质）分组
     *
     *
     */
    public List<ProInspect> selectBreedList();

    /**
     * 根据开始时间，结束时间查
     *
     *
     */
    public List<ProInspect> selectInspectListByTime(ProInspect proInspect);
}
