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
package com.gsm.platfra.api.services.platfra.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;


/**
 *
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/attendance/application-inquiry")
public class PlatfraController {

//    private final PlatfraService service;


//    @GetMapping()
//    public List<AttendanceApplicationInquiryDto> get(@RequestParam(required = false) String dclzBplcCd,
//                                                     @RequestParam(required = false) String empNo,
//                                                     @RequestParam(required = true) @DateTimeFormat(pattern = DateFormatPattern.yyyy_MM_dd) LocalDate sttDate,
//                                                     @RequestParam(required = true) @DateTimeFormat(pattern = DateFormatPattern.yyyy_MM_dd) LocalDate endDate,
//                                                     @RequestParam(required = false) List<String> dclzCdList,
//                                                     @RequestParam(required = false) String orgCd,
//                                                     @RequestParam(defaultValue = "false") Boolean includeSubOrg,
//                                                     @RequestParam(required = false) String innerOrgCd,
//                                                     @RequestParam(required = false) String dclzHnfTyCd,
//                                                     @RequestParam(required = false) String aprvStatCd,
//                                                     @RequestParam(required = false) String ofcrAppvlCd,
//                                                     @RequestParam(required = false) String bassWksyTyCd,
//                                                     @RequestParam(required = false) String wrkSchdulTyCd,
//                                                     @RequestParam(required = false) String wrksftCd) {
//        return service.get(UserContextUtil.getUserContext(),
//                dclzBplcCd,
//                empNo,
//                sttDate,
//                endDate,
//                dclzCdList,
//                orgCd,
//                includeSubOrg,
//                innerOrgCd,
//                dclzHnfTyCd,
//                aprvStatCd,
//                ofcrAppvlCd,
//                bassWksyTyCd,
//                wrkSchdulTyCd,
//                wrksftCd);
//    }


}

