/* indexcode: i_pu_20_b_src */
create  index i_pu_20_b_src on po_praybill_b (csourceid asc)
/

/* indexcode: i_pu_20_b_dtorg */
create  index i_pu_20_b_dtorg on po_praybill_b (dbilldate asc,
pk_purchaseorg asc)
/

/* indexcode: i_pu_20_b_rdtorg */
create  index i_pu_20_b_rdtorg on po_praybill_b (dreqdate asc,
pk_org asc)
/

/* indexcode: i_pu_20_b_fk */
create  index i_pu_20_b_fk on po_praybill_b (pk_praybill asc)
/

/* indexcode: i_pu_20_code */
create  index i_pu_20_code on po_praybill (vbillcode asc)
/

/* indexcode: i_pu_20_dtorg */
create  index i_pu_20_dtorg on po_praybill (dbilldate asc,
pk_org asc)
/

/* indexcode: i_pu_20_revsrc */
create  index i_pu_20_revsrc on po_praybill (pk_srcpraybill asc)
/

/* indexcode: i_pu_20_taudittime */
create  index i_pu_20_taudittime on po_praybill (taudittime asc)
/

