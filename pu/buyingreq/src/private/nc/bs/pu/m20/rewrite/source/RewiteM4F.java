package nc.bs.pu.m20.rewrite.source;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m20.maintain.rule.pub.IRewite;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.initgroup.InitGroupQuery;
import nc.pubitf.invp.po.IpoOrderForPu;
import nc.pubitf.invp.po.PoOrderWritebackVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;

/**
 * ��д���ƻ��ƻ�����
 * 
 * @since 6.0
 * @version 2011-12-6 ����03:58:32
 * @author �����
 */

public class RewiteM4F implements IRewite {

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, PoVO.class);
  // }

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (!this.isINVPEnabled()) {
      return;
    }
    if (rwParas == null || rwParas.isEmpty()) {
      return;
    }

    // �����д����
    PoOrderWritebackVO[] paraArray = this.buildParaArray(rwParas, bidVOMap);

    try {
      NCLocator.getInstance().lookup(IpoOrderForPu.class)
          .writebackForPu(paraArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

  }

  /**
   * ����ƻ�������д����
   * 
   * @param rwParas
   * @param bsc
   * @return �ƻ�������д����
   */
  private PoOrderWritebackVO[] buildParaArray(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }
    PoOrderWritebackVO[] paras = new PoOrderWritebackVO[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara rwPara = rwParas.get(i);
      paras[i] = new PoOrderWritebackVO();
      paras[i].setBsc(bidVOMap.get(rwParas.get(i).getCbill_bid()).getHVO()
          .getBsctype());
      paras[i].setDiffNum(rwPara.getNnum());
      paras[i].setPk_po(rwPara.getCsrcid());
    }
    return paras;
  }

  private boolean isINVPEnabled() {
    try {
      return InitGroupQuery.isEnabled(InvocationInfoProxy.getInstance()
          .getGroupId(), NCModule.INVP.getCode());
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

}
