/* tablename: �ɹ������ͷ */
create table po_purchaseinfi (
pk_stockps nchar(20) not null 
/*�ɹ���ͷID*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*�����֯*/,
pk_org_v nvarchar(20) null 
/*�����֯�汾*/,
cbilltypecode nvarchar(20) null 
/*�������ͱ���*/,
vtrantypecode nvarchar(20) null 
/*�������ͱ���*/,
pk_busitype nvarchar(20) null 
/*ҵ������*/,
vbillcode nvarchar(40) null 
/*��ⵥ��*/,
dbilldate nchar(19) null 
/*�������*/,
pk_stordoc nvarchar(20) null 
/*�ֿ�*/,
pk_dept nvarchar(20) null 
/*����ԭʼ��Ϣ*/,
pk_dept_v nvarchar(20) null 
/*����*/,
pk_psndoc nvarchar(20) null 
/*ҵ��Ա*/,
cwhsmanagerid nvarchar(20) null 
/*���Ա*/,
billmaker nvarchar(20) null 
/*�Ƶ���*/,
vnote nvarchar(181) null 
/*��ע*/,
freplenishflag nchar(1) null 
/*�˻���־*/,
modifier nvarchar(20) null 
/*����޸���*/,
modifiedtime nchar(19) null 
/*����޸�ʱ��*/,
vdef1 nvarchar(101) null 
/*�Զ�����1*/,
vdef2 nvarchar(101) null 
/*�Զ�����2*/,
vdef3 nvarchar(101) null 
/*�Զ�����3*/,
vdef4 nvarchar(101) null 
/*�Զ�����4*/,
vdef5 nvarchar(101) null 
/*�Զ�����5*/,
vdef6 nvarchar(101) null 
/*�Զ�����6*/,
vdef7 nvarchar(101) null 
/*�Զ�����7*/,
vdef8 nvarchar(101) null 
/*�Զ�����8*/,
vdef9 nvarchar(101) null 
/*�Զ�����9*/,
vdef10 nvarchar(101) null 
/*�Զ�����10*/,
vdef11 nvarchar(101) null 
/*�Զ�����11*/,
vdef12 nvarchar(101) null 
/*�Զ�����12*/,
vdef13 nvarchar(101) null 
/*�Զ�����13*/,
vdef14 nvarchar(101) null 
/*�Զ�����14*/,
vdef15 nvarchar(101) null 
/*�Զ�����15*/,
vdef16 nvarchar(101) null 
/*�Զ�����16*/,
vdef17 nvarchar(101) null 
/*�Զ�����17*/,
vdef18 nvarchar(101) null 
/*�Զ�����18*/,
vdef19 nvarchar(101) null 
/*�Զ�����19*/,
vdef20 nvarchar(101) null 
/*�Զ�����20*/,
creationtime nchar(19) null 
/*����ʱ��*/,
creator nvarchar(20) null 
/*������*/,
bnormpur nchar(1) null 
/*�Ƿ���ͨ�ɹ�*/,
bautotofi nchar(1) null default 'N' 
/*�Զ��������־*/,
ctrantypeid nvarchar(20) null 
/*��������*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
csendtypeid nvarchar(20) null 
/*���䷽ʽ*/,
ctradewordid nvarchar(20) null 
/*ó������*/,
bitinbill nchar(1) null 
/*������ⵥ*/,
approver nvarchar(20) null 
/*ǩ����*/,
taudittime nchar(19) null 
/*ǩ������*/,
 constraint pk_po_purchaseinfi primary key (pk_stockps),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ݹ����÷�̯��ϸ */
create table po_purchaseinfi_fd (
pk_stockps_fd nchar(20) not null 
/*�ݹ����÷�̯��ϸ*/,
pk_billtype nvarchar(20) null 
/*��̯���ݵ�������*/,
pk_distbybill nvarchar(20) null 
/*��̯���ݵ���*/,
pk_distbybill_b nvarchar(20) null 
/*��̯���ݵ�����*/,
ndistbynum decimal(28,8) null 
/*��̯��������*/,
ndistbymny decimal(28,8) null 
/*��̯���ݽ��*/,
ndistmny decimal(28,8) null 
/*��̯���*/,
pk_iasrc_b nvarchar(20) null 
/*���ɱ���ʶ*/,
pk_stockps_fee nchar(20) not null 
/*�ɹ�������ݹ���ϸ_����*/,
 constraint pk_purchaseinfi_fd primary key (pk_stockps_fd),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɹ�������� */
create table po_purchaseinfi_b (
pk_stockps_b nchar(20) not null 
/*�ɹ�����ID*/,
pk_group nvarchar(20) null 
/*����*/,
pk_org nvarchar(20) null 
/*�����֯*/,
pk_org_v nvarchar(20) null 
/*�����֯�汾*/,
pk_financeorg nvarchar(20) null 
/*���������֯*/,
pk_financeorg_v nvarchar(20) null 
/*���������֯�汾*/,
pk_apfinanceorg nvarchar(20) null 
/*Ӧ��������֯*/,
pk_apfinanceorg_v nvarchar(20) null 
/*Ӧ��������֯�汾*/,
pk_costregion nvarchar(20) null 
/*�ɱ���*/,
pk_purchaseorg nvarchar(20) null 
/*�ɹ���֯*/,
pk_purchaseorg_v nvarchar(20) null 
/*�ɹ���֯�汾*/,
pk_apliabcenter nvarchar(20) null 
/*���������������°汾*/,
pk_apliabcenter_v nvarchar(20) null 
/*������������*/,
pk_srcmaterial nvarchar(20) null 
/*����*/,
pk_material nvarchar(20) null 
/*���ϱ���*/,
pk_batchcode nvarchar(20) null 
/*���ε���*/,
vbatchcode nvarchar(40) null 
/*���κ�*/,
dbizdate nchar(19) null 
/*ҵ������*/,
castunitid nvarchar(20) null 
/*��λ*/,
ninnum decimal(28,8) null 
/*���������*/,
ninassistnum decimal(28,8) null 
/*ʵ�븨����*/,
blargess nchar(1) null 
/*�Ƿ���Ʒ*/,
cprojectid nvarchar(20) null 
/*��Ŀ*/,
vchangerate nvarchar(60) null 
/*������*/,
csourcetypecode nvarchar(20) null 
/*��Դ��������*/,
vsourcetrantype nvarchar(20) null 
/*��Դ��������*/,
csourceid nvarchar(20) null 
/*��Դ���ݱ�ͷ*/,
csourcebid nvarchar(20) null 
/*��Դ���ݱ���*/,
vsourcecode nvarchar(40) null 
/*��Դ���ݺ�*/,
vsourcerowno nvarchar(20) null 
/*��Դ�����к�*/,
cfirsttypecode nvarchar(20) null 
/*Դͷ��������*/,
vfirsttrantype nvarchar(20) null 
/*Դͷ���ݽ�������*/,
cfirstid nvarchar(20) null 
/*Դͷ���ݱ�ͷ*/,
cfirstbid nvarchar(20) null 
/*Դͷ���ݱ���*/,
vfirstcode nvarchar(40) null 
/*Դͷ���ݺ�*/,
vfirstrowno nvarchar(20) null 
/*Դͷ�����к�*/,
dfirstbilldate nvarchar(19) null 
/*Դͷ�����Ƶ�����*/,
vnotebody nvarchar(181) null 
/*�б�ע*/,
creceieveid nvarchar(20) null 
/*�ջ���λ*/,
crowno nvarchar(20) null 
/*�к�*/,
drequiredate nvarchar(19) null 
/*��������*/,
pk_reqstockorg nvarchar(20) null 
/*��������֯*/,
pk_reqstockorg_v nvarchar(20) null 
/*��������֯�汾*/,
pk_reqstocdoc nvarchar(20) null 
/*����ֿ�*/,
vordertrantypecode nvarchar(50) null 
/*������������*/,
pk_order nvarchar(20) null 
/*����*/,
pk_order_b nvarchar(20) null 
/*������ϸ*/,
vordercode nvarchar(50) null 
/*������*/,
cproductorid nvarchar(20) null 
/*��������*/,
cstateid nvarchar(20) null 
/*���״̬*/,
vctcode nvarchar(50) null 
/*�ɹ���ͬ��*/,
cunitid nvarchar(20) null 
/*����λ*/,
pk_supplier nvarchar(20) null 
/*��Ӧ��*/,
pk_vmisupplier nvarchar(20) null 
/*�Ĵ湩Ӧ��*/,
ncostprice decimal(28,8) null 
/*����*/,
ncostmny decimal(28,8) null 
/*���*/,
nplannedprice decimal(28,8) null 
/*�ƻ�����*/,
nplannedmny decimal(28,8) null 
/*�ƻ����*/,
cqtunitid nvarchar(20) null 
/*���۵�λ*/,
nqtunitnum decimal(28,8) null 
/*��������*/,
vqtunitrate nvarchar(60) null 
/*���۵�λ������*/,
corigcurrencyid nvarchar(20) null 
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
ftaxtypeflag int null 
/*��ⵥ��˰���*/,
norigmny decimal(28,8) null 
/*��ⵥ��˰���*/,
norigtaxmny decimal(28,8) null 
/*��ⵥ��˰�ϼ�*/,
ccurrencyid nvarchar(20) null 
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
pk_dtransaleid nvarchar(20) null 
/*��Ӧ��ֱ�����۶���*/,
pk_dtransalebid nvarchar(20) null 
/*��Ӧ��ֱ�����۶�����*/,
vbdef1 nvarchar(101) null 
/*�Զ�����1*/,
vbdef2 nvarchar(101) null 
/*�Զ�����2*/,
vbdef3 nvarchar(101) null 
/*�Զ�����3*/,
vbdef4 nvarchar(101) null 
/*�Զ�����4*/,
vbdef5 nvarchar(101) null 
/*�Զ�����5*/,
vbdef6 nvarchar(101) null 
/*�Զ�����6*/,
vbdef7 nvarchar(101) null 
/*�Զ�����7*/,
vbdef8 nvarchar(101) null 
/*�Զ�����8*/,
vbdef9 nvarchar(101) null 
/*�Զ�����9*/,
vbdef10 nvarchar(101) null 
/*�Զ�����10*/,
vbdef11 nvarchar(101) null 
/*�Զ�����11*/,
vbdef12 nvarchar(101) null 
/*�Զ�����12*/,
vbdef13 nvarchar(101) null 
/*�Զ�����13*/,
vbdef14 nvarchar(101) null 
/*�Զ�����14*/,
vbdef15 nvarchar(101) null 
/*�Զ�����15*/,
vbdef16 nvarchar(101) null 
/*�Զ�����16*/,
vbdef17 nvarchar(101) null 
/*�Զ�����17*/,
vbdef18 nvarchar(101) null 
/*�Զ�����18*/,
vbdef19 nvarchar(101) null 
/*�Զ�����19*/,
vbdef20 nvarchar(101) null 
/*�Զ�����20*/,
vfree1 nvarchar(101) null 
/*���ɸ�������1*/,
vfree2 nvarchar(101) null 
/*���ɸ�������2*/,
vfree3 nvarchar(101) null 
/*���ɸ�������3*/,
vfree4 nvarchar(101) null 
/*���ɸ�������4*/,
vfree5 nvarchar(101) null 
/*���ɸ�������5*/,
vfree6 nvarchar(101) null 
/*���ɸ�������6*/,
vfree7 nvarchar(101) null 
/*���ɸ�������7*/,
vfree8 nvarchar(101) null 
/*���ɸ�������8*/,
vfree9 nvarchar(101) null 
/*���ɸ�������9*/,
vfree10 nvarchar(101) null 
/*���ɸ�������10*/,
pk_costappsn nvarchar(20) null 
/*���ɱ���Ӧ����*/,
dtocostapdate nvarchar(19) null 
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
pk_estcurrency nvarchar(20) null 
/*����*/,
nestexhgrate decimal(28,8) null 
/*�۱�����*/,
nestomny decimal(28,8) null 
/*��˰���*/,
nestototalmny decimal(28,8) null 
/*��˰�ϼ�*/,
festtaxtype int null 
/*��˰���*/,
ftoiaflag int null default 0 
/*���ɱ���־*/,
ftoapflag int null default 0 
/*��Ӧ����־*/,
bsettlefinish nchar(1) null default 'N' 
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
pk_finishsettle_b nvarchar(20) null 
/*������ϵĽ��㵥����ϸ*/,
naccpreeststtlmny decimal(28,8) null 
/*�ݹ�ǰ�ۼƽ�����*/,
baffectcost nchar(1) null 
/*Ӱ��ɱ���־*/,
pk_stockps nchar(20) not null 
/*�ɹ������ͷ_����*/,
casscustid nvarchar(20) null 
/*�ͻ�*/,
ctaxcodeid nvarchar(20) null 
/*��ⵥ˰��*/,
nnosubtaxrate decimal(28,8) null 
/*��ⵥ���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*��ⵥ���ɵֿ�˰��*/,
ncaltaxmny decimal(28,8) null 
/*��ⵥ��˰���*/,
ncalcostmny decimal(28,8) null 
/*��ⵥ�Ƴɱ����*/,
bopptaxflag nchar(1) null 
/*������˰*/,
corigcountryid nvarchar(20) null 
/*��ⵥԭ����*/,
corigareaid nvarchar(20) null 
/*��ⵥԭ������*/,
cdesticountryid nvarchar(20) null 
/*��ⵥĿ�ĵع�*/,
cdestiareaid nvarchar(20) null 
/*��ⵥĿ�ĵص���*/,
cesttaxcodeid nvarchar(20) null 
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
cprojecttaskid nvarchar(20) null 
/*��Ŀ����*/,
naccadjustmny decimal(28,8) null 
/*�ۼƵ���������*/,
pk_arrliabcenter_v nvarchar(20) null 
/*�ջ���������*/,
pk_arrliabcenter nvarchar(20) null 
/*�ջ������������°汾*/,
baffectpccost nchar(1) null 
/*Ӱ���������ĳɱ�*/,
cffileid nvarchar(20) null 
/*������*/,
 constraint pk__purchaseinfi_b primary key (pk_stockps_b),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �ɹ�������ݹ���ϸ */
create table po_purchaseinfi_fee (
pk_stockps_fee nchar(20) not null 
/*�ɹ�������ݹ���ϸ*/,
pk_stockps nchar(20) null 
/*�ɹ������ͷ*/,
pk_group nvarchar(20) null 
/*����*/,
pk_financeorg nvarchar(20) null 
/*������֯*/,
pk_supplier nvarchar(20) null 
/*��Ӧ������*/,
pk_costfactor nvarchar(20) null 
/*�ɱ�Ҫ��*/,
pk_feematerial nvarchar(20) null 
/*������*/,
pk_srcfeematerial nvarchar(20) null 
/*�ݹ���������*/,
pk_estpsn nvarchar(20) null 
/*�ݹ���*/,
destdate nchar(19) null 
/*�ݹ�����*/,
nestexchgrate decimal(28,8) null 
/*�۱�����*/,
btoia nchar(1) null default 'N' 
/*���ɱ���־*/,
btoap nchar(1) null default 'N' 
/*��Ӧ����־*/,
nestmny decimal(28,8) null 
/*������˰���*/,
nesttaxmny decimal(28,8) null 
/*����˰��*/,
nesttaxrate decimal(28,8) null 
/*˰��*/,
nesttotalmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_estcurrency nvarchar(20) null 
/*����*/,
pk_localcurrency nvarchar(20) null 
/*����*/,
nestomny decimal(28,8) null 
/*ԭ����˰���*/,
nestototalmny decimal(28,8) null 
/*ԭ�Ҽ�˰�ϼ�*/,
pk_firstsettle nvarchar(20) null 
/*�ݹ��������ϵ�һ�ν���Ľ��㵥*/,
pk_firstsettle_b nvarchar(20) null 
/*�ݹ��������ϵ�һ�ν���Ľ��㵥��*/,
pk_stockps_b nchar(20) not null 
/*�ɹ��������_����*/,
csendcountryid nvarchar(20) null 
/*��������/����*/,
crececountryid nvarchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid nvarchar(20) null 
/*��˰����/����*/,
fbuysellflag int null 
/*��������*/,
btriatradeflag nchar(1) null 
/*����ó��*/,
ctaxcodeid nvarchar(20) null 
/*˰��*/,
ftaxtypeflag int null 
/*��˰���*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
nnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
bopptaxflag nchar(1) null 
/*������˰*/,
 constraint pk_urchaseinfi_fee primary key (pk_stockps_fee),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

