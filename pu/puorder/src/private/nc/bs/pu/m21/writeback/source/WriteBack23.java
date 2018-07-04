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
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写退货单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-12 上午08:54:51
 */
public class WriteBack23 implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      this.getWriteBackService().writebackNum(this.getWriteBackPara(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private IWriteback23For21Para[] getWriteBackPara(List<RewritePara> rwParas) {
    IWriteback23For21Para[] paras = new IWriteback23For21Para[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      final RewritePara rwPara = rwParas.get(i);
      paras[i] = new IWriteback23For21Para() {

        @Override
        public String getBID() {
          return rwPara.getCsrcbid();
        }

        @Override
        public UFDouble getDiffNum() {
          return rwPara.getNnum();
        }

        @Override
        public String getHID() {
          return rwPara.getCsrcid();
        }
      };
    }
    return paras;
  }

  private IWriteback23For21 getWriteBackService() {
    return NCLocator.getInstance().lookup(IWriteback23For21.class);
  }

}
