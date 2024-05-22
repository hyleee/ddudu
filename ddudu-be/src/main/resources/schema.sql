-- drop database ssafydb;
-- create database ssafydb;
use ssafydb;


-- DROP DATABASE ssafydb;

INSERT INTO USER ( user_id, user_password, user_name, user_email, user_age, user_height, user_weight, user_area)VALUES ( 'a', 'a', 'a', 'a@a.com', 1, 1, 1, 'area');
-- INSERT INTO comment (comment_content, user_id, article_id) VALUES ("내용", "a", 1);
select * from user;
-- select * FROM article;
-- select * from comment;

-- select * from exercise_diary;

-- insert into daily_plan (exercise_date,user_id, body_part, exercise_name) VALUES ('2024-05-19', 'a', '하체', '스쿼트');
-- select * from daily_plan;

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

<<<<<<< Updated upstream
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
=======
-- select * from daily_plan_detail;


-- select * from daily_plan_detail;
>>>>>>> Stashed changes


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
show databases;
select * from user;

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

INSERT INTO article (user_id, article_title, article_content, category)
VALUES ("z", "z 운동했어요", "zzzz 운동했어요", "등");
select * from article;
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
    exercise_date DATETIME DEFAULT CURRENT_TIMESTAMP,
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

CREATE TABLE Follow (
    follower_id VARCHAR(100) NOT NULL, -- 팔로우 당하는 놈
    following_id VARCHAR(100) NOT NULL, -- 팔로우 신청한 놈
    FOREIGN KEY (follower_id) REFERENCES User(user_id),
    FOREIGN KEY (following_id) REFERENCES User(user_id),
    -- 같은 사용자에 대한 중복된 팔로우를 방지하기 위해 follower_id 및 following_id 열의 조합이 고유해야 한다.
    CONSTRAINT unique_follow UNIQUE (follower_id, following_id)
);

-- daily_plan 테이블에 대한 INSERT 문
INSERT INTO daily_plan (exercise_date, user_id, body_part, exercise_name) VALUES 
('2024-05-19', 'a', '어깨', '숄더 프레스'),
('2024-05-19', 'a', '팔', '바이셉 컬'),
('2024-05-19', 'a', '등', '랫 풀 다운'),
('2024-05-19', 'a', '가슴', '벤치 프레스'),
('2024-05-19', 'z', '복근', '크런치'),
('2024-05-19', 'z', '하체', '스쿼트'),
('2024-05-19', 'z', '어깨', '사이드 레터럴 레이즈'),
('2024-05-19', 'z', '팔', '트라이셉 익스텐션'),
('2024-05-21', 'z', '등', '데드리프트'),
('2024-05-21', 'z', '가슴', '딥스'),
('2024-05-19', 'n', '어깨', '사이드 레터럴 레이즈'),
('2024-05-19', 'n', '팔', '트라이셉 익스텐션'),
('2024-05-21', 'n', '등', '데드리프트'),
('2024-05-21', 'n', '가슴', '딥스');

select * from daily_plan;

-- daily_plan_detail 테이블에 대한 INSERT 문
INSERT INTO daily_plan_detail (exercise_kg, exercise_count, plan_id) VALUES 
(20, 10, 2),
(15, 12, 2),
(40, 8, 3),
(60, 6, 4),
(0, 20, 5),
(80, 5, 6),
(10, 15, 7),
(25, 10, 8),
(100, 5, 9),
(30, 8, 10);

SELECT
            d.user_id AS userId,
            '팔' AS bodyPart,
            SUM(dd.exercise_kg * dd.exercise_count) AS totalWeight
        FROM
            daily_plan_detail dd
            JOIN daily_plan d ON dd.plan_id = d.plan_id
        WHERE
            d.body_part = '팔'
            AND d.exercise_date >= CURDATE() - INTERVAL (DAYOFWEEK(CURDATE()) - 1) DAY
            AND d.exercise_date < CURDATE() + INTERVAL (7 - DAYOFWEEK(CURDATE())) DAY + INTERVAL 1 DAY
        GROUP BY
            d.user_id, d.body_part
        ORDER BY
            totalWeight DESC;
            
	-- 현재 날짜가 2024-05-21이라 가정
SELECT
    d.user_id AS userId,
    '팔' AS bodyPart,
    SUM(dd.exercise_kg * dd.exercise_count) AS totalWeight
FROM
    daily_plan_detail dd
    JOIN daily_plan d ON dd.plan_id = d.plan_id
WHERE
    d.body_part = '팔'
    AND d.exercise_date >= '2024-05-19'
    AND d.exercise_date < '2024-05-26'
GROUP BY
    d.user_id, d.body_part
ORDER BY
    totalWeight DESC;

            
            
            