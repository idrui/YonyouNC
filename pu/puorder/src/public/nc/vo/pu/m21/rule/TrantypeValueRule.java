package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 获取交易类型，首先根据集团+库存组织+采购组织+物料匹配物料订单类型，没有匹配到的情况下查询接口关系定义。
 * 
 * @since 6.1
 * @version 2012-2-14 下午01:46:49
 * @author lixyp
 */
public class TrantypeValueRule {
  private OrderVO[] orderVOs = null;

  public TrantypeValueRule(OrderVO[] orderVOs) {
    this.orderVOs = orderVOs;
  }

  public void setTrantypeValue() {
    // 从物料订单类型中提取所有匹配的交易类型。
    Map<String, String> trantypeMap = this.getTranstype();

    for (OrderVO orderVO : this.orderVOs) {
      String pk_org = orderVO.getHVO().getPk_org();

      // 如果已经存在交易类型，则直接指定业务流程后退出。
      // if (orderVO.getHVO().getVtrantypecode() != null) {
      // this.setBusitype(orderVO);
      // continue;
      // }

      String trantype = null;
      for (OrderItemVO orderItemVO : orderVO.getBVO()) {
        String pk_srcMaterial = orderItemVO.getPk_srcmaterial();
        // 和王姐确认过，应该是需求库存组织
        String stockorg = orderItemVO.getPk_reqstoorg();
        String pk = pk_org + stockorg + pk_srcMaterial;
        trantype = trantypeMap.get(pk);
        if (trantype != null) {
          break;
        }
      }

      if (trantype == null) {
        trantype = this.getDestTransType(orderVO);
      }
      if (trantype != null) {
        orderVO.getHVO().setVtrantypecode(trantype);
        orderVO.getHVO().setCtrantypeid(
            PfServiceScmUtil.getTrantypeidByCode(new String[] {
              trantype
            }).get(trantype));
        this.setBusitype(orderVO);
      }
    }
  }

  /**
   * 从接口关系定义中查找交易类型。
   * 
   * @param orderVO
   * @return 交易类型
   */
  private String getDestTransType(OrderVO orderVO) {
    OrderItemVO[] orderItemVOs = orderVO.getBVO();
    if (orderItemVOs.length > 0) {
      String billtype = orderItemVOs[0].getCsourcetypecode();
      String trantype = orderItemVOs[0].getVsourcetrantype();
      if (StringUtils.isEmpty(billtype) && StringUtils.isEmpty(trantype)) {
        return null;
      }

      TransTypeMapping mapping = new TransTypeMapping();
      mapping.setSrcBillType(billtype);
      mapping.setSrcTransType(trantype);
      mapping.setDestBillType(POBillType.Order.getCode());

      // 取得订单类型
      PfBillItfDefUtil.queryTransTypeMapping(orderVO.getHVO().getPk_group(),
          mapping);
      return mapping.getDestTransTypeCode();
    }

    return null;
  }

  /**
   * 方法功能描述：根据集团、采购组织、库存组织和物料在物料订单类型设置中查找订单类型。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   *         key为库存组织+采购组织+物料。value为交易类型
   * @since 6.1
   * @author lixy
   * @time 2010-12-23 上午10:13:19
   */
  private Map<String, String> getTranstype() {
    Map<String, String> retMap = new HashMap<String, String>();
    if (this.orderVOs.length == 0) {
      return retMap;
    }

    // 声明查询接口必要的四个参数。
    String pk_group = this.orderVOs[0].getHVO().getPk_group();
    List<String> materialList = new ArrayList<String>();
    List<String> pk_orgs = new ArrayList<String>();
    List<String> stockOrgs = new ArrayList<String>();

    // 收集上面声明的四个参数
    for (OrderVO orderVO : this.orderVOs) {
      // 采购组织
      String pk_org = orderVO.getHVO().getPk_org();
      for (OrderItemVO orderItemVO : orderVO.getBVO()) {
        // 物料
        materialList.add(orderItemVO.getPk_srcmaterial());
        pk_orgs.add(pk_org);
        // 和王姐确认需求库存组织
        stockOrgs.add(orderItemVO.getPk_reqstoorg());
      }
    }
    // 调用接口查询。
    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    retMap =
        service.getTranstypeByOrg(pk_group,
            pk_orgs.toArray(new String[pk_orgs.size()]),
            materialList.toArray(new String[materialList.size()]),
            stockOrgs.toArray(new String[stockOrgs.size()]));

    return retMap;
  }

  /**
   * 设置流程
   * 
   * @param orderVO
   */
  private void setBusitype(OrderVO orderVO) {
    // 确定业务流程，以备走流程函数
    new BusitypeFillRule(new BillHelper<OrderVO>(orderVO),
        POBillType.Order.getCode()).process();
  }
}
