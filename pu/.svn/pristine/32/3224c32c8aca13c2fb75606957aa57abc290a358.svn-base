package nc.vo.pu.m23.rule.transfer;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算保质期天数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-6-19 上午11:09:47
 */
public class CalcValidDay {
  private ArriveVO[] aggVOArray;

  public CalcValidDay() {
    return;
  }

  public CalcValidDay(ArriveVO[] retVOArray) {
    this.aggVOArray = retVOArray;
  }

  /*
   * 得到之后间隔N月的年月
   */
  public static UFDate getUFDate(int year, int month, int day) {
    if (year <= 0 || month <= 0 || day <= 0) {
      return null;
    }
    String syear = year + "";
    String smonth = month + "";
    if (month < 10) {
      smonth = "0" + month;
    }
    int days = UFDate.getDaysMonth(year, month);
    int myday = day;
    if (myday > days) {
      myday = days;
    }
    String sday = myday + "";
    if (myday < 10) {
      sday = "0" + myday;
    }
    return new UFDate(syear + "-" + smonth + "-" + sday);
  }

  /*
   * 计算保质期
   */
  public Integer calcValidDay(MaterialStockVO mrlVO) {
    Integer unit = mrlVO.getQualityunit();
    Integer num = mrlVO.getQualitynum();

    if (unit == null || num == null) {
      return null;
    }

    UFDate today = new UFDate();
    UFDate invalidDay = null;
    if (num.intValue() == 0) {
      invalidDay = today;
    }

    if (IMaterialEnumConst.QUALITYUNIT_DAY == unit.intValue()) {
      invalidDay = today.getDateAfter(num.intValue());
    }
    else if (IMaterialEnumConst.QUALITYUNIT_MONTH == unit.intValue()) {
      int[] yearday =
          this.getAfterMonth(today.getYear(), today.getMonth(), num.intValue());
      if (yearday != null) {
        invalidDay =
            CalcValidDay.getUFDate(yearday[0], yearday[1], today.getDay());
      }
    }
    else if (IMaterialEnumConst.QUALITYUNIT_YEAR == unit.intValue()) {
      invalidDay =
          CalcValidDay.getUFDate(today.getYear() + num.intValue(),
              today.getMonth(), today.getDay());
    }
    return Integer.valueOf(UFDate.getDaysBetween(today, invalidDay));
  }

  public ArriveVO[] fillVaildDay() {
    ArriveItemVO[] itemVO = null;
    for (ArriveVO vo : this.aggVOArray) {
      // 根据物料PK+库存组织,获取物料的保质期属性
      String org = vo.getHVO().getPk_org();
      String[] mrls =
          (String[]) AggVOUtil.getDistinctItemFieldArray(this.aggVOArray,
              ArriveItemVO.PK_MATERIAL, String.class);

      String[] fields = new String[3];
      fields[0] = MaterialStockVO.QUALITYMANFLAG;
      fields[1] = MaterialStockVO.QUALITYUNIT;
      fields[2] = MaterialStockVO.QUALITYNUM;
      Map<String, MaterialStockVO> map = null;
      map = MaterialPubService.queryMaterialStockInfo(mrls, org, fields);

      itemVO = vo.getBVO();
      for (int i = 0, len = itemVO.length; i < len; i++) {
        String mrl = itemVO[i].getPk_material();
        if (map == null || map.get(mrl) == null) {
          continue;
        }

        UFBoolean flag = map.get(mrl).getQualitymanflag();
        if (flag == null || !flag.booleanValue()) {
          continue;// 不是保质期管理
        }

        // 计算保质期天数
        Integer validay = this.calcValidDay(map.get(mrl));
        itemVO[i].setIvalidday(validay);
      }
    }
    return this.aggVOArray;
  }

  /*
   * 得到之后间隔N月的年月
   */
  private int[] getAfterMonth(int year, int month, int interval) {
    if (interval <= 0) {
      return null;
    }
    int m = interval / 12;
    int n = interval % 12;

    int yearX = year + m;
    int monthX = month + n;
    if (monthX > 12) {
      yearX = yearX + 1;
      monthX = monthX - 12;
    }
    int[] iyearmonth = new int[2];
    iyearmonth[0] = yearX;
    iyearmonth[1] = monthX;

    return iyearmonth;
  }
}
