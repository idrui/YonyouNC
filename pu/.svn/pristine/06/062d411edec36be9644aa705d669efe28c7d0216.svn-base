package nc.itf.pu.reference.ic;

import nc.bs.framework.common.NCLocator;
import nc.itf.ic.batch.IBatchGenerateForPU;
import nc.pubitf.scmf.ic.batchcoderule.IBatchcodeRulePubService;
import nc.vo.ic.batch.AbstractBillItemAdapter;
import nc.vo.ic.batch.BatchcodeRelation;
import nc.vo.ic.batchcode.AbstractBatchFieldMap;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * @since 6.0
 * @version 2011-4-22 下午07:12:32
 * @author wuxla
 */

public class BatchServices {

  /**
   * 批次新增后
   * 
   * @param <T>
   * @param bills
   * @param adapterClazz
   * @param fields
   */
  public static <T extends AbstractBillItemAdapter> void batchProcessAfterSaveForInsert(
      AbstractBill[] bills, Class<T> adapterClazz, AbstractBatchFieldMap fields) {
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      service.batchProcessAfterSaveForInsert(bills, adapterClazz, fields);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 批次更新后
   * 
   * @param <T>
   * @param bills
   * @param originBills
   * @param adapterClazz
   * @param fields
   */
  public static <T extends AbstractBillItemAdapter> void batchProcessAfterSaveForUpdate(
      AbstractBill[] bills, AbstractBill[] originBills, Class<T> adapterClazz,
      AbstractBatchFieldMap fields) {
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      service.batchProcessAfterSaveForUpdate(bills, originBills, adapterClazz,
          fields);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 批次新增前
   * 
   * @param <T>
   * @param bills
   * @param adapterClass
   * @param fields
   */
  public static <T extends AbstractBillItemAdapter> void batchProcessBeforeSaveForInsAndDel(
      AbstractBill[] bills, Class<T> adapterClass, AbstractBatchFieldMap fields) {
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      service.batchProcessBeforeSaveForInsAndDel(bills, adapterClass, fields);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 批次更新后
   * 
   * @param <T>
   * @param bills
   * @param originBills
   * @param adapterClass
   * @param fields
   */
  public static <T extends AbstractBillItemAdapter> void batchProcessBeforeSaveForUpdate(
      AbstractBill[] bills, AbstractBill[] originBills, Class<T> adapterClass,
      AbstractBatchFieldMap fields) {
    IBatchGenerateForPU service =
        NCLocator.getInstance().lookup(IBatchGenerateForPU.class);
    try {
      service.batchProcessBeforeSaveForUpdate(bills, originBills, adapterClass,
          fields);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  public static void generateBatchcode(AbstractBill[] bills,
      BatchcodeRelation bizObj) {
    IBatchcodeRulePubService service =
        NCLocator.getInstance().lookup(IBatchcodeRulePubService.class);
    try {
      service.generateBatchcode(bills, bizObj);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
