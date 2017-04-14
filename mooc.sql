CREATE DATABASE mooc;
USE mooc; 
#DROP DATABASE mooc;

-- 用户表
CREATE TABLE `user`(
	user_id CHAR(32) NOT NULL,-- 用户编号，
	email VARCHAR(30) NOT NULL,-- 注册邮箱
	username VARCHAR(30) DEFAULT NULL,-- 昵称
	`password` VARCHAR(30) DEFAULT NULL,-- 密码
	sex CHAR(2) DEFAULT NULL,-- 性别
	user_desc VARCHAR(100) DEFAULT NULL,-- 个人签名
	user_img VARCHAR(100) DEFAULT NULL,  -- 图像路径
	`status` TINYINT(1) DEFAULT NULL,-- 邮箱是否激活,0表示未激活，1表示已激活
	activationCode CHAR(64) DEFAULT NULL,-- 邮箱激活码
	PRIMARY KEY (user_id),-- 主键
	UNIQUE KEY email(email)-- 唯一约束
)ENGINE=INNODB DEFAULT CHARSET=utf8;
#drop table `user`;
INSERT INTO `user` (user_id,email,username,`password`,sex, user_desc,user_img,`status`) VALUES('11111111','1234@qq.com','小白','123123','男','有梦才可爱','user_img/陈坤_s.jpg',1);

-- 学校
CREATE TABLE school(
	sch_id CHAR(32) NOT NULL, -- 学校id
	sch_name VARCHAR(100) DEFAULT NULL, -- 学校名字
	/* 学校的【大小图不同，大图是比较个性的，代表学校特色的，小图是主要是标明学校名称】*/
	sch_bimg VARCHAR(100) DEFAULT NULL, -- 学校的大图片
	sch_simg VARCHAR(100) DEFAULT NULL, -- 学校的小图片
	sch_desc VARCHAR(1000) DEFAULT NULL, -- 学校的描述
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (sch_id),-- 主键
	KEY `orderBy` (`orderBy`),
	UNIQUE KEY sch_name(sch_name)-- 唯一约束
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 王勇 添加学校数据
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0001','北京大学','school_img/beijing_b.jpg','school_img/beijing_s.jpg','北京大学，北京大学创办于1898年，初名京师大学堂，是中国第一所国立综合性大学，也是当时中国最高教育行政机关。辛亥革命后，于1912年改为现名。 学校为教育部直属全国重点大学，国家“211工程”、“985工程”建设大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0002','北京航空航天大学','school_img/beijinghangk_b.jpg','school_img/beijinghangk_s.jpg','北京航空航天大学（Beihang University）是中华人民共和国工业和信息化部直属的一所综合性全国重点大学，是国家“985工程”、“211工程”重点建设高校，是首批16所全国重点大学之一，入选“珠峰计划”、“2011计划”、“111计划”、“卓越工程师教育培养计划”、“卓越法律人才教育培养计划”...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0003','清华大学','school_img/qinghua_b.jpg','school_img/qinghua_s.png','清华大学（Tsinghua University）是中国著名高等学府，坐落于北京西北郊风景秀丽的清华园，是中国高层次人才培养和科学技术研究的重要基地。 清华大学是国家“211工程”、“985工程”建设大学，是中国顶尖学府C9联盟，以及东亚研究型大学协会、环太平洋大学联盟、清华大学—剑桥大学—麻省理工学院低碳能源大学联盟...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0004','北京理工大学','school_img/beijinglig_b.jpg','school_img/beijinglig_s.jpg','北京理工大学，是一所历史悠久的大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0005','武汉大学','school_img/wuhan_b.jpg','school_img/wuhan_s.png','武汉大学是国家教育部直属重点综合性大学，是国家“985工程”和“211工程”重点建设高校。 武汉大学溯源于1893年清末湖广总督张之洞奏请清政府创办的自强学堂，历经传承演变，1928年定名为国立武汉大学，是近代中国第一批国立大学...');


INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0006','大连理工大学','school_img/beijinglig_b.jpg','school_img/dalianlig_s.png','北京理工大学，是一所历史悠久的大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0007','复旦大学','school_img/beijinglig_b.jpg','school_img/fudan_s.png','复旦大学，是一所历史悠久的大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0008','哈尔滨工业大学','school_img/beijinglig_b.png','school_img/hagongda_s.png','哈尔滨工业大学，是一所历史悠久的大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0009','浙江大学','school_img/beijinglig_b.png','school_img/zhejiang_s.png','浙江大学，是一所历史悠久的大学...');
INSERT INTO school (sch_id,sch_name,sch_bimg,sch_simg,sch_desc) VALUES
('s0010','中山大学','school_img/beijinglig_b.png','school_img/zhongshan_s.png','中山大学，是一所历史悠久的大学...');


-- 老师
CREATE TABLE teacher(
	tea_id CHAR(32) NOT NULL,-- 教师编号
	tea_name VARCHAR(30) DEFAULT NULL,-- 教师姓名
	tea_bimg VARCHAR(100) DEFAULT NULL, -- 教师大图像
	tea_simg VARCHAR(100) DEFAULT NULL, -- 教师小图像
	tea_desc VARCHAR(500) DEFAULT NULL, -- 教师简介
	sch_id CHAR(32) DEFAULT NULL, -- 所属学校（外键）
	
	tea_jiyu VARCHAR(500) DEFAULT NULL,-- 教师寄语
	tea_guandian VARCHAR(500) DEFAULT NULL,-- 嘉宾观点
	del BOOLEAN DEFAULT FALSE,-- 是否删除
	PRIMARY KEY (tea_id),-- 主键
	-- 没用 KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_teacher_school` FOREIGN KEY (`sch_id`) REFERENCES `school` (`sch_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO teacher (tea_id,tea_name,tea_bimg,tea_simg,tea_desc,sch_id,tea_jiyu,tea_guandian) VALUES('t0001','彭林','teacher_img/彭林_b.jpg','teacher_img/彭林_s.jpg','彭林是一名幽默、专业的老师...','s0001','MOOC将会成为未来将育的一部分','大型开放式网络课程，即MOOC（massive open online courses）');
INSERT INTO teacher (tea_id,tea_name,tea_bimg,tea_simg,tea_desc,sch_id,tea_jiyu,tea_guandian) VALUES('t0002','陈江','teacher_img/陈江_b.jpg','teacher_img/陈江_s.jpg','陈江是一名优秀、风趣的老师...','s0002','MOOC将会成为未来将育的一部分','大型开放式网络课程，即MOOC（massive open online courses）');
INSERT INTO teacher (tea_id,tea_name,tea_bimg,tea_simg,tea_desc,sch_id,tea_jiyu,tea_guandian) VALUES('t0003','汪琼','teacher_img/汪琼_b.jpg','teacher_img/汪琼_s.jpg','汪琼是一名善于引导学生的老师...','s0003','MOOC将会成为未来将育的一部分','大型开放式网络课程，即MOOC（massive open online courses）');
INSERT INTO teacher (tea_id,tea_name,tea_bimg,tea_simg,tea_desc,sch_id,tea_jiyu,tea_guandian) VALUES('t0004','翁恺','teacher_img/翁恺_b.jpg','teacher_img/翁恺_s.jpg','翁恺是一名教学风格深受学生喜欢的老师...','s0004','MOOC将会成为未来将育的一部分','大型开放式网络课程，即MOOC（massive open online courses）');

-- 课程表 【做了修改】：CONSTRAINT `FK_course_school` FOREIGN KEY (`sch_id`) REFERENCES `teacher` (`sch_id`)
CREATE TABLE course(
	cou_id CHAR(32) NOT NULL, -- 课程编号
	cou_name VARCHAR(50) DEFAULT NULL, -- 课程名
	cate_id CHAR(32) DEFAULT NULL,-- 课程的二级分类（外键）
	tea_id CHAR(32) DEFAULT NULL,-- 授课教师（外键）
	sch_id CHAR(32) DEFAULT NULL,-- 所属学校（外键）
	cou_desc VARCHAR(1000) DEFAULT NULL,-- 课程概述
	learn_num INT DEFAULT NULL, -- 学生人数
	hour_length INT DEFAULT NULL, -- 课程时长的小时部分，单位为小时
	minu_length INT DEFAULT NULL, -- 课程时长的分钟部分，单位为分钟，和“hour_length”组成‘5小时25分’的形式
	cou_bimg VARCHAR(100) DEFAULT NULL, -- 课程的大图图片路径
	cou_simg VARCHAR(100) DEFAULT NULL, -- 课程的小图图片路径
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (cou_id),-- 主键
	KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_course_teacher` FOREIGN KEY (`tea_id`) REFERENCES `teacher` (`tea_id`),-- 引用teacher表的主键
	CONSTRAINT `FK_course_school` FOREIGN KEY (`sch_id`) REFERENCES `teacher` (`sch_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
# select * from course;
--  王勇 添加课程数据 【注意】tea_id，和sch_id要根据school和teacher表中的数据进行修改，而且tea_id，和sch_id有对应关系

-- 计算机技术中的 前端开发
INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0001','html和css入门','2001','t0001','s0001','html和css基础课程',45,18,34,'course_img/html和css入门_b.jpg','course_img/HTML和css_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0002','jQuery源码解析（DOM与核心模块','2001','t0001','s0001','jQuery源码解析（DOM与核心模块）',45,18,34,'course_img/计算机技术_b.jpg','course_img/jQuery源码解析（DOM与核心模块）_s.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0003','JS插件开发','2001','t0001','s0001','JS插件开发',45,18,34,'course_img/计算机技术_b.jpg','course_img/JS插件开发.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0004','DOM探索之基础详解篇','2001','t0002','s0002','DOM探索之基础详解篇',45,18,34,'course_img/计算机技术_b.jpg','course_img/DOM探索之基础详解篇_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0005','Ajax全接触','2001','t0002','s0002','Ajax全接触',45,18,34,'course_img/计算机技术_b.jpg','course_img/Ajax全接触_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0006','JavaScript深入浅出','2001','t0002','s0002','JavaScript深入浅出',45,18,34,'course_img/计算机技术_b.jpg','course_img/JavaScript深入浅出_s.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0007','KISSY框架','2001','t0003','s0003','KISSY框架',45,18,34,'course_img/计算机技术_b.jpg','course_img/KISSY框架_s.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0008','走进SVG','2001','t0003','s0003','走进SVG',45,18,34,'course_img/计算机技术_b.jpg','course_img/走进SVG_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0009','HTML5存储','2001','t0004','s0004','HTML5存储',45,18,34,'course_img/计算机技术_b.jpg','course_img/HTML5存储_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0010','HTML5音乐可视化','2001','t0004','s0004','HTML5音乐可视化',45,18,34,'course_img/计算机技术_b.jpg','course_img/HTML5音乐可视化.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0011','jQuery基础课程','2001','t0004','s0004','jQuery基础课程',45,18,34,'course_img/计算机技术_b.jpg','course_img/jQuery基础课程_s.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0012','进击Node.js基础','2001','t0001','s0001','进击Node.js基础',45,18,34,'course_img/计算机技术_b.jpg','course_img/进击Node.js基础_s.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c0013','玩转Bootstrap（基础）','2001','t0001','s0001','玩转Bootstrap（基础）',45,18,34,'course_img/计算机技术_b.jpg','course_img/玩转Bootstrap（基础）_s.jpg');

-- 计算机技术中的 后端开发

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c1001','Linux权限管理之基本权限','2002','t0001','s0004','Linux权限管理之基本权限',45,18,34,'course_img/计算机技术_b.jpg','course_img/Linux权限管理之基本权限_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c1002','java全面解析','2002','t0001','s0001','java全面解析',45,18,34,'course_img/计算机技术_b.jpg','course_img/java.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c1003','Oracle 数据库开发','2001','t0001','s0001','Oracle 数据库开发',45,18,34,'course_img/计算机技术_b.jpg','course_img/Oracle 数据库开发.jpg');


INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c1005','java全面解析','2002','t0001','s0001','java全面解析',45,18,34,'course_img/计算机技术_b.jpg','course_img/java.jpg');



 -- 计算机技术中的 移动开发

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c2001','Android volley 详解','2001','t0001','s0002','Android volley 详解',45,18,34,'course_img/计算机技术_b.jpg','course_img/Android volley 详解.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c2002','Android 3D画廊','2001','t0001','s0002','javascript',45,18,34,'course_img/计算机技术_b.jpg','course_img/Android 3D画廊.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c2003','Cocos2d 游戏开发','2001','t0001','s0002','Cocos2d 游戏开发 ',45,18,34,'course_img/计算机技术_b.jpg','course_img/Cocos2d 游戏开发.jpg');
INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c2004','MongoDB 经典入门','2001','t0001','s0002','MongoDB 经典入门',45,18,34,'course_img/计算机技术_b.jpg','course_img/MongoDB 经典入门.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c2005','Android高级特效-索引','2001','t0001','s0002','Android高级特效-索引',45,18,34,'course_img/计算机技术_b.jpg','course_img/Android高级特效-索引.jpg');


 -- 计算机技术中的 数据处理
INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c3001','MySQL开发技巧','2001','t0001','s0001','Mysql课程',45,18,34,'course_img/计算机技术_b.jpg','course_img/MySQL开发技巧_s.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c3002','R语言入门','2001','t0001','s0002','R语言入门',45,18,34,'course_img/计算机技术_b.jpg','course_img/R语言入门.jpg');

INSERT INTO course (cou_id,cou_name,cate_id,tea_id,sch_id,cou_desc,learn_num,hour_length,minu_length,cou_bimg,cou_simg) 
VALUES('c3003','Oracle触发器','2001','t0001','s0001','Oracle触发器',45,18,34,'course_img/计算机技术_b.jpg','course_img/Oracle触发器_s.jpg');


-- 课程表的数据添加完毕

-- 课程分类
CREATE TABLE category(
	cate_id CHAR(32) NOT NULL,-- 分类编号
	cate_name VARCHAR(30) DEFAULT NULL,-- 分类名称
	pcate_id CHAR(32) DEFAULT NULL, -- 父级分类
	cate_desc VARCHAR(1000) DEFAULT NULL, -- 分类简介
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (cate_id),-- 主键
	KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_category_category` FOREIGN KEY (`pcate_id`) REFERENCES `category` (`cate_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

-- 添加一二级分类
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0001','文学艺术',NULL,'文学艺术相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0002','计算机科学技术',NULL,'计算机科学技术');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0003','哲学历史',NULL,'哲学历史相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0004','经管法学',NULL,'经管法学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0005','基础科学',NULL,'基础科学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0006','工程技术',NULL,'工程技术相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('0007','农林医药',NULL,'农林医药相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('1001','语言','0001','语言相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('1002','文化','0001','文化相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('1003','艺术','0001','艺术相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('1004','其他','0001','其他文学艺术相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2001','前端开发','0002','前端开发相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2002','后端开发','0002','后端开发相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2003','移动开发','0002','移动开发相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2004','数据处理','0002','数据处理课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2005','图像处理','0002','图像处理相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('2006','大数据','0002','大数据相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('3001','政治','0003','政治相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('3002','世界历史','0003','世界历史相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('3003','中国近代史','0003','中国近代史相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('3004','马克思主义哲学','0003','马克思主义哲学课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('3005','哲学史','0003','哲学史相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('4001','金融学','0004','政治相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('4002','微观经济学','0004','微观经济学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('4003','工商管理','0004','工商管理相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('4004','行政法学','0004','行政法学课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('4005','其他','0004','其他相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('5001','高等数学','0005','高等数学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('5002','C语言程序设计','0005','C语言程序设计课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('5003','数字信号处理','0005','数字信号处理相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('5004','模拟电路基础','0005','模拟电路基础课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('5005','大学物理','0005','大学物理相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('6001','数据结构','0006','数据结构相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('6002','计算思维导论','0006','计算思维导论课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('6003','航空航天概论','0006','航空航天概论相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('6004','网络技术与应用','0006','网络技术与应用课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('6005','工程材料','0006','工程材料相关课程');

INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('7001','急救常识','0007','急救常识相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('7002','解剖学','0007','解剖学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('7003','预防医学','0007','预防医学相关课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('7004','药理学','0007','药理学课程');
INSERT INTO category (cate_id,cate_name,pcate_id,cate_desc) VALUES('7005','护理学','0007','护理学相关课程');






-- 课程的章
CREATE TABLE chapter(
	chap_id CHAR(32) NOT NULL, -- 章节id
	chap_num INT DEFAULT NULL,-- 章节号，即第几章
	chap_name VARCHAR(50) DEFAULT NULL,-- 章节名称
	hour_length INT  DEFAULT NULL,-- 章节的课时时长的小时部分，单位，小时
	minu_length INT  DEFAULT NULL,-- 章节的课时时长的分钟部分，单位，分钟
	cou_id CHAR(32) DEFAULT NULL, -- 所属哪个课程 外键
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (chap_id),-- 主键
	KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_chapter_course` FOREIGN KEY (`cou_id`) REFERENCES `course` (`cou_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO chapter (chap_id,chap_num,chap_name,hour_length,minu_length,cou_id)
VALUES ('ch0001',1,'第一个网页',0,36,'c0001');
INSERT INTO chapter (chap_id,chap_num,chap_name,hour_length,minu_length,cou_id)
VALUES ('ch0002',2,'使用HTML创建实用网页',0,42,'c0001');
INSERT INTO chapter (chap_id,chap_num,chap_name,hour_length,minu_length,cou_id)
VALUES ('ch0003',3,'充满创意的网页设计',1,06,'c0001');
INSERT INTO chapter (chap_id,chap_num,chap_name,hour_length,minu_length,cou_id)
VALUES ('ch0004',4,'动态网页',2,12,'c0001');
INSERT INTO chapter (chap_id,chap_num,chap_name,hour_length,minu_length,cou_id)
VALUES ('ch0005',5,'建立网站',4,12,'c0001');


CREATE TABLE video(
	vid_id CHAR(32) NOT NULL,-- 视频id
	vid_name VARCHAR(80) DEFAULT NULL,-- 视频名称
	vid_num INT DEFAULT NULL, -- 一章下的视频编号，即一章下的第几个视频
	minu_length INT DEFAULT NULL,-- 视频的时长，单位分钟
	vid_path VARCHAR(100) DEFAULT NULL, -- 视频路径
	like_num INT DEFAULT NULL, -- 点赞数
	chap_id CHAR(32) DEFAULT NULL, -- 所属章节的id，外键
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (vid_id),
	KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_video_chapter` FOREIGN KEY (`chap_id`) REFERENCES `chapter` (`chap_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;
# delete from video;
# select * from video;
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000001','理解HTML和XHTML',1,6,'videos/1.flv',53,'ch0001');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000002','开始创建网页',2,23,'videos/2.flv',26,'ch0001');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000003','链接到其他网页',3,18,'videos/3.flv',23,'ch0001');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000004','发布HTML网页',4,26,'videos/4.flv',23,'ch0001');


INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000005','基本文本对齐和格式化',1,6,'videos/1.flv',53,'ch0002');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000006','创建文本链接',2,23,'videos/1.flv',26,'ch0002');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000007','创建网页图像',3,18,'videos/1.flv',23,'ch0002');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000008','将图像放到网页中',4,26,'videos/1.flv',23,'ch0002');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000009','自定义背景和颜色',5,6,'videos/1.flv',53,'ch0002');


INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000010','使用表格组织和排列网页',1,6,'videos/2.flv',53,'ch0003');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000011','使用CSS格式化网页',2,23,'videos/2.flv',26,'ch0003');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000012','深入探讨样式表格式化',3,18,'videos/2.flv',23,'ch0003');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000013','定位',4,26,'videos/2.flv',23,'ch0003');

INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000014','使用框架创建多页面布局',1,6,'videos/3.flv',53,'ch0004');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000015','面向非程序员的网页脚本编程',2,23,'videos/3.flv',26,'ch0004');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000016','在网页中嵌入多媒体',3,18,'videos/3.flv',23,'ch0004');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000017','创建自己的博客',4,26,'videos/3.flv',23,'ch0004');


INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000018','组织和管理网站',1,6,'videos/4.flv',53,'ch0005');
INSERT INTO video (vid_id,vid_name,vid_num,minu_length,vid_path,like_num,chap_id)
VALUES ('v000019','超越传统网站',2,23,'videos/4.flv',26,'ch0005');

--   ----------------------------------------------------
CREATE TABLE user_video(
	user_id CHAR(32) NOT NULL,
	vid_id CHAR(32) NOT NULL,
	chap_id CHAR(32) NOT NULL,
	`orderBy` INT(11) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (user_id,vid_id),
	KEY `orderBy` (`orderBy`),
	CONSTRAINT `FK_uv_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
	CONSTRAINT `FK_uv_video_vid_id` FOREIGN KEY (`vid_id`) REFERENCES `video` (`vid_id`),
	CONSTRAINT `FK_uv_video_chap_id` FOREIGN KEY (`chap_id`) REFERENCES `video` (`chap_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

#SELECT * FROM user_video;
INSERT INTO user_video(user_id,vid_id,chap_id) VALUES('11111111','v000001','ch0001');
INSERT INTO user_video(user_id,vid_id,chap_id) VALUES('11111111','v000002','ch0001');
INSERT INTO user_video(user_id,vid_id,chap_id) VALUES('11111111','v000003','ch0001');


-- 管理员（齐敬佩添加、修改的三个表之第二个）
CREATE TABLE `admin` (
	`adm_id` INT  AUTO_INCREMENT,-- 排用来序，自增长
	`adm_name` VARCHAR(30) DEFAULT NULL,
	`adm_pwd` VARCHAR(30) DEFAULT NULL,
	`issuper` BOOLEAN	DEFAULT 0,-- 是否超级管理员，只有超级管理员才能添加管理员
	PRIMARY KEY (`adm_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT INTO admin VALUES(1,'zhangsan','123',1);
INSERT INTO admin VALUES(NULL,'lisi','123',0);

-- 用户和课程之间的联系（齐敬佩添加、修改的三个表之第三个）
CREATE TABLE user_course(
user_id CHAR(32) NOT NULL,-- 用户编号，，-- 用户编号，
cou_id CHAR(32) NOT NULL,-- 用户编号，, -- 课程编号
follow BOOLEAN DEFAULT FALSE, -- 是否关注：false表示没有关注，true表示关注
`TYPE` BOOLEAN DEFAULT FALSE,-- 状态：FALSE表示正在学，TRUE表示已学完
PRIMARY KEY (user_id,cou_id),
CONSTRAINT `FK_user_course1` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`),-- 外键
CONSTRAINT `FK_user_course2` FOREIGN KEY (`cou_id`) REFERENCES `course`(`cou_id`)-- 外键
);
# select * from user_course;
# delete from user_course;
INSERT INTO user_course VALUES('11111111','c0001',TRUE,FALSE);-- 关注，没有学习
INSERT INTO user_course VALUES('11111111','c0003',TRUE,FALSE);-- 关注，没有学习
INSERT INTO user_course VALUES('11111111','c0007',TRUE,FALSE);-- 关注，没有学习

INSERT INTO user_course VALUES('11111111','c0002',FALSE,FALSE);-- 正在学习

INSERT INTO user_course VALUES('11111111','c0005',FALSE,TRUE);-- 已学完
INSERT INTO user_course VALUES('11111111','c0006',FALSE,TRUE);-- 已学完
-- 王顺 视频模块-->

-- -评论表---

CREATE TABLE `comment` (
  `com_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '发表评论的用户姓名',
  `user_simg` TEXT COMMENT '用户图片',
  `vid_id` CHAR(32) DEFAULT NULL COMMENT '视频编号',
  `com_content` TEXT COMMENT '评论内容',
  `com_time` DATETIME DEFAULT NULL COMMENT '评论时间',
  `agree_num` INT(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`com_id`),
  KEY `FK_vid_id` (`vid_id`),
  CONSTRAINT `FK_vid_id` FOREIGN KEY (`vid_id`) REFERENCES `video` (`vid_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
# delete from comment;
INSERT INTO `comment` VALUES ('25', '小白', 'user_img/陈坤_s.jpg', 'v000001', '哇，这个课程好棒啊！', '2015-09-14 20:18:27', '0');
INSERT INTO `comment` VALUES ('28', '小白', 'user_img/陈坤_s.jpg', 'v000001', 'GreatMooc的课程都好棒啊！', '2015-09-14 20:38:25', '1');


-- -回复表---
CREATE TABLE `reply` (
  `repl_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `com_id` INT(11) DEFAULT NULL COMMENT '评论id',
  `repl_content` TEXT COMMENT '回复内容',
  `repl_time` DATETIME DEFAULT NULL COMMENT '回复时间',
  `agree_num` INT(11) DEFAULT NULL COMMENT '点赞数',
  `user_name` VARCHAR(50) DEFAULT NULL COMMENT '回复用户',
  `user_simg` TEXT COMMENT '用户图片',
  PRIMARY KEY (`repl_id`),
  KEY `FK_com_id` (`com_id`),
  CONSTRAINT `FK_com_id` FOREIGN KEY (`com_id`) REFERENCES `comment` (`com_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------

INSERT INTO `reply` VALUES ('18', '28', '哇，这个课程好棒啊！', '2015-09-14 20:42:12', '0', '小白', 'user_img/陈坤_s.jpg');
INSERT INTO `reply` VALUES ('19', '28', 'GreatMooc的课程都好棒啊！', '2015-09-14 20:42:43', '0', '小白', 'user_img/陈坤_s.jpg');

-- 控制用户点赞数量
CREATE TABLE `agree` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `use_id` CHAR(32) DEFAULT NULL COMMENT '用户id',
  `com_id` INT(11) DEFAULT NULL COMMENT '评论id',
  PRIMARY KEY (`id`),
  KEY `FK_agree_user` (`use_id`),
  KEY `FK_agree_com` (`com_id`),
  CONSTRAINT `FK_agree_com` FOREIGN KEY (`com_id`) REFERENCES `comment` (`com_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_agree_user` FOREIGN KEY (`use_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 王顺 视频模块-->