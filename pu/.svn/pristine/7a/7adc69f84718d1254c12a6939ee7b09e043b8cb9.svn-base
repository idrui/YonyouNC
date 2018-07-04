package nc.bs.pu.m24.maintain.rewrite;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.ic.m45.m24.IParameter45For24;
import nc.pubitf.ic.m45.m24.IRewrite45For24;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写采购入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-4 下午02:38:46
 */
public abstract class AbstractWrite45 implements IWriteBackSource {

  private ItemKeyMapping mapping;

  private PricestlVO[] pricestlVOs;

  /**
   * 方法功能描述：取得单据回写上游数量的工具类。
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapping1
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午01:16:33
   */
  public BillRewriter getBillRewriter(ItemKeyMapping mapping1) {
    BillRewriter tool = new BillRewriter(mapping1);
    tool.addSRCHeadClazz(ICBillType.PurchaseIn.getCode(),
        PurchaseInHeadVO.class);
    tool.addSRCItemClazz(ICBillType.PurchaseIn.getCode(),
        PurchaseInBodyVO.class);
    return tool;
  }

  public ItemKeyMapping getItemKeyMapping() {
    if (this.mapping == null) {
      this.mapping = new ItemKeyMapping();
      this.mapping.setVsrctypeKey(PricestlItemVO.CSOURCETYPE);
      this.mapping.setCsrcidKey(PricestlItemVO.CSOURCEID);
      this.mapping.setCsrcbidKey(PricestlItemVO.CSOURCEBID);
      this.mapping.setNnumKey(PricestlItemVO.NINNUM);
      this.mapping.setNnum2Key(PricestlItemVO.NASTINNUM);
    }
    return this.mapping;
  }

  /**
   * @return pricestlVOs
   */
  public PricestlVO[] getPricestlVOs() {
    return this.pricestlVOs;
  }

  public abstract List<RewritePara> getRewriteParaList(PricestlVO aggVO,
      PricestlVO originAggVO);

  /**
   * @param pricestlVOs
   *          要设置的 pricestlVOs
   */
  public void setPricestlVOs(PricestlVO[] pricestlVOs) {
    this.pricestlVOs = pricestlVOs;
  }

  @Override
  public void writeback(List<RewritePara> rwParas) {
    if ((rwParas == null) || rwParas.isEmpty()) {
      return;
    }

    // 构造回写参数
    IParameter45For24[] paraArray = this.buildParaArray(rwParas);

    IRewrite45For24 service =
        nc.bs.framework.common.NCLocator.getInstance().lookup(
            IRewrite45For24.class);
    try {
      service.rewrite45AccumsettlenumFor24(paraArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private IParameter45For24[] buildParaArray(List<RewritePara> rwParas) {
    if ((rwParas == null) || rwParas.isEmpty()) {
      return null;
    }

    IParameter45For24[] paraArray = new Parameter45For24[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara rwPara = rwParas.get(i);
      Parameter45For24 para = new Parameter45For24(rwPara);

      paraArray[i] = para;
    }
    return paraArray;
  }
}
