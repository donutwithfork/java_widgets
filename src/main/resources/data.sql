DROP TABLE IF EXISTS widget;

CREATE TABLE widget (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  x_coord INT NOT NULL,
  y_coord INT NOT NULL,
  width INT UNSIGNED NOT NULL DEFAULT 0,
  heigth INT UNSIGNED DEFAULT 0,
  zIndex INT,
  CONSTRAINT UC_zIndex UNIQUE (zIndex)
);

INSERT INTO widget (x_coord, y_coord, width, heigth, zIndex) VALUES
  (1, 1, 2, 2, 1),
  (2, 2, 4, 4, 2),
  (3, 3, 6, 6, 3),
  (0, 0, 10, 10, 4),
  (5, 5, 2, 2, 5);