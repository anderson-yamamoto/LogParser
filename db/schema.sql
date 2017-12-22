create table ip_access (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  ip CHAR(15) NOT NULL,
  access_date DATETIME NOT NULL,
  PRIMARY KEY (id)
)

create table blocked_ip (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  ip CHAR(15) NOT NULL,
  reason VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
)