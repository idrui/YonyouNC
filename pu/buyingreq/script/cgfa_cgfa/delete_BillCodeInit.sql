DELETE FROM pub_bcr_candiattr WHERE pk_nbcr = '0001Z31000000000B7QF';
DELETE FROM pub_bcr_elem WHERE pk_billcodebase in ( select pk_billcodebase from pub_bcr_RuleBase where nbcrcode = 'JT01' );
DELETE FROM pub_bcr_RuleBase WHERE nbcrcode = 'JT01';
DELETE FROM pub_bcr_nbcr WHERE pk_nbcr = '0001Z31000000000B7QF';
DELETE FROM pub_bcr_OrgRela WHERE pk_billcodebase = '0001Z31000000000B7QG';
DELETE FROM pub_bcr_RuleBase WHERE pk_billcodebase = '0001Z31000000000B7QG';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QH';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QI';
DELETE FROM pub_bcr_elem WHERE pk_billcodeelem = '0001Z31000000000B7QJ';