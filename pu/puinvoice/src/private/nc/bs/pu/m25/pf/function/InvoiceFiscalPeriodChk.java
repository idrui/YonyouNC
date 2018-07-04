package nc.bs.pu.m25.pf.function;

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
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.StringUtils;

/**
 * 发票的会计期间检查： 发票的会计期间不能早于入库单的会计期间
 * <p>
 * <b>本类主要完成以下功能：</b> 如果发票的来源是采购入库单，则进行如下判断。
 * <ul>
 * <li>取发票主组织财务组织的主账簿的会计期间方案，以发票表头的发票日期匹配此会计期间方案得到发票所处的会计期间。</li>
 * <li>取到采购入库单的主组织库存组织所属财务组织，取此财务组织的主账簿的会计期间方案。以采购入库单表体的入库日期匹配此会计期间方案得到入库单的会计期间
 * ，对每个采购入库单行取一个会计期间。</li>
 * <li>循环比较发票会计期间和入库单的会计期间，如果发票的会计期间早于入库单的会计期间，抛错“发票的会计期间不能早于入库单的会计期间，入库单号XXX，
 * 行号YYY。”多个入库单行一起提示。</li>
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-8-23 下午01:31:55
 * @author liuyand
 */
public class InvoiceFiscalPeriodChk {

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
        util.checkFiscalPeriod(vo.getParentVO(), pkPurInvItemVOMap, true);
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
          "4004050_0", "04004050-0127", null, new String[] {
            cgeneralhid, rowNos.toString()
          })/*
             * @res
             * 发票的会计期间不能早于入库单的会计期间，入库单号[{0}]行号{1}。\n
             */);
    }

    return errRow.toString();
  }
}
