package nc.bs.pu.m25.pf.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.StringUtils;

/**
 * 入库单跨月开票检查：入库单跨月开票，必须暂估
 * <p>
 * <b>本类主要完成以下功能：</b> 如果发票的来源是采购入库单，则进行如下判断。
 * <ul>
 * <li>取发票主组织财务组织的主账簿的会计期间方案，以发票表头的发票日期匹配此会计期间方案得到发票所处的会计期间。</li>
 * <li>取到采购入库单的主组织库存组织所属财务组织，取此财务组织的主账簿的会计期间方案。以采购入库单表体的入库日期匹配此会计期间方案得到入库单的会计期间，
 * 对每个采购入库单行取一个会计期间。</li>
 * <li>循环比较发票会计期间和入库单的会计期间，如果发票的会计期间晚于入库单的会计期间，要求采购入库单未结算部分必须暂估（因为结算部分已不可开票，
 * 所以就只剩暂估了），才可以开票。如果没有，不可以开票。提示“采购入库单XXX，行号YYYY，必须暂估才可开票。”多行一起提示。
 * 行号YYY。”多个入库单行一起提示。</li>
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-8-23 下午01:31:55
 * @author liuyand
 */
public class InvoiceFiscalPeriodNChk {

  public UFBoolean checkFiscalPeriod(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return UFBoolean.FALSE;
    }
    InvoiceVO vo = (InvoiceVO) aggVO;
    InvoiceFiscalPeriodUtil util = new InvoiceFiscalPeriodUtil();
    Map<String, InvoiceItemVO> pkPurInvItemVOMap =
        util.getInvItemVosGenByPurchanseIn(vo.getChildrenVO());
    // 如果发票的来源不是采购入库单，直接返回
    if (pkPurInvItemVOMap.size() == 0) {
      return UFBoolean.TRUE;
    }

    List<PurchaseInBodyVO> errInBodyVoList =
        util.checkFiscalPeriod(vo.getParentVO(), pkPurInvItemVOMap, false);
    if (errInBodyVoList.size() < 1) {
      return UFBoolean.TRUE;
    }

    errInBodyVoList = this.checkNest(errInBodyVoList);
    if (errInBodyVoList.size() < 1) {
      return UFBoolean.TRUE;
    }

    String errMsg = this.constructErrMsg(errInBodyVoList);
    if (StringUtils.isNotBlank(errMsg)) {
      throw new BusinessException(errMsg);
    }

    return UFBoolean.TRUE;
  }

  /**
   * 校验暂估
   * 采购入库单未结算部分必须暂估（因为结算部分已不可开票，所以就只剩暂估了），才可以开票。如果没有，不可以开票。
   * 
   * @param list 校验失败的采购入库单表体VO列表
   * @return
   */
  private List<PurchaseInBodyVO> checkNest(List<PurchaseInBodyVO> list) {
    List<PurchaseInBodyVO> errVOList = new ArrayList<PurchaseInBodyVO>();
    Map<String, PurchaseInBodyVO> pkInBodyVOMap =
        this.getPurchaseInBodyPkVOMap(list);
    Map<String, PurchaseinFIItemVO> pkFiItemVOMap =
        this.getFIItemVOMap(pkInBodyVOMap);
    for (PurchaseInBodyVO inBodyVO : list) {
      PurchaseinFIItemVO fiItemVO =
          pkFiItemVOMap.get(inBodyVO.getCgeneralbid());
      // 如果没有取到，不处理
      if (fiItemVO == null) {
        continue;
      }
      // 如果已结算完成，不处理
      if (UFBoolean.TRUE.equals(fiItemVO.getBsettlefinish())) {
        continue;
      }

      UFDouble ninnum = fiItemVO.getNinnum();
      UFDouble naccumsettlenum = fiItemVO.getNaccumsettlenum();
      UFDouble nestnum = fiItemVO.getNestnum();
      UFDouble naccestcoststtlnum = fiItemVO.getNaccestcoststtlnum();
      // 如果 暂估主数量-暂估部分累计结算数量>=入库主数量-累计结算数量 返回true(校验通过)，否则返回false
      if (MathTool.compareTo(MathTool.sub(nestnum, naccestcoststtlnum),
          MathTool.sub(ninnum, naccumsettlenum)) >= 0) {
        continue;
      }
      errVOList.add(pkInBodyVOMap.get(fiItemVO.getPk_stockps_b()));
    }
    return errVOList;
  }

  /**
   * 获取错误信息文本
   * 
   * @param errInBodyVoList 出错的采购入库单VO列表
   * @return 出错信息文本
   */
  private String constructErrMsg(List<PurchaseInBodyVO> errInBodyVoList) {
    StringBuffer errRow = new StringBuffer();
    Set<String> hidSet = new HashSet<String>();
    for (PurchaseInBodyVO inBodyVO : errInBodyVoList) {
      hidSet.add(inBodyVO.getCgeneralhid());
    }

    VOQuery<PurchaseInHeadVO> query =
        new VOQuery<PurchaseInHeadVO>(PurchaseInHeadVO.class);
    PurchaseInHeadVO[] purchaseInHeadVOs =
        query.query(hidSet.toArray(new String[hidSet.size()]));

    Map<String, String> pkHeadVo = new HashMap<String, String>();
    for (PurchaseInHeadVO headVO : purchaseInHeadVOs) {
      pkHeadVo.put(headVO.getCgeneralhid(), headVO.getVbillcode());
    }

    // 采购入库单表头主键
    String cgeneralhid = null;
    // 采购入库单表体行号
    StringBuffer rowNos = new StringBuffer();
    for (PurchaseInBodyVO inBodyVO : errInBodyVoList) {
      if (cgeneralhid == null) {
        cgeneralhid = inBodyVO.getCgeneralhid();
      }
      rowNos.append("[").append(inBodyVO.getCrowno()).append("]");
    }

    if (rowNos.length() > 0) {
      errRow.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004050_0", "04004050-0126", null, new String[] {
            cgeneralhid, rowNos.toString()
          })/*
             * @res
             * 采购入库单[{0}]行号{1}，必须暂估才可开票。\n"
             */);
    }

    return errRow.toString();
  }

  /**
   * 根据采购入库单表体VO列表，获取采购入财务体Map
   * 
   * @param list 采购入库单表体VO列表
   * @return 采购入财务体Map。key-主键，val-vo
   */
  private Map<String, PurchaseinFIItemVO> getFIItemVOMap(
      Map<String, PurchaseInBodyVO> pkVOMap) {
    // 取采购入库单表体VO
    VOQuery<PurchaseinFIItemVO> query =
        new VOQuery<PurchaseinFIItemVO>(PurchaseinFIItemVO.class);
    PurchaseinFIItemVO[] fiItemVOs =
        query.query(pkVOMap.keySet().toArray(new String[pkVOMap.size()]));

    Map<String, PurchaseinFIItemVO> pkFiItemVOMap =
        new HashMap<String, PurchaseinFIItemVO>();
    for (PurchaseinFIItemVO itemVO : fiItemVOs) {
      pkFiItemVOMap.put(itemVO.getPk_stockps_b(), itemVO);
    }
    return pkFiItemVOMap;
  }

  /**
   * 获取主键VOMap
   * 
   * @param list
   * @return
   */
  private Map<String, PurchaseInBodyVO> getPurchaseInBodyPkVOMap(
      List<PurchaseInBodyVO> list) {
    Map<String, PurchaseInBodyVO> pkVOMap =
        new HashMap<String, PurchaseInBodyVO>();
    for (PurchaseInBodyVO inBodyVO : list) {
      pkVOMap.put(inBodyVO.getCgeneralbid(), inBodyVO);
    }
    return pkVOMap;
  }
}
