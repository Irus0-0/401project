DROP TABLE customer;				
				
CREATE TABLE customer (				
	customer_id	VARCHAR2(50)		NOT NULL,
	customer_pw	VARCHAR2(50)		NULL,
	name	VARCHAR2(50)		NULL,
	phone_num	VARCHAR2(50)		NULL,
	gender	VARCHAR2(50)		NULL,
	birthdate	DATE		NULL,
	addr	VARCHAR2(50)		NULL,
	grade	VARCHAR2(50)	DEFAULT 1	NULL,
	sanctions_count	NUMBER	DEFAULT 0	NULL
);				
				
COMMENT ON COLUMN customer.customer_id IS '유저 ID';				
				
COMMENT ON COLUMN customer.customer_pw IS '유저 비밀번호';				
				
COMMENT ON COLUMN customer.name IS '유저의 이름';				
				
COMMENT ON COLUMN customer.phone_num IS '유저 핸드폰 번호';				
				
COMMENT ON COLUMN customer.gender IS '회원의 성별';				
				
COMMENT ON COLUMN customer.birthdate IS '유저 생일';				
				
COMMENT ON COLUMN customer.addr IS '유저의 집주소';				
				
COMMENT ON COLUMN customer.grade IS '유저의 등급 높은 숫자일 수록 높은등급';				
				
COMMENT ON COLUMN customer.sanctions_count IS '유저가 제재당한 횟수를 의미';				
				
DROP TABLE reservation;				
				
CREATE TABLE reservation (				
	reservation_num	VARCHAR2(50)		NOT NULL,
	customer_id	VARCHAR2(50)		NOT NULL,
	room_number	NUMBER		NOT NULL,
	accom_name	VARCHAR2(50)		NOT NULL,
	company_id	VARCHAR2(50)		NOT NULL,
	start_date	DATE		NULL,
	end_date	DATE		NULL,
	people_num	NUMBER		NULL
);				
				
COMMENT ON COLUMN reservation.customer_id IS '유저 ID';				
				
COMMENT ON COLUMN reservation.room_number IS '객실(방) 번호';				
				
COMMENT ON COLUMN reservation.accom_name IS '숙박시설명 				
양식 : 오류점 A호텔';				
				
COMMENT ON COLUMN reservation.company_id IS '기업ID';				
				
COMMENT ON COLUMN reservation.start_date IS '예약 당일 날짜';				
				
COMMENT ON COLUMN reservation.end_date IS '예약 종료 날짜';				
				
COMMENT ON COLUMN reservation.people_num IS '예약 인원 수';				
				
DROP TABLE accommodations;				
				
CREATE TABLE accommodations (				
	accom_name	VARCHAR2(50)		NOT NULL,
	company_id	VARCHAR2(50)		NOT NULL,
	accom_addr	VARCHAR2(50)		NULL,
	phone_num	VARCHAR2(50)		NULL,
	type	VARCHAR2(50)		NULL,
	set_up_date	DATE		NULL,
	star_point	NUMBER	DEFAULT 0	NULL,
	use_count	NUMBER	DEFAULT 0	NULL,
	description	CLOB		NULL
);				
				
COMMENT ON COLUMN accommodations.accom_name IS '숙박시설명 				
양식 : 오류점 A호텔';				
				
COMMENT ON COLUMN accommodations.company_id IS '기업ID';				
				
COMMENT ON COLUMN accommodations.accom_addr IS '숙박시설의주소지';				
				
COMMENT ON COLUMN accommodations.phone_num IS '숙박시설전화번호';				
				
COMMENT ON COLUMN accommodations.type IS '숙박시설의 종류, 호텔, 모텔 등';				
				
COMMENT ON COLUMN accommodations.set_up_date IS '숙박시설의 설립일';				
				
COMMENT ON COLUMN accommodations.star_point IS '사용자 리뷰들의 별점 평균';				
				
COMMENT ON COLUMN accommodations.use_count IS '총 사용자 이용횟수 의미';				
				
COMMENT ON COLUMN accommodations.description IS '시설설명';				
				
DROP TABLE checkin_out;				
				
CREATE TABLE checkin_out (				
	reservation_num	VARCHAR2(50)		NOT NULL,
	checkin	DATE		NULL,
	checkout	DATE		NULL
);				
				
COMMENT ON COLUMN checkin_out.checkin IS '체크인한 시간';				
				
COMMENT ON COLUMN checkin_out.checkout IS '체크아웃 시간';				
				
DROP TABLE review;				
				
CREATE TABLE review (				
	reservation_num	VARCHAR2(50)		NOT NULL,
	content	VARCHAR2(50)		NULL,
	star_point	NUMBER		NULL
);				
				
COMMENT ON COLUMN review.content IS '리뷰 내용';				
				
COMMENT ON COLUMN review.star_point IS '별점';				
				
DROP TABLE room;				
				
CREATE TABLE room (				
	room_number	NUMBER		NOT NULL,
	accom_name	VARCHAR2(50)		NOT NULL,
	company_id	VARCHAR2(50)		NOT NULL,
	grade	NUMBER		NULL,
	price	NUMBER		NULL,
	appropriate_num	NUMBER		NULL,
	description	CLOB		NULL
);				
				
COMMENT ON COLUMN room.room_number IS '객실(방) 번호';				
				
COMMENT ON COLUMN room.accom_name IS '숙박시설명 				
양식 : 오류점 A호텔';				
				
COMMENT ON COLUMN room.company_id IS '기업ID';				
				
COMMENT ON COLUMN room.grade IS '방 등급 3단계로 구성				
1등급이 가장 높은 등급';				
				
COMMENT ON COLUMN room.price IS '방 숙박료';				
				
COMMENT ON COLUMN room.appropriate_num IS '방 적정 인원 수';				
				
COMMENT ON COLUMN room.description IS '방의 설명';				
				
DROP TABLE company;				
				
CREATE TABLE company (				
	company_id	VARCHAR2(50)		NOT NULL,
	company_pw	VARCHAR2(50)		NULL,
	name	VARCHAR2(50)		NULL,
	biz_no	VARCHAR2(50)		NULL,
	owner_name	VARCHAR2(50)		NULL,
	phone_num	VARCHAR2(50)		NULL,
	addr	VARCHAR2(50)		NULL,
	scale	VARCHAR2(50)		NULL,
	sanctions_count	NUMBER	DEFAULT 0	NULL
);				
				
COMMENT ON COLUMN company.company_id IS '기업ID';				
				
COMMENT ON COLUMN company.company_pw IS '기업PW';				
				
COMMENT ON COLUMN company.name IS '기업의 상호명, 기업 이름을 의미';				
				
COMMENT ON COLUMN company.biz_no IS '사업번호';				
				
COMMENT ON COLUMN company.owner_name IS '해당 기업의 대표자 이름';				
				
COMMENT ON COLUMN company.phone_num IS '기업의 대표 전화, 핸드폰번호';				
				
COMMENT ON COLUMN company.addr IS '기업 소재지 주소';				
				
COMMENT ON COLUMN company.scale IS '기업의 규모';				
				
COMMENT ON COLUMN company.sanctions_count IS '제재횟수를 의미 기본값 0';				
				
DROP TABLE master;				
				
CREATE TABLE master (				
	master_id	VARCHAR2(50)		NOT NULL,
	master_pw	VARCHAR2(50)		NULL,
	name	VARCHAR2(50)		NULL,
	grade	VARCHAR2(50)		NULL
);				
				
COMMENT ON COLUMN master.master_id IS '관리자 ID';				
				
COMMENT ON COLUMN master.master_pw IS '관리자의 PW';				
				
COMMENT ON COLUMN master.name IS '관리자의 이름을 의미';				
				
COMMENT ON COLUMN master.grade IS '관리자 등급을 의미';				
				
DROP TABLE notice;				
				
CREATE TABLE notice (				
	notice_num	NUMBER		NOT NULL,
	master_id	VARCHAR2(50)		NULL,
	title	VARCHAR2(50)		NULL,
	content	CLOB		NULL,
	create_date	DATE		NULL,
	notice_grade	VARCHAR2(50)		NULL
);				
				
COMMENT ON COLUMN notice.notice_num IS '공지를 식별하기 위한 글 번호				
시퀀스로 1씩 증가';				
				
COMMENT ON COLUMN notice.title IS '공지의 제목';				
				
COMMENT ON COLUMN notice.content IS '공지사항의  내용';				
				
COMMENT ON COLUMN notice.create_date IS '공지를 작성한 날짜				
YYYY-MM-DD';				
				
COMMENT ON COLUMN notice.notice_grade IS '공지의 등급				
3단계로 구성되며				
1단계 평범한 공지				
2단계 주요 공지				
3단계 긴급 공지';				
				
DROP TABLE event;				
				
CREATE TABLE event (				
	event_num	NUMBER		NOT NULL,
	company_id	VARCHAR2(50)		NOT NULL,
	title	VARCHAR2(50)		NULL,
	content	CLOB		NULL,
	create_date	DATE		NULL,
	start_date	DATE		NULL,
	end_date	DATE		NULL
);				
				
COMMENT ON COLUMN event.event_num IS '작성일+그날의 순번';				
				
COMMENT ON COLUMN event.company_id IS '기업ID';				
				
COMMENT ON COLUMN event.title IS '이벤트의 제목';				
				
COMMENT ON COLUMN event.content IS '이벤트 내용';				
				
COMMENT ON COLUMN event.create_date IS '이벤트 작성일';				
				
COMMENT ON COLUMN event.start_date IS '이벤트 시작 첫날';				
				
COMMENT ON COLUMN event.end_date IS '이벤트가 종료되는 날짜';				
				
DROP TABLE evant_proposal;				
				
CREATE TABLE evant_proposal (				
	event_num	NUMBER		NOT NULL,
	master_id	VARCHAR2(50)		NOT NULL,
	proposal_isok	CHAR(1)	DEFAULT 'F'	NULL
);				
				
COMMENT ON COLUMN evant_proposal.event_num IS '작성일+그날의 순번';				
				
COMMENT ON COLUMN evant_proposal.master_id IS '관리자 ID';				
				
COMMENT ON COLUMN evant_proposal.proposal_isok IS 'F => 허가 안됨				
T => 허가 됨';				
				
DROP TABLE report_co;				
				
CREATE TABLE report_co (				
	report_num	NUMBER		NOT NULL,
	reservation_num	VARCHAR2(50)		NOT NULL,
	reason	CLOB		NULL
);				
				
COMMENT ON COLUMN report_co.report_num IS '날짜 + 해당 날의 순번				
또는 시퀀스';				
				
COMMENT ON COLUMN report_co.reason IS '해당 기업을 신고한 사유 작성';				
				
DROP TABLE report_user;				
				
CREATE TABLE report_user (				
	report_num	NUMBER		NOT NULL,
	reservation_num	VARCHAR2(50)		NOT NULL,
	reason	CLOB		NULL
);				
				
COMMENT ON COLUMN report_user.report_num IS '날짜 + 해당 날의 순번				
또는 시퀀스(기업신고 시퀀스랑 같이 사용)';				
				
COMMENT ON COLUMN report_user.reason IS '신고 사유';				
				
DROP TABLE report_co_process;				
				
CREATE TABLE report_co_process (				
	report_num	NUMBER		NOT NULL,
	master_id	VARCHAR2(50)		NOT NULL,
	result	VARCHAR2(50)		NULL,
	start_date	DATE		NULL,
	end_date	DATE		NULL
);				
				
COMMENT ON COLUMN report_co_process.report_num IS '날짜 + 해당 날의 순번				
또는 시퀀스';				
				
COMMENT ON COLUMN report_co_process.master_id IS '관리자 ID';				
				
DROP TABLE report_user_process;				
				
CREATE TABLE report_user_process (				
	report_num	NUMBER		NOT NULL,
	master_id	VARCHAR2(50)		NOT NULL,
	result	VARCHAR2(50)		NULL,
	start_date	DATE		NULL,
	end_date	DATE		NULL
);				
				
COMMENT ON COLUMN report_user_process.report_num IS '날짜 + 해당 날의 순번				
또는 시퀀스(기업신고 시퀀스랑 같이 사용)';				
				
COMMENT ON COLUMN report_user_process.master_id IS '관리자 ID';				
				
ALTER TABLE customer ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY (				
	customer_id			
);				
				
ALTER TABLE reservation ADD CONSTRAINT PK_RESERVATION PRIMARY KEY (				
	reservation_num			
);				
				
ALTER TABLE accommodations ADD CONSTRAINT PK_ACCOMMODATIONS PRIMARY KEY (				
	accom_name,			
	company_id			
);				
				
ALTER TABLE checkin_out ADD CONSTRAINT PK_CHECKIN_OUT PRIMARY KEY (				
	reservation_num			
);				
				
ALTER TABLE review ADD CONSTRAINT PK_REVIEW PRIMARY KEY (				
	reservation_num			
);				
				
ALTER TABLE room ADD CONSTRAINT PK_ROOM PRIMARY KEY (				
	room_number,			
	accom_name,			
	company_id			
);				
				
ALTER TABLE company ADD CONSTRAINT PK_COMPANY PRIMARY KEY (				
	company_id			
);				
				
ALTER TABLE master ADD CONSTRAINT PK_MASTER PRIMARY KEY (				
	master_id			
);				
				
ALTER TABLE notice ADD CONSTRAINT PK_NOTICE PRIMARY KEY (				
	notice_num			
);				
				
ALTER TABLE event ADD CONSTRAINT PK_EVENT PRIMARY KEY (				
	event_num			
);				
				
ALTER TABLE evant_proposal ADD CONSTRAINT PK_EVANT_PROPOSAL PRIMARY KEY (				
	event_num			
);				
				
ALTER TABLE report_co ADD CONSTRAINT PK_REPORT_CO PRIMARY KEY (				
	report_num			
);				
				
ALTER TABLE report_user ADD CONSTRAINT PK_REPORT_USER PRIMARY KEY (				
	report_num			
);				
				
ALTER TABLE report_co_process ADD CONSTRAINT PK_REPORT_CO_PROCESS PRIMARY KEY (				
	report_num			
);				
				
ALTER TABLE report_user_process ADD CONSTRAINT PK_REPORT_USER_PROCESS PRIMARY KEY (				
	report_num			
);				
				
ALTER TABLE accommodations ADD CONSTRAINT FK_company_TO_accom_1 FOREIGN KEY (				
	company_id			
)				
REFERENCES company (				
	company_id			
);				
				
ALTER TABLE checkin_out ADD CONSTRAINT FK_res_TO_checkin_out_1 FOREIGN KEY (				
	reservation_num			
)				
REFERENCES reservation (				
	reservation_num			
);				
				
ALTER TABLE review ADD CONSTRAINT FK_reservation_TO_review_1 FOREIGN KEY (				
	reservation_num			
)				
REFERENCES reservation (				
	reservation_num			
);				
				
ALTER TABLE room ADD CONSTRAINT FK_accommodations_TO_room_1 FOREIGN KEY (				
	accom_name, company_id		
)				
REFERENCES accommodations (				
	accom_name, company_id		
);				
				
ALTER TABLE evant_proposal ADD CONSTRAINT FK_event_TO_evant_proposal_1 FOREIGN KEY (				
	event_num			
)				
REFERENCES event (				
	event_num			
);				
				
ALTER TABLE report_co_process ADD CONSTRAINT FK_repco_TO_repco_process_1 FOREIGN KEY (				
	report_num			
)				
REFERENCES report_co (				
	report_num			
);				
				
ALTER TABLE report_user_process ADD CONSTRAINT FK_repusr_TO_repusr_process_1 FOREIGN KEY (				
	report_num			
)				
REFERENCES report_user (				
	report_num			
);				
				
