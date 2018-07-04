package nc.pubimpl.pu.m23.qc;

import nc.bs.pu.m23.writeback.qc.Writeback23ForC005AuditBP;
import nc.bs.pu.m23.writeback.qc.Writeback23ForC005OpenCloseBP;
import nc.bs.pu.m23.writeback.qc.Writeback23ForC005SaveAndDeleteBP;
import nc.pubitf.pu.m23.qc.IWriteback23ForC005;
import nc.pubitf.pu.m23.qc.Writeback23ForC005Para;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给质量的紧急放行单的回写累计紧急放行数量的服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-29 上午11:24:46
 */
public class Writeback23ForC005Impl implements IWriteback23ForC005 {

  @Override
  public void writebackWhenApprove(Writeback23ForC005Para[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC005AuditBP().writebackWhenApprove(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void writebackWhenClose(Writeback23ForC005Para[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC005OpenCloseBP().writebackWhenClose(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void writebackWhenDelete(Writeback23ForC005Para[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC005SaveAndDeleteBP().writebackWhenDelete(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void writebackWhenOpen(Writeback23ForC005Para[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC005OpenCloseBP().writebackWhenOpen(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void writebackWhenSave(Writeback23ForC005Para[] paras)
      throws BusinessException {

    try {
      new Writeback23ForC005SaveAndDeleteBP().writebackWhenSave(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void writebackWhenUnApprove(Writeback23ForC005Para[] paras)
      throws BusinessException {
    try {
      new Writeback23ForC005AuditBP().writebackWhenUnApprove(paras);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
