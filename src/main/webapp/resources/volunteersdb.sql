-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 13, 2013 at 02:41 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `volunteersdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `category_code` varchar(3) NOT NULL,
  `category_description` varchar(50) NOT NULL,
  PRIMARY KEY (`category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`category_code`, `category_description`) VALUES
('SEL', 'SELECT A CATEGORY'),
('WEB', 'Website Page');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `image` blob NOT NULL,
  `uploaded_image` blob NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE IF NOT EXISTS `projects` (
  `project_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `project_title` varchar(300) NOT NULL,
  `project_detail` varchar(500) NOT NULL,
  `posted_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `posted_by` int(10) unsigned NOT NULL,
  `category_code` varchar(3) DEFAULT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`project_id`),
  KEY `posted_by` (`posted_by`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`project_id`, `project_title`, `project_detail`, `posted_date`, `posted_by`, `category_code`, `status`) VALUES
(15, 'Company WebSite', 'Need a developer who can build me a simple website with HTML and CSS', '2013-10-12 15:26:33', 1, 'SEL', ''),
(16, 'Mobile Application', 'Need a HTML5 Mobile Web Application written for Android Devices. For More information please contact 0428575923!', '2013-10-12 15:41:05', 1, 'SEL', ''),
(17, 'Product LOGO Modification', 'Need a graphic designer to design a cool Logo for printing and web pages. Please contact 453432533 ASAP', '2013-10-12 15:41:54', 3, 'SEL', ''),
(18, 'Application Tester', ' An Application Tester is needed to test our product sales web site. Please contact 52345234 if you are interested!', '2013-10-12 15:42:47', 3, 'SEL', ''),
(19, 'Modifications to Java Framework', ' Need consultation advise from an experienced architect on how to test our existing software system suit. Please contact Robert on 6023452343 ASAP', '2013-10-12 15:43:47', 7, 'SEL', ''),
(20, 'Desktop Application', ' Export our excel documents to a Desktop based quering application', '2013-10-13 00:37:30', 1, 'SEL', ''),
(21, 'Document Management System', ' Need a developer to design and implement a document management system for our company intranet', '2013-10-13 00:38:16', 1, 'SEL', ''),
(22, 'Automated Scheduled Jobs', ' Need a developer to automate our data entry based on hard copy documents received', '2013-10-13 00:39:22', 10, 'SEL', ''),
(23, 'Changes to our company CSS', ' Need Web Designer to redisign our web site', '2013-10-13 00:39:46', 21, 'SEL', ''),
(24, 'Application Testing', 'Need a tester willing to document and test our intranet applications', '2013-10-13 00:40:13', 48, 'SEL', ''),
(25, 'Web Database', 'Need a web application for an existing database', '2013-10-13 00:40:38', 1, 'SEL', '');

-- --------------------------------------------------------

--
-- Table structure for table `project_volunteers`
--

CREATE TABLE IF NOT EXISTS `project_volunteers` (
  `project_id` int(10) unsigned NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `note` varchar(2000) DEFAULT NULL,
  `proj_vol_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_of_note` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `selected` tinyint(1) NOT NULL,
  PRIMARY KEY (`proj_vol_id`),
  KEY `user_id` (`user_id`),
  KEY `project_id` (`project_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `project_volunteers`
--

INSERT INTO `project_volunteers` (`project_id`, `user_id`, `note`, `proj_vol_id`, `date_of_note`, `selected`) VALUES
(16, 1, 'sfssdfsd', 16, '2012-10-26 13:58:33', 0),
(19, 7, 'sdfsd', 17, '2012-10-26 23:23:35', 0),
(17, 8, 'Type message here', 18, '2012-10-27 08:06:31', 0),
(18, 9, 'sfsfsfsfsdf', 19, '2012-10-27 08:56:22', 0),
(17, 1, 'afsfsfsd', 20, '2012-10-27 09:03:17', 0),
(17, 1, 'asgsdfsdfd', 21, '2012-10-27 09:03:25', 0),
(17, 1, 'asgsdfsdfd', 22, '2012-10-27 09:03:26', 0),
(17, 1, 'asgsdfsdfd', 23, '2012-10-27 09:03:27', 0),
(17, 1, 'asgsdfsdfd', 24, '2012-10-27 09:03:27', 0),
(17, 1, 'asgsdfsdfd', 25, '2012-10-27 09:03:28', 0),
(17, 1, 'asgsdfsdfd', 26, '2012-10-27 09:03:28', 0),
(18, 23, 'dfd', 27, '2012-10-27 13:28:18', 0),
(21, 1, 'Type message here', 28, '2012-10-28 02:42:11', 0),
(18, 47, ' asdfs sfdsdf asfsf sfds sfds sfdfsd sdfd', 29, '2012-10-28 11:23:07', 0),
(21, 1, 'asffssfsfsddfdsfdf', 30, '2012-10-29 10:07:58', 0),
(17, 1, 'Type message here', 31, '2012-10-30 05:03:37', 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  `user_role_id` int(10) unsigned NOT NULL DEFAULT '1',
  `displayname` varchar(200) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`, `user_role_id`, `displayname`) VALUES
(1, 'byorn', 'thejacket', 1, 1, ''),
(3, 'asanka', 'desilva', 1, 1, ''),
(7, 'andrew', 'willbrick', 1, 1, ''),
(8, 'AZAD', 'AZAD', 0, 1, ''),
(9, 'john', 'makin', 0, 1, ''),
(10, '', '', 0, 1, ''),
(21, 'aaaaaaaaaaa', 'aaaaaaaaaaa', 0, 1, ''),
(23, 'sdf', 'df', 0, 1, ''),
(47, 'johan', 'sdfsdf', 0, 1, ''),
(48, 'dssssssssssssss', 'sddddddddddddddd', 0, 1, ''),
(49, 'dfdsfdsfsffff', 'sdfsdfds', 0, 1, ''),
(50, 'jack', 'dawson', 0, 1, ''),
(51, 's@g.com', 's', 0, 1, '');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(10) unsigned NOT NULL,
  `authority` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `authority`) VALUES
(1, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `user_skills`
--

CREATE TABLE IF NOT EXISTS `user_skills` (
  `user_id` int(10) NOT NULL,
  `skills` varchar(300) NOT NULL,
  `volunteer_reason` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `project_volunteers`
--
ALTER TABLE `project_volunteers`
  ADD CONSTRAINT `project_volunteers_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `projects` (`project_id`),
  ADD CONSTRAINT `project_volunteers_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
