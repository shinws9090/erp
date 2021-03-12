
-- 계정 권한 부여
grant all
	on erp.*
	to 'user_erp'@'localhost'
	identified by 'rootroot';
	
-- file 권한 백업하고 복원하는 권한
GRANT File
	ON *.*
	to 'user_erp'@'localhost';