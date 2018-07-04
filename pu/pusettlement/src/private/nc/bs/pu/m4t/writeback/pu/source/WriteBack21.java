/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 下午01:14:20
 */
package nc.bs.pu.m4t.writeback.pu.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.pubitf.pu.m21.pu.m4t.IOrderWriteBackFor4T;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 下午01:14:20
 */
public class WriteBack21 implements IInitialEstWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.m4t.writeback.pu.source.IInitialEstWriteBackSource#writeback(java.util.List,
   *      nc.vo.pu.m4t.entity.InitialEstContext)
   */
  @Override
  public void writeback(List<RewritePara> rwParas, InitialEstContext context) {
    IOrderWriteBackFor4T service =
        NCLocator.getInstance().lookup(IOrderWriteBackFor4T.class);
    try {
      service.writeBackFor4T(this.createWriteBackVO(rwParas, context));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private IOrderWriteBackPara[] createWriteBackVO(List<RewritePara> rwParas,
      final InitialEstContext context) {
    IOrderWriteBackPara[] wbVos = new IOrderWriteBackPara[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      final RewritePara para = rwParas.get(i);
      wbVos[i] = new IOrderWriteBackPara() {

        @Override
        public String getBBID() {
          return null;
        }

        @Override
        public String getBID() {
          return para.getCsrcbid();
        }

        @Override
        public UFDouble getDiffNum() {
          return para.getNnum();
        }

        @Override
        public String getHID() {
          return para.getCsrcid();
        }

        @Override
        public boolean isClose() {
          return false;
        }

        @Override
        public boolean isNumUserConfirm() {
          return context.isUserComfirm();
        }

        @Override
        public boolean isReturn() {
          return false;
        }
      };
    }
    return wbVos;
  }
}
