package nc.vo.pu.m24.rule;

import java.util.ArrayList;
import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pp.hqhp.IHqHpAlgorithm;
import nc.vo.pp.hqhp.algorithm.HqHpAssistantVO;
import nc.vo.pp.hqhp.algorithm.HqHpCalculateVO;
import nc.vo.pp.hqhp.algorithm.HqHpParamVO;
import nc.vo.pp.hqhp.algorithm.HqHpReturnVO;
import nc.vo.pp.hqhp.qpschm.entity.QPSchmItemVO;
import nc.vo.pp.hqhp.qpstandard.entity.QPStandardItemVO;
import nc.vo.pp.hqhp.qpstandard.entity.QPStandardVO;
import nc.vo.pp.hqhp.qpstandard.enumeration.StandardType;
import nc.vo.pp.hqhp.qpstandard.enumeration.ValueType;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>进行优质优价计算Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-2 下午03:51:30
 */
public class PristlHphqRule {

  public PricestlVO[] getHqhpValue(PricestlVO[] vos) throws BusinessException {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    // 检查模块是否启用
    this.checkProdsEnable();

    PricestlVO[] ret = vos;
    // 取得优质优价参数
    HqHpParamVO[] params = this.getParam(vos);

    // 优质优价计算
    IHqHpAlgorithm hqhp = NCLocator.getInstance().lookup(IHqHpAlgorithm.class);
    HqHpReturnVO hqhpVO = hqhp.calHqHpBase(null, 1, params);

    this.setHqHpValue(vos, hqhpVO);

    return ret;
  }

  /**
   * 方法功能描述：检查模块是否启用。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author gaogr
   * @time 2010-8-2 下午03:51:58
   */
  private void checkProdsEnable() {
    boolean enable = SysInitGroupQuery.isICEnabled();
    if (!enable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004070_0", "04004070-0007")/*
                                                                   * @res
                                                                   * "库存模块未启用，不能进行优质优价计算。"
                                                                   */);
    }

    enable = SysInitGroupQuery.isPOEnabled();
    if (!enable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004070_0", "04004070-0008")/*
                                                                   * @res
                                                                   * "采购模块未启用，不能进行优质优价计算。"
                                                                   */);
    }

    enable = SysInitGroupQuery.isQCEnabled();
    if (!enable) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004070_0", "04004070-0009")/*
                                                                   * @res
                                                                   * "质检模块未启用，不能进行优质优价计算。"
                                                                   */);
    }
  }

  private QPSchmItemVO[] getAdjValStnds(QPSchmItemVO[] itemVOs) {
    ArrayList<QPSchmItemVO> v = new ArrayList<QPSchmItemVO>();

    for (int i = 0, len = itemVOs.length; i < len; i++) {
      if (itemVOs[i].getFschemetype().intValue() == StandardType.TYPE_TJ
          .toInt()) {
        v.add(itemVOs[i]);
      }
    }

    return v.toArray(new QPSchmItemVO[v.size()]);
  }

  private QPSchmItemVO getCompareStnds(QPStandardVO stndVO,
      QPSchmItemVO[] adjval_stnds) {
    String stnd_id = stndVO.getHVO().getPk_qpstandardbase();

    for (int i = 0, len = adjval_stnds.length; i < len; i++) {
      if (adjval_stnds[i].getPk_qpstandardbase().equals(stnd_id)) {
        return adjval_stnds[i];
      }
    }

    return null;
  }

  private HqHpParamVO[] getParam(PricestlVO[] vos) {
    ArrayList<HqHpParamVO> params = new ArrayList<HqHpParamVO>();
    for (PricestlVO vo : vos) {
      PricestlItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (PricestlItemVO item : items) {
        HqHpParamVO param = new HqHpParamVO();
        param.setDbizdate(item.getDbizdate());
        param.setNrowno(item.getCsourcerowno());
        param.setNshouldinnum(item.getNastshouldinnum());
        param.setPk_arriveorderb(item.getPk_arriveorderb());
        param.setPk_general_b(item.getCsourcebid());
        param.setPk_material(item.getPk_material());
        param.setPk_org(item.getPk_org());
        params.add(param);
      }
    }

    return params.toArray(new HqHpParamVO[params.size()]);
  }

  private QPStandardItemVO getStndItemUsed(QPStandardVO stndVO, String b_id) {
    if (b_id == null || b_id.length() == 0) {
      return null;
    }

    QPStandardItemVO[] items = stndVO.getBVO();

    for (int i = 0, len = items.length; i < len; i++) {
      if (items[i].getPk_qpstandard_b().equals(b_id)) {
        return items[i];
      }
    }

    return null;
  }

  /**
   * 处理数值（去掉无效的0） 小数位处理参照UFDouble定义，对于超出精度的位数直接舍去 没有考虑数据类型转换异常，此处由之前的算法保证
   * 
   * @param valuetype
   * @param strvalue
   * @return
   */
  private String handleNumCheckValue(int valuetype, String strvalue) {
    if (valuetype == ((Integer) ValueType.TYPEVALUE_CHAR.value()).intValue()) {
      return strvalue;
    }

    // 小数位精度
    int precision = -UFDouble.DEFAULT_POWER;
    // 小数点位置
    int dot = strvalue.indexOf(".");
    if (dot < 0) {
      return strvalue;
    }

    String ret = strvalue;
    // 游标位置（从精度位置游动到小数点为止）
    int index = ret.length() - 1;
    if (index - dot > precision) {
      index = dot + precision;
      ret = ret.substring(0, index + 1);
    }

    String currc;
    for (int i = index; i >= dot; i--) {
      currc = ret.substring(i, i + 1);
      if (currc.equals("0")) {
        ret = ret.substring(0, ret.length() - 1);
      }
      else if (currc.equals(".")) {
        ret = ret.substring(0, ret.length() - 1);
        break;
      }
      else {
        break;
      }
    }// end for

    return ret;
  }

  private void setBItemValue(PricestlVO vo, ArrayList<HqHpCalculateVO> lcalVOs) {
    if (lcalVOs.size() == 0) {
      return;
    }
    ArrayList<PricestlItemBVO> cals = new ArrayList<PricestlItemBVO>();
    for (HqHpCalculateVO calvo : lcalVOs) {
      HqHpAssistantVO astVO = calvo.getAssistant();
      QPStandardVO[] stnds = astVO.getStndVOs();
      String[] stnditemids = astVO.getPk_StndItemUsed();
      QPSchmItemVO[] adjval_stnds =
          this.getAdjValStnds(astVO.getSchmVO().getBVO());
      Object[] checkvals = astVO.getStndValsAvg();
      Object[] weightStndChkVals = astVO.getWeightStndChkVal();
      Object[] calvals = astVO.getStndAdjustVals();

      QPSchmItemVO temp_stnd;
      QPStandardItemVO temp_stnditem;
      int valuetype;

      for (int i = 0, len = stnds.length; i < len; i++) {
        PricestlItemBVO bvo = new PricestlItemBVO();

        /* 找到当前标准在方案中的匹配信息 */
        temp_stnd = this.getCompareStnds(stnds[i], adjval_stnds);

        /* 找到生效的标准表体 */
        /* 当检验值落在基准区间或者没有匹配的调整区间时，此值为空 */
        temp_stnditem = this.getStndItemUsed(stnds[i], stnditemids[i]);

        // 当前检验值类型
        valuetype = stnds[i].getHVO().getFvaluetype().intValue();
        bvo.setCqpschemeid(temp_stnd.getQpschemebody());
        // 优质优价主体标准ID
        bvo.setCqpbasestandid(stnds[i].getHVO().getPk_qpstandardbase());

        // 优质优价标准主表ID
        bvo.setCqpstandid(stnds[i].getHVO().getPk_qpstandard());

        // 优质优价标准子表ID
        bvo.setCqpstandbid(stnditemids[i]);

        // 计算优先级
        bvo.setIpriority(temp_stnd.getNpriority());

        // 计算先决条件code
        bvo.setVprecondcode(temp_stnd.getVprecondcode());

        // 计算先决条件name
        bvo.setVprecondname(temp_stnd.getVprecondname());

        // 检验值是否加权平均
        bvo.setBweightavg(temp_stnd.getBweightavg());

        // 基准值下限
        bvo.setVbaselowlmt(stnds[i].getHVO().getVbaselowlmt());

        // 基准值上限
        bvo.setVbaseuplmt(stnds[i].getHVO().getVbaseuplmt());

        // wuxla fengjq 2013-6-13 需要判断是加权平均还是算术平均。
        // 实际检验值
        if (temp_stnd.getBweightavg().booleanValue()) {
          bvo.setVcheckvalue(this.handleNumCheckValue(valuetype,
              weightStndChkVals[i].toString()));
        }
        else {
          bvo.setVcheckvalue(this.handleNumCheckValue(valuetype,
              checkvals[i].toString()));
        }

        // 标准的计算结果
        bvo.setNstandcalvalue((UFDouble) calvals[i]);

        // 累计方式
        bvo.setFaccumtype(stnds[i].getHVO().getFaccumtype());

        // 标准值类型
        bvo.setFvaluetype(stnds[i].getHVO().getFvaluetype());

        if (temp_stnditem != null) {
          // 调整类型
          bvo.setFadjusttype(temp_stnditem.getFadjusttype());

          // 是否包含下限
          bvo.setBincludelower(temp_stnditem.getBincludelower());

          // 是否包含上限
          bvo.setBincludeupper(temp_stnditem.getBincludeupper());

          // 下限值
          bvo.setVlower(temp_stnditem.getVlower());

          // 上限值
          bvo.setVupper(temp_stnditem.getVupper());

          // 标准公式编码
          bvo.setVfrmlcode(temp_stnditem.getVstandfrmlcode());

          // 标准公式名称
          bvo.setVfrmlname(temp_stnditem.getVstandfrmlname());

        }
        // dr
        bvo.setDr(Integer.valueOf(0));

        cals.add(bvo);
      }

    }

    if (cals.size() > 0) {
      vo.setBBVO(cals.toArray(new PricestlItemBVO[cals.size()]));
    }
  }

  private void setHqHpValue(PricestlVO[] vos, HqHpReturnVO hqhpVO) {
    HqHpCalculateVO[] calVOs = hqhpVO.getCalculateVOs();
    HashMap<String, String> err = hqhpVO.getErrmessage();
    int index = 0;
    for (PricestlVO vo : vos) {
      PricestlItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      ArrayList<HqHpCalculateVO> lcalVOs = new ArrayList<HqHpCalculateVO>();
      for (PricestlItemVO item : items) {

        if (!err.containsKey(calVOs[index].getParam().getPk_general_b())) {
          // 设置价格结算单子表
          this.setItemValue(item, calVOs[index]);
        }

        lcalVOs.add(calVOs[index]);
        index++;
      }

      this.setBItemValue(vo, lcalVOs);
    }

  }

  private void setItemValue(PricestlItemVO item, HqHpCalculateVO calVO) {
    HqHpAssistantVO astVO = calVO.getAssistant();
    // 到货数量
    item.setNarrvnum(astVO.getNarrvnum());
    item.setNastarrvnum(astVO.getNassistnum());

    // 税率
    if (astVO.getNtaxrate() == null) {
      astVO.setntaxrate(new UFDouble(0));
    }
    item.setNtaxrate(astVO.getNtaxrate());

    // 扣税类别
    item.setFtaxtypeflag(astVO.getIdiscounttaxtype());

    // 本币含税基价
    item.setNbasetaxprice(astVO.getNtaxprice());

    // 本币无税基价
    item.setNbaseprice(astVO.getNprice());

    // 本币无税单价:
    // //应税外加：含税/（1+税率）
    if (item.getFtaxtypeflag().equals(EnumDiscounttaxtype.TAXOUT.value())) {
      item.setNprice(calVO.getNresultValue().div(
          astVO.getNtaxrate().div(100).add(1)));
    }
    else if (item.getFtaxtypeflag().equals(EnumDiscounttaxtype.TAXIN.value())) {
      item.setNprice(calVO.getNresultValue().multiply(
          astVO.getNtaxrate().div(100).sub(1).multiply(-1)));
    }

    // 本币含税优质优价
    item.setNcalqualprice(calVO.getNresultValue());

    // 本币含税单价
    item.setNtaxprice(calVO.getNresultValue());

    // 本币价税合计：等于优质优价扣吨后数量（即入库单实收数量）*本币含税单价
    item.setNtaxmny(MathTool.nvl(item.getNcalqualprice()).multiply(
        MathTool.nvl(item.getNinnum())));

    // 到货单行ID

    // 订单号
    item.setCordercode(astVO.getVordercode());

    // 订单ID
    item.setCorderid(astVO.getPk_order());

    // 合同号
    item.setVcontractcode(astVO.getVcontractcode());

    // 合同ID
    item.setCcontractid(astVO.getPk_contract());

    // 质检单号
    item.setCcheckbillcode(astVO.getVcheckbillcode());

    // 质检单ID
    item.setCcheckbillid(astVO.getPk_checkbill());

    // 优质优价主体方案ID
    item.setCqpbaseschemeid(astVO.getPk_qpbasescheme());

    // 优质优价方案ID
    item.setCqpschemeid(astVO.getSchmVO().getHVO().getPk_qpscheme());

    // 基准值含税单价
    item.setDbaseprice(astVO.getSchmVO().getHVO().getDbaseprice());

    // 方案公式编码
    item.setVschemefrmlcode(astVO.getSchmVO().getHVO().getVadjustfrmlcode());

    // 方案公式名称
    item.setVschemefrmlname(astVO.getSchmVO().getHVO().getVadjustfrmlname());

    // 方案的计算结果
    item.setNschemecalvalue(calVO.getNresultValue());
  }
}
