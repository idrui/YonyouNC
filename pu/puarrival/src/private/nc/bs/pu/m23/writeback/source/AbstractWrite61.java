package nc.bs.pu.m23.writeback.source;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.sc.m61.pu.ISCOrderFor23Ref;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sc.m61.entity.SCOrderHeaderVO;
import nc.vo.sc.m61.entity.SCOrderItemVO;
import nc.vo.sc.m61.entity.wrtbck.SCOrdWrtbckVOForRef;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

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
public abstract class AbstractWrite61 {

  private ArrivalUIToBSEnv env;

  private ItemKeyMapping keymapping;

  public AbstractWrite61(ArrivalUIToBSEnv env) {
    this.env = env;
  }

  public BillRewriter getBillRewriter(ItemKeyMapping mapping) {
    BillRewriter tool = new BillRewriter(mapping);
    tool.addSRCHeadClazz(SCBillType.Order.getCode(), SCOrderHeaderVO.class);
    tool.addSRCItemClazz(SCBillType.Order.getCode(), SCOrderItemVO.class);
    return tool;
  }

  public ItemKeyMapping getItemKeyMapping() {
    if (this.keymapping == null) {
      this.keymapping = new ItemKeyMapping();
      this.keymapping.setVsrctypeKey(ArriveItemVO.CSOURCETYPECODE);
      this.keymapping.setCsrcbidKey(ArriveItemVO.CSOURCEBID);
      this.keymapping.setCsrcidKey(ArriveItemVO.CSOURCEID);
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
    // 构造回写回写委外订单的累计到货数量、途耗数量的参数
    SCOrdWrtbckVOForRef[] totalParas = this.buildParaArray(rwParas, vos);
    if (ArrayUtils.isEmpty(totalParas)) {
      return;
    }
    // 区分出到货、退货，调用不同的回写方法
    List<SCOrdWrtbckVOForRef> nomalParas = new ArrayList<SCOrdWrtbckVOForRef>();
    List<SCOrdWrtbckVOForRef> backParas = new ArrayList<SCOrdWrtbckVOForRef>();
    for (SCOrdWrtbckVOForRef para : totalParas) {
      String scbid = para.getBid();
      ArriveVO vo = this.findArriveVOBySCBid(vos, scbid);
      if (vo != null && ValueUtils.getBoolean(vo.getHVO().getBisback())) {
        backParas.add(para);
      }
      else {
        nomalParas.add(para);
      }
    }
    ISCOrderFor23Ref service =
        NCLocator.getInstance().lookup(ISCOrderFor23Ref.class);
    try {
      if (nomalParas.size() > 0) {
        service.writeback(nomalParas.toArray(new SCOrdWrtbckVOForRef[0]));
      }
      if (backParas.size() > 0) {
        service.writebackForRet(backParas.toArray(new SCOrdWrtbckVOForRef[0]));
      }
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private SCOrdWrtbckVOForRef[] buildParaArray(List<RewritePara> rwParas,
      ArriveVO[] vos) {
    if (rwParas == null || rwParas.isEmpty() || vos == null) {
      return null;
    }
    SCOrdWrtbckVOForRef[] paras = new SCOrdWrtbckVOForRef[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      String schid = rwParas.get(i).getCsrcid();
      String scbid = rwParas.get(i).getCsrcbid();
      UFDouble diffNum = rwParas.get(i).getNnum();
      UFDouble diffNum2 = rwParas.get(i).getNnum2();
      paras[i] = new SCOrdWrtbckVOForRef(schid, scbid, diffNum, diffNum2);
      ArriveVO vo = this.findArriveVOBySCBid(vos, scbid);
      if (vo != null) {
        paras[i].setRefBillcode(vo.getHVO().getVbillcode());
      }
      paras[i].setUserConfrm(this.env == null ? null : this.env.getbConfirm());
    }
    return paras;
  }

  private ArriveVO findArriveVOBySCBid(ArriveVO[] vos, String scbid) {
    if (StringUtils.isEmpty(scbid) || vos == null) {
      return null;
    }
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        String sourcebid = item.getCsourcebid();
        if (scbid.equals(sourcebid)) {
          return vo;
        }
      }
    }
    return null;
  }
}
