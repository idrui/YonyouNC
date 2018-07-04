package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.ic.M49PUServices;
import nc.pubitf.ic.general.IICGenRewritePara;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写库存借入单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-29 下午05:56:42
 */
public class WriteBack49 implements IWriteBackSource {

  @Override
  public void writeback(List<RewritePara> rwParas) {
    M49PUServices.writeback49For21(this.getWritebackPara(rwParas));
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
