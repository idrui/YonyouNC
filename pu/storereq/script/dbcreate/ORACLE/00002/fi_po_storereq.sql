/* indexcode: i_pu_422x_b_src */
create  index i_pu_422x_b_src on po_storereq_b (csourceid asc)
/

/* indexcode: i_pu_422x_b_dtorg */
create  index i_pu_422x_b_dtorg on po_storereq_b (dreqdate asc,
pk_org asc)
/

/* indexcode: i_pu_422x_b_fk */
create  index i_pu_422x_b_fk on po_storereq_b (pk_storereq asc)
/

/* indexcode: i_pu_422x_b_srctc2 */
create  index i_pu_422x_b_srctc2 on po_storereq_b (csourceid2 asc,
csourcetypecode2 asc)
/

/* indexcode: i_pu_422x_dtorg */
create  index i_pu_422x_dtorg on po_storereq (dbilldate asc,
pk_org asc)
/

/* indexcode: i_pu_422x_code */
create  index i_pu_422x_code on po_storereq (vbillcode asc)
/

