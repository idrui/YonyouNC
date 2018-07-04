/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-12 上午08:54:51
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.invp.po.IpoOrderForPu;
import nc.pubitf.invp.po.PoOrderWritebackVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购订单回写库存计划计划订单
 * 
 * @since 6.0
 * @version 2011-12-9 上午10:59:14
 * @author 田锋涛
 */
public class WriteBack4F implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      this.getWriteBackService().writebackForPu(this.getWriteBackPara(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 构造回写参数
   * 
   * @param rwParas
   * @return 库存计划计划订单回写参数
   */
  private PoOrderWritebackVO[] getWriteBackPara(List<RewritePara> rwParas) {
    PoOrderWritebackVO[] paras = new PoOrderWritebackVO[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      final RewritePara rwPara = rwParas.get(i);
      paras[i] = new PoOrderWritebackVO();
      paras[i].setBsc(UFBoolean.FALSE);// 委外标志
      paras[i].setDiffNum(rwPara.getNnum());// 差异数量
      paras[i].setPk_po(rwPara.getCsrcid());// 来源id
    }
    return paras;
  }

  /**
   * 库存计划计划订单回服务
   * 
   * @return
   */
  private IpoOrderForPu getWriteBackService() {
    return NCLocator.getInstance().lookup(IpoOrderForPu.class);
  }

}
