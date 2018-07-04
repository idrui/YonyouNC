package nc.bs.pu.m23.writeback.pu.m21;

import nc.bs.pu.m23.plugin.ArriveBPPlugInPoint;
import nc.bs.pu.m23.writeback.pu.m21.rule.ChkReplNumRule;
import nc.bs.pu.m23.writeback.pu.m21.rule.UpdateViewVOReplNumRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m23.pu.m21.IWriteback23For21Para;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��д���������ۼƲ����������ɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����10:47:37
 */
public class Writeback23For21BP {

  /**
   * ����������������д���������ۼƲ����������ɹ�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param paraArray��д��������
   *          <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����10:18:08
   */
  public void writeback(IWriteback23For21Para[] paraArray) {
    // ��ѯ������������ͼVO
    String[] bidArray = ArrivePublicUtil.getWriteParaBidArray(paraArray);
    ViewQuery<ArriveViewVO> queryBO =
        new ViewQuery<ArriveViewVO>(ArriveViewVO.class);
    queryBO.setSharedHead(true);
    ArriveViewVO[] oldViewVOArray = queryBO.query(bidArray);

    // ���ִ��ҵ�����
    AroundProcesser<ArriveViewVO> processer =
        new AroundProcesser<ArriveViewVO>(
            ArriveBPPlugInPoint.Writeback23For21BP);

    this.addAfterRule(processer, paraArray);

    processer.before(oldViewVOArray);

    // �־û�
    String[] names = new String[] {
      ArriveItemVO.NACCUMREPLNUM
    };
    ViewUpdate<ArriveViewVO> bo = new ViewUpdate<ArriveViewVO>();
    oldViewVOArray = bo.update(oldViewVOArray, ArriveItemVO.class, names);

    processer.after(oldViewVOArray);
  }

  private void addAfterRule(AroundProcesser<ArriveViewVO> processer,
      IWriteback23For21Para[] paraArray) {
    // ������ͼVO�е��ۼƲ�������
    processer.addBeforeRule(new UpdateViewVOReplNumRule(paraArray));

    // ���β����������ڳ����˻����ɲ���������Χ�����򣺲�������<=�˻�����
    processer.addAfterRule(new ChkReplNumRule());
  }
}
