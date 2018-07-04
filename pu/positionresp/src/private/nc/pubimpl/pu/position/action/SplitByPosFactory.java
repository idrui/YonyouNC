package nc.pubimpl.pu.position.action;

import nc.vo.pub.AggregatedValueObject;

/**
 * @since 6.0
 * @version 2010-11-10 ÉÏÎç08:14:39
 * @author wuxla
 */

public class SplitByPosFactory {
  private static SplitByPosFactory instance = new SplitByPosFactory();

  public static SplitByPosFactory getInstance() {
    return SplitByPosFactory.instance;
  }

  public SplitBorgByPos getForBorg(AggregatedValueObject vo, String[] keys,
      int pos) {
    return new SplitBorgByPos(vo, keys, pos);
  }

  public SplitHorgByPos getForHOrg(AggregatedValueObject vo, String[] keys,
      int pos) {
    return new SplitHorgByPos(vo, keys, pos);
  }

  public SplitMMByPos getForMM(AggregatedValueObject vo, String[] keys, int pos) {
    return new SplitMMByPos(vo, keys, pos);
  }
}
