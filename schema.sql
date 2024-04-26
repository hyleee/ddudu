-- DROP DATABASE ssafydb;

create database ssafydb;
USE ssafydb;
-- 사용자 테이블
CREATE TABLE User (
    user_id VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    user_height INT,
    user_weight INT,
    user_area VARCHAR(100),
    profile LONGBLOB,
    PRIMARY KEY (user_id)
);

-- 운동 일지 테이블
CREATE TABLE exercise_diary (
    user_id VARCHAR(100),
    diary_content VARCHAR(100),
    today_weight INT,
    diary_photo BLOB,
    exercise_date DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 게시판 테이블
CREATE TABLE board (
    article_id INT NOT NULL,
    user_id VARCHAR(100),
    user_area VARCHAR(100),
    category VARCHAR(100),
    PRIMARY KEY (article_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 게시글 테이블
CREATE TABLE article (
    article_id INT,
    user_id VARCHAR(100),
    user_area VARCHAR(100),
    category VARCHAR(100),
    article_title VARCHAR(100),
    article_content VARCHAR(100),
    article_created DATETIME,
    FOREIGN KEY (article_id) REFERENCES board(article_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 게시글 좋아요 테이블
CREATE TABLE article_like (
    article_id INT,
    user_id VARCHAR(100),
    FOREIGN KEY (article_id) REFERENCES article(article_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 댓글 테이블
CREATE TABLE comment (
    comment_id INT NOT NULL,
    comment_created DATETIME,
    comment_content VARCHAR(100),
    user_id VARCHAR(100),
    article_id INT,
    user_area VARCHAR(100),
    PRIMARY KEY (comment_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (article_id) REFERENCES article(article_id)
);

-- 댓글 좋아요 테이블
CREATE TABLE comment_like (
    comment_id INT,
    user_id VARCHAR(100),
    FOREIGN KEY (comment_id) REFERENCES comment(comment_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 답글 테이블
CREATE TABLE reply (
    reply_id INT NOT NULL,
    user_id VARCHAR(100),
    reply_content VARCHAR(100),
    reply_created DATETIME,
    comment_id INT,
    user_area VARCHAR(100),
    PRIMARY KEY (reply_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (comment_id) REFERENCES comment(comment_id)
);

-- 답글 좋아요 테이블
CREATE TABLE reply_like (
    reply_id INT,
    user_id VARCHAR(100),
    FOREIGN KEY (reply_id) REFERENCES reply(reply_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 운동계획 테이블
CREATE TABLE plan (
    exercise_date DATETIME,
    user_id VARCHAR(100),
    body_part VARCHAR(100),
    exercise_name VARCHAR(100),
    exercise_kg INT,
    exercise_count INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 부위별 운동량 테이블
CREATE TABLE exercise_per_part_sum (
    exercise_date DATETIME,
    user_id VARCHAR(100),
    chest_kg INT,
    back_kg INT,
    shoulder_kg INT,
    leg_kg INT,
    abs_kg INT,
    arm_kg INT,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
