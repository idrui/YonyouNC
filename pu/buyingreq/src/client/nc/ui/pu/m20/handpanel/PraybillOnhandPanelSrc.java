/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午10:25:47
 */
package nc.ui.pu.m20.handpanel;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.handpanel.PuDefaultOnhandPanelSrc;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-10 上午10:25:47
 */
public class PraybillOnhandPanelSrc extends PuDefaultOnhandPanelSrc {
  @Override
  public void initKeyTOOnhandVOKey() {

    Map<String, String> keys = new HashMap<String, String>();
    keys.put(OnhandDimVO.PK_GROUP, PraybillItemVO.PK_GROUP);
    keys.put(OnhandDimVO.PK_ORG, PraybillItemVO.PK_ORG);
    keys.put(OnhandDimVO.CWAREHOUSEID, PraybillItemVO.PK_REQSTOR);
    keys.put(OnhandDimVO.CMATERIALVID, PraybillItemVO.PK_MATERIAL);
    keys.put(OnhandDimVO.CMATERIALOID, PraybillItemVO.PK_SRCMATERIAL);
    keys.put(OnhandDimVO.CASTUNITID, PraybillItemVO.CASTUNITID);
    keys.put(OnhandDimVO.PK_BATCHCODE, PraybillItemVO.PK_BATCHCODE);
    keys.put(OnhandDimVO.VBATCHCODE, PraybillItemVO.VBATCHCODE);
    keys.put(OnhandDimVO.VCHANGERATE, PraybillItemVO.VCHANGERATE);
    keys.put(OnhandDimVO.CVENDORID, PraybillItemVO.PK_SUGGESTSUPPLIER);
    keys.put(OnhandDimVO.CPROJECTID, PraybillItemVO.CPROJECTID);
    keys.put(OnhandDimVO.CPRODUCTORID, PraybillItemVO.CPRODUCTORID);
    keys.put(OnhandDimVO.VFREE1, PraybillItemVO.VFREE1);
    keys.put(OnhandDimVO.VFREE2, PraybillItemVO.VFREE2);
    keys.put(OnhandDimVO.VFREE3, PraybillItemVO.VFREE3);
    keys.put(OnhandDimVO.VFREE4, PraybillItemVO.VFREE4);
    keys.put(OnhandDimVO.VFREE5, PraybillItemVO.VFREE5);
    keys.put(OnhandDimVO.VFREE6, PraybillItemVO.VFREE6);
    keys.put(OnhandDimVO.VFREE7, PraybillItemVO.VFREE7);
    keys.put(OnhandDimVO.VFREE8, PraybillItemVO.VFREE8);
    keys.put(OnhandDimVO.VFREE9, PraybillItemVO.VFREE9);
    keys.put(OnhandDimVO.VFREE10, PraybillItemVO.VFREE10);

    this.setKeyTOOnhandVOKey(keys);
  }
}
