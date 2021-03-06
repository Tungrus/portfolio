DROP DATABASE IF EXISTS airline;
CREATE DATABASE airline;
USE airline;


CREATE TABLE users(
    id INT(11) NOT NULL AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL UNIQUE COLLATE UTF8_GENERAL_CI,
    password VARCHAR(50) NOT NULL,
    userType VARCHAR(50) NOT NULL,
    KEY (userType),
    PRIMARY KEY (id)
)
CHARACTER SET utf8;

CREATE TABLE users_sessions(
    userId INT(11) UNIQUE NOT NULL,
    sessionString VARCHAR(50) UNIQUE NOT NULL,
    openSessionTime DATETIME NOT NULL,
    KEY (openSessionTime),
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
CHARACTER SET utf8;

CREATE TABLE name_params (
    userId INT(11) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    patronymic VARCHAR(50),
    KEY (firstName),
    KEY (lastName),
    KEY (patronymic),
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE admins(
    userId INT(11) NOT NULL,
    position VARCHAR(50) NOT NULL,
    KEY (position),
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE clients (
    userId INT(11) NOT NULL,
    phoneNumber VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    KEY (phoneNumber),
    KEY (email),
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE
)
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE planes (
    planeId INT(11) AUTO_INCREMENT NOT NULL,
    planeName VARCHAR(50) NOT NULL,

    economyRows INT(11) NOT NULL,
    placesInEconomyRow INT(11) NOT NULL,

    bisnessRows INT(11) NOT NULL,
    placesInBisnessRow INT(11) NOT NULL,

    KEY (planeName),
    KEY (economyRows),
    KEY (placesInEconomyRow),
    KEY (bisnessRows),
    KEY (placesInBisnessRow),
    PRIMARY KEY (planeId)
)
CHARACTER SET utf8;


CREATE TABLE flights (
    planeId INT(11) NOT NULL,
    flightId INT(11) NOT NULL AUTO_INCREMENT UNIQUE,
    scheduleId INT(11),
    approved BOOL NOT NULL,
    flightName VARCHAR(50) NOT NULL,
    fromTown VARCHAR(50) NOT NULL,
    toTown VARCHAR(50) NOT NULL,
    priceBusiness INT(11) NOT NULL,
    priceEconomy INT(11) NOT NULL,
    start TIME NOT NULL,
    duration TIME NOT NULL,

    KEY (approved),
    KEY (flightName),
    KEY (fromTown),
    KEY (toTown),
    KEY (priceBusiness),
    KEY (priceEconomy),
    KEY (start),
    KEY (duration),
    PRIMARY KEY (flightId),
    FOREIGN KEY (planeId) REFERENCES planes(planeId) ON DELETE CASCADE
)
CHARACTER SET utf8;

CREATE TABLE flightSchedule (
    flightId INT(11) NOT NULL,
    period VARCHAR(50) NOT NULL,
    fromDate DATE NOT NULL,
    toDate DATE NOT NULL,
    KEY (fromDate),
    KEY (toDate),
    KEY (period),
    FOREIGN KEY (flightId) REFERENCES flights(flightId) ON DELETE CASCADE
)
CHARACTER SET utf8;

CREATE TABLE flightDates (
    flightId INT(11) NOT NULL,
    flightDateId INT(11) NOT NULL AUTO_INCREMENT,
    flightDate DATE NOT NULL,
    KEY (flightDate),
    PRIMARY KEY (flightDateId),
    FOREIGN KEY (flightId) REFERENCES flights(flightId) ON DELETE CASCADE
)
CHARACTER SET utf8;

CREATE TABLE countries (
	id INT(11) NOT NULL,
    iso3166 CHAR(2) NOT NULL,
    name VARCHAR(80) NOT NULL,
    iso_3 VARCHAR(3) NOT NULL ,
    tld_extension varchar(3),
    numeric_code smallint(6),
    alpha2_year smallint(6) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE orders (
    orderId INT(11) NOT NULL AUTO_INCREMENT,
    flightDateId INT(11) NOT NULL,
    flightId INT(11) NOT NULL,
    userId INT(11) NOT NULL,
    totalPrice INT(11) NOT NULL,
    KEY (flightDateId),
    PRIMARY KEY (orderId),
    FOREIGN KEY (flightId) REFERENCES flights(flightId) ON DELETE CASCADE,
    FOREIGN KEY (flightDateId) REFERENCES flightDates(flightDateId) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE

)
CHARACTER SET utf8;

CREATE TABLE passengers (
    orderId INT(11) NOT NULL,
    ticketId INT(11) NOT NULL AUTO_INCREMENT,
    nationalityId INT(11) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    passportNumber VARCHAR(50) NOT NULL,
    typeOfPlace VARCHAR(50) NOT NULL,
    price INT(11) NOT NULL,
    PRIMARY KEY (ticketId),
    FOREIGN KEY (orderId) REFERENCES orders(orderId) ON DELETE CASCADE,
    FOREIGN KEY (nationalityId) REFERENCES countries(id) ON DELETE CASCADE
)
CHARACTER SET utf8;

CREATE TABLE freePlaces (
	businessPlaces INT(11) NOT NULL,
    economyPlaces INT(11) NOT NULL,
    departureId INT(11) NOT NULL,
	FOREIGN KEY (departureId) REFERENCES flightDates(flightDateId) ON DELETE CASCADE
);

CREATE TABLE places (
	ticketId INT(11) NOT NULL,
    placeNumber INT(11) NOT NULL,
    rowNumber INT(11) NOT NULL,
    stringRepresentation VARCHAR(50) NOT NULL UNIQUE,
    FOREIGN KEY (ticketId) REFERENCES passengers(ticketId) ON DELETE CASCADE
);

INSERT INTO planes(planeName, economyRows, placesInEconomyRow, bisnessRows, placesInBisnessRow) VALUES ("plane1", 10, 10, 10, 10);
INSERT INTO planes(planeName, economyRows, placesInEconomyRow, bisnessRows, placesInBisnessRow) VALUES ("plane2", 20, 20, 20, 20);
INSERT INTO planes(planeName, economyRows, placesInEconomyRow, bisnessRows, placesInBisnessRow) VALUES ("plane3", 30, 30, 30, 30);
INSERT INTO planes(planeName, economyRows, placesInEconomyRow, bisnessRows, placesInBisnessRow) VALUES ("plane4", 40, 40, 40, 40);
INSERT INTO planes(planeName, economyRows, placesInEconomyRow, bisnessRows, placesInBisnessRow) VALUES ("plane5", 50, 50, 50, 50);

INSERT INTO countries ( id, name, iso3166, iso_3, `tld_extension`, `numeric_code`, `alpha2_year`) VALUES
(1, 'Andorra', 'AD', 'AND', '.ad', 20, 1974),
(2, 'Afghanistan', 'AF', 'AFG', '.af', 4, 1974),
(3, 'Åland Islands', 'AX', 'ALA', '.ax', 248, 2004),
(4, 'Albania', 'AL', 'ALB', '.al', 8, 1974),
(5, 'Algeria', 'DZ', 'DZA', '.dz', 12, 1974),
(6, 'American Samoa', 'AS', 'ASM', '.as', 16, 1974),
(7, 'Angola', 'AO', 'AGO', '.ao', 24, 1974),
(8, 'Anguilla', 'AI', 'AIA', '.ai', 660, 1983),
(9, 'Antarctica', 'AQ', 'ATA', '.aq', 10, 1974),
(10, 'Antigua and Barbuda', 'AG', 'ATG', '.ag', 28, 1974),
(11, 'Argentina', 'AR', 'ARG', '.ar', 32, 1974),
(12, 'Armenia', 'AM', 'ARM', '.am', 51, 1992),
(13, 'Aruba', 'AW', 'ABW', '.aw', 533, 1986),
(14, 'Australia', 'AU', 'AUS', '.au', 36, 1974),
(15, 'Austria', 'AT', 'AUT', '.at', 40, 1974),
(16, 'Azerbaijan', 'AZ', 'AZE', '.az', 31, 1992),
(17, 'Bahamas', 'BS', 'BHS', '.bs', 44, 1974),
(18, 'Bahrain', 'BH', 'BHR', '.bh', 48, 1974),
(19, 'Bangladesh', 'BD', 'BGD', '.bd', 50, 1974),
(20, 'Barbados', 'BB', 'BRB', '.bb', 52, 1974),
(21, 'Belarus', 'BY', 'BLR', '.by', 112, 1974),
(22, 'Belgium', 'BE', 'BEL', '.be', 56, 1974),
(23, 'Belize', 'BZ', 'BLZ', '.bz', 84, 1974),
(24, 'Benin', 'BJ', 'BEN', '.bj', 204, 1977),
(25, 'Bermuda', 'BM', 'BMU', '.bm', 60, 1974),
(26, 'Bhutan', 'BT', 'BTN', '.bt', 64, 1974),
(27, 'Bolivia (Plurinational State of)', 'BO', 'BOL', '.bo', 68, 1974),
(28, 'Bonaire, Sint Eustatius and Saba', 'BQ', 'BES', '.bq', 535, 2010),
(29, 'Bosnia and Herzegovina', 'BA', 'BIH', '.ba', 70, 1992),
(30, 'Botswana', 'BW', 'BWA', '.bw', 72, 1974),
(31, 'Bouvet Island', 'BV', 'BVT', '.bv', 74, 1974),
(32, 'Brazil', 'BR', 'BRA', '.br', 76, 1974),
(33, 'British Indian Ocean Territory', 'IO', 'IOT', '.io', 86, 1974),
(34, 'Brunei Darussalam', 'BN', 'BRN', '.bn', 96, 1974),
(35, 'Bulgaria', 'BG', 'BGR', '.bg', 100, 1974),
(36, 'Burkina Faso', 'BF', 'BFA', '.bf', 854, 1984),
(37, 'Burundi', 'BI', 'BDI', '.bi', 108, 1974),
(38, 'Cabo Verde', 'CV', 'CPV', '.cv', 132, 1974),
(39, 'Cambodia', 'KH', 'KHM', '.kh', 116, 1974),
(40, 'Cameroon', 'CM', 'CMR', '.cm', 120, 1974),
(41, 'Canada', 'CA', 'CAN', '.ca', 124, 1974),
(42, 'Cayman Islands', 'KY', 'CYM', '.ky', 136, 1974),
(43, 'Central African Republic', 'CF', 'CAF', '.cf', 140, 1974),
(44, 'Chad', 'TD', 'TCD', '.td', 148, 1974),
(45, 'Chile', 'CL', 'CHL', '.cl', 152, 1974),
(46, 'China', 'CN', 'CHN', '.cn', 156, 1974),
(47, 'Christmas Island', 'CX', 'CXR', '.cx', 162, 1974),
(48, 'Cocos (Keeling) Islands', 'CC', 'CCK', '.cc', 166, 1974),
(49, 'Colombia', 'CO', 'COL', '.co', 170, 1974),
(50, 'Comoros', 'KM', 'COM', '.km', 174, 1974),
(51, 'Congo', 'CG', 'COG', '.cg', 178, 1974),
(52, 'Congo (Democratic Republic of the)', 'CD', 'COD', '.cd', 180, 1997),
(53, 'Cook Islands', 'CK', 'COK', '.ck', 184, 1974),
(54, 'Costa Rica', 'CR', 'CRI', '.cr', 188, 1974),
(55, 'Côte d''Ivoire', 'CI', 'CIV', '.ci', 384, 1974),
(56, 'Croatia', 'HR', 'HRV', '.hr', 191, 1992),
(57, 'Cuba', 'CU', 'CUB', '.cu', 192, 1974),
(58, 'Curaçao', 'CW', 'CUW', '.cw', 531, 2010),
(59, 'Cyprus', 'CY', 'CYP', '.cy', 196, 1974),
(60, 'Czech Republic', 'CZ', 'CZE', '.cz', 203, 1993),
(61, 'Denmark', 'DK', 'DNK', '.dk', 208, 1974),
(62, 'Djibouti', 'DJ', 'DJI', '.dj', 262, 1977),
(63, 'Dominica', 'DM', 'DMA', '.dm', 212, 1974),
(64, 'Dominican Republic', 'DO', 'DOM', '.do', 214, 1974),
(65, 'Ecuador', 'EC', 'ECU', '.ec', 218, 1974),
(66, 'Egypt', 'EG', 'EGY', '.eg', 818, 1974),
(67, 'El Salvador', 'SV', 'SLV', '.sv', 222, 1974),
(68, 'Equatorial Guinea', 'GQ', 'GNQ', '.gq', 226, 1974),
(69, 'Eritrea', 'ER', 'ERI', '.er', 232, 1993),
(70, 'Estonia', 'EE', 'EST', '.ee', 233, 1992),
(71, 'Ethiopia', 'ET', 'ETH', '.et', 231, 1974),
(72, 'Falkland Islands (Malvinas)', 'FK', 'FLK', '.fk', 238, 1974),
(73, 'Faroe Islands', 'FO', 'FRO', '.fo', 234, 1974),
(74, 'Fiji', 'FJ', 'FJI', '.fj', 242, 1974),
(75, 'Finland', 'FI', 'FIN', '.fi', 246, 1974),
(76, 'France', 'FR', 'FRA', '.fr', 250, 1974),
(77, 'French Guiana', 'GF', 'GUF', '.gf', 254, 1974),
(78, 'French Polynesia', 'PF', 'PYF', '.pf', 258, 1974),
(79, 'French Southern Territories', 'TF', 'ATF', '.tf', 260, 1979),
(80, 'Gabon', 'GA', 'GAB', '.ga', 266, 1974),
(81, 'Gambia', 'GM', 'GMB', '.gm', 270, 1974),
(82, 'Georgia', 'GE', 'GEO', '.ge', 268, 1992),
(83, 'Germany', 'DE', 'DEU', '.de', 276, 1974),
(84, 'Ghana', 'GH', 'GHA', '.gh', 288, 1974),
(85, 'Gibraltar', 'GI', 'GIB', '.gi', 292, 1974),
(86, 'Greece', 'GR', 'GRC', '.gr', 300, 1974),
(87, 'Greenland', 'GL', 'GRL', '.gl', 304, 1974),
(88, 'Grenada', 'GD', 'GRD', '.gd', 308, 1974),
(89, 'Guadeloupe', 'GP', 'GLP', '.gp', 312, 1974),
(90, 'Guam', 'GU', 'GUM', '.gu', 316, 1974),
(91, 'Guatemala', 'GT', 'GTM', '.gt', 320, 1974),
(92, 'Guernsey', 'GG', 'GGY', '.gg', 831, 2006),
(93, 'Guinea', 'GN', 'GIN', '.gn', 324, 1974),
(94, 'Guinea-Bissau', 'GW', 'GNB', '.gw', 624, 1974),
(95, 'Guyana', 'GY', 'GUY', '.gy', 328, 1974),
(96, 'Haiti', 'HT', 'HTI', '.ht', 332, 1974),
(97, 'Heard Island and McDonald Islands', 'HM', 'HMD', '.hm', 334, 1974),
(98, 'Holy See', 'VA', 'VAT', '.va', 336, 1974),
(99, 'Honduras', 'HN', 'HND', '.hn', 340, 1974),
(100, 'Hong Kong', 'HK', 'HKG', '.hk', 344, 1974),
(101, 'Hungary', 'HU', 'HUN', '.hu', 348, 1974),
(102, 'Iceland', 'IS', 'ISL', '.is', 352, 1974),
(103, 'India', 'IN', 'IND', '.in', 356, 1974),
(104, 'Indonesia', 'ID', 'IDN', '.id', 360, 1974),
(105, 'Iran (Islamic Republic of)', 'IR', 'IRN', '.ir', 364, 1974),
(106, 'Iraq', 'IQ', 'IRQ', '.iq', 368, 1974),
(107, 'Ireland', 'IE', 'IRL', '.ie', 372, 1974),
(108, 'Isle of Man', 'IM', 'IMN', '.im', 833, 2006),
(109, 'Israel', 'IL', 'ISR', '.il', 376, 1974),
(110, 'Italy', 'IT', 'ITA', '.it', 380, 1974),
(111, 'Jamaica', 'JM', 'JAM', '.jm', 388, 1974),
(112, 'Japan', 'JP', 'JPN', '.jp', 392, 1974),
(113, 'Jersey', 'JE', 'JEY', '.je', 832, 2006),
(114, 'Jordan', 'JO', 'JOR', '.jo', 400, 1974),
(115, 'Kazakhstan', 'KZ', 'KAZ', '.kz', 398, 1992),
(116, 'Kenya', 'KE', 'KEN', '.ke', 404, 1974),
(117, 'Kiribati', 'KI', 'KIR', '.ki', 296, 1979),
(118, 'Korea (Democratic People''s Republic of)', 'KP', 'PRK', '.kp', 408, 1974),
(119, 'Korea (Republic of)', 'KR', 'KOR', '.kr', 410, 1974),
(120, 'Kuwait', 'KW', 'KWT', '.kw', 414, 1974),
(121, 'Kyrgyzstan', 'KG', 'KGZ', '.kg', 417, 1992),
(122, 'Lao People''s Democratic Republic', 'LA', 'LAO', '.la', 418, 1974),
(123, 'Latvia', 'LV', 'LVA', '.lv', 428, 1992),
(124, 'Lebanon', 'LB', 'LBN', '.lb', 422, 1974),
(125, 'Lesotho', 'LS', 'LSO', '.ls', 426, 1974),
(126, 'Liberia', 'LR', 'LBR', '.lr', 430, 1974),
(127, 'Libya', 'LY', 'LBY', '.ly', 434, 1974),
(128, 'Liechtenstein', 'LI', 'LIE', '.li', 438, 1974),
(129, 'Lithuania', 'LT', 'LTU', '.lt', 440, 1992),
(130, 'Luxembourg', 'LU', 'LUX', '.lu', 442, 1974),
(131, 'Macao', 'MO', 'MAC', '.mo', 446, 1974),
(132, 'Macedonia (the former Yugoslav Republic of)', 'MK', 'MKD', '.mk', 807, 1993),
(133, 'Madagascar', 'MG', 'MDG', '.mg', 450, 1974),
(134, 'Malawi', 'MW', 'MWI', '.mw', 454, 1974),
(135, 'Malaysia', 'MY', 'MYS', '.my', 458, 1974),
(136, 'Maldives', 'MV', 'MDV', '.mv', 462, 1974),
(137, 'Mali', 'ML', 'MLI', '.ml', 466, 1974),
(138, 'Malta', 'MT', 'MLT', '.mt', 470, 1974),
(139, 'Marshall Islands', 'MH', 'MHL', '.mh', 584, 1986),
(140, 'Martinique', 'MQ', 'MTQ', '.mq', 474, 1974),
(141, 'Mauritania', 'MR', 'MRT', '.mr', 478, 1974),
(142, 'Mauritius', 'MU', 'MUS', '.mu', 480, 1974),
(143, 'Mayotte', 'YT', 'MYT', '.yt', 175, 1993),
(144, 'Mexico', 'MX', 'MEX', '.mx', 484, 1974),
(145, 'Micronesia (Federated States of)', 'FM', 'FSM', '.fm', 583, 1986),
(146, 'Moldova (Republic of)', 'MD', 'MDA', '.md', 498, 1992),
(147, 'Monaco', 'MC', 'MCO', '.mc', 492, 1974),
(148, 'Mongolia', 'MN', 'MNG', '.mn', 496, 1974),
(149, 'Montenegro', 'ME', 'MNE', '.me', 499, 2006),
(150, 'Montserrat', 'MS', 'MSR', '.ms', 500, 1974),
(151, 'Morocco', 'MA', 'MAR', '.ma', 504, 1974),
(152, 'Mozambique', 'MZ', 'MOZ', '.mz', 508, 1974),
(153, 'Myanmar', 'MM', 'MMR', '.mm', 104, 1989),
(154, 'Namibia', 'NA', 'NAM', '.na', 516, 1974),
(155, 'Nauru', 'NR', 'NRU', '.nr', 520, 1974),
(156, 'Nepal', 'NP', 'NPL', '.np', 524, 1974),
(157, 'Netherlands', 'NL', 'NLD', '.nl', 528, 1974),
(158, 'New Caledonia', 'NC', 'NCL', '.nc', 540, 1974),
(159, 'New Zealand', 'NZ', 'NZL', '.nz', 554, 1974),
(160, 'Nicaragua', 'NI', 'NIC', '.ni', 558, 1974),
(161, 'Niger', 'NE', 'NER', '.ne', 562, 1974),
(162, 'Nigeria', 'NG', 'NGA', '.ng', 566, 1974),
(163, 'Niue', 'NU', 'NIU', '.nu', 570, 1974),
(164, 'Norfolk Island', 'NF', 'NFK', '.nf', 574, 1974),
(165, 'Northern Mariana Islands', 'MP', 'MNP', '.mp', 580, 1986),
(166, 'Norway', 'NO', 'NOR', '.no', 578, 1974),
(167, 'Oman', 'OM', 'OMN', '.om', 512, 1974),
(168, 'Pakistan', 'PK', 'PAK', '.pk', 586, 1974),
(169, 'Palau', 'PW', 'PLW', '.pw', 585, 1986),
(170, 'Palestine, State of', 'PS', 'PSE', '.ps', 275, 1999),
(171, 'Panama', 'PA', 'PAN', '.pa', 591, 1974),
(172, 'Papua New Guinea', 'PG', 'PNG', '.pg', 598, 1974),
(173, 'Paraguay', 'PY', 'PRY', '.py', 600, 1974),
(174, 'Peru', 'PE', 'PER', '.pe', 604, 1974),
(175, 'Philippines', 'PH', 'PHL', '.ph', 608, 1974),
(176, 'Pitcairn', 'PN', 'PCN', '.pn', 612, 1974),
(177, 'Poland', 'PL', 'POL', '.pl', 616, 1974),
(178, 'Portugal', 'PT', 'PRT', '.pt', 620, 1974),
(179, 'Puerto Rico', 'PR', 'PRI', '.pr', 630, 1974),
(180, 'Qatar', 'QA', 'QAT', '.qa', 634, 1974),
(181, 'Réunion', 'RE', 'REU', '.re', 638, 1974),
(182, 'Romania', 'RO', 'ROU', '.ro', 642, 1974),
(183, 'Russian Federation', 'RU', 'RUS', '.ru', 643, 1992),
(184, 'Rwanda', 'RW', 'RWA', '.rw', 646, 1974),
(185, 'Saint Barthélemy', 'BL', 'BLM', '.bl', 652, 2007),
(186, 'Saint Helena, Ascension and Tristan da Cunha', 'SH', 'SHN', '.sh', 654, 1974),
(187, 'Saint Kitts and Nevis', 'KN', 'KNA', '.kn', 659, 1974),
(188, 'Saint Lucia', 'LC', 'LCA', '.lc', 662, 1974),
(189, 'Saint Martin (French part)', 'MF', 'MAF', '.mf', 663, 2007),
(190, 'Saint Pierre and Miquelon', 'PM', 'SPM', '.pm', 666, 1974),
(191, 'Saint Vincent and the Grenadines', 'VC', 'VCT', '.vc', 670, 1974),
(192, 'Samoa', 'WS', 'WSM', '.ws', 882, 1974),
(193, 'San Marino', 'SM', 'SMR', '.sm', 674, 1974),
(194, 'Sao Tome and Principe', 'ST', 'STP', '.st', 678, 1974),
(195, 'Saudi Arabia', 'SA', 'SAU', '.sa', 682, 1974),
(196, 'Senegal', 'SN', 'SEN', '.sn', 686, 1974),
(197, 'Serbia', 'RS', 'SRB', '.rs', 688, 2006),
(198, 'Seychelles', 'SC', 'SYC', '.sc', 690, 1974),
(199, 'Sierra Leone', 'SL', 'SLE', '.sl', 694, 1974),
(200, 'Singapore', 'SG', 'SGP', '.sg', 702, 1974),
(201, 'Sint Maarten (Dutch part)', 'SX', 'SXM', '.sx', 534, 2010),
(202, 'Slovakia', 'SK', 'SVK', '.sk', 703, 1993),
(203, 'Slovenia', 'SI', 'SVN', '.si', 705, 1992),
(204, 'Solomon Islands', 'SB', 'SLB', '.sb', 90, 1974),
(205, 'Somalia', 'SO', 'SOM', '.so', 706, 1974),
(206, 'South Africa', 'ZA', 'ZAF', '.za', 710, 1974),
(207, 'South Georgia and the South Sandwich Islands', 'GS', 'SGS', '.gs', 239, 1993),
(208, 'South Sudan', 'SS', 'SSD', '.ss', 728, 2011),
(209, 'Spain', 'ES', 'ESP', '.es', 724, 1974),
(210, 'Sri Lanka', 'LK', 'LKA', '.lk', 144, 1974),
(211, 'Sudan', 'SD', 'SDN', '.sd', 729, 1974),
(212, 'Suriname', 'SR', 'SUR', '.sr', 740, 1974),
(213, 'Svalbard and Jan Mayen', 'SJ', 'SJM', '.sj', 744, 1974),
(214, 'Swaziland', 'SZ', 'SWZ', '.sz', 748, 1974),
(215, 'Sweden', 'SE', 'SWE', '.se', 752, 1974),
(216, 'Switzerland', 'CH', 'CHE', '.ch', 756, 1974),
(217, 'Syrian Arab Republic', 'SY', 'SYR', '.sy', 760, 1974),
(218, 'Taiwan, Province of China', 'TW', 'TWN', '.tw', 158, 1974),
(219, 'Tajikistan', 'TJ', 'TJK', '.tj', 762, 1992),
(220, 'Tanzania, United Republic of', 'TZ', 'TZA', '.tz', 834, 1974),
(221, 'Thailand', 'TH', 'THA', '.th', 764, 1974),
(222, 'Timor-Leste', 'TL', 'TLS', '.tl', 626, 2002),
(223, 'Togo', 'TG', 'TGO', '.tg', 768, 1974),
(224, 'Tokelau', 'TK', 'TKL', '.tk', 772, 1974),
(225, 'Tonga', 'TO', 'TON', '.to', 776, 1974),
(226, 'Trinidad and Tobago', 'TT', 'TTO', '.tt', 780, 1974),
(227, 'Tunisia', 'TN', 'TUN', '.tn', 788, 1974),
(228, 'Turkey', 'TR', 'TUR', '.tr', 792, 1974),
(229, 'Turkmenistan', 'TM', 'TKM', '.tm', 795, 1992),
(230, 'Turks and Caicos Islands', 'TC', 'TCA', '.tc', 796, 1974),
(231, 'Tuvalu', 'TV', 'TUV', '.tv', 798, 1979),
(232, 'Uganda', 'UG', 'UGA', '.ug', 800, 1974),
(233, 'Ukraine', 'UA', 'UKR', '.ua', 804, 1974),
(234, 'United Arab Emirates', 'AE', 'ARE', '.ae', 784, 1974),
(235, 'United Kingdom of Great Britain and Northern Ireland', 'GB', 'GBR', '.uk', 826, 1974),
(236, 'United States Minor Outlying Islands', 'UM', 'UMI', '.um', 581, 1986),
(237, 'United States of America', 'US', 'USA', '.us', 840, 1974),
(238, 'Uruguay', 'UY', 'URY', '.uy', 858, 1974),
(239, 'Uzbekistan', 'UZ', 'UZB', '.uz', 860, 1992),
(240, 'Vanuatu', 'VU', 'VUT', '.vu', 548, 1980),
(241, 'Venezuela (Bolivarian Republic of)', 'VE', 'VEN', '.ve', 862, 1974),
(242, 'Viet Nam', 'VN', 'VNM', '.vn', 704, 1974),
(243, 'Virgin Islands (British)', 'VG', 'VGB', '.vg', 92, 1974),
(244, 'Virgin Islands (U.S.)', 'VI', 'VIR', '.vi', 850, 1974),
(245, 'Wallis and Futuna', 'WF', 'WLF', '.wf', 876, 1974),
(246, 'Western Sahara', 'EH', 'ESH', '.eh', 732, 1974),
(247, 'Yemen', 'YE', 'YEM', '.ye', 887, 1974),
(248, 'Zambia', 'ZM', 'ZMB', '.zm', 894, 1974),
(249, 'Zimbabwe', 'ZW', 'ZWE', '.zw', 716, 1980);