package nc.pubitf.pu.it;

import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ڽ����ƥ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.31
 * @version 2013-9-17 ����10:30:55
 * @author mengjian
 */
public interface ISettleMatchForIT {

  /**
   * �������������� �Զ������ƥ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaInvoice ��ƱVO[]
   * @param voaStock ��浥��VO[]
   * @param voaFee ���÷�ƱVO[]
   * @param voaDiscount �ۿ۷�ƱVO[]
   * @param settleEnv ���㻷��
   * @return ������ϵĽ��㵥VO����
   * @throws BusinessException <p>
   * @since 6.0
   * @author duy
   * @time 2010-8-31 ����07:48:04
   */
  SettleBillVO[] autoMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * �������������������Ͻ����ƥ�����
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] differentMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * �������������� ���ý����ƥ�����
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] feeMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * ��������������ͬ���Ͻ����ƥ�����
   * 
   * @param voaInvoice
   * @param voaStock
   * @param voaFee
   * @param voaDiscount
   * @param adjustInvcVos
   * @param settleEnv
   * @return
   * @throws BusinessException
   */
  SettleBillVO[] sameMaterialMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;

  /**
   * �޷�Ʊ���㡣
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param voaInvoice ��ƱVO[]
   * @param voaStock ��浥��VO[]
   * @param voaFee ���÷�ƱVO[]
   * @param voaDiscount �ۿ۷�ƱVO[]
   * @return
   * @throws BusinessException <p>
   * @author wangyf
   * @time 2010-1-4 ����01:28:01
   */
  SettleBillVO[] withoutInvoiceMatch(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) throws BusinessException;
}
