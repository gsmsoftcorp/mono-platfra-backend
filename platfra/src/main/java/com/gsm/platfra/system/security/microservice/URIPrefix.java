/*
 * Copyright (C) Hanwha Systems Ltd., 2021. All rights reserved.
 *
 * This software is covered by the license agreement between
 * the end user and Hanwha Systems Ltd., and may be
 * used and copied only in accordance with the terms of the
 * said agreement.
 *
 * Hanwha Systems Ltd., assumes no responsibility or
 * liability for any errors or inaccuracies in this software,
 * or any consequential, incidental or indirect damage arising
 * out of the use of the software.
 */
package com.gsm.platfra.system.security.microservice;

/**
 * Authentication URI Constant
 * Authentication Service의 URI Prefix를 관리한다.
 */
public class URIPrefix {

	public static final String AUTH = "/auth";
	
	public static final String AUTHENTICATION = "/token";
	public static final String REFRESH        = "/refresh";
	public static final String LOGOUT         = "/logout";
	
	public static final String AUTH_AUTHENTICATION = AUTH + AUTHENTICATION;
	public static final String AUTH_REFRESH        = AUTH + REFRESH;
	public static final String AUTH_LOGOUT         = AUTH + LOGOUT;

}
