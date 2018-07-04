/* tablename: �ɹ������ͷ */
create table po_purchaseinfi (pk_stockps char(20) not null 
/*�ɹ���ͷID*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*�����֯*/,
pk_org_v varchar(20) null 
/*�����֯�汾*/,
cbilltypecode varchar(20) null 
/*�������ͱ���*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
pk_busitype varchar(20) null 
/*ҵ������*/,
vbillcode varchar(40) null 
/*��ⵥ��*/,
dbilldate char(19) null 
/*�������*/,
pk_stordoc varchar(20) null 
/*�ֿ�*/,
pk_dept varchar(20) null 
/*����ԭʼ��Ϣ*/,
pk_dept_v varchar(20) null 
/*����*/,
pk_psndoc varchar(20) null 
/*ҵ��Ա*/,
cwhsmanagerid varchar(20) null 
/*���Ա*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
vnote varchar(181) null 
/*��ע*/,
freplenishflag char(1) null 
/*�˻���־*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
creationtime char(19) null 
/*����ʱ��*/,
creator varchar(20) null 
/*������*/,
bnormpur char(1) null 
/*�Ƿ���ͨ�ɹ�*/,
bautotofi char(1) null default 'N' 
/*�Զ��������־*/,
ctrantypeid varchar(20) null 
/*��������*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
csendtypeid varchar(20) null 
/*���䷽ʽ*/,
ctradewordid varchar(20) null 
/*ó������*/,
bitinbill char(1) null 
/*������ⵥ*/,
approver varchar(20) null 
/*ǩ����*/,
taudittime char(19) null 
/*ǩ������*/,
 constraint pk_po_purchaseinfi primary key (pk_stockps),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ݹ����÷�̯��ϸ */
create table po_purchaseinfi_fd (pk_stockps_fd char(20) not null 
/*�ݹ����÷�̯��ϸ*/,
pk_billtype varchar(20) null 
/*��̯���ݵ�������*/,
pk_distbybill varchar(20) null 
/*��̯���ݵ���*/,
pk_distbybill_b varchar(20) null 
/*��̯���ݵ�����*/,
ndistbynum decimal(28,8) null 
/*��̯��������*/,
ndistbymny decimal(28,8) null 
/*��̯���ݽ��*/,
ndistmny decimal(28,8) null 
/*��̯���*/,
pk_iasrc_b varchar(20) null 
/*���ɱ���ʶ*/,
pk_stockps_fee char(20) not null 
/*�ɹ�������ݹ���ϸ_����*/,
 constraint pk_purchaseinfi_fd primary key (pk_stockps_fd),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɹ�������� */
create table po_purchaseinfi_b (pk_stockps_b char(20) not null 
/*�ɹ�����ID*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*�����֯*/,
pk_org_v varchar(20) null 
/*�����֯�汾*/,
pk_financeorg varchar(20) null 
/*���������֯*/,
pk_financeorg_v varchar(20) null 
/*���������֯�汾*/,
pk_apfinanceorg varchar(20) null 
/*Ӧ��������֯*/,
pk_apfinanceorg_v varchar(20) null 
/*Ӧ��������֯�汾*/,
pk_costregion varchar(20) null 
/*�ɱ���*/,
pk_purchaseorg varchar(20) null 
/*�ɹ���֯*/,
pk_purchaseorg_v varchar(20) null 
/*�ɹ���֯�汾*/,
pk_apliabcenter varchar(20) null 
/*���������������°汾*/,
pk_apliabcenter_v varchar(20) null 
/*������������*/,
pk_srcmaterial varchar(20) null 
/*����*/,
pk_material varchar(20) null 
/*���ϱ���*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
vbatchcode varchar(40) null 
/*���κ�*/,
dbizdate char(19) null 
/*ҵ������*/,
castunitid varchar(20) null 
/*��λ*/,
ninnum decimal(28,8) null 
/*���������*/,
ninassistnum decimal(28,8) null 
/*ʵ�븨����*/,
blargess char(1) null 
/*�Ƿ���Ʒ*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
vchangerate varchar(60) null 
/*������*/,
csourcetypecode varchar(20) null 
/*��Դ��������*/,
vsourcetrantype varchar(20) null 
/*��Դ��������*/,
csourceid varchar(20) null 
/*��Դ���ݱ�ͷ*/,
csourcebid varchar(20) null 
/*��Դ���ݱ���*/,
vsourcecode varchar(40) null 
/*��Դ���ݺ�*/,
vsourcerowno varchar(20) null 
/*��Դ�����к�*/,
cfirsttypecode varchar(20) null 
/*Դͷ��������*/,
vfirsttrantype varchar(20) null 
/*Դͷ���ݽ�������*/,
cfirstid varchar(20) null 
/*Դͷ���ݱ�ͷ*/,
cfirstbid varchar(20) null 
/*Դͷ���ݱ���*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
dfirstbilldate varchar(19) null 
/*Դͷ�����Ƶ�����*/,
vnotebody varchar(181) null 
/*�б�ע*/,
creceieveid varchar(20) null 
/*�ջ���λ*/,
crowno varchar(20) null 
/*�к�*/,
drequiredate varchar(19) null 
/*��������*/,
pk_reqstockorg varchar(20) null 
/*��������֯*/,
pk_reqstockorg_v varchar(20) null 
/*��������֯�汾*/,
pk_reqstocdoc varchar(20) null 
/*����ֿ�*/,
vordertrantypecode varchar(50) null 
/*������������*/,
pk_order varchar(20) null 
/*����*/,
pk_order_b varchar(20) null 
/*������ϸ*/,
vordercode varchar(50) null 
/*������*/,
cproductorid varchar(20) null 
/*��������*/,
cstateid varchar(20) null 
/*���״̬*/,
vctcode varchar(50) null 
/*�ɹ���ͬ��*/,
cunitid varchar(20) null 
/*����λ*/,
pk_supplier varchar(20) null 
/*��Ӧ��*/,
pk_vmisupplier varchar(20) null 
/*�Ĵ湩Ӧ��*/,
ncostprice decimal(28,8) null 
/*����*/,
ncostmny decimal(28,8) null 
/*���*/,
nplannedprice decimal(28,8) null 
/*�ƻ�����*/,
nplannedmny decimal(28,8) null 
/*�ƻ����*/,
cqtunitid varchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate varchar(60) null 
/*���۵�λ������*/,
corigcurrencyid varchar(20) null 
/*ԭ�ұ���*/,
nqtorigprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxprice decimal(28,8) null 
/*��˰����*/,
norigprice decimal(28,8) null 
/*��ⵥ����˰����*/,
norigtaxprice decimal(28,8) null 
/*��ⵥ����˰����*/,
nqtorignetprice decimal(28,8) null 
/*��˰����*/,
nqtorigtaxnetprice decimal(28,8) null 
/*��˰����*/,
norignetprice decimal(28,8) null 
/*����˰����*/,
norigtaxnetprice decimal(28,8) null 
/*����˰����*/,
ntaxrate decimal(28,8) null 
/*��ⵥ˰��*/,
ftaxtypeflag integer null 
/*��ⵥ��˰���*/,
norigmny decimal(28,8) null 
/*��ⵥ��˰���*/,
norigtaxmny decimal(28,8) null 
/*��ⵥ��˰�ϼ�*/,
ccurrencyid varchar(20) null 
/*��λ��*/,
nchangestdrate decimal(28,8) null 
/*��ⵥ�۱�����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nqtprice decimal(28,8) null 
/*������˰����*/,
nqttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
nqtnetprice decimal(28,8) null 
/*������˰����*/,
nqttaxnetprice decimal(28,8) null 
/*���Һ�˰����*/,
nnetprice decimal(28,8) null 
/*��������˰����*/,
ntaxnetprice decimal(28,8) null 
/*�����Һ�˰����*/,
nmny decimal(28,8) null 
/*��ⵥ������˰���*/,
ntaxmny decimal(28,8) null 
/*��ⵥ���Ҽ�˰�ϼ�*/,
ntax decimal(28,8) null 
/*��ⵥ����˰��*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
pk_dtransaleid varchar(20) null 
/*��Ӧ��ֱ�����۶���*/,
pk_dtransalebid varchar(20) null 
/*��Ӧ��ֱ�����۶�����*/,
vbdef1 varchar(101) null 
/*�Զ�����1*/,
vbdef2 varchar(101) null 
/*�Զ�����2*/,
vbdef3 varchar(101) null 
/*�Զ�����3*/,
vbdef4 varchar(101) null 
/*�Զ�����4*/,
vbdef5 varchar(101) null 
/*�Զ�����5*/,
vbdef6 varchar(101) null 
/*�Զ�����6*/,
vbdef7 varchar(101) null 
/*�Զ�����7*/,
vbdef8 varchar(101) null 
/*�Զ�����8*/,
vbdef9 varchar(101) null 
/*�Զ�����9*/,
vbdef10 varchar(101) null 
/*�Զ�����10*/,
vbdef11 varchar(101) null 
/*�Զ�����11*/,
vbdef12 varchar(101) null 
/*�Զ�����12*/,
vbdef13 varchar(101) null 
/*�Զ�����13*/,
vbdef14 varchar(101) null 
/*�Զ�����14*/,
vbdef15 varchar(101) null 
/*�Զ�����15*/,
vbdef16 varchar(101) null 
/*�Զ�����16*/,
vbdef17 varchar(101) null 
/*�Զ�����17*/,
vbdef18 varchar(101) null 
/*�Զ�����18*/,
vbdef19 varchar(101) null 
/*�Զ�����19*/,
vbdef20 varchar(101) null 
/*�Զ�����20*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
pk_costappsn varchar(20) null 
/*���ɱ���Ӧ����*/,
dtocostapdate varchar(19) null 
/*�ݹ�����*/,
nestnum decimal(28,8) null default 0 
/*�ݹ�������*/,
nestprice decimal(28,8) null 
/*�ݹ�����*/,
nesttaxrate decimal(28,8) null 
/*˰��*/,
nesttaxprice decimal(28,8) null 
/*�ݹ���˰����*/,
nestoprice decimal(28,8) null 
/*����˰����*/,
nestotaxprice decimal(28,8) null 
/*����˰����*/,
nesttaxmny decimal(28,8) null 
/*˰��*/,
nestmny decimal(28,8) null 
/*������˰���*/,
nesttotalmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar(20) null 
/*����*/,
nestexhgrate decimal(28,8) null 
/*�۱�����*/,
nestomny decimal(28,8) null 
/*��˰���*/,
nestototalmny decimal(28,8) null 
/*��˰�ϼ�*/,
festtaxtype integer null 
/*��˰���*/,
ftoiaflag integer null default 0 
/*���ɱ���־*/,
ftoapflag integer null default 0 
/*��Ӧ����־*/,
bsettlefinish char(1) null default 'N' 
/*�Ƿ�������*/,
naccumsettlenum decimal(28,8) null 
/*�ۼƽ�������*/,
naccestcoststtlnum decimal(28,8) null 
/*�ݹ������ۼƽ�������*/,
naccestcostwashmny decimal(28,8) null 
/*�ۼƻس��ݹ��ɱ����*/,
nacctocostadjstmny decimal(28,8) null 
/*�ۼƵ���ȷ�ϳɱ����*/,
nacctoapadjstotmny decimal(28,8) null 
/*�ۼƵ���ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�*/,
naccgoodssettlemny decimal(28,8) null 
/*�ۼƻ��������*/,
naccsettlemny decimal(28,8) null 
/*�ۼ�???����*/,
naccfeesettlemny decimal(28,8) null 
/*�ۼƷ��ý�����*/,
pk_finishsettle_b varchar(20) null 
/*������ϵĽ��㵥����ϸ*/,
naccpreeststtlmny decimal(28,8) null 
/*�ݹ�ǰ�ۼƽ�����*/,
baffectcost char(1) null 
/*Ӱ��ɱ���־*/,
pk_stockps char(20) not null 
/*�ɹ������ͷ_����*/,
casscustid varchar(20) null 
/*�ͻ�*/,
ctaxcodeid varchar(20) null 
/*��ⵥ˰��*/,
nnosubtaxrate decimal(28,8) null 
/*��ⵥ���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*��ⵥ���ɵֿ�˰��*/,
ncaltaxmny decimal(28,8) null 
/*��ⵥ��˰���*/,
ncalcostmny decimal(28,8) null 
/*��ⵥ�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
corigcountryid varchar(20) null 
/*��ⵥԭ����*/,
corigareaid varchar(20) null 
/*��ⵥԭ������*/,
cdesticountryid varchar(20) null 
/*��ⵥĿ�ĵع�*/,
cdestiareaid varchar(20) null 
/*��ⵥĿ�ĵص���*/,
cesttaxcodeid varchar(20) null 
/*˰��*/,
nestnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nestcaltaxmny decimal(28,8) null 
/*��˰���*/,
nestnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
nestcalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
nestcalcostprice decimal(28,8) null 
/*�ǳɱ�����*/,
ncalcostprice decimal(28,8) null 
/*��ⵥ�ǳɱ�����*/,
cprojecttaskid varchar(20) null 
/*��Ŀ����*/,
naccadjustmny decimal(28,8) null 
/*�ۼƵ���������*/,
pk_arrliabcenter_v varchar(20) null 
/*�ջ���������*/,
pk_arrliabcenter varchar(20) null 
/*�ջ������������°汾*/,
baffectpccost char(1) null 
/*Ӱ���������ĳɱ�*/,
cffileid varchar(20) null 
/*������*/,
 constraint pk__purchaseinfi_b primary key (pk_stockps_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɹ�������ݹ���ϸ */
create table po_purchaseinfi_fee (pk_stockps_fee char(20) not null 
/*�ɹ�������ݹ���ϸ*/,
pk_stockps char(20) null 
/*�ɹ������ͷ*/,
pk_group varchar(20) null 
/*����*/,
pk_financeorg varchar(20) null 
/*������֯*/,
pk_supplier varchar(20) null 
/*��Ӧ������*/,
pk_costfactor varchar(20) null 
/*�ɱ�Ҫ��*/,
pk_feematerial varchar(20) null 
/*������*/,
pk_srcfeematerial varchar(20) null 
/*�ݹ���������*/,
pk_estpsn varchar(20) null 
/*�ݹ���*/,
destdate char(19) null 
/*�ݹ�����*/,
nestexchgrate decimal(28,8) null 
/*�۱�����*/,
btoia char(1) null default 'N' 
/*���ɱ���־*/,
btoap char(1) null default 'N' 
/*��Ӧ����־*/,
nestmny decimal(28,8) null 
/*������˰���*/,
nesttaxmny decimal(28,8) null 
/*����˰��*/,
nesttaxrate decimal(28,8) null 
/*˰��*/,
nesttotalmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency varchar(20) null 
/*����*/,
pk_localcurrency varchar(20) null 
/*����*/,
nestomny decimal(28,8) null 
/*ԭ����˰���*/,
nestototalmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
pk_firstsettle varchar(20) null 
/*�ݹ��������ϵ�һ�ν���Ľ��㵥*/,
pk_firstsettle_b varchar(20) null 
/*�ݹ��������ϵ�һ�ν���Ľ��㵥��*/,
pk_stockps_b char(20) not null 
/*�ɹ��������_����*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ftaxtypeflag integer null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
nnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag char(1) null 
/*������˰*/,
 constraint pk_urchaseinfi_fee primary key (pk_stockps_fee),
 ts char(19) null,
dr smallint null default 0
)
;

