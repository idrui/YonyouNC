package nc.pubitf.pu.m21.ec;

import java.io.Serializable;

import nc.bs.ml.NCLangResOnserver;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * Ϊ���Ӳɹ��ṩ����ʽ�������VO
 * 
 * @since 6.3
 * @version 2013-5-20 ����01:41:17
 * @author lixyp
 */
public class PushSaveVO implements Serializable {

  private static final long serialVersionUID = -7993472914987973614L;

  private OrderContext[] contexts = null;

  private OrderVO[] vos = null;

  public PushSaveVO(OrderVO[] vos, OrderContext[] contexts) {
    this.vos = vos;
    this.contexts = contexts;

    if (vos.length != contexts.length) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("40040400", "1400404000058", null, null)/*
                                                               * @res
                                                               * "��ʽ���涩��ʱ������������������������һһ��Ӧ��"
                                                               */);
    }
  }

  public OrderContext[] getContexts() {
    return this.contexts;
  }

  public OrderVO[] getVos() {
    return this.vos;
  }

}
