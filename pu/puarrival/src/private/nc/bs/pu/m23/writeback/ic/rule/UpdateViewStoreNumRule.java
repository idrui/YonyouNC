package nc.bs.pu.m23.writeback.ic.rule;

import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m23.ic.m45.IWriteback23For45Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * ������ͼVO�е��ۼ��������
 * @scene
 * �ɹ���ⵥ��д
 * @param
 * paraArray ���ɹ����Ļ�д������ʱ�Ĳ�����
 *
 * @since 6.3
 * @version 2010-1-11 ����06:43:36
 * @author hanbin
 */

public class UpdateViewStoreNumRule implements IRule<ArriveViewVO> {

  // �ɹ����Ļ�д������ʱ�Ĳ�����
  private IWriteback23For45Para[] paraArray;

  public UpdateViewStoreNumRule(IWriteback23For45Para[] paraArray) {
    this.paraArray = paraArray;
  }

  @Override
  public void process(ArriveViewVO[] viewVOArray) {
    // <��������ID,��д���������>
    MapList<String, IWriteback23For45Para> bidToParaMap =
        new MapList<String, IWriteback23For45Para>();
    for (IWriteback23For45Para para : this.paraArray) {
      bidToParaMap.put(para.getBID(), para);
    }
    // ������ͼVO�е��ۼ��������
    for (ArriveViewVO view : viewVOArray) {
      ArriveItemVO item = view.getBVO();
      List<IWriteback23For45Para> ps = bidToParaMap.get(item.getPrimaryKey());
      for (IWriteback23For45Para para : ps) {
        // �����ۼ��������
        UFDouble diffStoreNum = para.getDiffNum();
        // �־û���������� = �ɵ��ۼ�������� + �����ۼ��������
        UFDouble accumStoreNum =
            MathTool.add(item.getNaccumstorenum(), diffStoreNum);
        item.setNaccumstorenum(accumStoreNum);
      }
    }
  }
}
