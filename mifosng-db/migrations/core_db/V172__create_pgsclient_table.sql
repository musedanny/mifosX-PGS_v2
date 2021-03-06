DROP TABLE IF EXISTS `m_pgsclient`;
CREATE TABLE `m_pgsclient` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `pgs_client_account_no` varchar(20) NOT NULL,
 `external_id` varchar(100) DEFAULT NULL,
 `status_enum` int(5) NOT NULL DEFAULT '300',
 `activation_date` date DEFAULT NULL,
 `office_joining_date` date DEFAULT NULL,
 `office_id` bigint(20) NOT NULL,
 `transfer_to_office_id` bigint(20) DEFAULT NULL,
 `staff_id` bigint(20) DEFAULT NULL,
 `firstname` varchar(50) DEFAULT NULL,
 `middlename` varchar(50) DEFAULT NULL,
 `lastname` varchar(50) DEFAULT NULL,
 `fullname` varchar(100) DEFAULT NULL,
 `display_name` varchar(100) NOT NULL,
 `mobile_no` varchar(50) DEFAULT NULL,
 `gender_cv_id` int(11) DEFAULT NULL,
 `date_of_birth` date DEFAULT NULL,
 `image_id` bigint(20) DEFAULT NULL,
 `closure_reason_cv_id` int(11) DEFAULT NULL,
 `closedon_date` date DEFAULT NULL,
 `submittedon_date` date DEFAULT NULL,
 `submittedon_userid` bigint(20) DEFAULT NULL,
 `activatedon_userid` bigint(20) DEFAULT NULL,
 `closedon_userid` bigint(20) DEFAULT NULL,
 `default_savings_product` bigint(20) DEFAULT NULL,
 `default_savings_account` bigint(20) DEFAULT NULL,
 `mifos_client_id` bigint(20) NOT NULL, 
 `service_account` bigint(20) NOT NULL, 
 PRIMARY KEY (`id`),
 UNIQUE KEY `pgs_client_account_no_UNIQUE` (`pgs_client_account_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;