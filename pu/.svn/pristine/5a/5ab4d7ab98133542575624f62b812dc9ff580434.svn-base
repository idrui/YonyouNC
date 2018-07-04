package nc.pubimpl.pu.m23.qc;

import nc.bs.pu.m23.writeback.qc.Writeback23ForC001BP;
import nc.pubitf.pu.m23.qc.IWriteback23ForC001;
import nc.pubitf.pu.m23.qc.Writeback23ForC001Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class Writeback23ForC001Impl implements IWriteback23ForC001 {

  @Override
  public void writebackNum(Writeback23ForC001Para[] paras)
      throws BusinessException {
    // µ÷ÓÃBP
    try {
      Writeback23ForC001BP bp = new Writeback23ForC001BP();
      bp.writeback(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
