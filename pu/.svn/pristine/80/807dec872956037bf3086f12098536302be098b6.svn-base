/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-4 上午11:42:47
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.pubitf.ic.general.IICGenRewritePara;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写退库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-4 上午11:42:47
 */
public class WriteBack45 implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    M45PUServices.writeback45For21(this.getWritebackPara(rwParas));
  }

  private IICGenRewritePara[] getWritebackPara(List<RewritePara> rwParas) {
    IICGenRewritePara[] paras = new IICGenRewritePara[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      final RewritePara rwPara = rwParas.get(i);
      paras[i] = new IICGenRewritePara() {

        @Override
        public String getBid() {
          return rwPara.getCsrcbid();
        }

        @Override
        public String getHid() {
          return rwPara.getCsrcid();
        }

        @Override
        public UFDouble getNum1() {
          return rwPara.getNnum();
        }

        @Override
        public UFDouble getNum2() {
          return null;
        }
      };
    }

    return paras;
  }

}
