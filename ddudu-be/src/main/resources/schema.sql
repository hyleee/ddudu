drop database ssafydb;
create database ssafydb;
use ssafydb;

-- select * from exercise_diary;
-- select * from user;
-- select * from daily_plan;
-- select * from article; 

INSERT INTO User (user_id, user_password, user_name, user_email, user_age, user_height, user_weight, user_area, user_profile) VALUES
('박진우', 'pass1', '역삼동 암벽킹', '박진우@example.com', 25, 175, 70, '싸피1동', '박진우.png'),
('변지환', 'pass2', '번개장터 전무', '변지환@example.com', 30, 180, 75, '싸피2동', '변지환.png'),
('석준영', 'pass3', '아추워', '석준영@example.com', 28, 165, 68, '싸피3동', '석준영.png'),
('송예진', 'pass4', '빵순이', '송예진@example.com', 22, 170, 65, '싸피1동', '송예진.png'),
('안상용', 'pass5', '코딩중독', '안상용@example.com', 27, 160, 60, '싸피2동', '안상용.png'),
('오성환', 'pass6', '여자좋아', '오성환@example.com', 35, 185, 80, '싸피3동', '오성환.png'),
('옥진석', 'pass7', '모니터1개는모자라', '옥진석@example.com', 24, 172, 72, '싸피1동', '옥진석.png'),
('이혜윤', 'pass8', '인천앞바다', '이혜윤@example.com', 29, 168, 66, '싸피2동', '이혜윤.png'),
('이재성', 'pass9', '오리중독', '이재성@example.com', 33, 178, 74, '싸피3동', '이재성.png'),
('황정현', 'pass10', '로콜리', '황정현@example.com', 26, 182, 78, '싸피1동', '황정현.png');

INSERT INTO exercise_diary (user_id, diary_content, today_weight, diary_photo, exercise_date) VALUES 
('박진우', '오늘은 어깨 운동을 했어요. 정말 힘들었어요!', 70, 'shoulders.png', '2024-05-19 10:00:00'),
('박진우', '가슴 운동을 했습니다. 벤치프레스가 어려웠어요.', 71, 'chest.png', '2024-05-20 10:00:00'),
('박진우', '팔 운동을 했어요. 바이셉 컬을 열심히 했습니다.', 70, 'arms.png', '2024-05-21 10:00:00'),
('박진우', '다리 운동을 했습니다. 스쿼트로 땀을 많이 흘렸어요.', 71, 'legs.png', '2024-05-22 10:00:00'),
('박진우', '등 운동을 했습니다. 데드리프트가 정말 힘들었어요.', 70, 'back.png', '2024-05-23 10:00:00');


-- daily_plan 데이터 추가
INSERT INTO daily_plan (exercise_date, user_id, body_part, exercise_name) VALUES 
('2024-05-19', '박진우', 'back', '랫 풀 다운'),
('2024-05-20', '박진우', 'chest', '벤치 프레스'),
('2024-05-21', '박진우', 'arms', '바이셉 컬'),
('2024-05-22', '박진우', 'shoulders', '숄더 프레스'),
('2024-05-23', '박진우', 'legs', '스쿼트'),
('2024-05-19', '변지환', 'chest', '딥스'),
('2024-05-20', '변지환', 'arms', '트라이셉 익스텐션'),
('2024-05-21', '변지환', 'shoulders', '사이드 레터럴 레이즈'),
('2024-05-22', '변지환', 'legs', '레그 프레스'),
('2024-05-23', '변지환', 'abs', '크런치'),
('2024-05-19', '석준영', 'arms', '해머 컬'),
('2024-05-20', '석준영', 'shoulders', '밀리터리 프레스'),
('2024-05-21', '석준영', 'legs', '레그 레이즈'),
('2024-05-22', '석준영', 'abs', '레그 레이즈'),
('2024-05-23', '석준영', 'back', '바벨 로우'),
('2024-05-19', '송예진', 'shoulders', '숄더 프레스'),
('2024-05-20', '송예진', 'legs', '스쿼트'),
('2024-05-21', '송예진', 'abs', '크런치'),
('2024-05-22', '송예진', 'back', '랫 풀 다운'),
('2024-05-23', '송예진', 'chest', '벤치 프레스'),
('2024-05-19', '안상용', 'legs', '레그 프레스'),
('2024-05-20', '안상용', 'abs', '레그 레이즈'),
('2024-05-21', '안상용', 'back', '데드리프트'),
('2024-05-22', '안상용', 'chest', '딥스'),
('2024-05-23', '안상용', 'arms', '바이셉 컬'),
('2024-05-19', '오성환', 'abs', '크런치'),
('2024-05-20', '오성환', 'back', '바벨 로우'),
('2024-05-21', '오성환', 'chest', '케이블 크로스오버'),
('2024-05-22', '오성환', 'arms', '해머 컬'),
('2024-05-23', '오성환', 'shoulders', '밀리터리 프레스'),
('2024-05-19', '옥진석', 'back', '데드리프트'),
('2024-05-20', '옥진석', 'chest', '벤치 프레스'),
('2024-05-21', '옥진석', 'arms', '바이셉 컬'),
('2024-05-22', '옥진석', 'shoulders', '숄더 프레스'),
('2024-05-23', '옥진석', 'legs', '스쿼트'),
('2024-05-19', '이혜윤', 'chest', '딥스'),
('2024-05-20', '이혜윤', 'arms', '트라이셉 익스텐션'),
('2024-05-21', '이혜윤', 'shoulders', '사이드 레터럴 레이즈'),
('2024-05-22', '이혜윤', 'legs', '레그 프레스'),
('2024-05-23', '이혜윤', 'abs', '크런치'),
('2024-05-19', '이재성', 'arms', '해머 컬'),
('2024-05-20', '이재성', 'shoulders', '밀리터리 프레스'),
('2024-05-21', '이재성', 'legs', '레그 레이즈'),
('2024-05-22', '이재성', 'abs', '레그 레이즈'),
('2024-05-23', '이재성', 'back', '바벨 로우'),
('2024-05-19', '황정현', 'shoulders', '숄더 프레스'),
('2024-05-20', '황정현', 'legs', '스쿼트'),
('2024-05-21', '황정현', 'abs', '크런치'),
('2024-05-22', '황정현', 'back', '랫 풀 다운'),
('2024-05-23', '황정현', 'chest', '벤치 프레스');

-- daily_plan_detail 데이터 추가
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
(30, 8, 10),
(20, 10, 11),
(15, 12, 12),
(40, 8, 13),
(60, 6, 14),
(50, 10, 15),
(0, 20, 16),
(35, 8, 17),
(20, 15, 18),
(45, 12, 19),
(30, 10, 20),
(20, 10, 21),
(15, 12, 22),
(40, 8, 23),
(60, 6, 24),
(0, 20, 25),
(80, 5, 26),
(10, 15, 27),
(25, 10, 28),
(100, 5, 29),
(30, 8, 30),
(20, 10, 31),
(15, 12, 32),
(40, 8, 33),
(60, 6, 34),
(50, 10, 35),
(0, 20, 36),
(35, 8, 37),
(20, 15, 38),
(45, 12, 39),
(30, 10, 40),
(20, 10, 41),
(15, 12, 42),
(40, 8, 43),
(60, 6, 44),
(0, 20, 45),
(80, 5, 46),
(10, 15, 47),
(25, 10, 48),
(100, 5, 49),
(30, 8, 50);

drop table article;
drop table article_like;
drop table comment;
drop table comment_like;
drop table reply;
drop table reply_like;

INSERT INTO article (article_id, user_id, article_title, article_content, category) VALUES
(1, '박진우', '코딩이 제일 쉬웠어요', 'Content 1-1', 'shoulders'),
(2, '박진우', '클라이밍 갈 사람', 'Content 1-2', 'legs'),
(3, '변지환', '헬스장 ootd 평가부탁', 'Content 2-1', 'arms'),
(4, '변지환', 'Article 2-2', 'Content 2-2', 'back'),
(5, '석준영', '뭔 근동이냐!!!!', 'Content 3-1', 'chest'),
(6, '석준영', '블루아카이브 짱짱', 'Content 3-2', 'abs'),
(7, '송예진', 'Article 4-1', 'Content 4-1', 'shoulders'),
(8, '송예진', '빵보다 득근이 맛있나요?', 'Content 4-2', 'legs'),
(9, '안상용', '코딩보다 운동이 재밌나요?', 'Content 5-1', 'arms'),
(10, '안상용', '코딩 중독 치료 어떻게 하나요', 'Content 5-2', 'back'),
(11, '오성환', '헬스장 물 좋은가요?', 'Content 6-1', 'chest'),
(12, '오성환', 'Article 6-2', 'Content 6-2', 'abs'),
(13, '옥진석', '모니터 사요', 'Content 7-1', 'shoulders'),
(14, '옥진석', 'Article 7-2', 'Content 7-2', 'legs'),
(15, '이혜윤', 'Article 8-1', 'Content 8-1', 'arms'),
(16, '이혜윤', '닭가슴살 정보좀요', 'Content 8-2', 'back'),
(17, '이재성', '부스터 추천이요', 'Content 9-1', 'chest'),
(18, '이재성', '헬스보단 자전거', 'Content 9-2', 'abs'),
(19, '황정현', '브로콜리 먹으면 운동 잘됨', 'Content 10-1', 'shoulders'),
(20, '황정현', 'Article 10-2', 'Content 10-2', 'legs');

INSERT INTO comment (comment_content, user_id, article_id) VALUES
('Comment 1-1 by 박진우', '박진우', 1),
('Comment 1-2 by 변지환', '변지환', 1),
('Comment 2-1 by 박진우', '박진우', 2),
('Comment 2-2 by 석준영', '석준영', 2),
('Comment 3-1 by 변지환', '변지환', 3),
('Comment 3-2 by 송예진', '송예진', 3),
('Comment 4-1 by 변지환', '변지환', 4),
('Comment 4-2 by 안상용', '안상용', 4),
('Comment 5-1 by 석준영', '석준영', 5),
('Comment 5-2 by 오성환', '오성환', 5),
('Comment 6-1 by 석준영', '석준영', 6),
('Comment 6-2 by 옥진석', '옥진석', 6),
('Comment 7-1 by 송예진', '송예진', 7),
('Comment 7-2 by 이혜윤', '이혜윤', 7),
('Comment 8-1 by 송예진', '송예진', 8),
('Comment 8-2 by 이재성', '이재성', 8),
('Comment 9-1 by 안상용', '안상용', 9),
('Comment 9-2 by 황정현', '황정현', 9),
('Comment 10-1 by 안상용', '안상용', 10),
('Comment 10-2 by 박진우', '박진우', 10),
('Comment 11-1 by 오성환', '오성환', 11),
('Comment 11-2 by 변지환', '변지환', 11),
('Comment 12-1 by 오성환', '오성환', 12),
('Comment 12-2 by 석준영', '석준영', 12),
('Comment 13-1 by 옥진석', '옥진석', 13),
('Comment 13-2 by 송예진', '송예진', 13),
('Comment 14-1 by 옥진석', '옥진석', 14),
('Comment 14-2 by 안상용', '안상용', 14),
('Comment 15-1 by 이혜윤', '이혜윤', 15),
('Comment 15-2 by 오성환', '오성환', 15),
('Comment 16-1 by 이혜윤', '이혜윤', 16),
('Comment 16-2 by 옥진석', '옥진석', 16),
('Comment 17-1 by 이재성', '이재성', 17),
('Comment 17-2 by 이혜윤', '이혜윤', 17),
('Comment 18-1 by 이재성', '이재성', 18),
('Comment 18-2 by 박진우', '박진우', 18),
('Comment 19-1 by 황정현', '황정현', 19),
('Comment 19-2 by 변지환', '변지환', 19),
('Comment 20-1 by 황정현', '황정현', 20),
('Comment 20-2 by 석준영', '석준영', 20);



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
    diary_photo VARCHAR(100),
    exercise_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, exercise_date),
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);



-- 게시글 테이블
CREATE TABLE article (
    article_id INT AUTO_INCREMENT,
    user_id VARCHAR(100),
    article_title VARCHAR(100) NOT NULL,
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
