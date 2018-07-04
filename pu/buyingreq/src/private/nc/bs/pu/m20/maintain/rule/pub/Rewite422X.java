package nc.bs.pu.m20.maintain.rule.pub;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m422x.pu.m20.IReWrite422xFor20;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Rewite422X implements IRewite {

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    try {
      IReWrite422xFor20 reWrite422xFor20 =
          NCLocator.getInstance().lookup(IReWrite422xFor20.class);
      reWrite422xFor20.backWriteNum(this.buildParaArray(rwParas));
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private WriteBack422XVO[] buildParaArray(List<RewritePara> rwParas) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }

    WriteBack422XVO[] paraArray = new WriteBack422XVO[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara rwPara = rwParas.get(i);

      WriteBack422XVO wbVo = new WriteBack422XVO();
      wbVo.setPk_storereq(rwPara.getCsrcid());
      wbVo.setPk_storereq_b(rwPara.getCsrcbid());
      wbVo.setDiffNum(rwPara.getNnum());
      wbVo.setPk_downbill_b(rwPara.getCbill_bid());
      paraArray[i] = wbVo;
    }
    return paraArray;
  }

}
