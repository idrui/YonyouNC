/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-15 上午11:20:24
 */
package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单的各种上VO的处理工具类
 * <li>例如VO的转换
 * <li>VO生成主键到VO的MAP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-15 上午11:20:24
 */
public class OrderVOUtil {

  private static OrderVOUtil instance = new OrderVOUtil();

  private OrderVOUtil() {
    // 私有
  }

  public static OrderVO[] getBatchCodeVO(OrderVO[] vos) {
    Map<String, UFBoolean> po44Map = new HashMap<String, UFBoolean>();
    List<OrderVO> voList = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      String pk_arrvstoorg = vo.getBVO()[0].getPk_arrvstoorg();
      if (StringUtil.isEmptyWithTrim(pk_arrvstoorg)) {
        continue;
      }
      UFBoolean po44 = po44Map.get(pk_arrvstoorg);
      if (null == po44) {
        if (PUParaValue.po44.order
            .equals(PUSysParamUtil.getPO44(pk_arrvstoorg))) {
          po44Map.put(pk_arrvstoorg, UFBoolean.TRUE);
          voList.add(vo);
        }
        else {
          po44Map.put(pk_arrvstoorg, UFBoolean.FALSE);
        }
      }
      else if (po44.booleanValue()) {
        voList.add(vo);
      }
    }

    if (0 == voList.size()) {
      return null;
    }

    return voList.toArray(new OrderVO[voList.size()]);
  }

  public static OrderVO[] getHaveBatchCodeVO(OrderVO[] vos) {
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (!StringUtil.isEmptyWithTrim(itemVO.getVbatchcode())) {
          itemList.add(itemVO);
        }
      }
      if (itemList.size() > 0) {
        OrderVO cloneVO = (OrderVO) vo.clone();
        cloneVO.setBVO(itemList.toArray(new OrderItemVO[itemList.size()]));
        list.add(cloneVO);
      }
    }
    if (list.size() > 0) {
      return list.toArray(new OrderVO[list.size()]);
    }

    return null;
  }

  public static OrderVOUtil getInsance() {
    return OrderVOUtil.instance;
  }

  public static VATInfoQueryVO[] getVatInfoQueryVO(IKeyValue bill, int[] rows) {
    VATInfoQueryVO[] vatqueryvos = new VATInfoQueryVO[rows.length];
    for (int i = 0; i < rows.length; i++) {
      vatqueryvos[i] = OrderVOUtil.buildSingleVATInfoQueryVO(bill, rows[i]);
    }
    return vatqueryvos;

  }

  /**
   * 构造VAT信息查询参数VO
   * 
   * @param bills 订单
   * @return VATInfoQueryVO[] 返回的参数与单据表体长度及顺序一致
   */
  public static VATInfoQueryVO[] getVatInfoQueryVO(IKeyValue[] bills) {
    int rowcnt = 0;
    for (IKeyValue bill : bills) {
      rowcnt += bill.getItemCount();
    }
    VATInfoQueryVO[] vatqueryvos = new VATInfoQueryVO[rowcnt];
    rowcnt = 0;
    for (IKeyValue bill : bills) {
      int billrowcnt = bill.getItemCount();
      for (int i = 0; i < billrowcnt; i++) {
        vatqueryvos[rowcnt++] = OrderVOUtil.buildSingleVATInfoQueryVO(bill, i);
      }
    }
    return vatqueryvos;

  }

  /**
   * 是否含税优先
   * 
   * @param pk_org
   * @return
   */
  public static boolean isTaxPrior(String pk_org) {
    return PricePriority.TAXPRICE_PRIOR_TO_PRICE.equals(PUSysParamUtil
        .getPO28(pk_org));
  }

  private static VATInfoQueryVO buildSingleVATInfoQueryVO(IKeyValue bill,
      int row) {
    String sendcnty =
        (String) bill.getBodyValue(row, OrderItemVO.CSENDCOUNTRYID);// 发货国
    String rececnty =
        (String) bill.getBodyValue(row, OrderItemVO.CRECECOUNTRYID);// 收货国
    String taxcnty = (String) bill.getBodyValue(row, OrderItemVO.CTAXCOUNTRYID);// 报税国
    String pk_supplier = (String) bill.getHeadValue(OrderItemVO.PK_SUPPLIER);// 供应商
    UFDate bizdate = (UFDate) bill.getHeadValue(OrderItemVO.DBILLDATE);
    Integer bsflag = (Integer) bill.getBodyValue(row, OrderItemVO.FBUYSELLFLAG);// 购销类型
    BuySellFlagEnum bsflagenum =
        null == bsflag ? null : BuySellFlagEnum.valueOf(bsflag);
    UFBoolean btriatrade =
        ValueUtils.getUFBoolean(bill.getBodyValue(row,
            OrderItemVO.BTRIATRADEFLAG));// 三角贸易
    String pk_material =
        (String) bill.getBodyValue(row, InvoiceItemVO.PK_MATERIAL);// 物料
    VATInfoQueryVO queryVO =
        new VATInfoQueryVO(taxcnty, bsflagenum, btriatrade, sendcnty, rececnty,
            pk_supplier, pk_material, bizdate);
    return queryVO;
  }

  /**
   * 方法功能描述：得到回写VO订单子表ID到VO的MAP。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-15 上午11:22:51
   */
  public Map<String, IOrderWriteBackPara> createWBMap(IOrderWriteBackPara[] vos) {
    Map<String, IOrderWriteBackPara> map =
        new HashMap<String, IOrderWriteBackPara>();
    for (IOrderWriteBackPara vo : vos) {
      map.put(vo.getBID(), vo);
    }
    return map;
  }

  /**
   * 方法功能描述：从回写VO中生成订单子表ID数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-15 上午11:23:34
   */
  public String[] getBIDs(IWriteBackPubPara[] vos) {
    Set<String> ids = new HashSet<String>();
    for (IWriteBackPubPara vo : vos) {
      if (vo.getBID() != null) {
        ids.add(vo.getBID());
      }
    }
    return ids.toArray(new String[ids.size()]);
  }

  /**
   * 到货、入库回写订单时取得上游指定了哪些行需要强行关闭
   * 
   * @param wbParas
   * @return
   */
  public Set<String> getCloseBIDSet(IOrderWriteBackPara[] wbParas) {
    Set<String> bidSet = new HashSet<String>();
    for (IOrderWriteBackPara wbPara : wbParas) {
      if (wbPara.isClose()) {
        bidSet.add(wbPara.getBID());
      }
    }
    return bidSet;
  }

  public OrderVO[] getSupplyVO(OrderVO[] vos) {
    Map<String, PoTransTypeVO> transtypeMap = this.getTransType(vos);
    List<OrderVO> list = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      String vtrantypecode = vo.getHVO().getVtrantypecode();
      PoTransTypeVO transTypeVO = transtypeMap.get(vtrantypecode);
      if (null == transTypeVO) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0234", null, new String[] {
              vtrantypecode
            })/* 采购订单交易类型{0}扩展表没有数据，请检查 */);
        return null;
      }
      if (UFBoolean.TRUE.equals(transTypeVO.getBdirect())) {
        continue;
      }
      List<OrderItemVO> itemList = new ArrayList<OrderItemVO>();
      OrderItemVO[] itemVOs = vo.getBVO();
      for (OrderItemVO itemVO : itemVOs) {
        if (itemVO.getDplanarrvdate() != null) {
          itemList.add(itemVO);
        }
      }
      if (0 == itemList.size()) {
        continue;
      }
      OrderVO reserveVO = (OrderVO) vo.clone();
      OrderItemVO[] reserveItems =
          itemList.toArray(new OrderItemVO[itemList.size()]);
      reserveVO.setBVO(reserveItems);
      list.add(reserveVO);
    }
    if (list.size() > 0) {
      OrderVO[] reserveVOs = list.toArray(new OrderVO[list.size()]);
      return reserveVOs;
    }
    return null;
  }

  private Map<String, PoTransTypeVO> getTransType(OrderVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderVO vo : vos) {
      String vtrantypecode = vo.getHVO().getVtrantypecode();
      set.add(vtrantypecode);
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return service
          .queryAttrByTypes(set.toArray(new String[set.size()]), null);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

}
