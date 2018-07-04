package nc.bs.pub.action;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pub.BusinessException;
import nc.vo.pub.compiler.PfParameterVO;

/**
 * ��ע���ɹ������ı���
 * ���ݶ���ִ���еĶ�ִ̬����Ķ�ִ̬���ࡣ
 * �������ڣ�(2010-6-10)
 * 
 * @author ƽ̨�ű�����
 */
public class N_21_SAVEBASE extends AbstractCompiler2 {

  /**
   * N_21_SAVEBASE ������ע�⡣
   */
  public N_21_SAVEBASE() {
    super();
  }

  /*
   * ��ע��ƽ̨��дԭʼ�ű�
   */
  @Override
  public String getCodeRemark() {
    return " nc.vo.pu.m21.entity.OrderVO[] inObject =\n          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();\n      nc.vo.pu.m21.context.OrderContext[] ctxs =\n          nc.vo.pu.m21.pub.OrderContextUtil.getCtxs(vo);\n      nc.vo.pu.m21.context.OrderContext ctx = null;\n      if (ctxs != null) {\n        ctx = ctxs[0];\n      }\n      return nc.bs.framework.common.NCLocator.getInstance()\n          .lookup(nc.itf.pu.m21.IOrderMaintain.class).save(inObject, ctx);\n";
  }

  /*
   * ��ע��ƽ̨��д������ �ӿ�ִ����
   */
  @Override
  public Object runComClass(PfParameterVO vo) throws BusinessException {
    try {
      super.m_tmpVo = vo;
      nc.vo.pu.m21.entity.OrderVO[] inObject =
          (nc.vo.pu.m21.entity.OrderVO[]) this.getVos();
      nc.vo.pu.m21.context.OrderContext[] ctxs =
          nc.vo.pu.m21.pub.OrderContextUtil.getCtxs(vo);
      nc.vo.pu.m21.context.OrderContext ctx = null;
      if (ctxs != null) {
        ctx = ctxs[0];
      }
      return nc.bs.framework.common.NCLocator.getInstance()
          .lookup(nc.itf.pu.m21.IOrderMaintain.class).save(inObject, ctx);
    }
    catch (Exception ex) {
      nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
