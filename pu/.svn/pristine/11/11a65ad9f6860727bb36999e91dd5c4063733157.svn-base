package nc.bs.pu.m21.maintain.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.pu.m20.IOrderQueryFor20;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.m20.QueryParaFor20;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              采购订单来源请购单，请购单生成订单限制方式不是“不限制”，
 *              所以存在有效供应商价格才能生成或者经过价格审批才能生成。
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:44:24
 * @author luojw
 */
public class PrayToPoLimitRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    QueryParaFor20[] paras = this.createPara(vos);
    if (paras == null) {
      return;
    }
    /*
     * 采购订单来源请购单，请购单生成订单限制方式不是“不限制”，
     * 所以存在有效供应商价格才能生成或者经过价格审批才能生成。
     */
    String err =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0365");
    Set<String> filterItemIds = this.filterItemsByTrantype(paras);
    if (filterItemIds == null || filterItemIds.size() == 0) {
      ExceptionUtils.wrappBusinessException(err);
      return;
    }

    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        String csourcetype = itemVO.getCsourcetypecode();
        if (POBillType.PrayBill.getCode().equals(csourcetype)) {
          if (!filterItemIds.contains(itemVO.getCsourcebid())) {
            ExceptionUtils.wrappBusinessException(err);
          }
        }
      }
    }

  }

  private QueryParaFor20[] createPara(OrderVO[] vos) {
    List<QueryParaFor20> paraList = new ArrayList<QueryParaFor20>();
    for (OrderVO vo : vos) {
      OrderHeaderVO headvo = vo.getHVO();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String csourcetype = itemVO.getCsourcetypecode();
        if (POBillType.PrayBill.getCode().equals(csourcetype)) {
          QueryParaFor20 para = new QueryParaFor20();
          para.setCurrency(headvo.getCorigcurrencyid());
          para.setDate(vo.getHVO().getDbilldate());
          para.setPk_praybill_b(itemVO.getCsourcebid());
          para.setPk_srcmaterial(itemVO.getPk_srcmaterial());
          para.setPk_org(headvo.getPk_org());
          para.setPk_ordertrantype(headvo.getCtrantypeid());
          // wuxla V61 因为到采购订单，所以委外标记肯定为N
          // 必须设置委外标记，否则采购价格不会查询供应商价目表
          para.setBsc(UFBoolean.FALSE);
          paraList.add(para);
        }
      }
    }
    if (paraList.size() == 0) {
      return null;
    }
    return paraList.toArray(new QueryParaFor20[paraList.size()]);
  }

  private Set<String> filterItemsByTrantype(QueryParaFor20[] paras) {
    try {
      return NCLocator.getInstance().lookup(IOrderQueryFor20.class)
          .filterItemsByOrderTranType(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
