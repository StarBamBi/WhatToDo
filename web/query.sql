CREATE TABLE freeboard
(
    iboard    INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    icategory INT UNSIGNED,
    title     VARCHAR(100) NOT NULL,
    ctnt      TEXT         NOT NULL,
    iuser     INT UNSIGNED NOT NULL,
    hits      INT UNSIGNED     DEFAULT 0,
    isdel     TINYINT UNSIGNED DEFAULT 0,
    rdt       DATETIME         DEFAULT CURRENT_TIMESTAMP,
    mdt       DATETIME         DEFAULT CURRENT_TIMESTAMP,
    lastip    varchar(15)
);

CREATE TABLE menu_category
(
    icategory INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm        VARCHAR(10) NOT NULL,
    nmval     VARCHAR(20) NOT NULL,
    orderby   TINYINT     NOT NULL DEFAULT 0
);

INSERT INTO menu_category (nm)
VALUES ('게임'),
       ('넷플릭스'),
       ('유튜브'),
       ('게시판');

INSERT INTO menu_category (nmval)
VALUES ('game'),
       ('netflix'),
       ('youtube'),
       ('list');


CREATE TABLE whattodo_user
(
    iuser      INT UNSIGNED AUTO_INCREMENT,
    uid        VARCHAR(15)  NOT NULL,
    upw        VARCHAR(100) NOT NULL,
    nm         VARCHAR(10)  NOT NULL,
    gender     TINYINT      NOT NULL DEFAULT 3 CHECK (gender IN (1, 2, 3)),
    profileimg VARCHAR(100),
    rdt        DATETIME              DEFAULT CURRENT_TIMESTAMP,
    mdt        DATETIME              DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PRIMARY KEY (iuser),
    CONSTRAINT UNIQUE (uid)
);

CREATE TABLE rating_Game
(
    rnum       INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    gameNm     VARCHAR(100),
    gameRating VARCHAR(10),
    gameRank   VARCHAR(10)
);


CREATE TABLE meca_rankdb
(
    irank   INT AUTO_INCREMENT,
    rankNum VARCHAR(10),
    rankNm  VARCHAR(100),
    company VARCHAR(50),
    PRIMARY KEY (irank)
);

CREATE TABLE steam_rankdb
(
    irank   INT AUTO_INCREMENT,
    rankNum VARCHAR(10),
    rankNm  VARCHAR(100),
    PRIMARY KEY (irank)
);

CREATE TABLE rating_Game
(
    rnum       INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    gameNm     VARCHAR(100),
    gameRating VARCHAR(10),
    gameRank   VARCHAR(10)
);


CREATE TABLE webtoon
(
    wnum     INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm       VARCHAR(50) NOT NULL,
    writer   VARCHAR(20) NOT NULL,
    rating   VARCHAR(10),
    img      VARCHAR(300),
    weekend  VARCHAR(10),
    homepage INT DEFAULT 1,
    CONSTRAINT UNIQUE (nm)
);

CREATE TABLE webtoon_recommand
(
    wrnum    INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm       VARCHAR(50) NOT NULL,
    writer   VARCHAR(20) NOT NULL,
    rating   VARCHAR(10),
    img      VARCHAR(300),
    weekend  VARCHAR(10),
    link     VARCHAR(100),
    homepage INT DEFAULT 1
);


CREATE TABLE auto_login
(
    `index`         INT UNSIGNED AUTO_INCREMENT,         # primary 값
    auto_key        VARCHAR(100) NOT NULL,               # 자동로그인 key
    user_id         VARCHAR(15)  NOT NULL,               # 유저 id
    create_at       DATETIME     NOT NULL DEFAULT NOW(), # 키 생성일
    expiration_at   DATETIME     NOT NULL,               # 키 만료일
    expiration_flag BOOLEAN      NOT NULL DEFAULT FALSE, # 키 만료여부
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (auto_key),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES whattodo_user (uid)
);

CREATE TABLE fav_webtoon
(
    nm    VARCHAR(50),
    iuser INT UNSIGNED,
    rdt   DATETIME DEFAULT CURRENT_TIMESTAMP,
    mdt   DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (nm, iuser)
);

CREATE TABLE review_webtoon
(
    rnum     INT UNSIGNED auto_increment,
    ctnt     VARCHAR(500) NOT NULL,
    nm       VARCHAR(50)  NOT NULL,
    nickname VARCHAR(50),
    iuser    INT unsigned,
    wnum     INT unsigned,
    PRIMARY KEY (rnum),
    FOREIGN KEY (`nm`) REFERENCES `webtoon` (`nm`)
);