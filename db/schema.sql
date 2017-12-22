create table ip_access (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  ip CHAR(15) NOT NULL,
  access_date DATETIME NOT NULL,
  PRIMARY KEY (id)
)