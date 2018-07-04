/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-10 上午10:25:47
 */
package nc.ui.pu.m422x.handpanel;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.handpanel.PuDefaultOnhandPanelSrc;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

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
public class OnhandPanelSrc extends PuDefaultOnhandPanelSrc {
  @Override
  public void initKeyTOOnhandVOKey() {

    Map<String, String> keys = new HashMap<String, String>();
    keys.put(OnhandDimVO.PK_GROUP, StoreReqAppItemVO.PK_GROUP);
    keys.put(OnhandDimVO.PK_ORG, StoreReqAppItemVO.PK_ORG);
    keys.put(OnhandDimVO.CWAREHOUSEID, StoreReqAppItemVO.PK_REQSTORDOC);
    keys.put(OnhandDimVO.CMATERIALVID, StoreReqAppItemVO.PK_MATERIAL);
    keys.put(OnhandDimVO.CMATERIALOID, StoreReqAppItemVO.PK_SRCMATERIAL);
    keys.put(OnhandDimVO.CASTUNITID, StoreReqAppItemVO.CASTUNITID);
    keys.put(OnhandDimVO.PK_BATCHCODE, StoreReqAppItemVO.PK_BATCHCODE);
    keys.put(OnhandDimVO.VBATCHCODE, StoreReqAppItemVO.VBATCHCODE);
    keys.put(OnhandDimVO.VCHANGERATE, StoreReqAppItemVO.VCHANGERATE);
    keys.put(OnhandDimVO.CVENDORID, StoreReqAppItemVO.CVENDORID);
    keys.put(OnhandDimVO.CPROJECTID, StoreReqAppItemVO.CPROJECTID);
    keys.put(OnhandDimVO.CPRODUCTORID, StoreReqAppItemVO.CPRODUCTORID);
    keys.put(OnhandDimVO.VFREE1, StoreReqAppItemVO.VFREE1);
    keys.put(OnhandDimVO.VFREE2, StoreReqAppItemVO.VFREE2);
    keys.put(OnhandDimVO.VFREE3, StoreReqAppItemVO.VFREE3);
    keys.put(OnhandDimVO.VFREE4, StoreReqAppItemVO.VFREE4);
    keys.put(OnhandDimVO.VFREE5, StoreReqAppItemVO.VFREE5);
    keys.put(OnhandDimVO.VFREE6, StoreReqAppItemVO.VFREE6);
    keys.put(OnhandDimVO.VFREE7, StoreReqAppItemVO.VFREE7);
    keys.put(OnhandDimVO.VFREE8, StoreReqAppItemVO.VFREE8);
    keys.put(OnhandDimVO.VFREE9, StoreReqAppItemVO.VFREE9);
    keys.put(OnhandDimVO.VFREE10, StoreReqAppItemVO.VFREE10);

    this.setKeyTOOnhandVOKey(keys);
  }
}
