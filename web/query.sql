CREATE TABLE freeboard(
                          iboard INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                          icategory INT UNSIGNED NOT NULL,
                          title VARCHAR(100) NOT NULL,
                          ctnt TEXT NOT NULL,
                          iuser INT UNSIGNED NOT NULL,
                          hits INT UNSIGNED DEFAULT 0,
                          isdel TINYINT UNSIGNED DEFAULT 0,
                          rdt DATETIME DEFAULT CURRENT_TIMESTAMP,
                          mdt DATETIME DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE menu_category(
                              icategory INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
                              nm VARCHAR(10) NOT NULL,
                              nmval VARCHAR(20) NOT NULL,
                              orderby TINYINT NOT NULL DEFAULT 0
)
INSERT INTO menu_category (nm)
VALUES
('게임'),
('넷플릭스'),
('유튜브'),
('게시판');

INSERT INTO menu_category (nmval)
VALUES
    ('game'),
    ('netflix'),
    ('youtube'),
    ('list');