package nc.bs.pu.m422x.rewrite.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m422x.state.StoreReqAppStateMachine;
import nc.bs.pu.m422x.state.row.StoreReqAppRowCloseState;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            回写物资需求申请单时，如果累计出库数量>=申请数量×（1—出库下容差%）时，物资需求申请单行将自动关闭。
 * @scene
 *      回写物资需求申请单
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-12-16 下午03:51:37
 * @author wuxla
 */
public class AutoOutCloseRule implements IRule<StoreReqAppViewVO> {
	@Override
	public void process(StoreReqAppViewVO[] views) {
		Map<String, UFDouble> matMap = this.getMaterialMap(views);

		List<StoreReqAppViewVO> openList = new ArrayList<StoreReqAppViewVO>();
		List<StoreReqAppViewVO> closeList = new ArrayList<StoreReqAppViewVO>();
		for (StoreReqAppViewVO view : views) {
			UFDouble naccuoutnum = view.getNaccuoutnum();
			UFDouble nnum = view.getNnum();
			UFDouble outcloselowerlimit = matMap.get(view.getPk_material());
			UFDouble toleranceNum = nnum.multiply(
					MathTool.sub(new UFDouble(100), outcloselowerlimit)).div(100);

			if (MathTool.compareTo(naccuoutnum, toleranceNum) >= 0) {
				closeList.add(view);
				// view.setBclose(UFBoolean.TRUE);
			} else {
				openList.add(view);
				// view.setBclose(UFBoolean.FALSE);
			}
		}
		if (closeList.size() > 0) {
			StoreReqAppViewVO[] appViews = closeList
					.toArray(new StoreReqAppViewVO[closeList.size()]);
			StoreReqAppCloseVO[] closeViews = this.getCloseVO(appViews);
			StoreReqAppRowCloseState state = new StoreReqAppRowCloseState(
					UFBoolean.TRUE);
			new StoreReqAppStateMachine().setState(state, closeViews);
		}
		if (openList.size() > 0) {
			StoreReqAppViewVO[] appViews = openList
					.toArray(new StoreReqAppViewVO[openList.size()]);
			StoreReqAppCloseVO[] closeViews = this.getCloseVO(appViews);
			StoreReqAppRowCloseState state = new StoreReqAppRowCloseState(
					UFBoolean.FALSE);
			new StoreReqAppStateMachine().setState(state, closeViews);
		}
	}

	private StoreReqAppCloseVO[] getCloseVO(StoreReqAppViewVO[] appViews) {
		StoreReqAppCloseVO[] closeViews = new StoreReqAppCloseVO[appViews.length];
		for (int i = 0; i < appViews.length; ++i) {
			closeViews[i] = new StoreReqAppCloseVO();
			closeViews[i].setVO(appViews[i].getVO(StoreReqAppHeaderVO.class));
			closeViews[i].setVO(appViews[i].getVO(StoreReqAppItemVO.class));
		}

		return closeViews;
	}

	private Map<String, UFDouble> getMaterialMap(StoreReqAppViewVO[] views) {
		Set<String> matSet = new HashSet<String>();
		for (StoreReqAppViewVO view : views) {
			matSet.add(view.getPk_material());
		}

		Map<String, UFDouble> map = new HashMap<String, UFDouble>();
		Map<String, MaterialVO> materialMap = MaterialPubService
				.queryMaterialBaseInfo(matSet.toArray(new String[matSet.size()]),
						new String[]{MaterialVO.OUTCLOSELOWERLIMIT});
		if (null == materialMap || 0 == materialMap.size()) {
			return map;
		}

		for (Map.Entry<String, MaterialVO> entry : materialMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue().getOutcloselowerlimit());
		}
		return map;
	}

}
