/* indexcode: i_pu_47_dtorg */
create  index i_pu_47_dtorg on po_subcontinfi (dbilldate asc,
pk_org asc)
/

/* indexcode: i_pu_47_code */
create  index i_pu_47_code on po_subcontinfi (vbillcode asc)
/

/* indexcode: i_pu_47_b_ordb */
create  index i_pu_47_b_ordb on po_subcontinfi_b (pk_order_b asc)
/

/* indexcode: i_pu_47_b_fk */
create  index i_pu_47_b_fk on po_subcontinfi_b (pk_stockps asc)
/

/* indexcode: i_pu_47_b_dtfi */
create  index i_pu_47_b_dtfi on po_subcontinfi_b (dbizdate asc,
pk_financeorg asc)
/

/* indexcode: i_pu_47_b_ordh */
create  index i_pu_47_b_ordh on po_subcontinfi_b (pk_order asc)
/

