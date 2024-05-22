-- drop database ssafydb;
-- create database ssafydb;
use ssafydb;

-- ______________________________________INSERT ______________________________________

-- daily_plan 테이블에 대한 INSERT 문
INSERT INTO daily_plan (exercise_date, user_id, body_part, exercise_name) VALUES 
('2024-05-17', 'a', '어깨', '숄더 프레스'),
('2024-05-17', 'a', '팔', '바이셉 컬'),
('2024-05-18', 'a', '등', '랫 풀 다운'),
('2024-05-18', 'a', '가슴', '벤치 프레스'),
('2024-05-19', 'a', '복근', '크런치'),
('2024-05-19', 'a', '하체', '스쿼트'),
('2024-05-20', 'a', '어깨', '사이드 레터럴 레이즈'),
('2024-05-20', 'a', '팔', '트라이셉 익스텐션'),
('2024-05-21', 'a', '등', '데드리프트'),
('2024-05-21', 'a', '가슴', '딥스');
select * from daily_plan;

-- daily_plan_detail 테이블에 대한 INSERT 문
INSERT INTO daily_plan_detail (exercise_kg, exercise_count, plan_id) VALUES 
(20, 10, 1),
(15, 12, 2),
(40, 8, 3),
(60, 6, 4),
(0, 20, 5),
(80, 5, 6),
(10, 15, 7),
(25, 10, 8),
(100, 5, 9),
(30, 8, 10);


-- User 테이블에 대한 INSERT 문 10개
INSERT INTO User (user_id, user_password, user_name, user_email, user_age, user_height, user_weight, user_area, user_profile) VALUES
('u1', 'pass1', 'User One', 'user1@example.com', 25, 175, 70, 'area1', 'profile1.png'),
('u2', 'pass2', 'User Two', 'user2@example.com', 30, 180, 75, 'area2', 'profile2.png'),
('u3', 'pass3', 'User Three', 'user3@example.com', 28, 165, 68, 'area3', 'profile3.png'),
('u4', 'pass4', 'User Four', 'user4@example.com', 22, 170, 65, 'area1', 'profile4.png'),
('u5', 'pass5', 'User Five', 'user5@example.com', 27, 160, 60, 'area2', 'profile5.png'),
('u6', 'pass6', 'User Six', 'user6@example.com', 35, 185, 80, 'area3', 'profile6.png'),
('u7', 'pass7', 'User Seven', 'user7@example.com', 24, 172, 72, 'area1', 'profile7.png'),
('u8', 'pass8', 'User Eight', 'user8@example.com', 29, 168, 66, 'area2', 'profile8.png'),
('u9', 'pass9', 'User Nine', 'user9@example.com', 33, 178, 74, 'area3', 'profile9.png'),
('u10', 'pass10', 'User Ten', 'user10@example.com', 26, 182, 78, 'area1', 'profile10.png');

-- article 테이블에 대한 INSERT 문 20개
INSERT INTO article (article_id, user_id, article_title, article_content, category) VALUES
(1, 'u1', 'Article 1-1', 'Content 1-1', 'shoulders'),
(2, 'u1', 'Article 1-2', 'Content 1-2', 'legs'),
(3, 'u2', 'Article 2-1', 'Content 2-1', 'arms'),
(4, 'u2', 'Article 2-2', 'Content 2-2', 'back'),
(5, 'u3', 'Article 3-1', 'Content 3-1', 'chest'),
(6, 'u3', 'Article 3-2', 'Content 3-2', 'abs'),
(7, 'u4', 'Article 4-1', 'Content 4-1', 'shoulders'),
(8, 'u4', 'Article 4-2', 'Content 4-2', 'legs'),
(9, 'u5', 'Article 5-1', 'Content 5-1', 'arms'),
(10, 'u5', 'Article 5-2', 'Content 5-2', 'back'),
(11, 'u6', 'Article 6-1', 'Content 6-1', 'chest'),
(12, 'u6', 'Article 6-2', 'Content 6-2', 'abs'),
(13, 'u7', 'Article 7-1', 'Content 7-1', 'shoulders'),
(14, 'u7', 'Article 7-2', 'Content 7-2', 'legs'),
(15, 'u8', 'Article 8-1', 'Content 8-1', 'arms'),
(16, 'u8', 'Article 8-2', 'Content 8-2', 'back'),
(17, 'u9', 'Article 9-1', 'Content 9-1', 'chest'),
(18, 'u9', 'Article 9-2', 'Content 9-2', 'abs'),
(19, 'u10', 'Article 10-1', 'Content 10-1', 'shoulders'),
(20, 'u10', 'Article 10-2', 'Content 10-2', 'legs');


-- ______________________________________CREATE ______________________________________

-- 사용자 테이블
CREATE TABLE User (
    user_id VARCHAR(100) NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100),
    user_age INT,
    user_height INT,
    user_weight INT,
    user_area VARCHAR(100),
    user_profile VARCHAR(100),
    PRIMARY KEY (user_id)
);

CREATE TABLE auth (
    user_id VARCHAR(100) NOT NULL PRIMARY KEY,
    refresh_token TEXT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);
select * from auth;



-- 운동 일지 테이블
CREATE TABLE exercise_diary (
    user_id VARCHAR(100),
    diary_content VARCHAR(100),
    today_weight INT,
    diary_photo BLOB,
    exercise_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);


-- 게시글 테이블
CREATE TABLE article (
    article_id INT AUTO_INCREMENT,
    user_id VARCHAR(100),
    article_title VARCHAR(100),
    article_content VARCHAR(100),
    article_created  DATETIME DEFAULT CURRENT_TIMESTAMP,
    category VARCHAR(100),
    PRIMARY KEY (article_id, user_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);


-- 게시글 좋아요 테이블
CREATE TABLE article_like (
    article_id INT,
    user_id VARCHAR(100),
     -- 한 사용자가 각 게시물에 대해 단 한 번만 좋아요를 할 수 있도록 하며, 각 좋아요를 고유하게 식별하도록 복합키 사용
    PRIMARY KEY (article_id, user_id),
    FOREIGN KEY (article_id) REFERENCES article(article_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 댓글 테이블
CREATE TABLE comment (
    comment_id INT AUTO_INCREMENT,
    comment_created  DATETIME DEFAULT CURRENT_TIMESTAMP,
    comment_content VARCHAR(100),
    user_id VARCHAR(100),
    article_id INT,
    PRIMARY KEY (comment_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (article_id) REFERENCES article(article_id)
);

-- 댓글 좋아요 테이블
CREATE TABLE comment_like (
    comment_id INT,
    user_id VARCHAR(100),
     -- 한 사용자가 각 댓글에 대해 단 한 번만 좋아요를 할 수 있도록 하며, 각 좋아요를 고유하게 식별하도록 복합키 사용
     PRIMARY KEY (comment_id, user_id),
    FOREIGN KEY (comment_id) REFERENCES comment(comment_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 답글 테이블
CREATE TABLE reply (
    reply_id INT AUTO_INCREMENT,
    user_id VARCHAR(100),
    reply_content VARCHAR(100),
    reply_created  DATETIME DEFAULT CURRENT_TIMESTAMP,
    comment_id INT,
    PRIMARY KEY (reply_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (comment_id) REFERENCES comment(comment_id)
);

-- 답글 좋아요 테이블
CREATE TABLE reply_like (
    reply_id INT,
    user_id VARCHAR(100),
     -- 한 사용자가 각 답글에 대해 단 한 번만 좋아요를 할 수 있도록 하며, 각 좋아요를 고유하게 식별하도록 복합키 사용
    PRIMARY KEY (reply_id, user_id),
    FOREIGN KEY (reply_id) REFERENCES reply(reply_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 운동계획 테이블
CREATE TABLE daily_plan (
	plan_id INT AUTO_INCREMENT,
    exercise_date DATE,
    user_id VARCHAR(100),
    body_part VARCHAR(100),
    exercise_name VARCHAR(100),
    
    
    PRIMARY KEY (plan_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE daily_plan_detail (
    detail_id INT AUTO_INCREMENT,
    exercise_kg INT,
    exercise_count INT,
    plan_id INT,
    PRIMARY KEY (detail_id),
    FOREIGN KEY (plan_id) REFERENCES daily_plan(plan_id) ON DELETE CASCADE
);



-- 부위별 운동량 테이블
CREATE TABLE exercise_per_part_sum (
    exercise_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    user_id VARCHAR(100),
    chest_kg INT,
    back_kg INT,
    shoulder_kg INT,
    leg_kg INT,
    abs_kg INT,
    arm_kg INT,
    -- 각 사용자가 각 날짜에 대해 하루에 하나씩 부위별 운동량 가질 수 있다.
    PRIMARY KEY (exercise_date, user_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

CREATE TABLE video(
	video_id INT AUTO_INCREMENT PRIMARY KEY,
    video_title VARCHAR(100),
    body_part VARCHAR(100),
    youtube_id VARCHAR(100),
    channel_name VARCHAR(100),
    view_count INT
);

drop table follow;
CREATE TABLE Follow (
    follower_id VARCHAR(100) NOT NULL, -- 팔로우 당하는 놈
    following_id VARCHAR(100) NOT NULL, -- 팔로우 신청한 놈
    FOREIGN KEY (follower_id) REFERENCES User(user_id),
    FOREIGN KEY (following_id) REFERENCES User(user_id),
    -- 같은 사용자에 대한 중복된 팔로우를 방지하기 위해 follower_id 및 following_id 열의 조합이 고유해야 한다.
    CONSTRAINT unique_follow UNIQUE (follower_id, following_id)
);
select * from follow;
