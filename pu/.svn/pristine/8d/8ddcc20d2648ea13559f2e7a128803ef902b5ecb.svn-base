package nc.pubimpl.pu.m20.aim.action;

import nc.bs.pu.m20.maintain.PraybillInsertBP;
import nc.vo.pu.m20.entity.PraybillVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 资产配置申请推式保存请购单
 * 
 * @since 6.5
 * @version 2014-1-16 下午03:49:14
 * @author fanly3
 */
public class PushSave20For4A08Action {

  public void pushSave(PraybillVO[] praybills) {
    if (ArrayUtils.isEmpty(praybills)) {
      return;
    }
    new PraybillInsertBP().insert(praybills);
  }
}
