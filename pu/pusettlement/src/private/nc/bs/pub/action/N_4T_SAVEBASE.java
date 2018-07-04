package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;
import nc.vo.uap.pf.PFBusinessException;

/**
 * ��ע���ڳ��ݹ�����SAVEBASE
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * �������ڣ�(2011-3-11)
 * 
 * @author ƽ̨�ű�����
 */
public class N_4T_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_4T_SAVEBASE ������ע�⡣
   */
  public N_4T_SAVEBASE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return "	Object retObj = null;\n      nc.vo.pu.m4t.entity.InitialEstVO[] vos =\n          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();\n      nc.vo.pu.m4t.entity.InitialEstContext[] ctxs =\n          nc.vo.pu.m4t.util.InitialContextUtil.getCtxs(vo);\n      nc.vo.pu.m4t.entity.InitialEstContext ctx =\n          org.apache.commons.lang.ArrayUtils.isEmpty(ctxs) ? new nc.vo.pu.m4t.entity.InitialEstContext()\n              : ctxs[0];\n      retObj =\n          nc.bs.framework.common.NCLocator.getInstance()\n              .lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).save(vos, ctx);\n      return retObj;\n";
  }

  /*
   * ��ע��ƽ̨��д������
   * �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      Object retObj = null;
      nc.vo.pu.m4t.entity.InitialEstVO[] vos =
          (nc.vo.pu.m4t.entity.InitialEstVO[]) this.getVos();

      nc.vo.pu.m4t.entity.InitialEstContext[] ctxs =
          nc.vo.pu.m4t.util.InitialContextUtil.getCtxs(vo);
      nc.vo.pu.m4t.entity.InitialEstContext ctx =
          org.apache.commons.lang.ArrayUtils.isEmpty(ctxs) ? new nc.vo.pu.m4t.entity.InitialEstContext()
              : ctxs[0];
      retObj =
          nc.bs.framework.common.NCLocator.getInstance()
              .lookup(nc.itf.pu.m4t.IInitialEstMaintain.class).save(vos, ctx);

      return retObj;
    }
    catch (Exception ex) {
      if (ex instanceof BusinessException) {
        throw (BusinessException) ex;
      }
      throw new PFBusinessException(ex.getMessage(), ex);
    }
  }

  // /*
  // * ��ע�����ýű�������HAS
  // */
  // private void setParameter(String key, Object val) {
  // if (this.m_keyHas == null) {
  // this.m_keyHas = new Hashtable();
  // }
  // if (val != null) {
  // this.m_keyHas.put(key, val);
  // }
  // }
}
