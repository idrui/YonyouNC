/**
 * 
 */
package nc.bs.pu.m20.maintain;

import java.util.Arrays;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购安排的业务处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-25 下午8:00:24
 */
public class PrayarrangeUpdateBP {

  /**
   * 对比修改后的view和原始view的差异，做出更新
   * 
   * @param views
   * @param orgViews
   * @return
   *         下午4:44:03
   */
  public PrayarrangeViewVO[] update(PrayarrangeViewVO[] views,
      PrayarrangeViewVO[] orgViews) {
    PraybillItemVO[] vos = new PraybillItemVO[views.length];
    PraybillItemVO[] originVOs = new PraybillItemVO[views.length];

    for (int i = 0; i < views.length; ++i) {
      vos[i] = (PraybillItemVO) views[i].getVO(PraybillItemVO.class);
      boolean isY = vos[i].getBisarrange().booleanValue();
      vos[i].setBisarrange(UFBoolean.valueOf(!isY));
      originVOs[i] = (PraybillItemVO) orgViews[i].getVO(PraybillItemVO.class);
      PraybillHeaderVO headVO =
          (PraybillHeaderVO) orgViews[i].getVO(PraybillHeaderVO.class);
      views[i].setVO(headVO);
    }

    VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
    vos = update.update(vos, originVOs);

    Map<String, PraybillItemVO> itemMap = CirVOUtil.createKeyVOMap(vos);

    for (int i = 0; i < views.length; ++i) {
      PraybillItemVO vo = (PraybillItemVO) views[i].getVO(PraybillItemVO.class);
      views[i].setVO(itemMap.get(vo.getPk_praybill_b()));
    }
    Arrays.sort(views);
    return views;
  }

  /**
   * 根据所要修改的属性更新
   * 
   * @param views
   *          下午4:44:55
   */
  public void update(PrayarrangeViewVO[] views, String[] names) {
    PraybillItemVO[] vos = new PraybillItemVO[views.length];

    for (int i = 0; i < views.length; ++i) {
      vos[i] = (PraybillItemVO) views[i].getVO(PraybillItemVO.class);
      boolean isY = vos[i].getBisarrange().booleanValue();
      vos[i].setBisarrange(UFBoolean.valueOf(!isY));
    }

    VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
    vos = update.update(vos, names);
  }

}
