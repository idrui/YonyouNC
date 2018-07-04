INSERT INTO bd_billtype (ts, iseditableproperty, pk_billtypecode, ncbrcode, parentbilltype, canextendtransaction, istransaction, isbizflowbill, datafinderclz, referclassname, isaccount, pk_org, isroot, component, billtypename, emendenumclass, billcoderule, dr, nodecode, isenablebutton, pk_billtypeid, classname, systemcode, checkclassname, accountclass, islock, forwardbilltype, billtypename2, billtypename3, billtypename4, transtype_class, billtypename5, pk_group, billtypename6, webnodecode, billstyle, def3, isapprovebill, def2, isenabletranstypebcr, wherestring, def1 ) VALUES ('2016-12-14 16:38:00', null, 'JT01', '~', '~', 'Y', 'N', null, null, null, null, 'GLOBLE00000000000000', null, 'cgfa', '采购方案', null, '~', null, '40040207', null, '0001ZZ1000000000REVJ', null, 'PO', null, null, null, null, null, null, null, null, null, '~', null, '~', null, null, 'Y', null, null, null, null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'N', 'N', '0001ZZ1000000000REVK', null, 'SAVE', null, '送审', null, null, null, 'N', 10, '1', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'N', 'N', '0001ZZ1000000000REVL', null, 'APPROVE', null, '审核', null, null, null, 'N', 11, '2', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'Y', 'Y', '0001ZZ1000000000REVM', null, 'UNSAVEBILL', null, '收回', null, null, null, 'N', 13, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'N', 'Y', '0001ZZ1000000000REVN', null, 'UNAPPROVE', null, '弃审', null, null, null, 'N', 12, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'N', 'N', '0001ZZ1000000000REVO', null, 'DELETE', null, '删除', null, null, null, 'N', 30, '3', null, 'JT01', null );
INSERT INTO pub_billaction (ts, actionstyleremark, pushflag, pk_billtypeid, controlflag, finishflag, pk_billaction, actionnote6, actiontype, actionnote4, actionnote, actionnote5, actionnote2, actionnote3, constrictflag, action_type, actionstyle, showhint, pk_billtype, dr ) VALUES ('2016-12-14 16:39:41', null, null, '0001ZZ1000000000REVJ', 'N', 'N', '0001ZZ1000000000REVP', null, 'SAVEBASE', null, '保存', null, null, null, 'Y', 31, '1', null, 'JT01', null );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_SAVE', 'N', 'SAVE', '~', 0, 'JT01', '0001ZZ1000000000REVQ' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_APPROVE', 'N', 'APPROVE', '~', 0, 'JT01', '0001ZZ1000000000REVR' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_UNSAVEBILL', 'N', 'UNSAVEBILL', '~', 0, 'JT01', '0001ZZ1000000000REVS' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_UNAPPROVE', 'N', 'UNAPPROVE', '~', 0, 'JT01', '0001ZZ1000000000REVT' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_DELETE', 'N', 'DELETE', '~', 0, 'JT01', '0001ZZ1000000000REVU' );
INSERT INTO pub_busiclass (ts, pk_billtypeid, pk_businesstype, classname, isbefore, actiontype, pk_group, dr, pk_billtype, pk_busiclass ) VALUES ('2016-12-14 16:39:41', '0001ZZ1000000000REVJ', '~', 'N_JT01_SAVEBASE', 'N', 'SAVEBASE', '~', 0, 'JT01', '0001ZZ1000000000REVV' );