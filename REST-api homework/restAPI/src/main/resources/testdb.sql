use testdb;

ALTER TABLE comment DROP FOREIGN KEY FKs1slvnkuemjsq2kj4h3vhx7i1;

DROP TABLE IF EXISTS `Post` CASCADE;

CREATE TABLE `Post`
(
    `post_id`  INT NOT NULL AUTO_INCREMENT COMMENT 'post_id',
    `title`    VARCHAR(255) NOT NULL COMMENT 'title',
    `content`  VARCHAR(255) COMMENT 'content',
    PRIMARY KEY ( `post_id` )
) COMMENT = '게시글';


INSERT INTO Post (post_id, title, content) VALUES
                                             (1, '제목1', '안녕하세요'),
                                             (2, '제목2', '반가워요'),
                                             (3, '제목3', '잘있어요'),
                                             (4, '제목4', '다시만나요'),
                                             (5, '제목5', '아따맘마');

DROP TABLE IF EXISTS `comment` CASCADE;

CREATE TABLE comment (
                         comment_id INT AUTO_INCREMENT PRIMARY KEY,
                         post_id INT NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (post_id) REFERENCES Post(post_id) ON DELETE CASCADE
);

INSERT INTO comment (post_id, content, create_date) VALUES
                                                        (1, '첫 번째 댓글입니다.', NOW()),
                                                        (1, '첫 번째 댓글입니다. 또 다른 내용.', NOW()),
                                                        (2, '두 번째 댓글입니다.', NOW()),
                                                        (2, '두 번째 댓글에 대한 추가 댓글.', NOW()),
                                                        (3, '세 번째 댓글입니다.', NOW()),
                                                        (3, '세 번째 댓글에 대한 의견.', NOW()),
                                                        (4, '네 번째 댓글입니다.', NOW()),
                                                        (4, '네 번째 댓글에 대한 생각.', NOW()),
                                                        (5, '다섯 번째 댓글입니다.', NOW()),
                                                        (5, '다섯 번째 댓글에 대한 추가 의견.', NOW()),
                                                        (1, '첫 번째 댓글의 또 다른 예시입니다.', NOW()),
                                                        (2, '두 번째 댓글에 대한 또 다른 내용.', NOW()),
                                                        (3, '세 번째 댓글의 또 다른 예시입니다.', NOW()),
                                                        (4, '네 번째 댓글의 새로운 의견입니다.', NOW()),
                                                        (5, '다섯 번째 댓글에 대한 추가 내용.', NOW());


