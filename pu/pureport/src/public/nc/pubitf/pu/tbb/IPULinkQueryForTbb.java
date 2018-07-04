package nc.pubitf.pu.tbb;

import java.awt.Container;

import nc.vo.pub.BusinessException;
import nc.vo.tb.dailyexe.NtbLinkQueryData;

/**
 * 采购管理取数联查采购单据
 * 
 * @since 6.0
 * @version 2011-6-22 下午03:08:49
 * @author wuxla
 */

public interface IPULinkQueryForTbb {
  /**
   * 采购管理取数联查采购单据
   * 
   * @param data 取数参数
   * @param parent 取数的操作界面
   * @throws BusinessException
   */

  void linkQueryForTbb(NtbLinkQueryData data, Container parent)
      throws BusinessException;
}
