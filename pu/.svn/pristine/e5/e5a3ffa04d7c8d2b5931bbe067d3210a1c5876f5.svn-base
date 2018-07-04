package nc.pubimpl.pu.m20.aim.m4a08;

import nc.pubimpl.pu.m20.aim.action.Delete20For4A08Action;
import nc.pubitf.pu.m20.aim.m4A08.IDelete20For4A08;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 资产配置申请取消审批时删除下游请购单接口实现类
 * 
 * @since 6.5
 * @version 2014-2-14 上午10:40:39
 * @author fanly3
 */
public class Delete20For4A08Impl implements IDelete20For4A08 {

  @Override
  public void deletePrayBills(String[] pk_table, String[] pk_table_bs)
      throws BusinessException {
    try {
      new Delete20For4A08Action().delete(pk_table, pk_table_bs);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
