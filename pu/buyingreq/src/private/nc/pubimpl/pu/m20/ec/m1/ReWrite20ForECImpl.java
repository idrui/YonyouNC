package nc.pubimpl.pu.m20.ec.m1;

import nc.bs.pu.m20.rewrite.ec.ReWrite20ForECBP;
import nc.pubitf.pu.m20.ec.m1.IReWrite20ForM1;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>电子商务回写请购单实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-27 上午11:48:10
 */
public class ReWrite20ForECImpl implements IReWrite20ForM1 {

  @Override
  public void backWrite(String[] pkPraybillBs) throws BusinessException {
    try {
      new ReWrite20ForECBP().backWrite(pkPraybillBs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

}
