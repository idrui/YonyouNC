package nc.vo.pu.m23.rule;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������ʱ����������ṩ��VO����ķǿ���
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-19 ����05:01:13
 */
public class ChkEmptyWhenTransfer {
  private ArriveVO[] aggVOArray;

  public ChkEmptyWhenTransfer(
      ArriveVO[] aggVOArray) {
    this.aggVOArray = aggVOArray;
  }

  private void checkBodyItem(ArriveItemVO[] bvo) {
    ChkItemInfo[] itemArray = new ChkItemInfo[21];
    itemArray[0] = new ChkItemInfo(ArriveItemVO.PK_MATERIAL, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002911")/*@res "���ϱ���"*/);
    itemArray[1] = new ChkItemInfo(ArriveItemVO.CUNITID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0150")/*@res "����λ"*/);
    itemArray[2] = new ChkItemInfo(ArriveItemVO.CASTUNITID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0147")/*@res "��λ"*/);
    itemArray[3] = new ChkItemInfo(ArriveItemVO.NNUM, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0151")/*@res "������"*/);
    itemArray[4] = new ChkItemInfo(ArriveItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002282")/*@res "����"*/);
    itemArray[5] = new ChkItemInfo(ArriveItemVO.PK_APFINANCEORG, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0152")/*@res "Ӧ����֯���°汾"*/);
    itemArray[6] = new ChkItemInfo(ArriveItemVO.PK_PSFINANCEORG, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0153")/*@res "���������֯���°汾"*/);
    itemArray[7] = new ChkItemInfo(ArriveItemVO.CSOURCEID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0154")/*@res "��Դ��������"*/);
    itemArray[8] = new ChkItemInfo(ArriveItemVO.CSOURCEBID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0155")/*@res "��Դ������ϸ����"*/);
    itemArray[9] = new ChkItemInfo(ArriveItemVO.CSOURCETYPECODE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002753")/*@res "��Դ��������"*/);
    itemArray[10] = new ChkItemInfo(ArriveItemVO.VSOURCETRANTYPE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0156")/*@res "��Դ��������"*/);
    itemArray[11] = new ChkItemInfo(ArriveItemVO.VSOURCECODE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002750")/*@res "��Դ���ݺ�"*/);
    itemArray[12] = new ChkItemInfo(ArriveItemVO.VSOURCEROWNO, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0157")/*@res "��Դ�����к� "*/);
    itemArray[13] = new ChkItemInfo(ArriveItemVO.PK_APFINANCEORG_V, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0158")/*@res "Ӧ����֯"*/);
    itemArray[14] = new ChkItemInfo(ArriveItemVO.PK_PSFINANCEORG_V, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0159")/*@res "���������֯"*/);
    itemArray[15] = new ChkItemInfo(ArriveItemVO.CFIRSTID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0160")/*@res "Դͷ��������"*/);
    itemArray[16] = new ChkItemInfo(ArriveItemVO.CFIRSTBID, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0161")/*@res "Դͷ������ϸ����"*/);
    itemArray[17] = new ChkItemInfo(ArriveItemVO.CFIRSTTYPECODE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002878")/*@res "Դͷ��������"*/);
    itemArray[18] = new ChkItemInfo(ArriveItemVO.VFIRSTTRANTYPE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0162")/*@res "Դͷ��������"*/);
    itemArray[19] = new ChkItemInfo(ArriveItemVO.VFIRSTCODE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0002874")/*@res "Դͷ���ݺ�"*/);
    itemArray[20] = new ChkItemInfo(ArriveItemVO.VFIRSTROWNO, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0163")/*@res "Դͷ�����к� "*/);

    StringBuilder builder = new StringBuilder();
    for (ArriveItemVO itemVO : bvo) {
      for (ChkItemInfo chkItem : itemArray) {
        if (itemVO.getAttributeValue(chkItem.getItemCode()) != null) {
          continue;
        }
        builder.append(chkItem.getItemName()).append("\n");
      }
      if (builder.length() != 0) {
        String errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0164")/*@res "ת��ʱ���ε����ṩ�������ֶβ�����Ϊ�գ�\n"*/ + builder.toString();
        ExceptionUtils.wrappBusinessException(errorMsg);
      }
    }
  }

  public void checkEmpty() {
    for (ArriveVO vo : this.aggVOArray) {
      this.checkHeadItem(vo.getHVO());
      this.checkBodyItem(vo.getBVO());
    }
  }

  private void checkHeadItem(ArriveHeaderVO hvo) {
    ChkItemInfo[] itemArray = new ChkItemInfo[6];
    itemArray[0] = new ChkItemInfo(ArriveHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0165")/*@res "�����֯���°汾"*/);
    itemArray[1] = new ChkItemInfo(ArriveHeaderVO.PK_PURCHASEORG, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0166")/*@res "�ɹ���֯���°汾"*/);
    itemArray[2] = new ChkItemInfo(ArriveHeaderVO.PK_SUPPLIER, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0000275")/*@res "��Ӧ��"*/);
    itemArray[3] = new ChkItemInfo(ArriveHeaderVO.PK_BUSITYPE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000003")/*@res "ҵ������"*/);
    itemArray[4] = new ChkItemInfo(ArriveHeaderVO.PK_ORG_V, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0001825")/*@res "�����֯"*/);
    itemArray[5] = new ChkItemInfo(ArriveHeaderVO.PK_PURCHASEORG_V, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC000-0004091")/*@res "�ɹ���֯"*/);

    StringBuilder builder = new StringBuilder();
    for (ChkItemInfo chkItem : itemArray) {
      if (hvo.getAttributeValue(chkItem.getItemCode()) != null) {
        continue;
      }
      builder.append(chkItem.getItemName()).append("\n");
    }
    if (builder.length() != 0) {
      String errorMsg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0164")/*@res "ת��ʱ���ε����ṩ�������ֶβ�����Ϊ�գ�\n"*/ + builder.toString();
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }
}