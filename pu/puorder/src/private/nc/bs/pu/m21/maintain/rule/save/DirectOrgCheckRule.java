package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.so.M30SOServices;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * @description
 *              采购订单直运采购组织检查
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午1:45:09
 * @author luojw
 */

public class DirectOrgCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    Map<String, PoTransTypeVO> trantypeMap = this.getTrantypeMap(vos);
    Set<String> arrvOrgSet = new HashSet<String>();
    Set<String> cfirstbidSet = new HashSet<String>();
    List<OrderVO> checkVO = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      PoTransTypeVO trantypeVO = trantypeMap.get(vo.getHVO().getCtrantypeid());
      if (null == trantypeVO) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004030_0", "04004030-0234", null, new String[] {
              vo.getHVO().getVtrantypecode()
            })/* 采购订单交易类型{0}扩展表没有数据，请检查 */);
        return;
      }
      if (UFBoolean.FALSE.equals(trantypeVO.getBdirect())) {
        continue;
      }
      checkVO.add(vo);
      for (OrderItemVO itemVO : vo.getBVO()) {
        arrvOrgSet.add(itemVO.getPk_arrvstoorg());
        if (SOBillType.Order.getCode().equals(itemVO.getCfirsttypecode())) {
          cfirstbidSet.add(itemVO.getCfirstbid());
        }
      }
    }
    if (0 == checkVO.size()) {
      return;
    }

    String[] arrvOrgs = arrvOrgSet.toArray(new String[arrvOrgSet.size()]);
    Map<String, String> stockOrgMap =
        StockOrgPubService.queryFinanceOrgIDByStockOrgID(arrvOrgs);

    Map<String, String> financeMap = null;
    if (cfirstbidSet.size() > 0) {
      String[] cfirstbids =
          cfirstbidSet.toArray(new String[cfirstbidSet.size()]);
      financeMap = M30SOServices.getFinanceorgByBids(cfirstbids);
    }
    for (OrderVO vo : checkVO) {
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_psfinanceorg = itemVO.getPk_psfinanceorg();
        String cfirstbid = itemVO.getCfirstbid();
        String sofinanceorg =
            financeMap != null ? financeMap.get(cfirstbid) : null;
        if (sofinanceorg != null && !sofinanceorg.equals(pk_psfinanceorg)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0344")/*
                                                                       * @res
                                                                       * "直运采购订单，结算财务组织必须等于销售订单结算财务组织"
                                                                       */);
        }
        String financeorg = stockOrgMap.get(itemVO.getPk_arrvstoorg());
        if (!pk_psfinanceorg.equals(financeorg)) {
          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0118")/*
                                                                       * @res
                                                                       * "直运采购订单收货库存组织所属财务组织必须与结算财务组织相同"
                                                                       */);
        }
      }
    }
  }

  private Map<String, PoTransTypeVO> getTrantypeMap(OrderVO[] vos) {
    Set<String> trantypeSet = new HashSet<String>();
    for (OrderVO vo : vos) {
      trantypeSet.add(vo.getHVO().getCtrantypeid());
    }
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return service.queryAttrByIDs(trantypeSet.toArray(new String[trantypeSet
          .size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
