package nc.vo.pu.m25.pub;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>NC631�������÷�Ʊ���ͣ� ������ͨ�ɹ��ͽ����ڲɹ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.3
 * @since 6.3
 * @author liangchen1
 * @time 2013-8-21 ����10:42:13
 */

public class InvoiceTypeSetter {

  private InvoicePuImportEnum type;

  public InvoiceTypeSetter(InvoicePuImportEnum type) {
    this.type = type;
  }

  public void setInvocieType(IKeyValue keyvalue) {
    keyvalue.setHeadValue(InvoiceHeaderVO.FINVOICETYPE, this.type.toInteger());
  }

  public void setInvocieType(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (InvoiceVO vo : vos) {
      vo.getParentVO().setFinvoicetype(this.type.toInteger());
    }
  }
}
