package nc.pubitf.pu.m21.dm.m4804;

import nc.vo.pu.pub.writeback.IWriteBackPubPara;

/**
 * 运输单回写
 * 
 * @since 6.0
 * @version 2010-12-14 下午03:04:50
 * @author wuxla
 */

public interface IOrderWriteBackParaFor4804 extends IWriteBackPubPara {
  /**
   * 到货计划
   * 
   * @return
   */
  String getBBID();
}
