/* tablename: �����������뵥��ϸ */
create table po_storereq_b (pk_storereq_b char(20) not null 
/*�����������뵥��ϸ*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*�����֯���°汾*/,
pk_org_v varchar(20) null 
/*�����֯*/,
crowno varchar(20) null 
/*�к�*/,
pk_material varchar(20) null 
/*���ϰ汾��Ϣ*/,
pk_srcmaterial varchar(20) null 
/*������Ϣ*/,
cunitid varchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
castunitid varchar(20) null 
/*��λ*/,
nastnum decimal(28,8) null 
/*����*/,
vchangerate varchar(60) null 
/*������*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
pk_reqstordoc varchar(20) null 
/*����ֿ�*/,
dreqdate varchar(19) null 
/*��������*/,
pk_apppsn varchar(20) null 
/*������*/,
pk_appdept_v varchar(20) null 
/*���벿��*/,
pk_appdept varchar(20) null 
/*���벿�����°汾*/,
pk_receiveaddress varchar(20) null 
/*�ջ���ַ*/,
cdevareaid varchar(20) null 
/*�ջ�����*/,
cdevaddrid varchar(20) null 
/*�ջ��ص�*/,
csourcetypecode varchar(20) null 
/*��Դ��������*/,
csourceid varchar(20) null 
/*��Դ����*/,
csourcebid varchar(20) null 
/*��Դ������ϸ*/,
vsourcecode varchar(40) null 
/*��Դ���ݺ�*/,
vsourcetrantype varchar(20) null 
/*��Դ��������*/,
vsourcerowno varchar(20) null 
/*��Դ�����к�*/,
cfirsttypecode varchar(20) null 
/*Դͷ��������*/,
cfirstid varchar(20) null 
/*Դͷ����*/,
cfirstbid varchar(20) null 
/*Դͷ������ϸ*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
naccuoutnum decimal(28,8) null 
/*�ۼƳ���������*/,
naccuoutreqnum decimal(28,8) null 
/*�ۼ��������������*/,
bclose char(1) null default 'N' 
/*�Ƿ�ر�*/,
pk_batchcode varchar(20) null 
/*���κ�����*/,
vbatchcode varchar(40) null 
/*���κ�*/,
cvendorid varchar(20) null 
/*��Ӧ��*/,
cproductorid varchar(20) null 
/*��������*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cprojecttaskid varchar(20) null 
/*��Ŀ����*/,
ccostelementid varchar(20) null 
/*�ɱ�Ҫ��*/,
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
vbmemo varchar(181) null 
/*��ע*/,
dbilldate char(19) null 
/*��������*/,
ccurrencyid varchar(20) null 
/*���ұ���*/,
pk_storereq char(20) not null 
/*�����������뵥_����*/,
naccumbuyreqnum decimal(28,8) null 
/*�ۼ��빺������*/,
pk_reqstoorg_v varchar(20) null 
/*ԭʼ��������֯*/,
pk_reqstoorg varchar(20) null 
/*ԭʼ��������֯���°汾*/,
pk_nextbalanceorg_v varchar(20) null 
/*�´�ƽ������֯*/,
pk_nextbalanceorg varchar(20) null 
/*�´�ƽ������֯���°汾*/,
bendgather char(1) null 
/*��ƽ��*/,
naccustornum decimal(28,8) null 
/*�������������*/,
nnetnum decimal(28,8) null 
/*ת������������*/,
csourceid2 varchar(20) null 
/*���ε���*/,
csourcebid2 varchar(20) null 
/*���ε�����ϸ*/,
vsourcecode2 varchar(40) null 
/*���ε��ݺ�*/,
vsourcerowno2 varchar(20) null 
/*���ε����к�*/,
csourcetypecode2 varchar(20) null 
/*���ε�������*/,
vsourcetrantype2 varchar(20) null 
/*���ε��ݽ�������*/,
cfirstid2 varchar(20) null 
/*��������*/,
cfirstbid2 varchar(20) null 
/*����������ϸ*/,
vfirstcode2 varchar(40) null 
/*����������*/,
vfirstrowno2 varchar(20) null 
/*���������к�*/,
cfirsttypecode2 varchar(20) null 
/*������������*/,
vfirsttrantype2 varchar(20) null 
/*�������ݽ�������*/,
tgathertime char(19) null 
/*����ʱ��*/,
cgatherpsnid varchar(20) null 
/*������*/,
cgatherid varchar(20) null 
/*����ID*/,
naccumminusnum decimal(28,8) null 
/*����ƽ��ת�빺������*/,
cbs varchar(20) null 
/*CBS*/,
 constraint pk_po_storereq_b primary key (pk_storereq_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �����������뵥 */
create table po_storereq (pk_storereq char(20) not null 
/*�����������뵥*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*�����֯���°汾*/,
pk_org_v varchar(20) null 
/*�����֯*/,
vbillcode varchar(40) null 
/*���뵥��*/,
dbilldate char(19) null 
/*��������*/,
pk_appdepth varchar(20) null 
/*���벿�����°汾*/,
pk_appdepth_v varchar(20) null 
/*���벿��*/,
pk_apppsnh varchar(20) null 
/*������*/,
vtrantypecode varchar(20) null 
/*�������ͱ������*/,
fbillstatus integer null default 0 
/*����״̬*/,
burgency char(1) null default 'N' 
/*����*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotalorigmny decimal(28,8) null 
/*��˰�ϼ�*/,
pk_project varchar(20) null 
/*��Ŀ*/,
creator varchar(20) null 
/*������*/,
creationtime char(19) null 
/*����ʱ��*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*��������*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime varchar(19) null 
/*����޸�ʱ��*/,
iprintcount integer null default 0 
/*��ӡ����*/,
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
vmemo varchar(181) null 
/*��ע*/,
freqtypeflag integer null 
/*��������*/,
ctrantypeid varchar(20) null 
/*����������������*/,
 constraint pk_po_storereq primary key (pk_storereq),
 ts char(19) null,
dr smallint null default 0
)
;

