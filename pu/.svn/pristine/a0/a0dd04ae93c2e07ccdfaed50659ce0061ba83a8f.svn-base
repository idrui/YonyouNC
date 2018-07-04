/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 下午03:23:12
 */
package nc.vo.pu.m20.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.uapbd.IMaterialPubService_C;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description 请购单设置主数量和数量
 * @scene 计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:09:36
 * @author yanxm5
 */
public class CalculateNumRule implements IRule<PraybillVO> {

	/**
	 * 父类方 法重写
	 * 
	 * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
	 */
	@Override
	public void process(PraybillVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		this.setAllNum(vos);
	}

	private void setAllNum(PraybillVO[] vos) {
		this.setNum(vos);
		Set<String> materials = new HashSet<String>();
		for (PraybillVO praybillVO : vos) {
			for (PraybillItemVO bvo : praybillVO.getBVO()) {
				String pk_material = bvo.getPk_material();
				materials.add(pk_material);
			}
		}
		IMaterialPubService_C service_C = NCLocator.getInstance().lookup(
				IMaterialPubService_C.class);
		try {
			// 获取辅单位
			Map<String, String> map = service_C.queryMeasdocIDByPksAndType(
					materials.toArray(new String[materials.size()]),
					IMaterialPubService_C.MATERIAL_CONVERT_PU);
			ScaleUtils util = new ScaleUtils(BSContext.getInstance().getGroupID());
			for (PraybillVO vo : vos) {
				for (PraybillItemVO item : vo.getBVO()) {
					if (map.get(item.getPk_material()) != null) {
						item.setCastunitid(map.get(item.getPk_material()));
					}else{
					  item.setCastunitid(item.getCunitid());
					}
					// 获取换算率
					String hsl = service_C.queryMainMeasRateByMaterialAndMeasdoc(
							item.getPk_material(), item.getCastunitid());
					if (hsl == null) {
						hsl = "1/1";
						item.setNastnum(util.adjustNumScale(item.getNnum(),
								item.getCastunitid()));
					} else {
						UFDouble parseHsl = HslParseUtil.getUFDoubleHsl(hsl);
						if (parseHsl == null) {
							item.setNastnum(util.adjustNumScale(item.getNnum(),
									item.getCastunitid()));
						} else {
							item.setNastnum(util.adjustNumScale(item.getNnum().div(parseHsl),
									item.getCastunitid()));
						}
					}
					item.setVchangerate(hsl);
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	private void setNum(PraybillVO[] vos) {
		ScaleUtils scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
		for (PraybillVO vo : vos) {
			PraybillItemVO[] items = vo.getBVO();
			if (ArrayUtils.isEmpty(items)) {
				continue;
			}
			for (int i = 0, len = items.length; i < len; i++) {
				UFDouble num = items[i].getNnum();
				if (null == num) {
					return;
				}

				String vchangerate = items[i].getVchangerate();
				if (vchangerate == null) {
					vchangerate = scale.adjustHslScale("1/1");
				}

				String castunitid = items[i].getCastunitid();
				UFDouble nastnum = scale.adjustNumScale(HslParseUtil.hslDivUFDouble(
						scale.adjustHslScale(vchangerate), items[i].getNnum()), castunitid);
				items[i].setNastnum(nastnum);
			}
		}
	}

}
