package com.qygx.system.service.impl;

import com.alibaba.fastjson2.JSON;
import com.qygx.common.annotation.DataSource;
import com.qygx.common.enums.DataSourceType;
import com.qygx.common.exception.ServiceException;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.common.utils.bean.BeanValidators;
import com.qygx.system.domain.ProInspect;
import com.qygx.system.domain.vo.BreedVo;
import com.qygx.system.domain.vo.InspectVo;
import com.qygx.system.domain.vo.QualityVo;
import com.qygx.system.mapper.ProReportMapper;
import com.qygx.system.service.IProReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProReportServiceImpl implements IProReportService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private ProReportMapper reportMapper;

    @Value("${qygx.lianglv}")
    private double  LIANGLV;  //良率设定值

    @Autowired
    protected Validator validator;


    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<InspectVo> selectInspectList(ProInspect proInspect) throws ParseException {
        List<ProInspect> listInspect = reportMapper.selectInspectChart(proInspect);
        List<InspectVo> inspectVos = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");//设置日期格式
        for (int i = 0; i < listInspect.size(); i++) {
            Date beginTime = listInspect.get(i).getBeginTime();
            String fmtDate = DateUtils.isClassesDate(beginTime);
            //实现查找，找到则返回匹配的对象，找不到则返回null
            InspectVo iv = inspectVos.stream().filter(item -> item.getDate().equals(fmtDate)) .findAny()
                    .orElse(null);
            if(iv != null){
                int checkNum = listInspect.get(i).getInspectedNum() + iv.getCheckNum();
                int okNum = listInspect.get(i).getOkNum() + iv.getOkNum();
                double yieldRate = (float)okNum / checkNum ;

                iv.setCheckNum(checkNum);
                iv.setOkNum(okNum);
                iv.setYieldRate(yieldRate);
            }
            else{
                //todo 新建对象
                int checkNum = listInspect.get(i).getInspectedNum();
                int okNum = listInspect.get(i).getOkNum();
                double yieldRate = (float)okNum / checkNum ;
                iv = new InspectVo(fmtDate,checkNum,okNum,yieldRate);
                inspectVos.add(iv);
            }
        }
        return inspectVos;
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List selectBreedList() {
        List<ProInspect> breeds = reportMapper.selectBreedList();
        List<BreedVo> result = new ArrayList<>();
        for (int i = 0; i < breeds.size(); i++) {
            String productType = breeds.get(i).getProductType();
            String materialQuality = breeds.get(i).getMaterialQuality();
            if(productType != null && productType.length() != 0
                    && materialQuality != null && materialQuality.length() != 0){
                String str = productType + "." + materialQuality;
                result.add(new BreedVo(str));
            }
        }
        return result;
    }

    /**
     * 查询当前白班或夜班的 检测情况
     * @param proInspect
     * @return
     * @throws ParseException
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<InspectVo> selectShiftInspectList(ProInspect proInspect) throws ParseException {
        List<InspectVo> inspectVos = new ArrayList<>();
        Date nowDate = DateUtils.getNowDate();
        //todo 白班 2022-07-18 07:30:00  —— 2022-07-18 19:30:00
        //todo 夜班 2022-07-18 19:31:00  —— 2022-07-19 07:29:00
        Date[] dates = DateUtils.classesDateRange(nowDate);

        proInspect.setBeginTime(dates[0]);
        proInspect.setEndTime(dates[1]);
        List<ProInspect> proInspects = reportMapper.selectInspectListByTime(proInspect);

        for (ProInspect pi: proInspects ) {
            int checkNum = pi.getInspectedNum();
            int okNum = pi.getOkNum();
            double yieldRate = (float)okNum / checkNum ;
            InspectVo iv = new InspectVo(pi.getQrCode(),
                    checkNum,okNum,yieldRate);
            inspectVos.add(iv);
        }
        return inspectVos;
    }

    @Override
    public QualityVo info() throws ParseException {

        Date nowDate = DateUtils.getNowDate();
        String str = DateUtils.isClassesDate(nowDate);
        String date = DateUtils.parseDateToStr("YYYY-MM-dd",nowDate);
        String ban = str.substring(10);

        return new QualityVo(date,ban);
    }


    /**
     * 查询inspect
     *
     * @param id inspect主键
     * @return inspect
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public ProInspect selectProInspectById(Long id)
    {
        return reportMapper.selectInspectById(id);
    }


    /**
     * 查询inspect列表
     *
     * @param proInspect inspect
     * @return inspect
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<ProInspect> selectProInspectList(ProInspect proInspect)
    {
        return reportMapper.selectInspectList(proInspect);
    }

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<ProInspect> selectProInspectUnusualList(ProInspect proInspect) {
        List<ProInspect>  allList =  reportMapper.selectInspectList(proInspect);
        List<ProInspect>  unList = new ArrayList<>();
        if(allList.size() > 0){
            Map<Integer,String> map;
            for (ProInspect pi: allList) {
                int okNum = pi.getOkNum();
                int inspectedNum =  pi.getInspectedNum();
                double liangLv =  (float)okNum/inspectedNum;
                if(liangLv < LIANGLV){
                    map = new HashMap<>();
                    map.put(pi.getNg1() == null ? 0:pi.getNg1(),"材质不良");
                    map.put(pi.getNg2() == null ? 0:pi.getNg2(),"失圆");
                    map.put(pi.getNg3() == null ? 0:pi.getNg3(),"内裂");
                    map.put(pi.getNg4() == null ? 0:pi.getNg4(),"坑点");
                    map.put(pi.getNg5() == null ? 0:pi.getNg5(),"尺寸不良");
                    map.put(pi.getNg6() == null ? 0:pi.getNg6(),"发雾");
                    map.put(pi.getNg7() == null ? 0:pi.getNg7(),"伤痕");
                    map.put(pi.getNg8() == null ? 0:pi.getNg8(),"印渍");
                    map.put(pi.getNg9() == null ? 0:pi.getNg9(),"不洁");
                    map.put(pi.getNg10() == null ? 0:pi.getNg10(),"其他");
                    Integer[] arr = map.keySet().toArray(new Integer[map.size()]);
                    // Integer[] --> int[]
                    int[] num=Arrays.stream(arr).mapToInt(Integer::valueOf).toArray();
                    int max = Arrays.stream(num).max().getAsInt();
                    if(max > 0 ){
                        pi.setNgStr(map.get(max));
                    }
                    if(pi.getStatus() == null){
                        pi.setStatus(0);
                    }
                    pi.setOkPercent(liangLv);
                    unList.add(pi);
                }
            }
        }
        return unList;
    }

    /**
     * 新增inspect
     *
     * @param proInspect inspect
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int insertProInspect(ProInspect proInspect)
    {
        return reportMapper.insertInspect(proInspect);
    }

    /**
     * 修改inspect
     *
     * @param proInspect inspect
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public int updateProInspect(ProInspect proInspect)
    {
        proInspect.setStatus(1); //已处理
        return reportMapper.updateInspect(proInspect);
    }

    /**
     * 批量删除inspect
     *
     * @param ids 需要删除的inspect主键
     * @return 结果
     */
    @Override
    public int deleteProInspectByIds(Long[] ids)
    {
        return reportMapper.deleteInspectByIds(ids);
    }

    /**
     * 删除inspect信息
     *
     * @param id inspect主键
     * @return 结果
     */
    @Override
    public int deleteProInspectById(Long id)
    {
        return reportMapper.deleteInspectById(id);
    }

    /**
     * 导入检测数据
     *
     * @param inspectList 检测数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public String importInspect(List<ProInspect> inspectList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(inspectList) || inspectList.size() == 0)
        {
            throw new ServiceException("导入检测数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ProInspect inspect : inspectList)
        {
            try
            {
                // 验证是否存在这个用户
                ProInspect i = reportMapper.selectInspectByQrCode(inspect.getQrCode());
                if (StringUtils.isNull(i))
                {
                    BeanValidators.validateWithException(validator, inspect);
                    this.insertProInspect(inspect);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、批次号 " + inspect.getQrCode() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, inspect);

                    this.updateProInspect(inspect);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、批次号 " + inspect.getQrCode() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、批次号 " + inspect.getQrCode() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、批次号 " + inspect.getQrCode() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

}
