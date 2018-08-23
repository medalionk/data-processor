DROP TABLE if EXISTS feed;

CREATE TABLE feed (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  created_date_time datetime NOT NULL,
  last_modified_date_time datetime DEFAULT NULL,
  contents varchar(255) DEFAULT NULL,
  date_time datetime NOT NULL,
  description varchar(255) DEFAULT NULL,
  link varchar(255) NOT NULL UNIQUE,
  title varchar(255) NOT NULL,
  PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;