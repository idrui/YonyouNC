package nc.bs.pu.m23.maintain.rule;

import java.util.Map;

import nc.impl.pu.m23.utils.ArrivePrivateUtil;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * �������桢�޸ı��涼���õ��˹��򣬱�����Ҫ������¹��ܣ�
 * �����������������ϣ������䱨���������ϸ����������ϸ�����
 * @scene
 * �����������޸�
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-14 ����03:42:46
 * @author hanbin
 */

public class DealFreeChkItemRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    for (ArriveVO aggVO : voArray) {
      // ��������е��������
      this.dealFreeChkItem(aggVO);
    }
  }

  /**
   * �������������������������������ϣ������䱨���������ϸ����������ϸ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   *          <p>
   * @since 6.0
   * @author hanbin
   * @throws BusinessException
   * @time 2010-1-14 ����04:03:20
   */
  private void dealFreeChkItem(ArriveVO aggVO) {
    ArriveItemVO[] itemVOArray = aggVO.getBVO();
    // ��Ӧ�̡������֯
    String supplierPK = aggVO.getHVO().getPk_supplier();
    String stockOrgPK = aggVO.getHVO().getPk_org();

    // ��������PK+�����֯+��Ӧ��,�ж������Ƿ����
    AggregatedValueObject[] vos = new AggregatedValueObject[1];
    vos[0] = aggVO;
    String[] mrlPKS =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_MATERIAL, String.class);
    Map<String, UFBoolean> mrlToIsFreeChkMap =
        ArrivePrivateUtil.isMaterialFreeChk(mrlPKS, supplierPK, stockOrgPK);

    // �������������ۼƱ����������ϸ����������ϸ�����
    UFDouble nnum = null;
    UFDouble nelignum = null;
    UFDouble nnotelignum = null;
    UFDouble checknum = null;
    for (int i = 0; i < itemVOArray.length; i++) {
      nnum = MathTool.nvl(itemVOArray[i].getNnum());
      checknum = MathTool.nvl(itemVOArray[i].getNaccumchecknum());
      nelignum = MathTool.nvl(itemVOArray[i].getNelignum());
      nnotelignum = MathTool.nvl(itemVOArray[i].getNnotelignum());

      // �����������죬�򣺺ϸ����� = ��������,���ϸ����� = 0
      if (mrlToIsFreeChkMap != null
          && mrlToIsFreeChkMap.get(itemVOArray[i].getPk_material())
              .booleanValue()) {
        nelignum = nnum;
        nnotelignum = new UFDouble(0);
      }
      else {
        // �ۼƱ������� = �ϸ����� + ���ϸ�����
        checknum = nelignum.add(nnotelignum);
      }
      itemVOArray[i].setNaccumchecknum(checknum);
      itemVOArray[i].setNelignum(nelignum);
      itemVOArray[i].setNnotelignum(nnotelignum);
    }
  }
}
