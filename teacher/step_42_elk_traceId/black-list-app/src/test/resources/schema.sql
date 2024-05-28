
CREATE TABLE black_listed_persons (
  id BIGINT NOT NULL AUTO_INCREMENT,
  person_first_name VARCHAR(200) NOT NULL,
  person_last_name VARCHAR(200) NOT NULL,
  person_code VARCHAR(200) NOT NULL,
  PRIMARY KEY (id)
);

CREATE UNIQUE INDEX ix_unique_black_listed_persons
ON black_listed_persons(person_first_name, person_last_name, person_code);
