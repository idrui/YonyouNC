package nc.bs.pu.m422x.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.pm.PMServiceForPU;
import nc.vo.pbm.materialplan.MaterialPlanForSCMVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;

/**
 * 回写项目管理下得物资及服务需求单
 * 
 * @since 6.3.1
 * @version 2013-8-8 下午08:48:52
 * @author fanly3
 */
public class WriteBack4D14 implements IWriteBackSource {

  @Override
  public void writeback(List<RewritePara> rwParas) {
    // 组织回写参数
    MaterialPlanForSCMVO[] backVos = this.getWritebackVO(rwParas);
    // 调用项目提供的回写接口实现真正回写
    PMServiceForPU.writeback4D14(backVos);
  }

  /**
   * 组织回写参数VO
   * 
   * @param rwParas
   * @return 回写参数VO数组
   */
  private MaterialPlanForSCMVO[] getWritebackVO(List<RewritePara> rwParas) {
    MaterialPlanForSCMVO[] vos = new MaterialPlanForSCMVO[rwParas.size()];
    for (int i = 0; i < vos.length; i++) {
      RewritePara para = rwParas.get(i);
      vos[i] = new MaterialPlanForSCMVO();
      vos[i].setSrc_pk_bill(para.getCsrcid());
      vos[i].setSrc_pk_bill_b(para.getCsrcbid());
      // vos[i].setHeadTs(para.getSrcTS().toString());
      // vos[i].setBodyTs(para.getSrcbTS().toString());
      vos[i].setActual_num(para.getNnum());
    }

    return vos;
  }
}
