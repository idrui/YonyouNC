package nc.vo.pu.m422x.rule;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

public class NumValueSetter {
  private IKeyValue keyValue;

  public NumValueSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setNastnum(int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    ScaleUtils scale =
        new ScaleUtils(
            (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_GROUP));
    for (int row : rows) {
      // ÊýÁ¿
      String vchangerate =
          (String) this.keyValue.getBodyValue(row,
              StoreReqAppItemVO.VCHANGERATE);
      String castunitid =
          (String) this.keyValue
              .getBodyValue(row, StoreReqAppItemVO.CASTUNITID);
      UFDouble nastnum = null;
      if (vchangerate != null) {
        nastnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale(vchangerate),
                (UFDouble) this.keyValue.getBodyValue(row,
                    StoreReqAppItemVO.NNUM));
      }
      else {
        nastnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale("1.00/1.00"),
                (UFDouble) this.keyValue.getBodyValue(row,
                    StoreReqAppItemVO.NNUM));
      }
      nastnum = scale.adjustNumScale(nastnum, castunitid);
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.NASTNUM, nastnum);

    }
  }
}
