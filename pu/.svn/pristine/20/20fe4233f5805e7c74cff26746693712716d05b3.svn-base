package nc.bs.pu.m23.writeback.source;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackFor23;
import nc.pubitf.pu.m21.pu.m23.IOrderWriteBackParaFor23;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写委外订单的累计到货数量、途耗数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-26 上午11:37:23
 */
public abstract class AbstractWrite21 {

  private ArrivalUIToBSEnv env;

  private ItemKeyMapping keymapping;

  public AbstractWrite21(ArrivalUIToBSEnv env) {
    this.env = env;
  }

  public BillRewriter getBillRewriter(ItemKeyMapping mapping) {
    BillRewriter tool = new BillRewriter(mapping);
    tool.addSRCHeadClazz(POBillType.Order.getCode(), OrderHeaderVO.class);
    tool.addSRCItemClazz(POBillType.Order.getCode(), OrderItemVO.class);
    return tool;
  }

  public ItemKeyMapping getItemKeyMapping() {
    if (this.keymapping == null) {
      this.keymapping = new ItemKeyMapping();
      this.keymapping.setVsrctypeKey(ArriveItemVO.CSOURCETYPECODE);
      this.keymapping.setCsrcidKey(ArriveItemVO.CSOURCEID);
      this.keymapping.setCsrcbidKey(ArriveItemVO.CSOURCEBID);
      this.keymapping.setCsrcbbidKey(ArriveItemVO.PK_ORDER_BB1);
      this.keymapping.setNnumKey(ArriveItemVO.NNUM);// 到货主数量
      this.keymapping.setNnum2Key(ArriveItemVO.NWASTNUM);// 途耗主数量
      this.keymapping.setSrcTSKey(ArriveItemVO.SOURCETS);
      this.keymapping.setSrcbTSKey(ArriveItemVO.SOURCEBTS);

    }
    return this.keymapping;
  }

  public void writeback(List<RewritePara> rwParas, ArriveVO[] vos) {
    if (rwParas == null || rwParas.isEmpty() || vos == null) {
      return;
    }

    // 构造回写回写采购订单的累计到货数量、途耗数量的参数
    IOrderWriteBackParaFor23[] paraArray = this.buildParaArray(rwParas, vos);
    IOrderWriteBackFor23 service =
        NCLocator.getInstance().lookup(IOrderWriteBackFor23.class);
    try {
      service.writeBackFor23(paraArray, this.env == null ? null : this.env
          .getbConfirm());
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private IOrderWriteBackParaFor23[] buildParaArray(List<RewritePara> rwParas,
      ArriveVO[] vos) {
    if (rwParas == null || rwParas.isEmpty() || vos == null) {
      return null;
    }
    Set<String> close21BIdSet = this.getClose21BIdSet(vos);
    // 用于判断是否退货
    Set<String> set = new HashSet<String>();
    for (ArriveVO arriveVO : vos) {
      if (arriveVO.getHVO().getBisback().booleanValue()) {
        for (ArriveItemVO arriveItemVO : arriveVO.getBVO()) {
          set.add(arriveItemVO.getPk_arriveorder_b());
        }
      }
    }
    IOrderWriteBackParaFor23[] paraArray =
        new OrderWriteBackParaFor23[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara rwPara = rwParas.get(i);
      OrderWriteBackParaFor23 para = new OrderWriteBackParaFor23(rwPara);
      if (set.contains(rwPara.getCbill_bid())) {
        para.setReturn(true);
      }
      if (close21BIdSet.contains(rwPara.getCsrcbid())) {
        para.setClose(true);
      }
      paraArray[i] = para;
    }
    return paraArray;
  }

  private Set<String> getClose21BIdSet(ArriveVO[] vos) {
    Set<String> bidSet = new HashSet<String>();
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        if (UFBoolean.TRUE.equals(item.getBcloseorder())) {
          bidSet.add(item.getCsourcebid());
        }
      }
    }
    return bidSet;
  }
}
