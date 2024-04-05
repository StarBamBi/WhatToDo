CREATE DATABASE whattodo;

use whattodo;


CREATE TABLE menu_category # 메인페이지 메뉴
(
    icategory INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm        VARCHAR(10) NOT NULL,
    nmval     VARCHAR(20) NOT NULL,
    orderby   TINYINT     NOT NULL DEFAULT 0
);

INSERT INTO menu_category (nm, nmval)
VALUES ('게임', 'game'),
       ('넷플릭스', 'netflix'),
       ('유튜브', 'youtube'),
       ('게시판', 'list');

CREATE TABLE freeboard # 자유게시판
(
    iboard INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title  VARCHAR(100) NOT NULL,
    ctnt   TEXT         NOT NULL,
    iuser  INT UNSIGNED NOT NULL,
    hits   INT UNSIGNED     DEFAULT 0,
    isdel  TINYINT UNSIGNED DEFAULT 0,
    rdt    DATETIME         DEFAULT CURRENT_TIMESTAMP,
    mdt    DATETIME         DEFAULT CURRENT_TIMESTAMP,
    lastip VARCHAR(15),
    nm     VARCHAR(15)  NOT NULL
);

CREATE TABLE freeboard_cmt # 자유게시판 댓글
(
    icmt   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    iboard INT UNSIGNED NOT NULL,
    iuser  INT UNSIGNED NOT NULL,
    ctnt   TEXT         NOT NULL,
    rdt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    mdt    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    nm     VARCHAR(15)  NOT NULL
);

CREATE TABLE freeboard_like # 자유게시판 좋아요
(
    iboard INT UNSIGNED,
    iuser  INT UNSIGNED,
    rdt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (iboard, iuser)
);

CREATE TABLE meca_rankdb # 게임메카 순위 DB
(
    irank   INT AUTO_INCREMENT,
    rankNum VARCHAR(10),
    gameNm  VARCHAR(100),
    company VARCHAR(50),
    imgsrc  VARCHAR(200),
    selLink VARCHAR(300),
    PRIMARY KEY (irank)
);

CREATE TABLE platform_rankdb
(
    irank   INT AUTO_INCREMENT,
    rankNum VARCHAR(10),
    gameNm  VARCHAR(100),
    company VARCHAR(50),
    genre   VARCHAR(30),
    selLink VARCHAR(300),
    PRIMARY KEY (irank)
);

CREATE TABLE platform_img
(
    i_img  INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    imgsrc VARCHAR(500),
    gameNm VARCHAR(100)
);

CREATE TABLE game_cmt # 게임 댓글
(
    icmt   INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    gameNm VARCHAR(50)  NOT NULL,
    iuser  INT UNSIGNED NOT NULL,
    ctnt   VARCHAR(50)  NOT NULL,
    rdt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    mdt    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    nm     VARCHAR(15)  NOT NULL
);

CREATE TABLE webtoon # 웹툰 크롤링
(
    wnum     INT UNSIGNED,
    nm       VARCHAR(50) NOT NULL,
    writer   VARCHAR(20) NOT NULL,
    rating   VARCHAR(10),
    img      VARCHAR(300),
    weekend  VARCHAR(10),
    link     VARCHAR(100),
    homepage INT DEFAULT 1,
    PRIMARY KEY (nm, weekend)
);
drop table webtoon;
CREATE TABLE webtoon_recommand # 추천 웹툰
(
    wrnum     INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm        VARCHAR(50) NOT NULL,
    writer    VARCHAR(20) NOT NULL,
    rating    VARCHAR(10),
    img       VARCHAR(300),
    weekend   VARCHAR(10),
    link      VARCHAR(100),
    homepage  INT DEFAULT 1,
    stateNull INT
);


CREATE TABLE whattodo_user # 유저정보관리 DB
(
    iuser      INT UNSIGNED AUTO_INCREMENT,                                 # primary
    uid        VARCHAR(100) NOT NULL,                                       # email
    upw        VARCHAR(100) NOT NULL,                                       # password
    nm         VARCHAR(15)  NOT NULL,                                       # 성명
    gender     TINYINT      NOT NULL DEFAULT 3 CHECK (gender IN (1, 2, 3)), # 성별 1: 남자 2: 여자 3: 선택안함
    contact    INT          NOT NULL,                                       # 연락처(휴대폰)
    postaddr   INT          NOT NULL,                                       # 우편번호
    addrfirst  VARCHAR(100) NOT NULL,                                       # 주소
    addrsecond VARCHAR(100),                                                # 상세주소
    profileimg VARCHAR(100),                                                # 프로필이미지
    rdt        DATETIME              DEFAULT CURRENT_TIMESTAMP,             # 계정 생성일
    mdt        DATETIME              DEFAULT CURRENT_TIMESTAMP,             # 계정 수정일
    level      TINYINT      NOT NULL CHECK ( level IN (1, 2, 3)),           # 유저 종류 구분 1: 관리자 2: 자체유저 3: 소셜유저
    CONSTRAINT PRIMARY KEY (iuser),
    CONSTRAINT UNIQUE (uid)
);

CREATE TABLE auto_login # 자동로그인
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
    nm     VARCHAR(50),
    iuser  INT UNSIGNED,
    rdt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    mdt    DATETIME DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP,
    iboard INT,
    PRIMARY KEY (nm, iuser)
);

CREATE TABLE review_webtoon # 웹툰 댓글
(
    rnum     INT UNSIGNED AUTO_INCREMENT,
    ctnt     VARCHAR(500) NOT NULL,
    nm       VARCHAR(50)  NOT NULL, # 웹툰 이름
    nickname VARCHAR(50),           # 유저 이름
    iuser    INT UNSIGNED,
    wnum     INT UNSIGNED,
    PRIMARY KEY (rnum),
    FOREIGN KEY (`nm`) REFERENCES `webtoon` (`nm`)
);

CREATE TABLE game_like # 게임 게시판 좋아요
(
    gameNm VARCHAR(100) NOT NULL,
    iuser  INT UNSIGNED,
    rdt    DATETIME DEFAULT CURRENT_TIMESTAMP,
    iboard INT,
    PRIMARY KEY (gameNm, iuser)
);

CREATE TABLE webtoon_genre # 장르별 웹툰
(
    wgnum    INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    nm       VARCHAR(50) NOT NULL,
    writer   VARCHAR(20) NOT NULL,
    rating   VARCHAR(10),
    img      VARCHAR(300),
    genre    VARCHAR(10),
    state    VARCHAR(10),
    link     VARCHAR(100),
    homepage INT DEFAULT 1
);

CREATE TABLE video_movie # 영화 크롤링 DB
(
    mnum          INT AUTO_INCREMENT PRIMARY KEY,
    movieNm       VARCHAR(50) NOT NULL,
    rank          VARCHAR(5),
    boxofficeType VARCHAR(20),
    showRange     VARCHAR(20),
    movieCd       VARCHAR(30),
    img           VARCHAR(100),
    rating        VARCHAR(10),
    actor         VARCHAR(100),
    director      VARCHAR(20)
);

CREATE TABLE rating_game
(
    rnum       INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    gameNm     VARCHAR(100),
    gameRating VARCHAR(10),
    gameRank   VARCHAR(10)
);

CREATE TABLE find_pw # 비밀번호 찾기
(
    pk              INT UNSIGNED AUTO_INCREMENT,         # primary
    cookie          VARCHAR(100) NOT NULL,               # 비밀번호찾기 쿠키
    iuser           INT UNSIGNED,                        # 유저 id
    create_at       DATETIME     NOT NULL DEFAULT NOW(), # 키 생성일
    expiration_at   DATETIME     NOT NULL,               # 키 만료일
    expiration_flag BOOLEAN      NOT NULL DEFAULT FALSE, # 키 만료여부
    CONSTRAINT PRIMARY KEY (pk),
    CONSTRAINT UNIQUE (cookie),
    CONSTRAINT FOREIGN KEY (iuser) REFERENCES whattodo_user (iuser)
);

